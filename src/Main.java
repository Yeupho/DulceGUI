

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
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
		
/*@@@@@@@@@@@@EMPLOYEE Main Menu@@@@@@@@@@@@@@@@@@
 * /
 */
	BorderPane empMenu1 = new BorderPane();
	HBox empMenuV = new HBox();
	MenuBar menus = new MenuBar();
	Menu menuFile = new Menu("File");
	menuFile.setText("File");
	Menu menuEdit = new Menu("Edit");
	Menu menuView = new Menu("View");
	Menu menuHelp = new Menu("Help");
	
	menus.getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);
	empMenu1.setTop(empMenuV);
	
	empMenuV.getChildren().addAll(menus);

	Scene EmpScene = new Scene(empMenu1, 1360, 700);
	
/*=============CUSTOMER THIRD PAGE===============
 * 
 * 
 * 
 * */
	BorderPane bord3 = new BorderPane();
	bord3.setStyle("-fx-background-color: #B09268");
	HBox topMenu3 = new HBox();
	HBox bottomMenu3 = new HBox();
	VBox centerMenu3 = new VBox();
	VBox rightMenu3 = new VBox();
	
	
	
	
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
	//Second Page	
	BorderPane bordPane = new BorderPane();
	bordPane.setStyle("-fx-background-color: #B09268");
	HBox topMenu = new HBox();
	HBox bottomMenu = new HBox();
	VBox centerMenu = new VBox();
	VBox rightMenu = new VBox();
	
	bordPane.setTop(topMenu);
	bordPane.setBottom(bottomMenu);
	bordPane.setCenter(centerMenu);
	bordPane.setRight(rightMenu);
	
	//Top Menu Items
	Label label1 = new Label("What would you like today?");
		label1.setPadding(new Insets(30, 30, 30, 30));
		label1.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
	topMenu.getChildren().addAll(label1);
	
	//Bottom Menu Items	
	bottomMenu.setPadding(new Insets(30, 30, 30, 30));
	bottomMenu.setSpacing(30);
	Button ButtonA = new Button("Go Back");
		ButtonA.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
	Button ButtonB = new Button("Cancel Order");
		ButtonB.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
	Button ButtonC = new Button("Continue");
		ButtonC.setStyle("-fx-font: 35 arial; -fx-base: #7CD674");
	bottomMenu.getChildren().addAll(ButtonA, ButtonB, ButtonC);
		
	//Right Menu Items
	Image disImg = new Image("img/tapioca.png");
	ImageView iv = new ImageView();
	iv.setImage(disImg);
	
	rightMenu.setPadding(new Insets(50, 40, 30, 30));
	rightMenu.setSpacing(20);
	rightMenu.getChildren().add(iv);

	//Scene 2 Center Menu Items	
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
		
/*=================CUSTOMER FIRST PAGE==================
 * First Page is the Welcome Page for Customers
 * -Displays "WELCOME TO DULCE TAPIOCA and "Began Order" button
 * -Start Button takes User to Customer Second Page*/
	BorderPane Anchor = new BorderPane();
	VBox inception = new VBox();
	
	Label lab2 = new Label("Welcome");
	lab2.setPadding(new Insets (10, 10, 10, 10));
	lab2.setStyle("-fx-font: 40 arial;");
	
	Label lab3 = new Label("to");
	lab3.setPadding(new Insets (10, 10, 10, 10));
	lab3.setStyle("-fx-font: 40 arial;");
	
	Label lab4 = new Label("Dulce Tapioca!");
	lab4.setPadding(new Insets (10, 10, 10, 10));
	lab4.setStyle("-fx-font: 40 arial;");
	
	Button start = new Button("Began Order");
	start.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");
	inception.getChildren().addAll(lab2, lab3, lab4,start);
	Anchor.setCenter(inception);
	
	Pane can1 = new Pane();
	can1.setPrefSize(100, 100);
	Anchor.setLeft(can1);
	inception.setAlignment(Pos.CENTER);
	Anchor.setStyle("");
	
	
	Scene scene2 = new Scene(bordPane,1360,900);
	start.setOnAction(e-> window.setScene(scene2));
	
/*===================LOGIN PAGE=================
 * Login page will have Username and password
 * -Pressing the Login button checks the typed Username and Password, then checks the database for it as well
 * -Create if-else statement that takes User to either "Welcome Page" or "Employee Page" 
 * -Once they get to the Welcome Page, there is no turning back without closing the program
 * -"Welcome Page" is used by the Customer, "Employee Page" will be for employees of course. 
 * -
 * */
	GridPane grid = new GridPane();
	grid.setPadding(new Insets(10,10,10,10));
	grid.setVgap(10);
	grid.setHgap(10);
	//Name	Label, placed in grid 0, 0
	Label userLabel = new Label("Username");
	GridPane.setConstraints(userLabel, 0, 1);
	//Name Input, placed in grid 1, 0
	TextField userIn = new TextField();
	userIn.setPromptText("username");
	GridPane.setConstraints(userIn, 1, 1);
	
	//Password Label, placed in grid 0, 1
	Label passLabel = new Label("Password");
	GridPane.setConstraints(passLabel, 0, 2);
	//Password Input
	TextField passIn = new TextField();
	passIn.setPromptText("password");
	GridPane.setConstraints(passIn, 1, 2);
	//Login Button,
	Button logButt = new Button("Login");
	GridPane.setConstraints(logButt, 0,4);
	
	ImageView iv1 = new ImageView();
	iv1.setImage(disImg);
	GridPane.setConstraints(iv1, 1,0);
	
	Button EmpButt = new Button("Temporary EmpMenuButt");
	EmpButt.setOnAction(e-> window.setScene(EmpScene));
	GridPane.setConstraints(EmpButt, 1, 5);
	
	grid.getChildren().addAll(userLabel, userIn, passLabel, passIn, logButt, iv1, EmpButt);
	
	Scene scene1 = new Scene(Anchor, 1360, 900);
/*================================================================================*/
	logButt.setOnAction(new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent e){
			
			if ((userIn.getText() != null && userIn.getText().contains("Employee")) && passIn.getText().contains("Employee")) {
	           window.setScene(EmpScene);
	        }
			else if ((userIn.getText() != null && userIn.getText().contains("Customer")) && passIn.getText().contains("Customer")){
				window.setScene(scene1);
			}
			else {
	        	System.out.println("looololol, try 'Employee' in both fields or 'Customer' in both fields");
	        	Label youfaildlogin = new Label("*Incorrect Username/Password");
	        	GridPane.setConstraints(youfaildlogin, 1, 4);
	        	grid.getChildren().add(youfaildlogin);
	        }

		}
		
	});
	ButtonA.setOnAction(e-> window.setScene(scene1));
	//Button B always go back to main menu, NOTE: ADD CONFIRMATION scene to this-
	ButtonB.setOnAction(e-> window.setScene(scene1));
/*==================WINDOW DISPLAY=================
 * This sets up the scenes and shows the windows. */
	Scene logMenu = new Scene(grid, 400, 400);
	window.setScene(logMenu);
	window.show();	
	}
	
}

