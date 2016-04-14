package sample;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ViewLocations {
	private static ObservableList<ObservableList> data;
	private static TableView tableview;

	public static void buildData(String InsertSql) {
		Connection c;
		data = FXCollections.observableArrayList();
		try {
			c = DBconnect.connect();
			// SQL FOR SELECTING ALL OF CUSTOMER
			String SQL = InsertSql;

			// ResultSet
			ResultSet rs = c.createStatement().executeQuery(SQL);

			/**********************************
			 * TABLE COLUMN ADDED DYNAMICALLY *
			 **********************************/
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				// We are using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j).toString());
							}
						});
				
				tableview.getColumns().addAll(col);
				System.out.println("Column [" + i + "] ");
			}

			/********************************
			 * Data added to ObservableList *
			 ********************************/
			while (rs.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					String notthere = "null";
					rs.getString(i);
					if (rs.wasNull()) {
						row.add(notthere);
					} else {
						row.add(rs.getString(i));
					}

				}
				System.out.println("Row [1] added " + row);
				data.add(row);

			}

			// FINALLY ADDED TO TableView
			tableview.setItems(data);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
	}

	public static void GeneralLocation() {

		String SQL = "SELECT Location.LocationID, Location.Address, Location.RentCost, Country.CountryName, "
				+ "State.StateName, City.CityName, BuildingType.TypeDesc "
				+ "FROM Location "
				+ "FULL OUTER JOIN Country ON Location.CountryID = Country.CountryID "
				+ "FULL OUTER JOIN State ON Location.StateID = State.StateID "
				+ "FULL OUTER JOIN City ON Location.CityCode = City.CityCode "
				+ "FULL OUTER JOIN BuildingType ON Location.TypeID = BuildingType.TypeID "
				+ "  WHERE Address <> '---' ;";

		String SQL2 = "SELECT Location.Address, Employee.FirstName, Employee.LastName, HoursWorked.StartTime, HoursWorked.EndTime, DayofWeekz.Day "
				+ "FROM WeekSchedule " 
				+ "FULL OUTER JOIN Employee ON WeekSchedule.EmployeeID = Employee.EmployeeID "
				+ "FULL OUTER JOIN HoursWorked ON WeekSchedule.ShiftID = HoursWorked.ShiftID "
				+ "FULL OUTER JOIN DayofWeekz ON WeekSchedule.DayID = DayofWeekz.DayID "
				+ "FULL OUTER JOIN Employee ON Employee.LocationID = Location.LocationID; ";

		Stage window = new Stage();
		Label GeneralLocs = new Label("Location Reports");
		Label RandomInfo = new Label("Select one of the options to view in the left column");
		
		Button ButtGenEmp = new Button("General Location \nInformation");
		ButtGenEmp.setMinSize(150, 50);
		ButtGenEmp.setMaxSize(100, 50);
		ButtGenEmp.setStyle("" 
				+ "-fx-font-size: 13px;"  
				+ "-fx-background-radius:100; "
				+ "-fx-background-color: #C06A45");
		Button ButtEmpSchedule = new Button("Location Employees");
		ButtEmpSchedule.setMinSize(150, 50);
		ButtEmpSchedule.setMaxSize(100, 50);
		ButtEmpSchedule.setStyle("" 
				+ "-fx-font-size: 11px;"  
				+ "-fx-background-radius:100; "
				+ "-fx-background-color: #B96F6F");
		Button ButtLocSales = new Button("Location \n"
				+ "Costs & Sales");
		ButtLocSales.setMinSize(150, 50);
		ButtLocSales.setMaxSize(100, 50);
		ButtLocSales.setStyle("" 
				+ "-fx-font-size: 13px;"  
				+ "-fx-background-radius:100; "
				+ "-fx-background-color: #C98A4B");
		

		BorderPane layout = new BorderPane();
		VBox Left = new VBox();
		VBox CenterValue = new VBox();
		ScrollPane Scrolls = new ScrollPane();
		Scrolls.setContent(CenterValue);
		CenterValue.getChildren().addAll(RandomInfo);

		window.setTitle("Update Locations");
		window.initModality(Modality.APPLICATION_MODAL);
		layout.setStyle("-fx-background-color: ffd773");
		GeneralLocs.setStyle("-fx-font-size: 40;");
		GeneralLocs.setPadding(new Insets(30, 30, 30, 30));
		
		ButtGenEmp.setOnAction(e -> {
			tableview = new TableView();
			buildData(SQL);
			CenterValue.getChildren().clear();
			CenterValue.getChildren().addAll(tableview);
		});
		ButtEmpSchedule.setOnAction(e -> {
			tableview = new TableView();
			buildData(SQL2);
			CenterValue.getChildren().clear();
			CenterValue.getChildren().addAll(tableview);
		});
		Left.getChildren().addAll(ButtGenEmp, ButtEmpSchedule, ButtLocSales);
		
		VBox Right = new VBox();
		Label Stuff = new Label("                 ");
		Right.minWidth(100);
		Right.getChildren().add(Stuff);
		layout.setRight(Right);
		layout.setTop(GeneralLocs);
		layout.setLeft(Left);
		layout.setCenter(CenterValue);
		// Main Scene
		Scene scene = new Scene(layout, 950, 600);

		window.setScene(scene);
		window.show();

	}
}
