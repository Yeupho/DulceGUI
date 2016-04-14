package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddtoThings {
	// static final String JDBC_Driver =
	// "com.microsoft.sqlserver.jdbc.SQLServerDriver()";
	// static final String dbURL =
	// "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=DulceDatabase;integratedSecurity=true;";

	static ComboBox<String> combobox;
	static ComboBox<String> dankbox;
	static ComboBox<String> jukebox;
	static ComboBox<String> takebox;
	public static void display() {

		Stage window = new Stage();
		window.setTitle("Add Employee");
		window.initModality(Modality.APPLICATION_MODAL);

		BorderPane emp_Addemp = new BorderPane();
		emp_Addemp.setStyle("-fx-background-color: ffd773");
		VBox VBoxAddEmp = new VBox();
		VBoxAddEmp.setPadding(new Insets(20, 20, 20, 20));

		Label Text = new Label("Add Employee Information");
		Text.setStyle("-fx-font-size: 40;");
		Text.setPadding(new Insets(30, 30, 30, 30));

		Label EmployeeFName = new Label("First Name");
		TextField Da = new TextField();
		Label EmployeeLName = new Label("Last Name");
		TextField Ha = new TextField();
		Label Phone = new Label("Phone Number");
		TextField Ga = new TextField();
		Ga.setPromptText("xxx-xxx-xxxx");
		VBoxAddEmp.getChildren().addAll(EmployeeFName, Da, EmployeeLName, Ha, Phone, Ga);

		VBox OtherInfo = new VBox();
		OtherInfo.setPadding(new Insets(20, 20, 20, 20));
		Label Location = new Label("Work Location");

		dankbox = new ComboBox<>();
		dankbox.setPromptText("Select Work Address");
		// Connections with SQL server always have to be through try/catch
		// methods
		try {

			// This creates a connection to the
			Connection c = DBconnect.connect();
			String SQL = "SELECT Location.Address FROM Location "
					+ " WHERE Address <> '---' ;";
			ResultSet rs = c.createStatement().executeQuery(SQL);

			/*
			 * This loop goes through the column Address in Location table.
			 * -rs.next goes to the next iteration of the row; -Address =
			 * rs.getString(1) means the second column (java starts count at 0)
			 * gets put into the Address String. -dankbox (ComboBox) adds the
			 * Address string to its list.
			 */
			String Address = null;
			while (rs.next()) {
				Address = rs.getString(1);
				dankbox.getItems().add(Address);
			}

		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Label City = new Label("City");
		TextField Ca = new TextField();
		Label State = new Label("State");

		Label Zipcode = new Label("Zipcode");
		TextField Za = new TextField();
		Za.setMaxWidth(100);
		Label Role = new Label("Role");
		combobox = new ComboBox<>();
		combobox.setPromptText("Assign Role");
		try {
			String Address = null;

			Connection c = DBconnect.connect();
			String SQL = "SELECT Role.RoleName FROM Role;";
			ResultSet rs = c.createStatement().executeQuery(SQL);
			while (rs.next()) {
				Address = rs.getString(1);
				combobox.getItems().add(Address);
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Label labStart = new Label("Start Date");
		DatePicker SelectDate = new DatePicker();
		SelectDate.setOnAction(e -> {
			LocalDate date = SelectDate.getValue();
		});
		Label Notes = new Label("Notes about new Employee");
		TextField inNotes = new TextField();

		OtherInfo.getChildren().addAll(Location, dankbox, Role, combobox, labStart, SelectDate, Notes, inNotes);
		Button Submit = new Button("Submit");
		Submit.setStyle("-fx-background-color: #77dd77; -fx-font-size: 16;");
		Submit.setPadding(new Insets(20, 20, 20, 20));
		Button Cancel = new Button("Cancel");
		Cancel.setStyle("-fx-background-color: #ff6961; -fx-font-size: 16;");
		Cancel.setPadding(new Insets(20, 20, 20, 20));
		HBox BotPanel = new HBox();
		BotPanel.setSpacing(20);
		BotPanel.setPadding(new Insets(20, 20, 20, 20));
		BotPanel.getChildren().addAll(Submit, Cancel);
		emp_Addemp.setTop(Text);
		emp_Addemp.setLeft(VBoxAddEmp);
		emp_Addemp.setCenter(OtherInfo);
		emp_Addemp.setBottom(BotPanel);

		Submit.setOnAction(e -> {
			String est = null;
			int LocationID;
			LocationID = 1 + dankbox.getSelectionModel().getSelectedIndex();
			int RoleID;
			RoleID = 1 + combobox.getSelectionModel().getSelectedIndex();
			String Test = null;
			
			
			if (Da.getText() != est && Ha.getText() != est && Ga.getText() != est) {
				try {

					Connection c1 = DBconnect.connect();
					// c1 = DriverManager.getConnection(dbURL);
					Statement stmt = c1.createStatement();
					
					
					String dank = Test;
					String AddEmp = "INSERT INTO Employee( FirstName, LastName, EmployeePhone, LocationID, RoleID) "
							+ "VALUES ('" + Da.getText() + "' , '" + Ha.getText() + "', '" + Ga.getText() + "" + "','"
							+ LocationID + "','" + RoleID + "')";
					stmt.executeUpdate(AddEmp);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				System.out.print("Nothing was executed.");
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			window.close();
		});
		Cancel.setOnAction(e -> {

			window.close();
		});
		Scene EmpScene1 = new Scene(emp_Addemp, 600, 600);
		window.setScene(EmpScene1);
		window.showAndWait();
		/*
		 * try{ DriverManager.registerDriver(new
		 * com.microsoft.sqlserver.jdbc.SQLServerDriver());
		 * 
		 * String url =
		 * "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=DulceDatabase;integratedSecurity=true;";
		 * 
		 * Connection con = DriverManager.getConnection(url); Statement stmt =
		 * con.createStatement(); String SQL =
		 * "INSERT INTO CITY (CityName) VALUES ('"+Da.getText()+"'";
		 * 
		 * //ResultSet rs = stmt.executeQuery(SQL); //rs.next(); //String first
		 * = rs.getString("CityName"); System.out.println(
		 * "Connection Success, PARTY!");
		 * 
		 * } catch(SQLException ex){ ex.printStackTrace(); }
		 */

	}

	public static void displayOrder() {
		Stage window = new Stage();
		window.setTitle("Add Ingredient");
		window.initModality(Modality.APPLICATION_MODAL);
		BorderPane ord_Addord = new BorderPane();
		ord_Addord.setStyle("-fx-background-color: ffd773");
		VBox VBoxAddOrd = new VBox();
		VBoxAddOrd.setPadding(new Insets(20, 20, 20, 20));

		Label Text = new Label("Add New Ingredient");
		Text.setStyle("-fx-font-size: 40;");
		Text.setPadding(new Insets(30, 30, 30, 30));
		Label IngrName = new Label("Ingredient Name");
		TextField Da = new TextField();

		Label IngrType = new Label("Ingredient Type");
		combobox = new ComboBox<>();
		combobox.setPromptText("Select Type");
		try {
			String DrinkName = null;

			Connection c = DBconnect.connect();
			String SQL = "SELECT DrinkType.TypeName FROM DrinkType;";
			ResultSet rs = c.createStatement().executeQuery(SQL);
			while (rs.next()) {
				DrinkName = rs.getString(1);
				combobox.getItems().add(DrinkName);
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Label IngrCost = new Label("Cost: ($0.00 to $1.99)");
		TextField Aa = new TextField();
		Aa.setMaxWidth(50);
		VBoxAddOrd.getChildren().addAll(IngrName, Da, IngrType, combobox, IngrCost, Aa);
		Button Submit = new Button("Submit");
		Submit.setStyle("-fx-background-color: #77dd77; -fx-font-size: 16;");
		Submit.setPadding(new Insets(20, 20, 20, 20));
		Button Cancel = new Button("Cancel");
		Cancel.setStyle("-fx-background-color: #ff6961; -fx-font-size: 16;");
		Cancel.setPadding(new Insets(20, 20, 20, 20));
		HBox BotPanel = new HBox();
		BotPanel.setSpacing(20);
		BotPanel.setPadding(new Insets(20, 20, 20, 20));
		BotPanel.getChildren().addAll(Submit, Cancel);
		ord_Addord.setTop(Text);
		ord_Addord.setCenter(VBoxAddOrd);
		ord_Addord.setBottom(BotPanel);

		Submit.setOnAction(e -> {
			String est = null;
			int xa;
			xa = 1 + combobox.getSelectionModel().getSelectedIndex();
			if (Da.getText() != est && combobox.getSelectionModel().getSelectedItem() != est && Aa.getText() != est) {
				try {

					Connection c1 = DBconnect.connect();
					// c1 = DriverManager.getConnection(dbURL);
					Statement stmt = c1.createStatement();

					String AddOrd = "INSERT INTO Drink(DrinkName, DrinkTypeID, Cost) " + "VALUES ('" + Da.getText()
							+ "' , '" + xa + "', '" + Aa.getText() + "')";
					stmt.executeUpdate(AddOrd);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				System.out.print("Nothing was executed.");
			}
		
		});
		Cancel.setOnAction(e -> {

			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			window.close();
		});

		Scene OrdAddScene1 = new Scene(ord_Addord, 500, 500);
		window.setScene(OrdAddScene1);
		window.showAndWait();

	}

	public static void displayLocation() {
		Stage window = new Stage();
		window.setTitle("Add Location");
		window.initModality(Modality.APPLICATION_MODAL);
		BorderPane ord_Addord = new BorderPane();
		ord_Addord.setStyle("-fx-background-color: ffd773");
		VBox VBoxAddOrd = new VBox();
		VBoxAddOrd.setPadding(new Insets(20, 20, 20, 20));

		Label Text = new Label("Add New Location");
		Text.setStyle("-fx-font-size: 40;");
		Text.setPadding(new Insets(30, 30, 30, 30));
		UUID u1 = UUID.randomUUID();
		Label LocID = new Label("Building ID: " + u1);
		Label space = new Label(" ");
		Label LocAddress = new Label("Location Address");
		TextField Da = new TextField();
		Label MonRent = new Label("Monthly Rent");
		TextField InMonRent = new TextField();
		Label City = new Label("City");
		TextField Ca = new TextField();
		jukebox = new ComboBox<>();
		try {
			String ListCountry = null;

			Connection c = DBconnect.connect();
			String SQL = "SELECT CityName FROM City;";
			ResultSet rs = c.createStatement().executeQuery(SQL);
			while (rs.next()) {
				ListCountry = rs.getString(1);
				jukebox.getItems().add(ListCountry);
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Label Country = new Label("Country");
		combobox = new ComboBox<>();
		try {
			String ListCountry = null;

			Connection c = DBconnect.connect();
			String SQL = "SELECT CountryName FROM COUNTRY;";
			ResultSet rs = c.createStatement().executeQuery(SQL);
			while (rs.next()) {
				ListCountry = rs.getString(1);
				combobox.getItems().add(ListCountry);
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Label State = new Label("State");
		dankbox = new ComboBox<>();
		try {
			String ListStates = null;

			Connection c = DBconnect.connect();
			String SQL = "SELECT StateName FROM State;";
			ResultSet rs = c.createStatement().executeQuery(SQL);
			while (rs.next()) {
				ListStates = rs.getString(1);
				dankbox.getItems().add(ListStates);
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Label LabZip = new Label("Zipcode");
		TextField InZip = new TextField();
		InZip.setMaxWidth(100);
		Label LabBuildType = new Label("Building Type: ");
		takebox = new ComboBox<>();
		try {
			String ListStates = null;

			Connection c = DBconnect.connect();
			String SQL = "SELECT TypeDesc FROM BuildingType;";
			ResultSet rs = c.createStatement().executeQuery(SQL);
			while (rs.next()) {
				ListStates = rs.getString(1);
				takebox.getItems().add(ListStates);
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		VBoxAddOrd.getChildren().addAll(LocID, space, LocAddress, Da, MonRent, InMonRent, City, jukebox, State, dankbox,Country, combobox,
				LabZip, InZip, LabBuildType, takebox);

		Button Submit = new Button("Submit");
		Submit.setStyle("-fx-background-color: #77dd77; -fx-font-size: 16;");
		Submit.setPadding(new Insets(20, 20, 20, 20));
		Button Cancel = new Button("Cancel");
		Cancel.setStyle("-fx-background-color: #ff6961; -fx-font-size: 16;");
		Cancel.setPadding(new Insets(20, 20, 20, 20));

		HBox BotPanel = new HBox();
		BotPanel.setSpacing(20);
		BotPanel.setPadding(new Insets(20, 20, 20, 20));
		BotPanel.getChildren().addAll(Submit, Cancel);
		ord_Addord.setTop(Text);
		ord_Addord.setCenter(VBoxAddOrd);
		ord_Addord.setBottom(BotPanel);

		Submit.setOnAction(e -> {
			String est = null;
			int x = 1+combobox.getSelectionModel().getSelectedIndex();
			int y = 1+dankbox.getSelectionModel().getSelectedIndex();
			int z = 1+jukebox.getSelectionModel().getSelectedIndex();
			int w = 1+takebox.getSelectionModel().getSelectedIndex();
			int k = Integer.parseInt(InZip.getText());
			// Work in Progress
			if (Da.getText() != est && Ca.getText() !=est && combobox.getSelectionModel().getSelectedItem() != est
					&& InZip.getText() != est) {
				try {

					Connection c1 = DBconnect.connect();
					// c1 = DriverManager.getConnection(dbURL);
					Statement stmt = c1.createStatement();
					String ViewLoc = "SELECT Location.LocationID FROM Location;";
					ResultSet rs = stmt.executeQuery(ViewLoc);
					int Test = 0;
					while (rs.next()){
						Test = rs.getInt(1);
					}
					Test++;
					
					String AddOrd = "INSERT INTO Location(LocationID, BuildingName, Address, RentCost, CityCode, "
							+ "CountryID, StateID, TypeID, ZipCodeID) " 
							+ "VALUES ('" +Test+"','" +u1+"','" +Da.getText()+ "','"+InMonRent.getText()+"','"+z+"','" +x+ "','"+y+ "','"+w+"','"+k+"');";
					stmt.executeUpdate(AddOrd);

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				System.out.print("Nothing was executed.");
			}
		});
		Cancel.setOnAction(e -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			window.close();
		});

		Scene OrdAddScene1 = new Scene(ord_Addord, 500, 600);
		window.setScene(OrdAddScene1);
		window.showAndWait();

	}

}