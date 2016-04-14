package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditThings {
	static ComboBox<String> combobox;
	static ComboBox<String> dankbox;
	static ComboBox<String> junkbox;
	static int Test;
	public static void EditDate(String SQL){
		
	}
	public static void EmployeePage() {
		// Main Stage
		Stage window = new Stage();
		BorderPane Main = new BorderPane();
		Label TopText = new Label("Update Employees");
		
		//CSS Main 
		window.setTitle("Update Employees");
		window.initModality(Modality.APPLICATION_MODAL);
		Main.setStyle("-fx-background-color: #ffd773");
		TopText.setStyle("-fx-font-size: 40;");
		TopText.setPadding(new Insets(30, 30, 30, 30));
		
	
		// Center Section
		ScrollPane Scrolls = new ScrollPane();
		HBox SelectEm = new HBox();
		VBox CenterValue = new VBox();
		Scrolls.setContent(CenterValue);
		Scrolls.setPadding(new Insets(20, 20, 20, 20));
		Scrolls.setStyle("-fx-background-color: #ffd773");
		Scrolls.setContent(CenterValue);
		CenterValue.setPadding(new Insets(20, 20, 20, 20));

		// Left Section
		VBox LeftSide = new VBox();
		Button ButtEmpGenInfo = new Button("Employee Info");
		Button ButtEmpSched = new Button("Update Employee\nSchedule");
		Button ButtEmpBankInfo = new Button("Employee Bank\nInformation");
		Button ButtEmpSalary = new Button("Employee Salary");
		Button ButtDelEmp = new Button("Remove Employee");
		LeftSide.getChildren().addAll(ButtEmpGenInfo, ButtEmpSched, ButtEmpBankInfo, ButtEmpSalary, ButtDelEmp);

		// Set Sections
		Main.setTop(TopText);
		Main.setLeft(LeftSide);
		Main.setCenter(Scrolls);
		/*
		 * =================Button Employee General Info========================
		 */
		ButtEmpGenInfo.setOnAction(e -> {
			SelectEm.getChildren().clear();
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
		/* =================Button Employee Account======================== */
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
		
		//CSS Main 
				window.setTitle("Update Drinks");
				window.initModality(Modality.APPLICATION_MODAL);
				Main.setStyle("-fx-background-color: #ffd773");
				TopText.setStyle("-fx-font-size: 40;");
				TopText.setPadding(new Insets(30, 30, 30, 30));
		
		// Put class specific stuff here
		// Center Section
		ScrollPane Scrolls = new ScrollPane();
		HBox SelectEm = new HBox();
		VBox CenterValue = new VBox();
		
		//CSS Center
		Scrolls.setContent(CenterValue);
		Scrolls.setPadding(new Insets(20, 20, 20, 20));
		Scrolls.setStyle("-fx-background-color: #ffd773");
		Scrolls.setContent(CenterValue);
		CenterValue.setPadding(new Insets(20, 20, 20, 20));
		
		// Left Section
		VBox LeftSide = new VBox();
		Button ButtOrdGenInfo = new Button("Update Order");
		Button ButtDelORder = new Button("Remove Ingredient");
		LeftSide.getChildren().addAll(ButtOrdGenInfo, ButtDelORder);

		// Set Sections
		Main.setTop(TopText);
		Main.setLeft(LeftSide);
		Main.setCenter(Scrolls);

		ButtOrdGenInfo.setOnAction(e -> {
			
			CenterValue.getChildren().clear();
			SelectEm.getChildren().clear();
			dankbox = new ComboBox<>();
			Label FirstName = new Label("Ingredient Name");
			TextField InFirstName = new TextField();
			Label LastName = new Label("Ingredient Type");
			TextField InLastName = new TextField();
			Label Phone = new Label("Cost (Range $.00 to $.99)");
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

				Test = dankbox.getSelectionModel().getSelectedIndex();
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
					Commit.setOnAction(b->{
						if (InFirstName !=null && InLastName !=null && InPhone !=null){
							try {
								Connection cn = DBconnect.connect();
								String SQL1 = "UPDATE Drink "
										+ "SET DrinkName='"+First+"', DrinkTypeID='1',Cost='"+Phone1+"' "
										+ "WHERE DrinkID ='"+Test+"';";
								
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					});
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
		HBox SelectEm = new HBox();
		VBox CenterValue = new VBox();
		ScrollPane Scrolls = new ScrollPane();
		//CSS Main 
				window.setTitle("Update Locations");
				window.initModality(Modality.APPLICATION_MODAL);
				Main.setStyle("-fx-background-color: #ffd773");
				TopText.setStyle("-fx-font-size: 40;");
				TopText.setPadding(new Insets(30, 30, 30, 30));
				
		//CSS Center		
		Scrolls.setPadding(new Insets(20, 20, 20, 20));
		Scrolls.setStyle("-fx-background-color: #ffd773");
		Scrolls.setContent(CenterValue);
		CenterValue.setPadding(new Insets(20, 20, 20, 20));
		
		// Left Section
		VBox LeftSide = new VBox();
		Button ButtLocGenInfo = new Button("Update Location");
		Button ButtDelLoc = new Button("Remove Location");
		LeftSide.getChildren().addAll(ButtLocGenInfo,ButtDelLoc);

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
				String SQL = "SELECT * FROM Location"
						+ " WHERE Address <> '---' ;";
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
					Name = rs.getString(1) + " " + rs.getString(3);
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
/*=======================REMOVE LOCATION ==================================*/
		ButtDelLoc.setOnAction(e->{
			SelectEm.getChildren().clear();
			CenterValue.getChildren().clear();
			dankbox = new ComboBox<>();
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
					Name = rs.getString(1) + " " + rs.getString(3);
					dankbox.getItems().add(Name);
				}

			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			Button Update = new Button("Update");
			Button Commit = new Button("Remove Location");
			SelectEm.getChildren().addAll(dankbox, Update, Commit);
			CenterValue.getChildren().addAll(SelectEm);
			
			Commit.setOnAction(a->{
				Connection c;
				Test = dankbox.getSelectionModel().getSelectedIndex();
				Test++;
				try {
					c = DBconnect.connect();
					String SQL = "UPDATE Location"
							+ " SET Address = '---' "
							+ " WHERE LocationID = "+Test+ " ;";
					c.createStatement().executeUpdate(SQL);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			});
			
		});
/*========================SET SCENES =======================================*/
		// End of Class specific stuff
		Scene layout = new Scene(Main, 700, 400);
		window.setScene(layout);
		window.showAndWait();
	}
	
}
