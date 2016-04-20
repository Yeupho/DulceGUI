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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.effect.DropShadow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ViewEmployee {
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
					String notthere = "---";
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

	public static void GeneralEmplyoee() {
		//View General Employee Information
		String SQL = "SELECT Employee.FirstName, Employee.LastName, Role.RoleName, Location.Address, City.CityName " 
				+ "FROM Employee "
				+ "INNER JOIN Role ON Employee.RoleID = Role.RoleID "
				+ "INNER JOIN Location ON Employee.LocationID = Location.LocationID "
				+ "INNER JOIN City ON Location.CityCode = City.CityCode ";
		//View Employee Schedules
		String SQL2 = " SELECT Employee.EmployeeID, Employee.FirstName, Employee.LastName, HoursWorked.ShiftName,  "
				+ " DayofWeekz.DayName, DayofWeekz.DayID "
				+ " FROM WeekSchedule " 
				+ " INNER JOIN Employee ON WeekSchedule.EmployeeID = Employee.EmployeeID "
				+ " INNER JOIN HoursWorked ON WeekSchedule.ShiftID = HoursWorked.ShiftID "
				+ " INNER JOIN DayofWeekz ON WeekSchedule.DayID = DayofWeekz.DayID "
				+ " WHERE WeekSchedule.WeekSchedule IS NOT NULL "
				+ " ORDER BY DayofWeekz.DayID ASC ; ";
		//View Employee Salaries 
		String SQL3 ="SELECT Employee.FirstName, Employee.LastName, EmployeeSchedule.TotHrsWorked, HourRate.BasePay, Payroll.Bonus, "
				+ "PaymentType.TypeID, Payroll.Notes "
				+ "FROM Payroll "
				+ "FULL OUTER JOIN Employee ON Payroll.EmployeeID = Employee.EmployeeID "
				+ "FULL OUTER JOIN HourRate ON Payroll.BaseRateID = HourRate.RateID "
				+ "FULL OUTER JOIN PaymentType ON Payroll.PaymentType = PaymentType.TypeID "
				+ "FULL OUTER JOIN EmployeeSchedule ON Payroll.ScheduleID = EmployeeSchedule.ScheduleID ";
		//View Employee Bank Info
		String SQL4 ="SELECT Employee.EmployeeID, Employee.FirstName, Employee.LastName, BankInfo.Bank, "
				+ " BankInfo.AccountNumber, BankInfo.SavingsActNumber, BankInfo.RoutingNumber, HourRate.BasePay "
				+ " FROM BankInfo "
				+ " FULL OUTER JOIN Employee ON BankInfo.EmployeeID = Employee.EmployeeID "
				+ " INNER JOIN Payroll ON Payroll.EmployeeID = Employee.EmployeeID "
				+ " INNER JOIN HourRate ON Payroll.BaseRateID = HourRate.RateID ";
		//View 
		Stage window = new Stage();
		Label GeneralEmps = new Label("Employee Information");
		Label RandomInfo = new Label("<-Select one of the options to view in the left column");
		RandomInfo.setStyle("-fx-font-size: 20;");
		
		
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.3, 0.3, 0.3));
		Button ButtGenEmp = new Button("General Emplyoee\nInformation");
		ButtGenEmp.setEffect(dropShadow);
		ButtGenEmp.setMinSize(150, 50);
		ButtGenEmp.setMaxSize(100, 50);
		ButtGenEmp.setStyle("" 
				+ "-fx-font-size: 13px;"  
				+ "-fx-background-radius:100; "
				+ "-fx-background-color: #C06A45");
		Button ButtEmpSchedule = new Button("Employee Schedules");
		ButtEmpSchedule.setEffect(dropShadow);
		ButtEmpSchedule.setMinSize(150, 50);
		ButtEmpSchedule.setMaxSize(100, 50);
		ButtEmpSchedule.setStyle("" 
				+ "-fx-font-size: 13px;"  
				+ "-fx-background-radius:100; "
				+ "-fx-background-color: #DAA9B5");
		Button ButtEmpSalary = new Button("Employee Salaries");
		ButtEmpSalary.setEffect(dropShadow);
		ButtEmpSalary.setMinSize(150, 50);
		ButtEmpSalary.setMaxSize(100, 50);
		ButtEmpSalary.setStyle("" 
				+ "-fx-font-size: 15px;"  
				+ "-fx-background-radius:100; "
				+ "-fx-background-color: #5EAE9E");
		Button ButtEmpBank = new Button("Employee Bank Info");
		ButtEmpBank.setEffect(dropShadow);
		ButtEmpBank.setMinSize(150, 50);
		ButtEmpBank.setMaxSize(100, 50);
		ButtEmpBank.setStyle("" 
				+ "-fx-font-size: 13px;"  
				+ "-fx-background-radius:100; "
				+ "-fx-background-color: #63E9FC");
		BorderPane layout = new BorderPane();
		VBox Left = new VBox();
		VBox CenterValue = new VBox();
		ScrollPane Scrolls = new ScrollPane();
		Scrolls.setContent(CenterValue);
		CenterValue.getChildren().addAll(RandomInfo);

		//CSS Main 
				
				window.setTitle("Update Locations");
				window.initModality(Modality.APPLICATION_MODAL);
				layout.setStyle("-fx-background-color: B09268");
				GeneralEmps.setStyle("-fx-font-size: 40;");
				GeneralEmps.setPadding(new Insets(30, 30, 30, 30));
		
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
		ButtEmpSalary.setOnAction(e -> {
			tableview = new TableView();
			buildData(SQL3);
			CenterValue.getChildren().clear();
			CenterValue.getChildren().addAll(tableview);
		});
		ButtEmpBank.setOnAction(e->{
			tableview = new TableView();
			buildData(SQL4);
			CenterValue.getChildren().clear();
			CenterValue.getChildren().addAll(tableview);
		});
		
		Left.getChildren().addAll(ButtGenEmp, ButtEmpSchedule, ButtEmpSalary, ButtEmpBank);

		VBox Right = new VBox();
		Label Stuff = new Label("                 ");
		Right.minWidth(100);
		Right.getChildren().add(Stuff);
		
		//CSS to set everything 
				Left.setPadding(new Insets(0, 10, 0, 10));
				HBox Bottom = new HBox();
				Bottom.setPadding(new Insets(0, 10, 30, 30));
				Button Close = new Button("Close");
				Close.setOnAction(e-> window.close());
				Close.setEffect(dropShadow);
				Close.setPrefWidth(100);
				Close.setStyle("" 
							+ "-fx-font-size: 20px;"
							+ "-fx-background-radius:50; "
							+ "-fx-background-color: #ff6961 ");
				Bottom.getChildren().add(Close);
				layout.setBottom(Bottom);
		
		layout.setRight(Right);
		layout.setTop(GeneralEmps);
		layout.setLeft(Left);
		layout.setCenter(CenterValue);
		// Main Scene
		Scene scene = new Scene(layout, 900, 600);

		window.setScene(scene);
		window.show();

	}
}
