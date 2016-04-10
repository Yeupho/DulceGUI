package sample;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddEmployee  {
	static final String JDBC_Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver()";
	static final String dbURL = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=DulceDatabase;integratedSecurity=true;";
	
	static ComboBox<String> combobox;
	static ComboBox<String>	dankbox;
	public static void display(){

		
		
		
		
		Stage window = new Stage();
		window.setTitle("Add Employee");
		window.initModality(Modality.APPLICATION_MODAL);
		
		
		 
		BorderPane emp_Addemp = new BorderPane();
		 VBox VBoxAddEmp = new VBox();
	     VBoxAddEmp.setPadding(new Insets(20,20,20,20));
		 		 
		 	Label Text = new Label("Add Employee Information");
		 	Text.setStyle("-fx-font-size: 40;");
		 	Text.setPadding(new Insets(30,30,30,30));
		 	
	        
	        Label EmployeeFName = new Label("First Name");
	        TextField Da = new TextField();
	        Label EmployeeLName = new Label("Last Name");
	        TextField Ha = new TextField();
	        Label Phone = new Label("Phone Number");
	        TextField Ga = new TextField();
	        Ga.setPromptText("xxx-xxx-xxxx");
	        VBoxAddEmp.getChildren().addAll(EmployeeFName, Da, EmployeeLName, Ha, Phone, Ga);
	        
	     VBox OtherInfo = new VBox();
	     OtherInfo.setPadding(new Insets(20,20,20,20));
	        Label Location = new Label("Address");
	        TextField Qa = new TextField();
	        Label City = new Label("City");
	        TextField Ca = new TextField();
	        Label State = new Label("State");
	        dankbox = new ComboBox<>();
	        dankbox.getItems().addAll(
	        		"TX",
	        		"AL",
	        		"AK",
	        		"AZ",
	        		"AR",
	        		"CA",
	        		"CO",
	        		"CT",
	        		"DE",
	        		"FL",
	        		"GA",
	        		"HI",
	        		"ID",
	        		"IL",
	        		"IN",
	        		"IA",
	        		"KS",
	        		"KY",
	        		"LA",
	        		"ME",
	        		"MD",
	        		"MA",
	        		"MI",
	        		"MN",
	        		"MS",
	        		"MO",
	        		"MT",
	        		"NE",
	        		"NV",
	        		"NH",
	        		"NJ",
	        		"NM",
	        		"NY",
	        		"NC",
	        		"ND",
	        		"OH",
	        		"OK",
	        		"OR",
	        		"PA",
	        		"RI",
	        		"SC",
	        		"SD",
	        		"TN"
	        		);
	        Label Zipcode = new Label("Zipcode");
	        TextField Za = new TextField();
	        Za.setMaxWidth(100);
	        Label Role = new Label("Role");
	        combobox = new ComboBox<>();
	        combobox.getItems().addAll("Cashier", "Barista", "Manager", "Janitor");
	        
	        OtherInfo.getChildren().addAll(Location, Qa, City, Ca, State, dankbox, Zipcode, Za, Role, combobox);
	        Button Submit = new Button("Submit");
	        Button Cancel = new Button("Cancel");
	        HBox BotPanel = new HBox();
	        BotPanel.getChildren().addAll(Submit, Cancel);
	        emp_Addemp.setTop(Text);
	        emp_Addemp.setLeft(VBoxAddEmp);
	        emp_Addemp.setCenter(OtherInfo);
	        emp_Addemp.setBottom(BotPanel);
	        
	        
	        
	        Submit.setOnAction(e-> {
	        	String est = null;
	        	if(Da.getText() != est && Ha.getText() != est && Ga.getText() != est ){
	        		try {
	        			
	        			Connection c1= DBconnect.connect();
	        			c1 = DriverManager.getConnection(dbURL);
	        			Statement stmt = c1.createStatement();
	        			String SQL = "SELECT COUNT(*) FROM Employee; ";
	        			ResultSet rs = stmt.executeQuery(SQL);
	        			rs.next();
	        			int rowC = rs.getInt(1);
	        			rowC++;
	        			String AddEmp = "INSERT INTO Employee(EmployeeID, FirstName, LastName, EmployeePhone) "
	        					+ "VALUES ('"  +rowC+ "' , '"+ Da.getText()+ "' , '"+Ha.getText()+ "', '"+Ga.getText()+"')";
	        			stmt.executeUpdate(AddEmp);
	        			
	        			
	        		} catch (SQLException e1) {
	        			// TODO Auto-generated catch block
	        			e1.printStackTrace();
	        		}
	        	}
	        	else {
	        		System.out.print("Nothing was executed.");
	        	}
	        });
	        
			 Scene EmpScene1 = new Scene(emp_Addemp, 700, 700);
			 window.setScene(EmpScene1);
			 window.showAndWait();
			 /*try{
		    		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

					String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=DulceDatabase;integratedSecurity=true;";
				
					Connection con = DriverManager.getConnection(url);
					Statement stmt = con.createStatement();
					String SQL = "INSERT INTO CITY (CityName) VALUES ('"+Da.getText()+"'";
				
					//ResultSet rs = stmt.executeQuery(SQL);
					//rs.next();
					//String first = rs.getString("CityName");
					System.out.println("Connection Success, PARTY!");
					
			 	}
				catch(SQLException ex){
					ex.printStackTrace();
				}*/
			 
	}
	public static void displayOrder(){
		Stage window = new Stage();
		window.setTitle("Add Ingredient");
		window.initModality(Modality.APPLICATION_MODAL);
		BorderPane ord_Addord = new BorderPane();
		 VBox VBoxAddOrd = new VBox();
	     VBoxAddOrd.setPadding(new Insets(20,20,20,20));
		 		 
		Label Text = new Label("Add New Ingredient");
		Text.setStyle("-fx-font-size: 40;");
		Text.setPadding(new Insets(30,30,30,30));
		Label IngrName = new Label("Ingredient Name");
        TextField Da = new TextField();
        
        Label IngrType = new Label("Ingredient Type");
        combobox = new ComboBox<>();
        combobox.getItems().addAll(
        		"Cold Cafe", 
        		"Frios",
        		"Hot Cafe", 
        		"Juiced Tea",      		
        		"Milk Tea",
        		"Smoothies",
        		"Soda");
        Label IngrCost = new Label("Cost: $");
        TextField Aa = new TextField();
        Aa.setMaxWidth(50);
        VBoxAddOrd.getChildren().addAll(
        		IngrName, 
        		Da, 
        		IngrType, 
        		combobox, 
        		IngrCost,
        		Aa);
        Button Submit = new Button("Submit");
        Button Cancel = new Button("Cancel");
        HBox BotPanel = new HBox();
        BotPanel.getChildren().addAll(Submit, Cancel);
        ord_Addord.setTop(Text);
        ord_Addord.setCenter(VBoxAddOrd);
        ord_Addord.setBottom(BotPanel);
        Scene OrdAddScene1 = new Scene(ord_Addord, 500, 350);
		 window.setScene(OrdAddScene1);
		 window.showAndWait();
		
	}
	public static void displayLocation(){
		Stage window = new Stage();
		window.setTitle("Add Ingredient");
		window.initModality(Modality.APPLICATION_MODAL);
		BorderPane ord_Addord = new BorderPane();
		 VBox VBoxAddOrd = new VBox();
	     VBoxAddOrd.setPadding(new Insets(20,20,20,20));
		 		 
		Label Text = new Label("Add New Location");
		Text.setStyle("-fx-font-size: 40;");
		Text.setPadding(new Insets(30,30,30,30));
		Label LocID = new Label("Location ID: " + "11W3GENR850M3T1NG");
		Label space = new Label(" ");
        Label LocAddress = new Label("Location Address");
        TextField Da = new TextField();
        Label City = new Label("City");
        TextField Ca = new TextField();
        Label Country = new Label("Country");
        combobox = new ComboBox<>();
        combobox.getItems().addAll(
        		"Cold Cafe", 
        		"Frios",
        		"Hot Cafe", 
        		"Juiced Tea",      		
        		"Milk Tea",
        		"Smoothies",
        		"Soda");
        Label State = new Label("State");
        dankbox = new ComboBox<>();
        dankbox.getItems().addAll(
        		"TX",
        		"AL",
        		"AK",
        		"AZ",
        		"AR",
        		"CA",
        		"CO",
        		"CT",
        		"DE",
        		"FL",
        		"GA",
        		"HI",
        		"ID",
        		"IL",
        		"IN",
        		"IA",
        		"KS",
        		"KY",
        		"LA",
        		"ME",
        		"MD",
        		"MA",
        		"MI",
        		"MN",
        		"MS",
        		"MO",
        		"MT",
        		"NE",
        		"NV",
        		"NH",
        		"NJ",
        		"NM",
        		"NY",
        		"NC",
        		"ND",
        		"OH",
        		"OK",
        		"OR",
        		"PA",
        		"RI",
        		"SC",
        		"SD",
        		"TN"
        		);

        Label IngrCost = new Label("Zipcode");
        TextField Aa = new TextField();
        VBoxAddOrd.getChildren().addAll(
        		LocID,
        		space,
        		LocAddress,
        		Da, 
        		City,
        		Ca,
        		Country, 
        		combobox,
        		State,
        		dankbox,
        		IngrCost,
        		Aa);
        Button Submit = new Button("Submit");
        Button Cancel = new Button("Cancel");
        HBox BotPanel = new HBox();
        BotPanel.getChildren().addAll(Submit, Cancel);
        ord_Addord.setTop(Text);
        ord_Addord.setCenter(VBoxAddOrd);
        ord_Addord.setBottom(BotPanel);
        Scene OrdAddScene1 = new Scene(ord_Addord, 500, 500);
		 window.setScene(OrdAddScene1);
		 window.showAndWait();
		
	}
	
}