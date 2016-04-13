package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EditThings {
	static ComboBox<String> combobox;
	static ComboBox<String> dankbox;
	static ComboBox<String> junkbox;

	public static void EmployeePage() {
		// Main Stage
		Stage window = new Stage();
		BorderPane Main = new BorderPane();
		Label TopText = new Label("Update Employees");

		// Center Section
		ScrollPane Scrolls = new ScrollPane();
		HBox SelectEm = new HBox();
		VBox CenterValue = new VBox();
		Scrolls.setContent(CenterValue);

		// Left Section
		VBox LeftSide = new VBox();
		Button ButtEmpGenInfo = new Button("Employee Info");
		Button ButtEmpSched = new Button("Update Employee\nSchedule");
		Button ButtEmpBankInfo = new Button("Employee Bank\nInformation");
		Button ButtEmpSalary = new Button("Employee Salary");
		LeftSide.getChildren().addAll(ButtEmpGenInfo, ButtEmpSched, ButtEmpBankInfo, ButtEmpSalary);

		// Set Sections
		Main.setTop(TopText);
		Main.setLeft(LeftSide);
		Main.setCenter(Scrolls);
/*=================Button Employee General Info========================*/
		ButtEmpGenInfo.setOnAction(e -> {

			CenterValue.getChildren().clear();
			dankbox = new ComboBox<>();
			Label FirstName = new Label("First Name");
			TextField InFirstName = new TextField();
			Label LastName = new Label("Last Name");
			TextField InLastName = new TextField();
			Label Phone = new Label("Phone Number");
			TextField InPhone = new TextField();
			try {

				// This creates a connection to the
				Connection c = DBconnect.connect();
				String SQL = "SELECT Employee.FirstName, Employee.LastName FROM Employee;";
				ResultSet rs = c.createStatement().executeQuery(SQL);

				/*
				 * This loop goes through the column Address in Location table.
				 * -rs.next goes to the next iteration of the row; -Address =
				 * rs.getString(1) means the second column (java starts count at
				 * 0) gets put into the Address String. -dankbox (ComboBox) adds
				 * the Address string to its list.
				 */
				String Name = null;
				while (rs.next()) {
					Name = rs.getString(1) + " " + rs.getString(2);
					dankbox.getItems().add(Name);
				}

			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Button Update = new Button("Update");
			Button Commit = new Button("Commit Changes");
			SelectEm.getChildren().addAll(dankbox, Update, Commit);
			dankbox.getSelectionModel().getSelectedItem();

			CenterValue.getChildren().addAll(SelectEm, FirstName, InFirstName, LastName, InLastName, Phone, InPhone);

			Update.setOnAction(a -> {

				int Test = dankbox.getSelectionModel().getSelectedIndex();
				Test++;
				try {
					String First;
					String Last;
					String Phone1;
					// This creates a connection to the
					Connection c = DBconnect.connect();
					String SQL = "SELECT * FROM Employee " + "WHERE EmployeeID =" + Test + ";";
					ResultSet rs = c.createStatement().executeQuery(SQL);
					rs.next();
					First = rs.getString(2);
					Last = rs.getString(3);
					Phone1 = rs.getString(4);
					InFirstName.setText(First);
					InLastName.setText(Last);
					InPhone.setText(Phone1);

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.out.println(Test);

			});
		});
/*=================Button Employee Account========================*/		
		ButtEmpBankInfo.setOnAction(e -> {

			CenterValue.getChildren().clear();
			dankbox = new ComboBox<>();
			Label FirstName = new Label("First Name");
			TextField InFirstName = new TextField();
			Label LastName = new Label("Last Name");
			TextField InLastName = new TextField();
			Label Phone = new Label("Phone Number");
			TextField InPhone = new TextField();
			try {

				// This creates a connection to the
				Connection c = DBconnect.connect();
				String SQL = "SELECT Employee.FirstName, Employee.LastName FROM Employee;";
				ResultSet rs = c.createStatement().executeQuery(SQL);

				/*
				 * This loop goes through the column Address in Location table.
				 * -rs.next goes to the next iteration of the row; -Address =
				 * rs.getString(1) means the second column (java starts count at
				 * 0) gets put into the Address String. -dankbox (ComboBox) adds
				 * the Address string to its list.
				 */
				String Name = null;
				while (rs.next()) {
					Name = rs.getString(1) + " " + rs.getString(2);
					dankbox.getItems().add(Name);
				}

			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Button Update = new Button("Update");
			Button Commit = new Button("Commit Changes");
			SelectEm.getChildren().addAll(dankbox, Update, Commit);
			CenterValue.getChildren().add(SelectEm);
			
			
		});
		Scene layout = new Scene(Main, 700, 400);
		window.setScene(layout);
		window.showAndWait();

	}

	/*
	 * ======================ORDER PAGE ========================================
	 */
	public static void OrderPage() {
		Stage window = new Stage();

		BorderPane Main = new BorderPane();
		Label TopText = new Label("Update Drinks");
		// Put class specific stuff here
		// Center Section
		ScrollPane Scrolls = new ScrollPane();
		HBox SelectEm = new HBox();
		VBox CenterValue = new VBox();
		Scrolls.setContent(CenterValue);

		// Left Section
		VBox LeftSide = new VBox();
		Button ButtOrdGenInfo = new Button("Update Order");
		LeftSide.getChildren().addAll(ButtOrdGenInfo);

		// Set Sections
		Main.setTop(TopText);
		Main.setLeft(LeftSide);
		Main.setCenter(Scrolls);

		ButtOrdGenInfo.setOnAction(e -> {

			CenterValue.getChildren().clear();
			dankbox = new ComboBox<>();
			Label FirstName = new Label("First Name");
			TextField InFirstName = new TextField();
			Label LastName = new Label("Last Name");
			TextField InLastName = new TextField();
			Label Phone = new Label("Phone Number");
			TextField InPhone = new TextField();
			try {

				// This creates a connection to the
				Connection c = DBconnect.connect();
				String SQL = "SELECT Drink.DrinkName, DrinkType.TypeName, Drink.Cost FROM Drink"
						+ " FULL OUTER JOIN DrinkType ON Drink.DrinkTypeID = DrinkType.DrinkTypeID;";
				ResultSet rs = c.createStatement().executeQuery(SQL);

				/*
				 * This loop goes through the column Address in Location table.
				 * -rs.next goes to the next iteration of the row; -Address =
				 * rs.getString(1) means the second column (java starts count at
				 * 0) gets put into the Address String. -dankbox (ComboBox) adds
				 * the Address string to its list.
				 */
				String Name = null;
				while (rs.next()) {
					Name = rs.getString(1) + " " + rs.getString(2);
					dankbox.getItems().add(Name);
				}

			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Button Update = new Button("Update");
			Button Commit = new Button("Commit Changes");
			SelectEm.getChildren().addAll(dankbox, Update, Commit);
			dankbox.getSelectionModel().getSelectedItem();

			CenterValue.getChildren().addAll(SelectEm, FirstName, InFirstName, LastName, InLastName, Phone, InPhone);

			Update.setOnAction(a -> {

				int Test = dankbox.getSelectionModel().getSelectedIndex();
				Test++;
				try {
					String First;
					String Last;
					String Phone1;
					// This creates a connection to the
					Connection c = DBconnect.connect();
					String SQL = "SELECT Drink.DrinkName, DrinkType.TypeName, Drink.Cost FROM Drink"
							+ " FULL OUTER JOIN DrinkType ON Drink.DrinkTypeID = DrinkType.DrinkTypeID "
							+ "WHERE DrinkID =" + Test + ";";
					ResultSet rs = c.createStatement().executeQuery(SQL);
					rs.next();
					First = rs.getString(1);
					Last = rs.getString(2);
					Phone1 = rs.getString(3);
					InFirstName.setText(First);
					InLastName.setText(Last);
					InPhone.setText(Phone1);

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.out.println(Test);

			});
		});

		// End of Class specific stuff
		Scene layout = new Scene(Main, 700, 400);
		window.setScene(layout);
		window.showAndWait();
	}

	/*
	 * =======================LOCATION PAGE =================================
	 */
	public static void LocationPage() {
		Stage window = new Stage();

		BorderPane Main = new BorderPane();
		Label TopText = new Label("Update Locations");
		// Put class specific stuff here
		// Center Section
		ScrollPane Scrolls = new ScrollPane();
		HBox SelectEm = new HBox();
		VBox CenterValue = new VBox();
		Scrolls.setContent(CenterValue);

		// Left Section
		VBox LeftSide = new VBox();
		Button ButtLocGenInfo = new Button("Update Location");
		LeftSide.getChildren().addAll(ButtLocGenInfo);

		// Set Sections
		Main.setTop(TopText);
		Main.setLeft(LeftSide);
		Main.setCenter(Scrolls);

		ButtLocGenInfo.setOnAction(e -> {
			SelectEm.getChildren().clear();
			CenterValue.getChildren().clear();
			dankbox = new ComboBox<>();

			Label BuildingName = new Label("Building Name: ");
			Label FirstName = new Label("Address");
			TextField InFirstName = new TextField();
			Label LastName = new Label("Rent Cost");
			TextField InLastName = new TextField();
			Label Phone = new Label("City");
			TextField InPhone = new TextField();
			try {

				// This creates a connection to the
				Connection c = DBconnect.connect();
				String SQL = "SELECT * FROM Location;";
				ResultSet rs = c.createStatement().executeQuery(SQL);

				/*
				 * This loop goes through the column Address in Location table.
				 * -rs.next goes to the next iteration of the row; -Address =
				 * rs.getString(1) means the second column (java starts count at
				 * 0) gets put into the Address String. -dankbox (ComboBox) adds
				 * the Address string to its list.
				 */
				String Name = null;
				while (rs.next()) {
					Name = rs.getString(2) + " " + rs.getString(3);
					dankbox.getItems().add(Name);
				}

			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Button Update = new Button("Update");
			Button Commit = new Button("Commit Changes");
			SelectEm.getChildren().addAll(dankbox, Update, Commit);
			dankbox.getSelectionModel().getSelectedItem();

			CenterValue.getChildren().addAll(SelectEm, BuildingName, FirstName, InFirstName, LastName, InLastName,
					Phone, InPhone);

			Update.setOnAction(a -> {

				int Test = dankbox.getSelectionModel().getSelectedIndex();
				Test++;
				try {
					String First;
					String Last;
					String Phone1;
					String BD;
					// This creates a connection to the
					Connection c = DBconnect.connect();
					String SQL = "SELECT * FROM Location " + "WHERE LocationID =" + Test + ";";
					ResultSet rs = c.createStatement().executeQuery(SQL);
					rs.next();
					BD = rs.getString(2);
					First = rs.getString(3);
					Last = rs.getString(4);
					Phone1 = rs.getString(5);
					BuildingName.setText("Building Name: " + BD);
					InFirstName.setText(First);
					InLastName.setText(Last);
					InPhone.setText(Phone1);

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.out.println(Test);

			});
		});

		// End of Class specific stuff
		Scene layout = new Scene(Main, 700, 400);
		window.setScene(layout);
		window.showAndWait();
	}

}
