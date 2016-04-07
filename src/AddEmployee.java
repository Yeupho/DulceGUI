

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddEmployee {
	static ComboBox<String> combobox;
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
	        Label Location = new Label("Location");
	        TextField Qa = new TextField();
	        Label Role = new Label("Role");
	        TextField Pa = new TextField();
	        OtherInfo.getChildren().addAll(Location, Qa, Role, Pa);
	        emp_Addemp.setTop(Text);
	        emp_Addemp.setLeft(VBoxAddEmp);
	        emp_Addemp.setCenter(OtherInfo);
	        
	        
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
        Label IngrCost = new Label("Ingredient Type");
        TextField Aa = new TextField();
        VBoxAddOrd.getChildren().addAll(
        		IngrName, 
        		Da, 
        		IngrType, 
        		combobox, 
        		IngrCost,
        		Aa);
        
        ord_Addord.setTop(Text);
        ord_Addord.setCenter(VBoxAddOrd);
        
        Scene OrdAddScene1 = new Scene(ord_Addord, 700, 700);
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
        Label IngrCost = new Label("Ingredient Type");
        TextField Aa = new TextField();
        VBoxAddOrd.getChildren().addAll(
        		IngrName, 
        		Da, 
        		IngrType, 
        		combobox, 
        		IngrCost,
        		Aa);
        
        ord_Addord.setTop(Text);
        ord_Addord.setCenter(VBoxAddOrd);
        
        Scene OrdAddScene1 = new Scene(ord_Addord, 700, 700);
		 window.setScene(OrdAddScene1);
		 window.showAndWait();
		
	}
}
