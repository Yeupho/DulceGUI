

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

public class AddEmployee {
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
	        
			 Scene EmpScene1 = new Scene(emp_Addemp, 700, 700);
			 window.setScene(EmpScene1);
			 window.showAndWait();
	    
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
