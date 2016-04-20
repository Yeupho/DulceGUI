package sample; 

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;

import java.util.*;

public class Doc {
	
	public static void display(String title){
		Stage window = new Stage();

		TabPane tp = new TabPane();
		tp.setStyle("-fx-background-color: #add8e6");
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	
		//tab 1
		Tab tab1 = new Tab("User Guide");
		VBox vb = new VBox();
		Label l1 = new Label("Welcome to the Dulce help system!");
		l1.setStyle("-fx-font-size: 20px");
		
		Label t1 = new Label("\n\n This section provides the user with additional resources\n"
				+ " to help them better understand the software.\n"
				+ " Please feel free to explore the list of options above\n\n"
				+ " 	• Getting Started\n"
				+ " 	• Features\n"
				+ " 	• Tips and Tricks\n"
				+ " 	• Contacts");
				
		t1.setStyle("-fx-font-size: 15px");
		
		vb.getChildren().addAll(l1, t1);
		vb.setPadding(new Insets(5));
		vb.setSpacing(20);
		vb.setAlignment(Pos.CENTER);
		tab1.setContent(vb);
		
		Image img1 = new Image("drinks.png");
        ImageView drink = new ImageView();
        drink.setImage(img1);
        vb.getChildren().add(drink);
		vb.setAlignment(Pos.CENTER);
		
		//tab 2
		Tab tab2 = new Tab("Getting Started");
		VBox vb2 = new VBox();
		Label l2 = new Label("A Basic Tutorial");
		l2.setStyle("-fx-font-size: 20px");
		
		Label t2 = new Label("\n\n  Requirements:\n"
				+ "• An authorized user.\n"
				+ "• A verified username and password.\n\n"
				+ "  1. The homepage displays available options for updating and viewing information.\n"
				+ "     Primarily, managing orders, employees, or location.\n"
				+ "  2. The File menu deals with file management and logging out.\n"
				+ "  3. The Help menu offers software information and user support.\n"
				+ "  4. Select an option by clicking the button associated with the desired task.\n"
				+ "  5. You can also add and view information using the drop-dowm menu at the top.\n"
				+ "     Both the buttons and the drop menu have the same functionality.\n"
				+ "  99. Once the user is finished. GO under File > Close to exit the application.");			
		t2.setStyle("-fx-font-size: 15px");
				
		vb2.getChildren().addAll(l2, t2);
		vb2.setPadding(new Insets(5));
		vb2.setSpacing(20);
		tab2.setContent(vb2);
		
        Image img2 = new Image("Menu.png");
        ImageView iv12 = new ImageView();
        iv12.setImage(img2);
        vb2.getChildren().add(iv12);
		vb2.setAlignment(Pos.TOP_CENTER);
		
		//tab 3
		Tab tab3 = new Tab("Features");
		VBox vb3 = new VBox();
		Label l3 = new Label("Key Platforms");
		l3.setStyle("-fx-font-size: 20px");
		
		Label t3 = new Label(" JavaFX includes:\n"
				+ " 	• Java APIs\n"
				+ " 	• Canvas API\n"
				+ " 	• FXML and Scene Builder\n"
				+ " 	• WebView\n"
				+ " 	• Built-in UI controls and CSS\n"
				+ " 	• Multitouch Support\n"
				+ " 	• 3D graphics features\n"
				+ " 	• Hardware-accelerated graphics pipeline\n"
				+ " 	• High-performance media engine\n"
				+ " 	• Self-contained application deployment model\n\n"
				+ " 			For more info: docs.oracle.com/javafx/\n\n"
				+ " Sql Server 2014 includes:\n"
				+ " 	• 2x Data compression\n"
				+ " 	• In-Memory OLTP Engine\n"
				+ " 	• AlwaysOn Enhancements\n"
				+ " 	• Buffer Pool Extension to SSD\n"
				+ " 	• Storage I/O control\n"
				+ " 	• I/O Resource Governor\n"
				+ " 	• Mirrored Backups\n"
				+ " 	• Backup encryption support\n"
				+ " 	• Query Store\n"
				+ " 	• Proactice Caching\n"
				+ " 	• Advanced data mining\n"
				+ " 	• Data quality services\n"
				+ " 	• Delayed durability\n"
				+ " 	• Incremental Statistics\n\n"
				+ " 			For more info: https://www.microsoft.com/en-us/server-cloud/products/sql-server/\n\n");
		t3.setStyle("-fx-font-size: 15px");
		

		vb3.getChildren().addAll(l3, t3);
		vb3.setPadding(new Insets(5));
		vb3.setSpacing(20);
		vb3.setAlignment(Pos.TOP_CENTER);
		tab3.setContent(vb3);
		
		//tab 4
		Tab tab4 = new Tab("Tips and Tricks");
		VBox vb4 = new VBox();
		Label l4 = new Label("Frequently Asked Questions");
		l4.setStyle("-fx-font-size: 20px");
		
		Label t4 = new Label("\n\n 1. What is Dulce GUI?\n"
				+ " 	• Dulce GUI is an graphical user interface designed to allow employees to easily manage information.\n\n"
				+ " 2. Do I have to be somewhat tech-savvy to use the software?\n"
				+ " 	• No, it is easy to learn.\n\n"
				+ " 3. Who is allowed to access the software?\n"
				+ " 	• Only authorized employees.\n\n"
				+ " 4. How do I login?\n"
				+ " 	• Enter your username and password in the correct fields.\n\n"
				+ " 5. How do I logout?\n"
				+ " 	• GO to File > Log Out.\n\n"
				+ " 6. Do I click the buttons or use the drop-down menu to add or view information?\n"
				+ " 	• Either one is perfectly fine.\n\n"
				+ " 7. Where do I get support?\n"
				+ " 	• GO to Help from the drop-down menu or seek assistance from a trained user.\n\n"
				+ " 8. What is the software's current version?\n"
				+ " 	• GO to Help > About.\n\n"
				+ " 9. How do I contact the developers?\n"
				+ " 	• GO to Help > Documentation > Contacts.\n\n"
				+ " 10. Why is the design so awesome?\n"
				+ " 	• Because we are teh best.\n\n");
		t4.setStyle("-fx-font-size: 15px");
						
		vb4.getChildren().addAll(l4, t4);
		vb4.setPadding(new Insets(5));
		vb4.setSpacing(10);
		vb4.setAlignment(Pos.TOP_CENTER);
		tab4.setContent(vb4);
		
		//tab 5
		Tab tab5 = new Tab("Contacts");
		VBox vb5 = new VBox();
		Label l5 = new Label("Team Solar personal contact info\n"
				+ "(Name, role, phone, and email):\n\n");
		l5.setStyle("-fx-font-size: 20px");
		
		Label t5 = new Label("Luke Nguyen (Project Manager):\n\n"
				+ "Taylor Tran (Consultant):\n\n"
				+ "Jose Reyes (System Analyst):\n\n"
				+ "Andrea Nguyen (System Analyst):\n\n"
				+ "Susan Nguyen (System Analyst):\n\n"
				+ "Dwayne Belcon (Programmer):\n\n"
				+ "Gia Nguyen (Programmer):		832-488-5617, 	giapet17@gmail.com");	 
		t5.setStyle("-fx-font-size: 15px");
						
		vb5.getChildren().addAll(l5, t5);
		vb5.setPadding(new Insets(5));
		vb5.setSpacing(20);
		vb5.setAlignment(Pos.TOP_CENTER);
		tab5.setContent(vb5);	
		
        Image img5 = new Image("device.jpg");
        ImageView pc = new ImageView();
        pc.setImage(img5);
        vb5.getChildren().add(pc);
		vb5.setAlignment(Pos.TOP_CENTER);
		
		//tab 6
		Tab tab6 = new Tab("Dev Build");
		VBox vb6 = new VBox();
		Label l6 = new Label("Patch Notes for v1.8.0");
		l6.setStyle("-fx-font-size: 20px");
		
		Label t6 = new Label("\n\n 	• New Interface design\n"
				+ " 	• Updated database configurations\n"
				+ " 	• Updated menu changes\n"
				+ " 	• Added functionality to reports\n"
				+ " 	• Added link between forms and SQL back-end\n"
				+ " 	• Fixed software to server connection\n"
				+ " 	• Fixed query logic\n"
				+ " 	• Updated table scripts\n"
				+ " 	• (Performance) Decreased server side lag\n"
				+ " 	• (Performance) Optimized client side framerate\n"
				+ " 	• Removed search function\n"
				+ " 	• Improved backup speeds\n"
				+ " 	• Improved security measures\n"
				+ " 	• Increased NaCl levels drastically\n"
				+ " 	• Added more overall epicness\n");
		t6.setStyle("-fx-font-size: 15px");		
						
		vb6.getChildren().addAll(l6, t6);
		vb6.setPadding(new Insets(5));
		vb6.setSpacing(20);
		vb6.setAlignment(Pos.TOP_LEFT);
		tab6.setContent(vb6);
		
        Image img6 = new Image("bandaid.png");
        ImageView patch = new ImageView();
        patch.setImage(img6);
        vb6.getChildren().add(patch);
		vb6.setAlignment(Pos.TOP_CENTER);
		
		tp.getTabs().addAll(tab1, tab2, tab3, tab4, tab5, tab6);
		
		//window dimensions 
		Scene scene = new Scene(tp);
		window.setTitle(title);
		window.setX(400);
		window.setY(300);
		window.setMinHeight(500);
		window.setMinWidth(600);
		window.setScene(scene);
		window.showAndWait();		
		
	}
}