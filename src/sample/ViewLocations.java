package sample;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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

		String SQL = "SELECT * FROM Location;";

		String SQL2 = "SELECT Employee.FirstName, Employee.LastName, HoursWorked.StartTime, HoursWorked.EndTime, DayofWeek.Day "
				+ "FROM WeekSchedule " + "FULL OUTER JOIN Employee ON WeekSchedule.EmployeeID = Employee.EmployeeID "
				+ "FULL OUTER JOIN HoursWorked ON WeekSchedule.ShiftID = HoursWorked.ShiftID "
				+ "FULL OUTER JOIN DayofWeek ON WeekSchedule.DayID = DayofWeek.DayID; ";

		Stage window = new Stage();
		Label GeneralEmps = new Label("Emplyoee Reports");
		Label RandomInfo = new Label("Select one of the options to view in the left column");
		Button ButtGenEmp = new Button("General Emplyoee Information");
		Button ButtEmpSchedule = new Button("Employee Schedules");
		Button ButtEmpSalary = new Button("Employee Salaries");

		BorderPane layout = new BorderPane();
		VBox Left = new VBox();
		VBox CenterValue = new VBox();
		ScrollPane Scrolls = new ScrollPane();
		Scrolls.setContent(CenterValue);
		CenterValue.getChildren().addAll(RandomInfo);

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
		Left.getChildren().addAll(ButtGenEmp, ButtEmpSchedule, ButtEmpSalary);

		layout.setTop(GeneralEmps);
		layout.setLeft(Left);
		layout.setCenter(CenterValue);
		// Main Scene
		Scene scene = new Scene(layout, 700, 400);

		window.setScene(scene);
		window.show();

	}
}
