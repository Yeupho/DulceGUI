package sample;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ViewReports {
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
	public static void ReportDisplay(){
		
		/*String SQLLocation = "SELECT Location.LocationID, Location.Address, Location.RentCost, Country.CountryName, "
				+ "State.StateName, City.CityName, BuildingType.TypeDesc "
				+ "FROM Location "
				+ "FULL OUTER JOIN Country ON Location.CountryID = Country.CountryID "
				+ "FULL OUTER JOIN State ON Location.StateID = State.StateID "
				+ "FULL OUTER JOIN City ON Location.CityCode = City.CityCode "
				+ "FULL OUTER JOIN BuildingType ON Location.TypeID = BuildingType.TypeID "
				+ "  WHERE Address <> '---' ;";*/
		String SQL = " SELECT Ticket.TicketID, Ticket.TicketDate, OrderTable.OrderID, Drink.DrinkName, Ice.IceLevel, SugarLevel.AmountDesc, Size.SizeOptions, "
				+ " Temperature.TemperatureOption, Topping.ToppingName, TrantnOrder.Cost "
				+ " FROM OrderTable"
				+ " FULL OUTER JOIN Drink ON OrderTable.DrinkID = Drink.DrinkID "
				+ " FULL OUTER JOIN Ice ON OrderTable.Ice = Ice.Ice "
				+ " FULL OUTER JOIN SugarLevel ON OrderTable.Sugar = SugarLevel.LevelID"
				+ " FULL OUTER JOIN Size ON OrderTable.Size = Size.Size "
				+ " FULL OUTER JOIN Temperature ON OrderTable.Temperature = Temperature.Temperature "
				+ " FULL OUTER JOIN Topping ON OrderTable.Topping = Topping.ToppingID "
				+ " RIGHT OUTER JOIN TrantnOrder ON OrderTable.OrderID = TrantnOrder.OrderID"
				+ " RIGHT OUTER JOIN Ticket ON TrantnOrder.TicketID = Ticket.TicketID"
				+ "  ";
		
		String SQLTemp = "SELECT OrderTable.OrderID, Drink.DrinkName, Ticket.TicketID, Ticket.TicketDate, Temperature.TemperatureOption "
				+ "FROM TrantnOrder "
				+ "INNER JOIN OrderTable ON TrantnOrder.OrderID = OrderTable.OrderID "
				+ "INNER JOIN Drink ON  OrderTable.DrinkID = Drink.DrinkID "
				+ "INNER JOIN Ticket ON Ticket.TicketID = TrantnOrder.TicketID "
				+ "INNER JOIN Temperature ON OrderTable.Temperature = Temperature.Temperature ";
		String SQLTemp2 = "ORDER BY OrderTable.Temperature ASC";
		
		String SQLDType = "SELECT OrderTable.OrderID,DrinkType.TypeName,Ticket.TicketDate,Ticket.TicketID "
				+ "FROM TrantnOrder "
				+ "INNER JOIN OrderTable ON TrantnOrder.OrderID = OrderTable.OrderID "
				+ "INNER JOIN Drink ON OrderTable.DrinkID = Drink.DrinkID "
				+ "INNER JOIN Ticket ON Ticket.TicketID = TrantnOrder.TransactID "
				+ "INNER JOIN DrinkType ON Drink.DrinkTypeID = DrinkType.DrinkTypeID ";
		String SQLDType2 = "ORDER BY Ticket.TicketDate DESC";
		
		String SQLTopping = "SELECT TrantnOrder.Quantity,Location.BuildingName,Topping.ToppingName, Ticket.TicketDate "
				+ "FROM Ticket "
				+ "INNER JOIN PositionTime ON Ticket.PositionTimeID = PositionTime.PositionTimeID "
				+ "INNER JOIN TrantnOrder ON Ticket.TicketID = TrantnOrder.TicketID "
				+ "INNER JOIN Employee ON PositionTime.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Location ON Employee.LocationID = Location.LocationID "
				+ "INNER JOIN OrderTable ON TrantnOrder.OrderID = OrderTable.OrderID "
				+ "INNER JOIN Topping ON OrderTable.Topping = Topping.ToppingID ";
		String SQLTopping2 = "ORDER BY Location.LocationID ASC ";
		
		String SQLSize = "SELECT Size.SizeOptions,Drink.DrinkName,DrinkType.TypeName, Topping.ToppingName, Ticket.TicketDate "
				+ "FROM Ticket	"
				+ "INNER JOIN TrantnOrder ON Ticket.TicketID = TrantnOrder.TicketID "
				+ "INNER JOIN OrderTable ON TrantnOrder.OrderID = OrderTable.OrderID "
				+ "INNER JOIN Topping ON OrderTable.Topping = Topping.ToppingID "
				+ "INNER JOIN Size ON OrderTable.Size = Size.Size "
				+ "INNER JOIN Drink ON OrderTable.DrinkID = Drink.DrinkID "
				+ "INNER JOIN DrinkType ON Drink.DrinkTypeID = DrinkType.DrinkTypeID "; 
		String SQLSize2 = "ORDER BY Ticket.TicketDate ASC";
		
		String SQLQuantity = "SELECT OrderTable.OrderID, Drink.DrinkName, DrinkType.TypeName, Ticket.TicketID,Ticket.TicketDate,TrantnOrder.Quantity "
				+ "FROM TrantnOrder "
				+ "INNER JOIN OrderTable ON TrantnOrder.OrderID = OrderTable.OrderID "
				+ "INNER JOIN Drink ON OrderTable.DrinkID = Drink.DrinkID "
				+ "INNER JOIN Ticket ON Ticket.TicketID = TrantnOrder.TicketID "
				+ "INNER JOIN DrinkType ON Drink.DrinkTypeID = DrinkType.DrinkTypeID";
		String SQLQuantity2= "ORDER BY TrantnOrder.Quantity DESC";
		
		String SQLTickLocation = "SELECT Location.Address, State.StateName, City.CityName, OrderTable.OrderID, Ticket.TicketDate "
				+ "FROM Location "
				+ "INNER JOIN Employee ON Location.LocationID = Employee.LocationID "
				+ "INNER JOIN PositionTime ON PositionTime.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Ticket ON Ticket.PositionTimeID = PositionTime.PositionTimeID "
				+ "INNER JOIN TrantnOrder ON TrantnOrder.TicketID = Ticket.TicketID "
				+ "INNER JOIN OrderTable ON OrderTable.OrderID = TrantnOrder.OrderID "
				+ "INNER JOIN State ON Location.StateID = State.StateID "
				+ "INNER JOIN City ON City.CityCode = Location.CityCode ";
		String SQLTickLocation2 = " ORDER BY Location.Address ASC ";

		String SQLIceLocation = "SELECT Location.Address, State.StateName, City.CityName, Ice.IceLevel, Ticket.TicketDate "
				+ "FROM Location "
				+ "INNER JOIN Employee ON Location.LocationID = Employee.LocationID "
				+ "INNER JOIN PositionTime ON PositionTime.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Ticket ON Ticket.PositionTimeID = PositionTime.PositionTimeID "
				+ "INNER JOIN TrantnOrder ON TrantnOrder.TicketID = Ticket.TicketID "
				+ "INNER JOIN OrderTable ON OrderTable.OrderID = TrantnOrder.OrderID "
				+ "INNER JOIN State ON Location.StateID = State.StateID "
				+ "INNER JOIN City ON City.CityCode = Location.CityCode "
				+ "INNER JOIN Ice ON OrderTable.Ice = Ice.Ice ";
		String SQLIceLocation2 = " ORDER BY Location.Address ASC ";
		
		String SQLDrinkTypeLoc = "SELECT Location.Address, State.StateName, City.CityName, DrinkType.TypeName, Ticket.TicketDate "
				+ " FROM Location "
				+ " INNER JOIN Employee ON Location.LocationID = Employee.LocationID "
				+ " INNER JOIN PositionTime ON PositionTime.EmployeeID = Employee.EmployeeID "
				+ " INNER JOIN Ticket ON Ticket.PositionTimeID = PositionTime.PositionTimeID "
				+ " INNER JOIN TrantnOrder ON TrantnOrder.TicketID = Ticket.TicketID "
				+ " INNER JOIN OrderTable ON OrderTable.OrderID = TrantnOrder.OrderID "
				+ " INNER JOIN State ON Location.StateID = State.StateID "
				+ " INNER JOIN City ON City.CityCode = Location.CityCode "
				+ " Inner JOIN Drink ON Drink.DrinkID = OrderTable.DrinkID "
				+ " INNER JOIN DrinkType ON Drink.DrinkTypeID = DrinkType.DrinkTypeID ";
		String SQLDrinkTypeLoc2 = " ORDER BY Location.Address ASC ";
		
		String SQLTempLoc = "SELECT Location.Address, State.StateName, City.CityName, Temperature.TemperatureOption, Ticket.TicketDate "
				+ " FROM Location "
				+ " INNER JOIN Employee ON Location.LocationID = Employee.LocationID "
				+ " INNER JOIN PositionTime ON PositionTime.EmployeeID = Employee.EmployeeID "
				+ " INNER JOIN Ticket ON Ticket.PositionTimeID = PositionTime.PositionTimeID "
				+ " INNER JOIN TrantnOrder ON TrantnOrder.TicketID = Ticket.TicketID "
				+ " INNER JOIN OrderTable ON OrderTable.OrderID = TrantnOrder.OrderID "
				+ " INNER JOIN State ON Location.StateID = State.StateID "
				+ " INNER JOIN City ON City.CityCode = Location.CityCode "
				+ " INNER JOIN Temperature ON OrderTable.Temperature = Temperature.Temperature ";
		String SQLTempLoc2 = " ORDER BY Location.Address ASC ";
		
		String SQLToppingLoc = "SELECT Location.Address, State.StateName, City.CityName, Temperature.TemperatureOption, Ticket.TicketDate "
				+ " FROM Location "
				+ " INNER JOIN Employee ON Location.LocationID = Employee.LocationID "
				+ " INNER JOIN PositionTime ON PositionTime.EmployeeID = Employee.EmployeeID "
				+ " INNER JOIN Ticket ON Ticket.PositionTimeID = PositionTime.PositionTimeID "
				+ " INNER JOIN TrantnOrder ON TrantnOrder.TicketID = Ticket.TicketID "
				+ " INNER JOIN OrderTable ON OrderTable.OrderID = TrantnOrder.OrderID "
				+ " INNER JOIN State ON Location.StateID = State.StateID "
				+ " INNER JOIN City ON City.CityCode = Location.CityCode "
				+ " INNER JOIN Temperature ON OrderTable.Temperature = Temperature.Temperature ";
		String SQLToppingLoc2 = " ORDER BY Location.Address ASC ";
		
		String SQLSizeLoc = "SELECT Location.Address, State.StateName, City.CityName, Size.SizeOptions, Ticket.TicketDate "
				+ " FROM Location "
				+ " INNER JOIN Employee ON Location.LocationID = Employee.LocationID "
				+ " INNER JOIN PositionTime ON PositionTime.EmployeeID = Employee.EmployeeID "
				+ " INNER JOIN Ticket ON Ticket.PositionTimeID = PositionTime.PositionTimeID "
				+ " INNER JOIN TrantnOrder ON TrantnOrder.TicketID = Ticket.TicketID "
				+ " INNER JOIN OrderTable ON OrderTable.OrderID = TrantnOrder.OrderID "
				+ " INNER JOIN State ON Location.StateID = State.StateID "
				+ " INNER JOIN City ON City.CityCode = Location.CityCode "
				+ " INNER JOIN Size ON OrderTable.Size = Size.Size ";
		String SQLSizeLoc2 = " ORDER BY Location.Address ASC ";
		
		
		
		
		
/*===========EMPLOYEE SQL ===================================================
 * 
 * 
 * 
 * ==========================================================================*/
		//Employee Ticket Sales 
		String SQLEmpTicket = "SELECT Ticket.TicketID,TrantnOrder.Quantity,TrantnOrder.Cost,PositionTime.EmployeeID, "
				+ "Employee.FirstName,	Employee.LastName, Ticket.TicketDate "
				+ "FROM Ticket "
				+ "INNER JOIN PositionTime ON Ticket.PositionTimeID = PositionTime.PositionTimeID "
				+ "INNER JOIN TrantnOrder ON Ticket.TicketID = TrantnOrder.TicketID "
				+ "INNER JOIN Employee ON PositionTime.EmployeeID = Employee.EmployeeID ";
		String SQLEmpTicket2= "ORDER BY Ticket.TicketDate DESC";
		//Status or Activity of Employee
		String SQLEmpStatus = "SELECT Employee.EmployeeID,Employee.FirstName,Employee.LastName,EmployeeStatus.Status, "
				+ "EmployeeStatus.DateOfStatus,Role.RoleName,Location.Address "
				+ "FROM EmployeeStatus "
				+ "INNER JOIN Employee ON EmployeeStatus.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Role ON Employee.RoleID = Role.RoleID "
				+ "INNER JOIN Location ON Employee.LocationID = Location.LocationID ";
		//View Employee PAY Status
		String SQLEmpPayStatus = "SELECT PayStatus.PayDate,Employee.FirstName, Employee.LastName,PayStatus.Paid_Answer, "
				+ "PaymentType.Name, HourRate.BasePay, EmployeeSchedule.TotHrsWorked,BankInfo.RoutingNumber,"
				+ "BankInfo.AccountNumber,BankInfo.SavingsActNumber FROM PayStatus "
				+ "INNER JOIN Payroll ON PayStatus.PayrollID = Payroll.PayrollID "
				+ "INNER JOIN PaymentType ON Payroll.PaymentType = PaymentType.TypeID "
				+ "INNER JOIN Employee ON Employee.EmployeeID = Payroll.EmployeeID "
				+ "INNER JOIN HourRate ON Payroll.BaseRateID = HourRate.BasePay "
				+ "INNER JOIN EmployeeSchedule ON Payroll.ScheduleID = EmployeeSchedule.ScheduleID "
				+ "INNER JOIN BankInfo ON Employee.EmployeeID = BankInfo.EmployeeID ";
		//View Employee General Info
		String SQLEmpGen = "SELECT Employee.FirstName, Employee.LastName, Role.RoleName, Location.Address, City.CityName " 
				+ "FROM Employee "
				+ "INNER JOIN Role ON Employee.RoleID = Role.RoleID "
				+ "INNER JOIN Location ON Employee.LocationID = Location.LocationID "
				+ "INNER JOIN City ON Location.CityCode = City.CityCode ";
		//View Employee Schedules
		String SQLEmpSched = " SELECT Employee.EmployeeID, Employee.FirstName, Employee.LastName, HoursWorked.ShiftName,  "
				+ " DayofWeekz.DayName, DayofWeekz.DayID "
				+ " FROM WeekSchedule " 
				+ " INNER JOIN Employee ON WeekSchedule.EmployeeID = Employee.EmployeeID "
				+ " INNER JOIN HoursWorked ON WeekSchedule.ShiftID = HoursWorked.ShiftID "
				+ " INNER JOIN DayofWeekz ON WeekSchedule.DayID = DayofWeekz.DayID "
				+ " WHERE WeekSchedule.WeekSchedule IS NOT NULL "
				+ " ORDER BY DayofWeekz.DayID ASC ; ";
		//View Employee Bank Info
		String SQLEmpBankInfo ="SELECT Employee.EmployeeID, Employee.FirstName, Employee.LastName, BankInfo.Bank, "
				+ " BankInfo.AccountNumber, BankInfo.SavingsActNumber, BankInfo.RoutingNumber, HourRate.BasePay "
				+ " FROM BankInfo "
				+ " FULL OUTER JOIN Employee ON BankInfo.EmployeeID = Employee.EmployeeID "
				+ " INNER JOIN Payroll ON Payroll.EmployeeID = Employee.EmployeeID "
				+ " INNER JOIN HourRate ON Payroll.BaseRateID = HourRate.RateID ";
		//View Detailed Schedules
		String SQLEmpSchedDetail = "SELECT WeekSchedule.EmployeeID,EmployeeSchedule.WeekScheduleID,WeekSchedule.Date,DayofWeekz.DayName,HoursWorked.ShiftName, "
				+ "HoursWorked.StartTime,HoursWorked.EndTime,EmployeeSchedule.TotHrsWorked, "
				+ "WeekSchedule.DayHoursWorked FROM EmployeeSchedule "
				+ "INNER JOIN WeekSchedule ON EmployeeSchedule.WeekScheduleID = WeekSchedule.WeekSchedule "
				+ "INNER JOIN HoursWorked ON WeekSchedule.ShiftID = HoursWorked.ShiftID AND WeekSchedule.ShiftID = HoursWorked.ShiftID "
				+ "INNER JOIN DayofWeekz ON WeekSchedule.DayID = DayofWeekz.DayID ";
		String SQLEmpSchedDetail2 ="ORDER BY WeekSchedule.Date ASC";
		
		String SQLEmpSalesLoc = "SELECT Ticket.TicketID, TrantnOrder.Quantity,TrantnOrder.Cost,PositionTime.EmployeeID,Employee.FirstName,Location.BuildingName "
				+ "FROM Ticket "
				+ "INNER JOIN PositionTime ON Ticket.PositionTimeID = PositionTime.PositionTimeID "
				+ "INNER JOIN TrantnOrder ON Ticket.TicketID = TrantnOrder.TicketID "
				+ "INNER JOIN Employee ON PositionTime.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Location ON Employee.LocationID = Location.LocationID ";
		
		String SQLEmpSAlesLocDetail = "SELECT Ticket.TicketID,TrantnOrder.Quantity,TrantnOrder.Cost,PositionTime.EmployeeID,Employee.FirstName, "
				+ "Employee.LastName,Location.BuildingName,Employee.LocationID,Ticket.TicketDate "
				+ "FROM Ticket "
				+ "INNER JOIN PositionTime ON Ticket.PositionTimeID = PositionTime.PositionTimeID "
				+ "INNER JOIN TrantnOrder ON Ticket.TicketID = TrantnOrder.TicketID "
				+ "INNER JOIN Employee ON PositionTime.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Location ON Employee.LocationID = Location.LocationID ";
		
		String SQLEmpHired = "SELECT Employee.EmployeeID, Employee.Firstname, Employee.LastName, EmployeeStatus.Status, DayofWeekz.DayName "
				+ "FROM Employee "
				+ "INNER JOIN EmployeeStatus ON EmployeeStatus.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Location On Employee.LocationID = Location.LocationID  "
				+ "INNER JOIN WeekSchedule ON Employee.EmployeeID = WeekSchedule.EmployeeID "
				+ "INNER JOIN DayofWeekz ON WeekSchedule.DayID = DayofWeekz.DayID "
				+ "WHERE EmployeeStatus.Status LIKE 'Hired' ";
		String SQLEmpHired2 = "ORDER BY Employee.EmployeeID ASC";
		
		String SQLEmpVacation = "SELECT Employee.EmployeeID, Employee.Firstname, Employee.LastName, EmployeeStatus.Status,  "
				+ "EmployeeStatus.DateOfStatus, Location.Address, State.StateName "
				+ "FROM EmployeeStatus "
				+ "INNER JOIN Employee ON EmployeeStatus.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Location On Employee.LocationID = Location.LocationID  "
				+ "INNER JOIN State ON Location.StateID = State.StateID "
				+ "WHERE EmployeeStatus.Status LIKE 'Vacation' ";
		String SQLEmpVacation2 = "ORDER BY Employee.EmployeeID ASC";
		
		String SQLEmpFired = "SELECT Employee.EmployeeID, Employee.Firstname, Employee.LastName, EmployeeStatus.Status, "
				+ "EmployeeStatus.DateOfStatus, Location.Address, State.StateName "
				+ "FROM EmployeeStatus "
				+ "INNER JOIN Employee ON EmployeeStatus.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Location On Employee.LocationID = Location.LocationID  "
				+ "INNER JOIN State ON Location.StateID = State.StateID "
				+ "WHERE EmployeeStatus.Status LIKE 'Fired' ";
		String SQLEmpFired2 = "ORDER BY Employee.EmployeeID ASC";
		
		String SQLBasePayLoc = "SELECT Location.Address, HourRate.BasePay, Employee.EmployeeID, Role.RoleName "
				+ "FROM Payroll "
				+ "INNER JOIN HourRate ON HourRate.RateID = Payroll.BaseRateID "
				+ "INNER JOIN Employee ON Payroll.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Location ON Employee.LocationID = Location.LocationID "
				+ "INNER JOIN Role ON Employee.RoleID = Role.RoleID ";
		String SQLBasePayLoc2 = "ORDER BY Location.Address ASC";
		
		String SQLBasePayCountry = "SELECT Country.CountryName, HourRate.BasePay, Employee.EmployeeID, Role.RoleName "
				+ "FROM Payroll "
				+ "INNER JOIN HourRate ON HourRate.RateID = Payroll.BaseRateID "
				+ "INNER JOIN Employee ON Payroll.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Location ON Employee.LocationID = Location.LocationID "
				+ "INNER JOIN Country ON Location.CountryID = Country.CountryID "
				+ "INNER JOIN Role ON Employee.RoleID = Role.RoleID ";
		String SQLBasePayCountry2 = "ORDER BY CountryName DESC";
		
		String SQLBasePayState = "SELECT State.StateName, HourRate.BasePay, Employee.EmployeeID, Role.RoleName "
				+ "FROM Payroll "
				+ "INNER JOIN HourRate ON HourRate.RateID = Payroll.BaseRateID "
				+ "INNER JOIN Employee ON Payroll.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Location ON Employee.LocationID = Location.LocationID "
				+ "INNER JOIN State ON Location.StateID = State.StateID "
				+ "INNER JOIN Role ON Employee.RoleID = Role.RoleID " ;
		String SQLBasePayState2 = "ORDER BY StateName ASC ";
		
		String SQLBasePayCity = "SELECT City.CityName, HourRate.BasePay, Employee.EmployeeID, Role.RoleName "
				+ "FROM Payroll "
				+ "INNER JOIN HourRate ON HourRate.RateID = Payroll.BaseRateID "
				+ "INNER JOIN Employee ON Payroll.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Location ON Employee.LocationID = Location.LocationID "
				+ "INNER JOIN City ON Location.CityCode = City.CityCode "
				+ "INNER JOIN Role ON Employee.RoleID = Role.RoleID " ;
		String SQLBasePayCity2 = "ORDER BY CityName ASC";
		
		String SQLBasePayBuildingType = "SELECT BuildingType.TypeDesc, HourRate.BasePay, Employee.EmployeeID, Role.RoleName "
				+ "FROM Payroll "
				+ "INNER JOIN HourRate ON HourRate.RateID = Payroll.BaseRateID "
				+ "INNER JOIN Employee ON Payroll.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Location ON Employee.LocationID = Location.LocationID "
				+ "INNER JOIN BuildingType ON Location.TypeID = BuildingType.TypeID "
				+ "INNER JOIN Role ON Employee.RoleID = Role.RoleID ";
		String SQLBasePayBuildingType2 = "ORDER BY BuildingType.TypeDesc ASC";
		
		String SQLBasePayRoleType = "SELECT  Employee.EmployeeID,Role.RoleName,HourRate.BasePay, Payroll.Bonus,EmployeeSchedule.TotHrsWorked "
				+ "FROM Payroll "
				+ "INNER JOIN Employee ON Employee.EmployeeID = Payroll.EmployeeID "
				+ "INNER JOIN Role ON Employee.RoleID = Role.RoleID "
				+ "INNER JOIN HourRate ON Payroll.BaseRateID = HourRate.BasePay "
				+ "INNER JOIN EmployeeSchedule ON Payroll.ScheduleID = EmployeeSchedule.ScheduleID";
		String SQLBasePayRoleType2 = "ORDER BY Role.RoleName ASC";
		
		
		/*======================Customer SQL STuff==============================================
		 * 
		 * 
		 * 
		 * 
		 * ===================================================================================*/

		String SQLActNumberDrinkName = "SELECT Account.Account_PhoneNumber, Ticket.TicketDate,Drink.DrinkName,DrinkType.TypeName "
				+ "FROM Ticket "
				+ "INNER JOIN Account ON Account.Account_PhoneNumber = Ticket.PhoneNumber "
				+ "INNER JOIN TrantnOrder ON Ticket.TicketID = TrantnOrder.TicketID "
				+ "INNER JOIN OrderTable ON TrantnOrder.OrderID = OrderTable.OrderID "
				+ "INNER JOIN Drink ON OrderTable.DrinkID = Drink.DrinkID "
				+ "Inner JOIN DrinkType ON Drink.DrinkTypeID = DrinkType.DrinkTypeID ";
		String SQLActNumberDrinkName2 = "ORDER BY Ticket.TicketDate Desc"; 
			
		String SQLActNumberDrinkType = "SELECT Account.Account_PhoneNumber, Ticket.TicketDate,DrinkType.TypeName "
				+ "FROM Ticket "
				+ "INNER JOIN Account ON Account.Account_PhoneNumber = Ticket.PhoneNumber "
				+ "INNER JOIN TrantnOrder ON Ticket.TicketID = TrantnOrder.TicketID "
				+ "INNER JOIN OrderTable ON TrantnOrder.OrderID = OrderTable.OrderID "
				+ "INNER JOIN Drink ON OrderTable.DrinkID = Drink.DrinkID "
				+ "Inner JOIN DrinkType ON Drink.DrinkTypeID = DrinkType.DrinkTypeID ";
		String SQLActNumberDrinkType2 = "ORDER BY Ticket.PhoneNumber Desc" ;
		
		String SQLActNumberState = "SELECT Account.Account_PhoneNumber, Ticket.TicketDate, Location.Address, State.StateName "
				+ "FROM Ticket "
				+ "INNER JOIN Account ON Account.Account_PhoneNumber = Ticket.PhoneNumber "
				+ "INNER JOIN PositionTime ON PositionTime.EmployeeID = Ticket.PositionTimeID "
				+ "Inner JOIN Employee ON PositionTime.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Location ON Employee.LocationID = Location.LocationID "
				+ "INNER JOIN State ON Location.StateID = State.StateID";
		String SQLActNumberState2 = "ORDER BY Account.Account_PhoneNumber ASC";
		
		String SQLActNumberLoc = "SELECT Account.Account_PhoneNumber, Ticket.TicketDate, Location.Address "
				+ "FROM Ticket "
				+ "INNER JOIN Account ON Account.Account_PhoneNumber = Ticket.PhoneNumber "
				+ "INNER JOIN PositionTime ON PositionTime.EmployeeID = Ticket.PositionTimeID "
				+ "Inner JOIN Employee ON PositionTime.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Location ON Employee.LocationID = Location.LocationID " ;
		String SQLActNumberLoc2 = "ORDER BY Ticket.PhoneNumber Desc";
		
		String SQLActNumberLocDetail = "SELECT Account.Account_PhoneNumber, Ticket.TicketDate, Location.Address, State.StateName, City.CityName "
				+ "FROM Ticket "
				+ "INNER JOIN Account ON Account.Account_PhoneNumber = Ticket.PhoneNumber "
				+ "INNER JOIN PositionTime ON PositionTime.EmployeeID = Ticket.PositionTimeID "
				+ "Inner JOIN Employee ON PositionTime.EmployeeID = Employee.EmployeeID "
				+ "INNER JOIN Location ON Employee.LocationID = Location.LocationID "
				+ "INNER JOIN State ON Location.StateID = State.StateID "
				+ "INNER JOIN City ON Location.CityCode = City.CityCode ";				
		String SQLActNumberLocDetail2 = "ORDER BY Account.Account_PhoneNumber ASC " ;
		
		
		
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		HBox TopBox = new HBox();
		Label GeneralLocs = new Label("Location Reports");
		GeneralLocs.setStyle("-fx-font-size: 40;");
		GeneralLocs.setPadding(new Insets(0, 30, 0, 30));
		TopBox.setPadding(new Insets(30, 30, 30, 30));
		
		BorderPane layout = new BorderPane();
		layout.setStyle("-fx-background-color: #B09268");
		
		
		
		HBox CenterValue = new HBox();
		CenterValue.setSpacing(20);
		ScrollPane Scrolls = new ScrollPane();
		Scrolls.setContent(CenterValue);
		
		Button ButtGenTick = new Button("Ticket Sales\n& Purchases");
		Button ButtEmpRep = new Button("Employee\nReport");
		Button ButtLocRep = new Button("Location\nReports");
		CenterValue.getChildren().addAll(ButtGenTick,ButtEmpRep,ButtLocRep);
		CenterValue.setAlignment(Pos.TOP_CENTER);
		//CSS for Buttons
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(5.0);
		dropShadow.setOffsetY(5.0);
		dropShadow.setColor(Color.color(0.2, 0.2, 0.2));
		
		ButtGenTick.setEffect(dropShadow);
		ButtGenTick.setMinSize(200, 200);
		ButtGenTick.setMaxSize(100, 150);
		ButtGenTick.setStyle("" 
				+ "-fx-font-size: 25;"  
				+ "-fx-background-radius:100; "
				+ "-fx-background-color: #C06A45; "
				+ "-fx-text-fill: white; "
				+ "-fx-font-weight: bold;");
		ButtEmpRep.setEffect(dropShadow);
		ButtEmpRep.setPadding(new Insets(0, 30, 0, 30));
		ButtEmpRep.setMinSize(200, 200);
		ButtEmpRep.setMaxSize(100, 150);
		ButtEmpRep.setStyle("" 
				+ "-fx-font-size: 25;"  
				+ "-fx-background-radius:100; "
				+ "-fx-background-color: #B96F6F; "
				+ "-fx-text-fill: white; "
				+ "-fx-font-weight: bold;");
		ButtLocRep.setEffect(dropShadow);
		ButtLocRep.setMinSize(200, 200);
		ButtLocRep.setMaxSize(100, 150);
		ButtLocRep.setStyle("" 
				+ "-fx-font-size: 25;"  
				+ "-fx-background-radius:100; "
				+ "-fx-background-color: #C98A4B; "
				+ "-fx-text-fill: white; "
				+ "-fx-font-weight: bold;");
		
		Button Close = new Button("Close");
		Close.setOnAction(e-> window.close());
		Close.setEffect(dropShadow);
		Close.setPrefWidth(100);
		TopBox.setSpacing(20);
		Close.setStyle("" 
					+ "-fx-font-size: 16px;"
					+ "-fx-background-radius:50; "
					+ "-fx-background-color: #ff6961 ");
		
		TopBox.getChildren().addAll(GeneralLocs, Close);
		
		
		layout.setTop(TopBox);
		layout.setCenter(CenterValue);
		Scene scene = new Scene(layout, 950, 600);

		window.setScene(scene);
		window.show();
		
		Button BackButton = new Button("Go Back");
		BackButton.setOnAction(e-> window.setScene(scene));
		BackButton.setEffect(dropShadow);
		BackButton.setPrefWidth(100);
		BackButton.setStyle("" 
					+ "-fx-font-size: 16px;"
					+ "-fx-background-radius:50; "
					+ "-fx-background-color: #ffa500 ");
		Button BackButton2 = new Button("Go Back");
		BackButton2.setOnAction(e-> window.setScene(scene));
		BackButton2.setEffect(dropShadow);
		BackButton2.setPrefWidth(100);
		BackButton2.setStyle("" 
					+ "-fx-font-size: 16px;"
					+ "-fx-background-radius:50; "
					+ "-fx-background-color: #ffa500 ");
		Button BackButton3 = new Button("Go Back");
		BackButton3.setOnAction(e-> window.setScene(scene));
		BackButton3.setEffect(dropShadow);
		BackButton3.setPrefWidth(100);
		BackButton3.setStyle("" 
					+ "-fx-font-size: 16px;"
					+ "-fx-background-radius:50; "
					+ "-fx-background-color: #ffa500 ");
		Button Close2 = new Button("Close");
		Close2.setOnAction(e-> window.close());
		Close2.setEffect(dropShadow);
		Close2.setPrefWidth(100);
		Close2.setStyle("" 
					+ "-fx-font-size: 16px;"
					+ "-fx-background-radius:50; "
					+ "-fx-background-color: #ff6961 ");
		Button Close3 = new Button("Close");
		Close3.setOnAction(e-> window.close());
		Close3.setEffect(dropShadow);
		Close3.setPrefWidth(100);
		Close3.setStyle("" 
					+ "-fx-font-size: 16px;"
					+ "-fx-background-radius:50; "
					+ "-fx-background-color: #ff6961 ");
		/*=====================================================================*/
		BorderPane Ticklayout = new BorderPane();
		HBox TickBox = new HBox();
		
		FlowPane flowbutts = new FlowPane();
		flowbutts.setHgap(20);
		flowbutts.setVgap(20);
		Label LabTick = new Label("Ticket Reports");
		FlowPane flowbuttsa = new FlowPane();
		flowbuttsa.setHgap(20);
		flowbuttsa.setVgap(20);
		FlowPane flowbuttsb = new FlowPane();
		flowbuttsb.setHgap(20);
		flowbuttsb.setVgap(20);
		Label TickLab1 = new Label("Ticket Sales Information");
		Label TickLab2 = new Label("Sales Reports by Location");
		
		TickLab1.setStyle("-fx-font-size: 20;");
		TickLab1.setPadding(new Insets(0, 15, 15, 15));
		
		TickLab2.setStyle("-fx-font-size: 20;");
		TickLab2.setPadding(new Insets(20, 15, 15, 15));
		
		Ticklayout.setPadding(new Insets(30,30,30,30));
		Ticklayout.setStyle("-fx-background-color: #B09268");
		TickBox.setSpacing(30);
		TickBox.getChildren().addAll(LabTick, BackButton, Close);
		
		VBox TickVBox = new VBox();
		TickVBox.getChildren().addAll(TickLab1,flowbutts,TickLab2,flowbuttsa, flowbuttsb);
		
		
		LabTick.setStyle("-fx-font-size: 40;");
		LabTick.setPadding(new Insets(0, 30, 30, 30));
		
		
		Button Tick1 = new Button("General Ticket");
		Button Tick2 = new Button("Temperature");
		Button Tick3 = new Button("Drink Types");
		Button Tick4 = new Button("Toppings");
		Button Tick5 = new Button("Size Options");
		Button Tick6 = new Button("Quantity");
		Button Tick7 = new Button("General Sales\nby Location");
		Button Tick8 = new Button("Ice Sales");
		Button Tick9 = new Button("DrinkType\nSales");
		Button Tick10 = new Button("Temperature\nSales");
		Button Tick11 = new Button("Topping Sales");
		Button Tick12 = new Button("DrinkType\nSales");

		
		Tick1.setOnAction(e->{ BuildReports.BuildRep("Ticket Report", SQL, " ", "Ticket.TicketDate");});
		Tick1.setEffect(dropShadow);
		Tick1.setMinSize(150, 75);
		Tick1.setMaxSize(150, 75);
		Tick1.setPadding(new Insets(10, 20, 5, 20));
		Tick1.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #AFE197; ");
		Tick2.setOnAction(e->{ BuildReports.BuildRep("Temperature Report", SQLTemp, SQLTemp2, "Ticket.TicketDate");});
		Tick2.setEffect(dropShadow);
		Tick2.setMinSize(150, 75);
		Tick2.setMaxSize(150, 75);
		Tick2.setPadding(new Insets(10, 20, 5, 20));
		Tick2.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #AFE197; ");
		Tick3.setOnAction(e->{ BuildReports.BuildRep("Drink Type Report", SQLDType, SQLDType2, "Ticket.TicketDate");});
		Tick3.setEffect(dropShadow);
		Tick3.setMinSize(150, 75);
		Tick3.setMaxSize(150, 75);
		Tick3.setPadding(new Insets(10, 20, 5, 20));
		Tick3.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #AFE197; ");
		Tick4.setOnAction(e->{ BuildReports.BuildRep("Toppings Report", SQLTopping, SQLTopping2, "Ticket.TicketDate");});
		Tick4.setEffect(dropShadow);
		Tick4.setMinSize(150, 75);
		Tick4.setMaxSize(150, 75);
		Tick4.setPadding(new Insets(10, 20, 5, 20));
		Tick4.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #AFE197; ");
		Tick5.setOnAction(e->{ BuildReports.BuildRep("Drink Size Report", SQLSize, SQLSize2, "Ticket.TicketDate");});
		Tick5.setEffect(dropShadow);
		Tick5.setMinSize(150, 75);
		Tick5.setMaxSize(150, 75);
		Tick5.setPadding(new Insets(10, 20, 5, 20));
		Tick5.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #AFE197; ");
		Tick6.setOnAction(e->{ BuildReports.BuildRep("Quantity Report", SQLQuantity, SQLQuantity2, "Ticket.TicketDate");});
		Tick6.setEffect(dropShadow);
		Tick6.setMinSize(150, 75);
		Tick6.setMaxSize(150, 75);
		Tick6.setPadding(new Insets(10, 20, 5, 20));
		Tick6.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #AFE197; ");
		Tick7.setOnAction(e->{ BuildReports.BuildRep("Location Sales", SQLTickLocation, SQLTickLocation2, "Ticket.TicketDate");});
		Tick7.setEffect(dropShadow);
		Tick7.setMinSize(150, 75);
		Tick7.setMaxSize(150, 75);
		Tick7.setPadding(new Insets(10, 20, 5, 20));
		Tick7.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #a4ffca; ");
		Tick8.setOnAction(e->{ BuildReports.BuildRep("Ice Levels by Location", SQLIceLocation, SQLIceLocation2, "Ticket.TicketDate");});
		Tick8.setEffect(dropShadow);
		Tick8.setMinSize(150, 75);
		Tick8.setMaxSize(150, 75);
		Tick8.setPadding(new Insets(10, 20, 5, 20));
		Tick8.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #a4ffca; ");
		Tick9.setOnAction(e->{ BuildReports.BuildRep3("Drink Type By Location", SQLDrinkTypeLoc, SQLDrinkTypeLoc2);});
		Tick9.setEffect(dropShadow);
		Tick9.setMinSize(150, 75);
		Tick9.setMaxSize(150, 75);
		Tick9.setPadding(new Insets(10, 20, 5, 20));
		Tick9.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #a4ffca; ");
		Tick10.setOnAction(e->{ BuildReports.BuildRep3("Drink Temperature By Location", SQLTempLoc, SQLTempLoc2);});
		Tick10.setEffect(dropShadow);
		Tick10.setMinSize(150, 75);
		Tick10.setMaxSize(150, 75);
		Tick10.setPadding(new Insets(10, 20, 5, 20));
		Tick10.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #a4ffca; ");
		Tick11.setOnAction(e->{ BuildReports.BuildRep3("Drink Sizes By Location", SQLToppingLoc, SQLToppingLoc2);});
		Tick11.setEffect(dropShadow);
		Tick11.setMinSize(150, 75);
		Tick11.setMaxSize(150, 75);
		Tick11.setPadding(new Insets(10, 20, 5, 20));
		Tick11.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #a4ffca; ");
		Tick12.setOnAction(e->{ BuildReports.BuildRep3("Ice Levels by Location", SQLSizeLoc, SQLSizeLoc2);});
		Tick12.setEffect(dropShadow);
		Tick12.setMinSize(150, 75);
		Tick12.setMaxSize(150, 75);
		Tick12.setPadding(new Insets(10, 20, 5, 20));
		Tick12.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #a4ffca; ");
		flowbutts.getChildren().addAll(Tick1, Tick2, Tick3, Tick4, Tick5, Tick6);
		flowbuttsa.getChildren().addAll(Tick7, Tick8, Tick9, Tick10, Tick11, Tick12);
		Ticklayout.setTop(TickBox);
		Ticklayout.setCenter(TickVBox);
		Scene SceneTicket = new Scene(Ticklayout, 950, 600);
		
		
		/*=====================================================================*/
		
		
		
		BorderPane EmpLayout = new BorderPane();
		HBox EmpBox = new HBox();
		FlowPane flowbutts2 = new FlowPane();
		flowbutts2.setHgap(20);
		flowbutts2.setVgap(20);
		FlowPane flowbutts2a = new FlowPane();
		flowbutts2a.setHgap(20);
		flowbutts2a.setVgap(20);
		FlowPane flowbutts2b = new FlowPane();
		flowbutts2b.setHgap(20);
		flowbutts2b.setVgap(20);
		Label EmpLab1 = new Label("Employee Information");
		Label EmpLab2 = new Label("Employee Pay");
		EmpLab1.setStyle("-fx-font-size: 20;");
		EmpLab1.setPadding(new Insets(0, 15, 15, 15));
		
		EmpLab2.setStyle("-fx-font-size: 20;");
		EmpLab2.setPadding(new Insets(20, 15, 15, 15));
		
		VBox EmpVBox = new VBox();
		EmpVBox.getChildren().addAll(EmpLab1,flowbutts2,EmpLab2,flowbutts2a, flowbutts2b);
		Label LabEmp = new Label("Employee Reports");
		
		EmpLayout.setPadding(new Insets(30,30,30,30));
		EmpLayout.setStyle("-fx-background-color: #B09268");
		EmpBox.setSpacing(30);
		EmpBox.getChildren().addAll(LabEmp, BackButton2, Close2);
		
		
		
		LabEmp.setStyle("-fx-font-size: 40;");
		LabEmp.setPadding(new Insets(0, 30, 30, 30));
		
		Button Emp1 = new Button("Employee Sales");
		Button Emp2 = new Button("Employee\nStatus");
		Button Emp3 = new Button("Pay Status");
		Button Emp4 = new Button("Employee Info");
		Button Emp5 = new Button("Schedules");
		Button Emp6 = new Button("Bank Info");
		Button Emp7 = new Button("Schedule Detail");
		Button Emp8 = new Button("Emp. Sales\nby Location");
		Button Emp9 = new Button("Emp. Sales by \nLocation Detail");
		Button Emp10 = new Button("Base Pay By\nAddress");
		Button Emp11 = new Button("Base Pay By\nCountry");
		Button Emp12 = new Button("Base Pay By\nState");
		Button Emp13 = new Button("Base Pay By\nCity");
		Button Emp14 = new Button("Base Pay By\nBuilding Type");
		Button Emp15 = new Button("Base Pay By\nRole Type");
		Button Emp16 = new Button("Available\nEmployees");
		Button Emp17 = new Button("Employees\non Vacation");
		Button Emp18 = new Button("Employees\nInactive");
		
		Emp1.setOnAction(e->{BuildReports.BuildRep("Employee Sales Report", SQLEmpTicket, SQLEmpTicket2, "Ticket.TicketDate");});
		Emp1.setEffect(dropShadow);
		Emp1.setMinSize(150, 75);
		Emp1.setMaxSize(150, 75);
		Emp1.setPadding(new Insets(10, 20, 5, 20));
		Emp1.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #ffcc33; ");
		Emp2.setOnAction(e->{BuildReports.BuildRep2("Status Report", SQLEmpStatus, " ", "EmployeeStatus.DateOfStatus");});
		Emp2.setEffect(dropShadow);
		Emp2.setMinSize(150, 75);
		Emp2.setMaxSize(150, 75);
		Emp2.setPadding(new Insets(10, 20, 5, 20));
		Emp2.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #ffcc33; ");
		Emp3.setOnAction(e->{BuildReports.BuildRep2("Pay Report", SQLEmpPayStatus, " ", "PayStatus.PayDate");});
		Emp3.setEffect(dropShadow);
		Emp3.setMinSize(150, 75);
		Emp3.setMaxSize(150, 75);
		Emp3.setPadding(new Insets(10, 20, 5, 20));
		Emp3.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #00ced1; ");
		Emp4.setOnAction(e->{BuildReports.BuildRep3("Employee Info", SQLEmpGen, " ");});
		Emp4.setEffect(dropShadow);
		Emp4.setMinSize(150, 75);
		Emp4.setMaxSize(150, 75);
		Emp4.setPadding(new Insets(10, 20, 5, 20));
		Emp4.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #ffcc33; ");
		Emp5.setOnAction(e->{BuildReports.BuildRep3("Employee Schedules", SQLEmpSched, " ");});
		Emp5.setEffect(dropShadow);
		Emp5.setMinSize(150, 75);
		Emp5.setMaxSize(150, 75);
		Emp5.setPadding(new Insets(10, 20, 5, 20));
		Emp5.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #ffcc33; ");
		Emp6.setOnAction(e->{BuildReports.BuildRep3("Employee Bank Info", SQLEmpBankInfo, " ");});
		Emp6.setEffect(dropShadow);
		Emp6.setMinSize(150, 75);
		Emp6.setMaxSize(150, 75);
		Emp6.setPadding(new Insets(10, 20, 5, 20));
		Emp6.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #00ced1; ");
		Emp7.setOnAction(e->{BuildReports.BuildRep3("Employee Schedule, Detailed", SQLEmpSchedDetail, SQLEmpSchedDetail2);});
		Emp7.setEffect(dropShadow);
		Emp7.setMinSize(150, 75);
		Emp7.setMaxSize(150, 75);
		Emp7.setPadding(new Insets(10, 20, 5, 20));
		Emp7.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #ffcc33; ");
		Emp8.setOnAction(e->{BuildReports.BuildRep3("Employee Sales by Location", SQLEmpSalesLoc, " ");});
		Emp8.setEffect(dropShadow);
		Emp8.setMinSize(150, 75);
		Emp8.setMaxSize(150, 75);
		Emp8.setPadding(new Insets(10, 20, 5, 20));
		Emp8.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #ffcc33; ");
		Emp9.setOnAction(e->{BuildReports.BuildRep3("Employee Sales by Location Detail", SQLEmpSAlesLocDetail, " ");});
		Emp9.setEffect(dropShadow);
		Emp9.setMinSize(150, 75);
		Emp9.setMaxSize(150, 75);
		Emp9.setPadding(new Insets(10, 20, 5, 20));
		Emp9.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #ffcc33; ");
		Emp10.setOnAction(e->{BuildReports.BuildRep3("Base Pay By Location Address", SQLBasePayLoc, SQLBasePayLoc2);});
		Emp10.setEffect(dropShadow);
		Emp10.setMinSize(150, 75);
		Emp10.setMaxSize(150, 75);
		Emp10.setPadding(new Insets(10, 20, 5, 20));
		Emp10.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #b7d3e3; ");
		Emp11.setOnAction(e->{BuildReports.BuildRep3("Base Pay By Country", SQLBasePayCountry, SQLBasePayCountry2);});
		Emp11.setEffect(dropShadow);
		Emp11.setMinSize(150, 75);
		Emp11.setMaxSize(150, 75);
		Emp11.setPadding(new Insets(10, 20, 5, 20));
		Emp11.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #b7d3e3; ");
		Emp12.setOnAction(e->{BuildReports.BuildRep3("Base Pay By State", SQLBasePayState,SQLBasePayState2);});
		Emp12.setEffect(dropShadow);
		Emp12.setMinSize(150, 75);
		Emp12.setMaxSize(150, 75);
		Emp12.setPadding(new Insets(10, 20, 5, 20));
		Emp12.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #b7d3e3; ");
		Emp13.setOnAction(e->{BuildReports.BuildRep3("Base Pay By City", SQLBasePayCity, SQLBasePayCity2);});
		Emp13.setEffect(dropShadow);
		Emp13.setMinSize(150, 75);
		Emp13.setMaxSize(150, 75);
		Emp13.setPadding(new Insets(10, 20, 5, 20));
		Emp13.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #b7d3e3; ");
		Emp14.setOnAction(e->{BuildReports.BuildRep3("Base Pay By Building Type", SQLBasePayBuildingType, SQLBasePayBuildingType2);});
		Emp14.setEffect(dropShadow);
		Emp14.setMinSize(150, 75);
		Emp14.setMaxSize(150, 75);
		Emp14.setPadding(new Insets(10, 20, 5, 20));
		Emp14.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #b7d3e3; ");
		Emp15.setOnAction(e->{BuildReports.BuildRep3("Base Pay By Role Type", SQLBasePayRoleType, SQLBasePayRoleType2);});
		Emp15.setEffect(dropShadow);
		Emp15.setMinSize(150, 75);
		Emp15.setMaxSize(150, 75);
		Emp15.setPadding(new Insets(10, 20, 5, 20));
		Emp15.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #b7d3e3; ");
		Emp16.setOnAction(e->{BuildReports.BuildRep3("Available Employees", SQLEmpHired, SQLEmpHired2);});
		Emp16.setEffect(dropShadow);
		Emp16.setMinSize(150, 75);
		Emp16.setMaxSize(150, 75);
		Emp16.setPadding(new Insets(10, 20, 5, 20));
		Emp16.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #e2727b; ");
		Emp17.setOnAction(e->{BuildReports.BuildRep3("Employees on Vacation", SQLEmpVacation, SQLEmpVacation2);});
		Emp17.setEffect(dropShadow);
		Emp17.setMinSize(150, 75);
		Emp17.setMaxSize(150, 75);
		Emp17.setPadding(new Insets(10, 20, 5, 20));
		Emp17.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #e2727b; ");
		Emp18.setOnAction(e->{BuildReports.BuildRep3("Employees Inactive", SQLEmpFired, SQLEmpFired2);});
		Emp18.setEffect(dropShadow);
		Emp18.setMinSize(150, 75);
		Emp18.setMaxSize(150, 75);
		Emp18.setPadding(new Insets(10, 20, 5, 20));
		Emp18.setStyle("" 
				+ "-fx-font-size: 16px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #e2727b; ");
		
		flowbutts2.getChildren().addAll(Emp1,Emp2,Emp4,Emp5,Emp7,Emp8,Emp9, Emp16, Emp17, Emp18);
		flowbutts2a.getChildren().addAll(Emp3,Emp6,Emp10,Emp11,Emp12,Emp13,Emp14,Emp15);
		EmpLayout.setTop(EmpBox);
		EmpLayout.setCenter(EmpVBox);
		Scene SceneEmp = new Scene(EmpLayout, 950, 600);
		/*======================CUSTOMER/ETC SCENES ==============================
		 * 
		 * 
		 * 
		 * */
		BorderPane EtcLayout = new BorderPane();
		HBox EtcBox = new HBox();
		FlowPane flowbutts3 = new FlowPane();
		Label LabEtc = new Label("Other Reports");
		
		EtcLayout.setPadding(new Insets(30,30,30,30));
		EtcLayout.setStyle("-fx-background-color: #B09268");
		EtcBox.setSpacing(30);
		EtcBox.getChildren().addAll(LabEtc, BackButton3, Close3);
		
		flowbutts3.setHgap(20);
		flowbutts3.setVgap(20);
		
		LabEtc.setStyle("-fx-font-size: 40;");
		LabEtc.setPadding(new Insets(0, 30, 30, 30));
		
		Button Etc1 = new Button("Account Prefered\nDrinks");
		Button Etc2 = new Button("Account Prefered\nTypes");
		Button Etc3 = new Button("Account Prefered\nStates");
		Button Etc4 = new Button("Account Prefered\nLocation");
		Button Etc5 = new Button("Account Prefered\nLocation Specifics");
		Button Etc6 = new Button("Account To\nEmployee Report");
		Button Etc7 = new Button("???");
		Etc1.setOnAction(e->{BuildReports.BuildRep("Account Prefered Drinks", SQLActNumberDrinkName, SQLActNumberDrinkName2, "Ticket.TicketDate");});
		Etc1.setEffect(dropShadow);
		Etc1.setMinSize(150, 75);
		Etc1.setMaxSize(150, 75);
		Etc1.setPadding(new Insets(10, 20, 5, 20));
		Etc1.setStyle("" 
				+ "-fx-font-size: 12px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #ff7f50;"
				+ "-fx-font-weight: bold; ");
		Etc2.setOnAction(e->{BuildReports.BuildRep("Account Prefered Drink Types", SQLActNumberDrinkType, SQLActNumberDrinkType2, "Ticket.TicketDate");});
		Etc2.setEffect(dropShadow);
		Etc2.setMinSize(150, 75);
		Etc2.setMaxSize(150, 75);
		Etc2.setPadding(new Insets(10, 20, 5, 20));
		Etc2.setStyle("" 
				+ "-fx-font-size: 12px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #ff7f50;"
				+ "-fx-font-weight: bold; ");
		Etc3.setOnAction(e->{BuildReports.BuildRep("Account Prefered States to go to", SQLActNumberState, SQLActNumberState2, "Ticket.TicketDate");});
		Etc3.setEffect(dropShadow);
		Etc3.setMinSize(150, 75);
		Etc3.setMaxSize(150, 75);
		Etc3.setPadding(new Insets(10, 20, 5, 20));
		Etc3.setStyle("" 
				+ "-fx-font-size: 12px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #ff7f50;"
				+ "-fx-font-weight: bold; ");
		Etc4.setOnAction(e->{BuildReports.BuildRep("Account Prefered Locations", SQLActNumberLoc, SQLActNumberLoc2, "Ticket.TicketDate");});
		Etc4.setEffect(dropShadow);
		Etc4.setMinSize(150, 75);
		Etc4.setMaxSize(150, 75);
		Etc4.setPadding(new Insets(10, 20, 5, 20));
		Etc4.setStyle("" 
				+ "-fx-font-size: 12px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #ff7f50;"
				+ "-fx-font-weight: bold; ");
		Etc5.setOnAction(e->{BuildReports.BuildRep("Account Prefered Locations in Detail", SQLActNumberLocDetail, SQLActNumberLocDetail2, "Ticket.TicketDate");});
		Etc5.setEffect(dropShadow);
		Etc5.setMinSize(150, 75);
		Etc5.setMaxSize(150, 75);
		Etc5.setPadding(new Insets(10, 20, 5, 20));
		Etc5.setStyle("" 
				+ "-fx-font-size: 12px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #ff7f50;"
				+ "-fx-font-weight: bold; ");
		Etc6.setOnAction(e->{BuildReports.BuildRep("Account to Employee Report", SQLActNumberDrinkName, SQLActNumberDrinkName2, "Ticket.TicketDate");});
		Etc6.setEffect(dropShadow);
		Etc6.setMinSize(150, 75);
		Etc6.setMaxSize(150, 75);
		Etc6.setPadding(new Insets(10, 20, 5, 20));
		Etc6.setStyle("" 
				+ "-fx-font-size: 12px;" 
				+ "-fx-border-radius: 50; " 
				+ "-fx-background-radius: 50; "
				+ "-fx-background-color: #ff7f50;"
				+ "-fx-font-weight: bold; ");

		flowbutts3.getChildren().addAll(Etc1,Etc2,Etc3,Etc4,Etc5,Etc6);
		EtcLayout.setTop(EtcBox);
		EtcLayout.setCenter(flowbutts3);
		Scene SceneEtc = new Scene(EtcLayout, 950, 600);
		/*=============END BUILD =============================================*/
		
		ButtGenTick.setOnAction(e-> window.setScene(SceneTicket));
		ButtEmpRep.setOnAction(e-> window.setScene(SceneEmp));
		ButtLocRep.setOnAction(e->window.setScene(SceneEtc));
		
		
	}
	public static void GeneralReps() {

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
		Label RandomInfo = new Label("<-Select one of the options to view in the left column");
		RandomInfo.setStyle("-fx-font-size: 20;");
		
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
		
		//CSS to set everything 
				Left.setPadding(new Insets(0, 10, 0, 10));
				HBox Bottom = new HBox();
				Bottom.setPadding(new Insets(0, 10, 30, 30));
				Button Close = new Button("Close");
				Close.setOnAction(e-> window.close());
				Close.setPrefWidth(100);
				Close.setStyle("" 
							+ "-fx-font-size: 16px;"
							+ "-fx-background-radius:50; "
							+ "-fx-background-color: #ff6961 ");
				Bottom.getChildren().add(Close);
				layout.setBottom(Bottom);
		
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
