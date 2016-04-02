/*Link to MENU GUI design here:
 * http://www.pdf-archive.com/2016/04/02/drawing1/drawing1.pdf
 * Is a rough draft though, not final. */

import com.sun.javafx.geom.Rectangle;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application{
	Stage window;
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		window = primaryStage;
		window.setTitle("Dulce Tapioca");
		

	
	
/*=============CUSTOMER SECOND PAGE==============
 * -Top label asks What type of drink customer would like
 * -Bottom Menu has option to: Go Back, Cancel, Continue
 * -Right Menu displays order, plus kawaii image on top
 * -Center menu displays the Options as buttons
 * -Once Type of ingredient is clicked, the scene takes you to Third Page
 * -Third page will be hard because it will have to read from database 
 * 		FIRST before displaying options, because of what gets picked in
 * 		SECOND page. 
 * 
 * 
 * */	
	//Top Menu
	HBox topMenu = new HBox();
	Label label1 = new Label("What would you like today?");
		label1.setPadding(new Insets(30, 30, 30, 30));
		label1.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
	topMenu.getChildren().addAll(label1);
	
	//Bottom Menu
	HBox bottomMenu = new HBox();
	bottomMenu.setPadding(new Insets(30, 30, 30, 30));
	bottomMenu.setSpacing(30);
	Button ButtonA = new Button("Go Back");
		ButtonA.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
	Button ButtonB = new Button("Cancel Order");
		ButtonB.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
	Button ButtonC = new Button("Continue");
		ButtonC.setStyle("-fx-font: 35 arial; -fx-base: #7CD674");
	bottomMenu.getChildren().addAll(ButtonA, ButtonB, ButtonC);
		
	//Right Menu
	Image disImg = new Image("tapioca.png");
	VBox rightMenu = new VBox();
	rightMenu.setPadding(new Insets(50, 40, 30, 30));
	rightMenu.setSpacing(20);
	ImageView iv = new ImageView();
	iv.setImage(disImg);
	rightMenu.getChildren().add(iv);

	//Scene 2 Center Menu
	VBox centerMenu = new VBox();
	centerMenu.setPadding(new Insets(50, 40, 30, 30));
	centerMenu.setSpacing(20);
	Button ButtonD = new Button("Smoothie");
		ButtonD.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");
	Button ButtonE = new Button("Tea");
		ButtonE.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");
	Button ButtonF = new Button("Coffee");
		ButtonF.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");
	Button ButtonG = new Button("Frio");
		ButtonG.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");
	Button ButtonH = new Button("Milk Tea");
		ButtonH.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");
	centerMenu.getChildren().addAll(ButtonD, ButtonE, ButtonF, ButtonG, ButtonH);
		
	//Second Page	
	BorderPane bordPane = new BorderPane();
	bordPane.setStyle("-fx-background-color: #B09268");
	bordPane.setTop(topMenu);
	bordPane.setBottom(bottomMenu);
	bordPane.setCenter(centerMenu);
	bordPane.setRight(rightMenu);
	
/*=================CUSTOMER FIRST PAGE==================
 * First Page is the Welcome Page for Customers
 * -Displays "WELCOME TO DULCE TAPIOCA and "Began Order" button
 * -Start Button takes User to Customer Second Page*/
	AnchorPane Anchor = new AnchorPane();
	StackPane SP = new StackPane();
	Label lab2 = new Label("Welcome");
	Label lab3 = new Label("to");
	Label lab4 = new Label("Dulce Tapioca!");
	Button start = new Button("Began Order");
	SP.getChildren().addAll(lab2, lab3, lab4);
	Anchor.getChildren().addAll(SP);
	Anchor.setStyle("-fx-background-color: #B09268");
	
/*===================LOGIN PAGE=================
 * Login page will have Username and password
 * -Pressing the Login button checks the typed Username and Password, then checks the database for it as well
 * -Create if-else statement that takes User to either "Welcome Page" or "Employee Page" 
 * -Once they get to the Welcome Page, there is no turning back without closing the program
 * -"Welcome Page" is used by the Customer, "Employee Page" will be for employees of course. 
 * -
 * */
	
/*==================WINDOW DISPLAY=================
 * This sets up the scenes and shows the windows. */
	Scene first = new Scene(Anchor, 1360, 900);
	Scene scene1 = new Scene(bordPane,1360,900);
	window.setScene(first);
	window.show();	
	}
}
