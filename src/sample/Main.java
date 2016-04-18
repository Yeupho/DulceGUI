package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application{
    Stage window;
    Double orderTotal = 0.0;
    Double finalOrderTotal = 0.0;
    Double defaultOrderTotal = 0.0;
    int sizeNum = 1;
    int iceNum = 2;

    ListView<String> c = new ListView<>();
    ListView<String> d = new ListView<>();
    ListView<String> f = new ListView<>();
    ListView<String> g = new ListView<>();

    String cart;
    ArrayList<String> cart1 = new ArrayList<>();
    ObservableList<String> cartItems = FXCollections.observableArrayList(cart1);


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Dulce Tapioca");




        /*==================LOGIN GRID=================*/
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle("-fx-background-color: #B09268");
        grid.setAlignment(Pos.CENTER);

        /*==================Order Total Labels=================*/
        //Customer First Page
        Label ordertot = new Label("Order Total:   $" + defaultOrderTotal);

        //Smoothie
        Label ordertot1 = new Label("Order Total:   $" + defaultOrderTotal);

        //Extra Ingredients
        Label ordertot2 = new Label("Order Total:   $" + orderTotal);

        //Fine Tune
        Label ordertot3 = new Label("Order Total:   $" + orderTotal);


        /*==================WINDOW DISPLAY================
         * This sets up the scenes and shows the windows. */
        Scene logMenu = new Scene(grid, 400, 400);
        window.setScene(logMenu);
        window.show();


        /*==================SCENE 1 - CUSTOMER FIRST PAGE=================*/
        BorderPane Anchor = new BorderPane();

        Button helpscr = new Button("Help");
        helpscr.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");


        VBox inception = new VBox();
        inception.setAlignment(Pos.CENTER);
        inception.setStyle("-fx-background-color: #B09268");

        HBox incep = new HBox();
        incep.setAlignment(Pos.TOP_RIGHT);
        incep.setStyle("-fx-background-color: #B09268");
        incep.getChildren().addAll(helpscr);

        Anchor.setTop(incep);


        Scene scene1 = new Scene(Anchor, 1000, 600);



        /*==================HELP SCENE=================*/
        BorderPane Anchor1 = new BorderPane();

        Button mainscr = new Button();
        mainscr.setMinSize(365, 100);
        mainscr.setStyle("-fx-background-image: url('sh.jpg')");

        Label welcome = new Label("Welcome to Dulce Tapioca");
        welcome.setStyle("-fx-font: 40 arial;");

        VBox welcomeb = new VBox();
        welcomeb.setAlignment(Pos.TOP_CENTER);
        welcomeb.getChildren().add(welcome);

        VBox inception1 = new VBox();
        inception1.setAlignment(Pos.BOTTOM_LEFT);
        inception1.getChildren().addAll(mainscr);


        BackgroundImage bg = new BackgroundImage(new Image("DulceInstructions.png",1000,600,false,true), BackgroundRepeat.NO_REPEAT
                , BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Anchor1.setBackground(new Background(bg));


        Anchor1.setBottom(inception1);



        Scene scenehelp = new Scene(Anchor1, 1000, 600);

        mainscr.setOnAction(e -> window.setScene(scene1));
        helpscr.setOnAction(e -> window.setScene(scenehelp));




        /*==================SCENE 2 - CUSTOMER SECOND PAGE=================*/
        BorderPane bordPane = new BorderPane();
        bordPane.setStyle("-fx-background-color: #B09268");
        HBox topMenu = new HBox();
        HBox bottomMenu = new HBox();
        VBox centerMenu = new VBox();
        VBox rightMenu = new VBox();



        centerMenu.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");

        bordPane.setTop(topMenu);
        bordPane.setBottom(bottomMenu);
        bordPane.setCenter(centerMenu);
        bordPane.setRight(rightMenu);


        /*==================SCENE 3 - SMOOTHIES=================*/
        BorderPane bord3 = new BorderPane();
        bord3.setStyle("-fx-background-color: #B09268");

        HBox topMenu3 = new HBox();
        HBox bottomMenu3 = new HBox();
        //VBox centerMenu3 = new VBox();
        VBox rightMenu3 = new VBox();

        FlowPane smoothFlow = new FlowPane();
        smoothFlow.setPadding(new Insets(10, 30, 10, 0));
        smoothFlow.setVgap(4);
        smoothFlow.setHgap(4);
        smoothFlow.setAlignment(Pos.TOP_CENTER);
        smoothFlow.setPrefWrapLength(200);
        smoothFlow.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");

        Scene scene3 = new Scene(bord3, 1000, 600);

        bord3.setTop(topMenu3);
        bord3.setBottom(bottomMenu3);
        bord3.setCenter(smoothFlow);
        bord3.setRight(rightMenu3);


        /*==================SCENE 4 - Extra Ingredients=================*/
        BorderPane bord4 = new BorderPane();
        bord4.setStyle("-fx-background-color: #B09268");

        HBox topMenu4 = new HBox();
        HBox bottomMenu4 = new HBox();
        VBox rightMenu4 = new VBox();




        FlowPane exFlow = new FlowPane();
        exFlow.setPadding(new Insets(10, 30, 10, 0));
        exFlow.setVgap(4);
        exFlow.setHgap(4);
        exFlow.setAlignment(Pos.TOP_CENTER);
        exFlow.setPrefWrapLength(200);
        exFlow.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");

        Scene scene4 = new Scene(bord4, 1000, 600);

        bord4.setTop(topMenu4);
        bord4.setBottom(bottomMenu4);
        bord4.setCenter(exFlow);
        bord4.setRight(rightMenu4);

        /*==================SCENE 5 - Fine Tune=================*/
        BorderPane bord5 = new BorderPane();
        bord5.setStyle("-fx-background-color: #B09268");

        HBox topMenu5 = new HBox();
        HBox bottomMenu5 = new HBox();
        VBox rightMenu5 = new VBox();

        GridPane ftGrid = new GridPane();
        ftGrid.setPadding(new Insets(10, 10, 10, 10));
        ftGrid.setVgap(20);
        ftGrid.setHgap(25);
        ftGrid.setStyle("-fx-background-color: #B09268");
       ftGrid.setAlignment(Pos.CENTER_LEFT);






        Scene scene5 = new Scene(bord5, 1000, 600);

        bord5.setTop(topMenu5);
        bord5.setBottom(bottomMenu5);
        bord5.setCenter(ftGrid);
        bord5.setRight(rightMenu5);


        /*===================LOGIN PAGE=================
 * Login page will have Username and password
 * -Pressing the Login button checks the typed Username and Password, then checks the database for it as well
 * -Create if-else statement that takes User to either "Welcome Page" or "Employee Page"
 * -Once they get to the Welcome Page, there is no turning back without closing the program
 * -"Welcome Page" is used by the Customer, "Employee Page" will be for employees of course.
 * -
 * */
        //Name	Label, placed in grid 0, 0
        Label userLabel = new Label("Username");
        GridPane.setConstraints(userLabel, 0, 1);
        userLabel.setStyle("-fx-font: 16.5 arial;");

        //Name Input, placed in grid 1, 0
        TextField userIn = new TextField();
        userIn.setPromptText("username");
        GridPane.setConstraints(userIn, 1, 1);

        //Password Label, placed in grid 0, 1
        Label passLabel = new Label("Password");
        passLabel.setStyle("-fx-font: 16.5 arial;");
        GridPane.setConstraints(passLabel, 0, 2);

        //Password Input
       /* TextField passIn = new TextField();
        passIn.setPromptText("password"); */
        PasswordField passIn = new PasswordField();
        passIn.setPromptText("password");

        GridPane.setConstraints(passIn, 1, 2);

        //Login Button,
        Button logButt = new Button("Login");
        logButt.setStyle("-fx-font: 16.5 arial; -fx-base: #FFC524");
        logButt.setMinWidth(250);
        logButt.setAlignment(Pos.CENTER);
        GridPane.setConstraints(logButt, 1, 4);

        Image disImg = new Image("tapioca.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(disImg);
        GridPane.setConstraints(iv1, 1, 0);

        grid.getChildren().addAll(userLabel, userIn, passLabel, passIn, logButt, iv1);

		/*
		 * @@@@@@@@@@@@EMPLOYEE Main Menu@@@@@@@@@@@@@@@@@@ /
		 */

		/*
		 * @@@@@@@@@@@@@@@@@@@ EMPLOYEE Menu Bar + TreeList @@@@@@@@@@@@@@@@@@@
		 * This is the menu bar that will go into ALL of the pages of the
		 * Employee GUI The tree list is a quick and simple navigation
		 */
		MenuBar menus = new MenuBar();
		menus.setStyle("-fx-background-color: #b78345;" + "-fx-font-size:30;" + "-fx-stroke: black;"
				+ "-fx-stroke-width: 3;" + "-fx-text-fill: white;");
		Menu menuFile = new Menu("File");
		MenuItem menFNew = new MenuItem("New...");
		MenuItem menFRef = new MenuItem("Refresh");
		MenuItem menFProp = new MenuItem("Properties");
		MenuItem menFLogout = new MenuItem("Log out");
		MenuItem menFexit = new MenuItem("Close");
		menFexit.setOnAction(e -> window.close());
		menuFile.getItems().addAll(menFNew, menFRef, menFProp, menFLogout, menFexit);

		Menu menuAdd = new Menu("Add");
		MenuItem menAItems = new MenuItem("Menu Item");
		MenuItem menAEmployee = new MenuItem("Employee");
		MenuItem menALocation = new MenuItem("Location");
		menuAdd.getItems().addAll(menAEmployee, menAItems, menALocation);

		menAEmployee.setOnAction(e -> AddtoThings.display());
		menAItems.setOnAction(e -> AddtoThings.displayOrder());
		menALocation.setOnAction(e -> AddtoThings.displayLocation());
		menFLogout.setOnAction(e -> window.setScene(logMenu));

		Menu menuView = new Menu("View");
		MenuItem menVOrd = new MenuItem("Order Reports");
		MenuItem menVEmp = new MenuItem("Emplyoee Reports");
		menVOrd.setOnAction(e -> ViewOrders.GeneralOrder());
		menVEmp.setOnAction(e -> ViewEmployee.GeneralEmplyoee());
		menuView.getItems().addAll(menVOrd, menVEmp);
		menuView.getItems().add(new MenuItem("Employee Reports"));
		menuView.getItems().add(new MenuItem("Location Reports"));

		Menu menuHelp = new Menu("Help");
		menuHelp.getItems().add(new MenuItem("About Team Solar"));
		menuHelp.getItems().add(new MenuItem("Documentation"));
		menuHelp.getItems().add(new MenuItem());
		menus.getMenus().addAll(menuFile, menuAdd, menuView, menuHelp);


		/*
		 * ==============================Employee Center
		 * Display=======================================
		 */
		VBox EmpFlow = new VBox();
		EmpFlow.setStyle("-fx-background-color: #B09268");
		Label EmpFlow1a = new Label("Orders");
		Label EmpFlow2a = new Label("Employees");
		Label EmpFlow3a = new Label("Location");
		EmpFlow1a.setStyle("-fx-font-size:20; -fx-padding: 10, 0, 0, 0;");
		EmpFlow2a.setStyle("-fx-font-size:20; -fx-padding: 10, 0, 0, 0;");
		EmpFlow3a.setStyle("-fx-font-size:20; -fx-padding: 10, 0, 0, 0;");

		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.3, 0.3, 0.3));

		FlowPane EmpFlow1 = new FlowPane();
		EmpFlow1.setStyle("-fx-background-color: #B09268");
		EmpFlow1.setVgap(20);
		EmpFlow1.setHgap(20);
		EmpFlow1.setPrefWrapLength(800);
		EmpFlow1.setPadding(new Insets(20, 0, 0, 20));
		FlowPane EmpFlow2 = new FlowPane();
		EmpFlow2.setStyle("-fx-background-color: #B09268");
		EmpFlow2.setVgap(20);
		EmpFlow2.setHgap(20);
		EmpFlow2.setPrefWrapLength(800);
		EmpFlow2.setPadding(new Insets(20, 0, 0, 20));
		FlowPane EmpFlow3 = new FlowPane();
		EmpFlow3.setStyle("-fx-background-color: #B09268");
		EmpFlow3.setVgap(20);
		EmpFlow3.setHgap(20);
		EmpFlow3.setPrefWrapLength(800);
		EmpFlow3.setPadding(new Insets(20, 0, 0, 20));

		// First Row
		Button ButtVOrd = new Button("");
		ButtVOrd.setOnAction(e -> ViewOrders.GeneralOrder());
		Button ButtAddIngr = new Button("");
		ButtAddIngr.setOnAction(e -> AddtoThings.displayOrder());
		Button ButtEditIngr = new Button("");
		ButtEditIngr.setOnAction(e -> EditThings.OrderPage());

		// Second Row
		Button ButtVEmp = new Button("");
		ButtVEmp.setOnAction(e -> ViewEmployee.GeneralEmplyoee());
		Button ButtAddemp = new Button("");
		ButtAddemp.setOnAction(e -> AddtoThings.display());
		Button ButtUpEmp = new Button("");
		ButtUpEmp.setOnAction(e -> EditThings.EmployeePage());

		// Third Row
		Button ButtVRep = new Button("");
		ButtVRep.setOnAction(e -> ViewReports.ReportDisplay());
		Button ButtAddLoc = new Button("");
		ButtAddLoc.setOnAction(e -> AddtoThings.displayLocation());
		Button ButtUpLoc = new Button("");
		ButtUpLoc.setOnAction(e -> EditThings.LocationPage());

		ButtVOrd.setEffect(dropShadow);
		ButtVOrd.setMinSize(200, 100);
		ButtVOrd.setMaxSize(200, 100);
		ButtVOrd.setPadding(new Insets(30, 20, 30, 20));
		ButtVOrd.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
				+ "-fx-background-color: #DECC8C;"
				+ "-fx-background-image: url('ord.png')");
		ButtVEmp.setEffect(dropShadow);
		ButtVEmp.setMinSize(200, 100);
		ButtVEmp.setMaxSize(200, 100);
		ButtVEmp.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
				+ "-fx-background-color: #F28A99;"
				+ "-fx-background-image: url('empinfo.png')");
		ButtVRep.setEffect(dropShadow);
		ButtVRep.setMinSize(200, 100);
		ButtVRep.setMaxSize(200, 100);
		ButtVRep.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
				+ "-fx-background-color: #D0B040; "
				+ "-fx-background-image: url('reports.png');");
		ButtAddIngr.setEffect(dropShadow);
		ButtAddIngr.setMinSize(200, 100);
		ButtAddIngr.setMaxSize(200, 100);
		ButtAddIngr.setPadding(new Insets(30, 20, 30, 20));
		ButtAddIngr.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
				+ "-fx-background-color: #DE986D;"
				+ "-fx-background-image: url('addord.png');");
		ButtAddemp.setEffect(dropShadow);
		ButtAddemp.setMinSize(200, 100);
		ButtAddemp.setMaxSize(200, 100);
		ButtAddemp.setPadding(new Insets(30, 20, 30, 20));
		ButtAddemp.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
				+ "-fx-background-color: #AB6890;"
				+ "-fx-background-image: url('addemp.png')");

		ButtAddLoc.setEffect(dropShadow);
		ButtAddLoc.setMinSize(200, 100);
		ButtAddLoc.setMaxSize(200, 100);
		ButtAddLoc.setPadding(new Insets(30, 20, 30, 20));
		ButtAddLoc.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
				+ "-fx-background-color: #68AB9F; "
				+ "-fx-background-image: url('addloc.png')");

		ButtEditIngr.setEffect(dropShadow);
		ButtEditIngr.setMinSize(200, 100);
		ButtEditIngr.setMaxSize(200, 100);
		ButtEditIngr.setPadding(new Insets(30, 20, 30, 20));
		ButtEditIngr.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
				+ "-fx-background-color: #7096AE;"
				+ "-fx-background-image: url('upord.png');");

		ButtUpEmp.setEffect(dropShadow);
		ButtUpEmp.setMinSize(200, 100);
		ButtUpEmp.setMaxSize(200, 100);
		ButtUpEmp.setPadding(new Insets(30, 20, 30, 20));
		ButtUpEmp.setStyle("" + "-fx-font-size: 15px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
				+ "-fx-background-color: #DAA9B5;"
				+ "-fx-background-image: url('upemp.png');");
		ButtUpLoc.setEffect(dropShadow);
		ButtUpLoc.setMinSize(200, 100);
		ButtUpLoc.setMaxSize(200, 100);
		ButtUpLoc.setPadding(new Insets(30, 20, 30, 20));
		ButtUpLoc.setStyle("" + "-fx-font-size: 20px;" + "-fx-border-radius: 50; " + "-fx-background-radius: 50; "
				+ "-fx-background-color: #AFE197; "
				+ "-fx-background-image: url('editloc.png');");

		EmpFlow1.getChildren().addAll(ButtVOrd, ButtAddIngr, ButtEditIngr);
		EmpFlow2.getChildren().addAll(ButtVEmp, ButtAddemp, ButtUpEmp);
		EmpFlow3.getChildren().addAll(ButtVRep, ButtAddLoc, ButtUpLoc);
		EmpFlow.getChildren().addAll(EmpFlow1a, EmpFlow1, EmpFlow2a, EmpFlow2, EmpFlow3a, EmpFlow3);

		/*
		 * @@@@@@@@@@@@@@@@@@@@EMPLYOEE Main
		 * Page @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ First page of the Employee
		 * GUI, contains List to add data, edit data, display form, display
		 * reports
		 */

		BorderPane empMenu1 = new BorderPane();
		VBox EmpLeftSet = new VBox();
		EmpLeftSet.setStyle("-fx-background-color: #B09268");
		Image disImg1 = new Image("tapioca.png");
		ImageView iv12 = new ImageView();
		iv12.setImage(disImg1);
		EmpLeftSet.getChildren().add(iv12);
		empMenu1.setTop(menus);
		empMenu1.setLeft(EmpLeftSet);
		empMenu1.setCenter(EmpFlow);
		Scene EmpScene = new Scene(empMenu1, 1000, 600);
		/*
		 * ==================================CONDITIONAL
		 * LOGIN==============================================
		 */

        logButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {


                if ((userIn.getText() != null && userIn.getText().contains("a")) && passIn.getText().contains("a")) {
                    window.setScene(EmpScene);
                } else if ((userIn.getText() != null && userIn.getText().contains("Customer")) && passIn.getText().contains("Customer")) {
                    window.setScene(scene1);
                } else {
                    Okay.display("Incorrect Login", "Bakaaaaa~~~ Please verify your credentials");
                    System.out.println("looololol, try 'Employee' in both fields or 'Customer' in both fields");

                }

            }

        });

        passIn.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    if ((userIn.getText() != null && userIn.getText().contains("Employee")) && passIn.getText().contains("Employee")) {
                        window.setScene(EmpScene);
                    } else if ((userIn.getText() != null && userIn.getText().contains("Customer")) && passIn.getText().contains("Customer")) {
                        window.setScene(scene1);
                    } else {
                        Okay.display("Incorrect Login", "Bakaaaaa~~~ Please verify your credentials");
                        System.out.println("looololol, try 'Employee' in both fields or 'Customer' in both fields");

                    }
                }
            }
        });




/*=================CUSTOMER FIRST PAGE==================
 * First Page is the Welcome Page for Customers
 * -Displays "WELCOME TO DULCE TAPIOCA and "Began Order" button
 * -Start Button takes User to Customer Second Page*/
        Label lab2 = new Label("Welcome");
        lab2.setPadding(new Insets(10, 10, 10, 10));
        lab2.setStyle("-fx-font: 40 arial;");

        Label lab3 = new Label("to");
        lab3.setPadding(new Insets(10, 10, 10, 10));
        lab3.setStyle("-fx-font: 40 arial;");

        Label lab4 = new Label("Dulce Tapioca!");
        lab4.setPadding(new Insets(10, 10, 10, 10));
        lab4.setStyle("-fx-font: 40 arial;");

        Button start = new Button("Begin Order");
        start.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");
        inception.getChildren().addAll(lab2, lab3, lab4, start);
        Anchor.setCenter(inception);

        start.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.F12)) {
                    window.setScene(logMenu);
                }
            }
        });

        Pane can1 = new Pane();
        can1.setPrefSize(0, 100);
        Anchor.setLeft(can1);
        inception.setAlignment(Pos.CENTER);
        Anchor.setStyle("");


        Scene scene2 = new Scene(bordPane, 1000, 600);
        start.setOnAction(e -> window.setScene(scene2));


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
        //Top Menu Items
        Label label1 = new Label("What would you like today?");
        label1.setPadding(new Insets(30, 30, 30, 30));
        label1.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        topMenu.getChildren().addAll(label1);

        //Bottom Menu Items
        bottomMenu.setPadding(new Insets(30, 30, 30, 30));
        bottomMenu.setSpacing(30);

        Button ButtonA = new Button("Go Back");
        ButtonA.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonA.setMinWidth(200);
        ButtonA.setAlignment(Pos.CENTER);

        Button ButtonB = new Button("Cancel Order");
        ButtonB.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonB.setMinWidth(200);
        ButtonB.setAlignment(Pos.CENTER);

        bottomMenu.setMinHeight(-10);
        bottomMenu.getChildren().addAll(ButtonA, ButtonB);

        //Right Menu Items
        ImageView iv2 = new ImageView();
        Image step1 = new Image("step1.png");
        iv2.setImage(step1);
        iv2.setFitHeight(100);
        iv2.setFitWidth(150);

        rightMenu.setAlignment(Pos.CENTER);
        rightMenu.setPadding(new Insets(50, 40, 30, 30));
        rightMenu.setSpacing(20);


        buildCart(c);

        VBox vcart = new VBox();
        Label carttit = new Label("Shopping Cart");
        carttit.setStyle("-fx-font: 15 arial;");

        ordertot.setStyle("-fx-font: 15 arial;");

        vcart.getChildren().addAll(carttit, c, ordertot);




        rightMenu.getChildren().addAll(iv2, vcart);

        //Scene 2 Center Menu Items
        centerMenu.setPadding(new Insets(50, 40, 30, 30));
        centerMenu.setSpacing(10);

        Button ButtonD = new Button("Smoothie");
        ButtonD.setMinSize(200, 60);
        ButtonD.setAlignment(Pos.CENTER);
        ButtonD.setOnAction(e -> window.setScene(scene3));

        Button ButtonE = new Button("Tea");
        ButtonE.setMinSize(200, 60);
        ButtonE.setAlignment(Pos.CENTER);
        ButtonE.setOnAction(e -> {
            boolean ans = ConfirmOrder.display("Print Order", "Would you like to print your order?");

            if (ans == true) {
                Okay.display("Order Complete", "Printing Order");

                window.setScene(scene1);
            } else {
                window.setScene(scene2);
            }
        });

        Button ButtonF = new Button("Coffee");
        ButtonF.setMinSize(200, 60);
        ButtonF.setAlignment(Pos.CENTER);

        Button ButtonG = new Button("Frio");
        ButtonG.setMinSize(200, 60);
        ButtonG.setAlignment(Pos.CENTER);

        Button ButtonH = new Button("Milk Tea");
        ButtonH.setMinSize(200, 60);
        ButtonH.setAlignment(Pos.CENTER);

        centerMenu.setSpacing(0);
        centerMenu.getChildren().addAll(ButtonD, ButtonE, ButtonF, ButtonG, ButtonH);

        ButtonA.setOnAction(e -> {
            //clearCart();
            window.setScene(scene1);
        });


        //Button B always go back to main menu, NOTE: ADD CONFIRMATION scene to this-
        ButtonB.setOnAction(e -> window.setScene(scene1));



/*=============CUSTOMER SMOOTHIE PAGE===============
 *===============WORK IN PROGRESS================
 *
 *
 * */
        //Top Menu Items
        Label lab1 = new Label("What flavor would you like?");
        lab1.setPadding(new Insets(30, 30, 30, 30));
        lab1.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        topMenu3.getChildren().addAll(lab1);

        buildCart(d);
        d.setItems(cartItems);

        //Bottom Menu Items
        bottomMenu3.setPadding(new Insets(30, 30, 30, 30));
        bottomMenu3.setSpacing(30);

        Button ButtonA3 = new Button("Go Back");
        ButtonA3.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonA3.setMinWidth(200);
        ButtonA3.setAlignment(Pos.CENTER);

        Button ButtonB3 = new Button("Cancel Order");
        ButtonB3.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        ButtonB3.setMinWidth(200);
        ButtonB3.setAlignment(Pos.CENTER);


        Button ButtonC3 = new Button("Continue");
        ButtonC3.setStyle("-fx-font: 20 arial; -fx-base: #7CD674");
        ButtonC3.setMinWidth(200);
        ButtonC3.setAlignment(Pos.CENTER);

        bottomMenu3.setMinHeight(-10);
        bottomMenu3.getChildren().addAll(ButtonA3, ButtonB3, ButtonC3);

        //Clear cart whenever the customer goes back or clicks cancel
        ButtonB3.setOnAction(e -> {
            window.setScene(scene1);
            clearCart(d);
        });
        ButtonA3.setOnAction(e -> {
            window.setScene(scene2);
            clearCart(d);
        });




        //Right Menu - Image
        ImageView iv3 = new ImageView();
        Image step2 = new Image("step2.png");
        iv3.setImage(step2);
        iv3.setFitHeight(100);
        iv3.setFitWidth(150);

        rightMenu3.setPadding(new Insets(50, 40, 30, 30));
        rightMenu3.setSpacing(20);


        VBox vcart1 = new VBox();
        Label carttit1 = new Label("Shopping Cart");
        carttit1.setStyle("-fx-font: 15 arial;");
        ordertot1.setStyle("-fx-font: 15 arial;");

        vcart1.getChildren().addAll(carttit1, d, ordertot1);

        rightMenu3.setAlignment(Pos.CENTER);
        rightMenu3.getChildren().addAll(iv3, vcart1);


        //Scene 2 Center Menu Items

        Button flavorFlave = new Button("Poop");
        flavorFlave.setAlignment(Pos.CENTER);
        flavorFlave.setMinSize(200, 75);

        Button flavorFlave2 = new Button("More Poop");
        flavorFlave2.setAlignment(Pos.CENTER);
        flavorFlave2.setMinSize(200, 75);

        Button flavor3 = new Button("Even More Poop");
        flavor3.setMinSize(200, 75);
        flavor3.setAlignment(Pos.CENTER);

        Button sf4 = new Button("Fresh Avocado");
        sf4.setMinSize(200, 75);
        sf4.setAlignment(Pos.CENTER);
        sf4.setOnAction(e -> {
            cart = sf4.getText() + "    ($420)";
            addCart(cart, cartItems);
            orderTotal += 420;
            finalOrderTotal += 420;
            window.setScene(scene4);
            ordertot2.setText("Order Total: $" + orderTotal);

        });


        Button sf5 = new Button("Fresh Strawberry");
        sf5.setMinSize(200, 75);
        sf5.setAlignment(Pos.CENTER);
        sf5.setOnAction(e -> {
            cart = sf5.getText() + "    ($420)";
            addCart(cart, cartItems);
            orderTotal += 420;
            finalOrderTotal += 420;
            window.setScene(scene4);
            ordertot2.setText("Order Total: $" + orderTotal);
        });

        Button sf6 = new Button("Fresh Mango");
        sf6.setMinSize(200, 75);
        sf6.setAlignment(Pos.CENTER);

        Button sf7 = new Button("Fresh Banana");
        sf7.setMinSize(200, 75);
        sf7.setAlignment(Pos.CENTER);

        Button sf8 = new Button("Fresh Poop");
        sf8.setMinSize(200, 75);
        sf8.setAlignment(Pos.CENTER);

        Button sf9 = new Button("Fresh Raspberry");
        sf9.setMinSize(200, 75);
        sf9.setAlignment(Pos.CENTER);

        Button sf10 = new Button("Fresh Raspberry");
        sf10.setMinSize(200, 75);
        sf10.setAlignment(Pos.CENTER);

        Button sf11 = new Button("Fresh Raspberry");
        sf11.setMinSize(200, 75);
        sf11.setAlignment(Pos.CENTER);

        Button sf12 = new Button("Fresh Raspberry");
        sf12.setMinSize(200, 75);
        sf12.setAlignment(Pos.CENTER);

        smoothFlow.getChildren().addAll(flavorFlave, flavorFlave2, flavor3, sf4, sf5, sf6, sf7, sf8, sf9, sf10, sf11,
                sf12);

     /*=============EXTRA INGREDIENT PAGE===============*/

        //Bottom Menu Buttons
        Button gb1 = new Button("Go Back");
        Button co1 = new Button("Cancel Order");
        Button cont1 = new Button("Skip");




        //Top Menu Items
        Label l4 = new Label("Add Extra Ingredients?");
        l4.setPadding(new Insets(30, 30, 30, 30));
        l4.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        topMenu4.getChildren().addAll(l4);

        buildCart(f);

        f.setItems(cartItems);



        //Order Total Label


        //Center Menu
        Button ei1 = new Button("Chocolate Syrup");
        ei1.setMinSize(200, 75);
        ei1.setAlignment(Pos.CENTER);
        ei1.setOnAction(e -> {
            cart = "    -"+ ei1.getText() + "\n      $500";
            orderTotal += 500;

            addCart(cart, cartItems);
            ordertot2.setText("Order Total: $" + orderTotal);
        });


        Button ei2 = new Button("Honey");
        ei2.setMinSize(200, 75);
        ei2.setAlignment(Pos.CENTER);
        ei2.setOnAction(e -> {
            cart = "    -" + ei2.getText() + "\n      $500";
            orderTotal += 500;
            addCart(cart, cartItems);
            ordertot2.setText("Order Total: $" + orderTotal);
        });

        Button ei3 = new Button("Whiskey");
        ei3.setMinSize(200, 75);
        ei3.setAlignment(Pos.CENTER);
        ei3.setOnAction(e -> {
            orderTotal += 125;
            cart = "    -" + ei3.getText() + "\n      $125";
            addCart(cart, cartItems);
            ordertot2.setText("Order Total: $" + orderTotal);
            ordertot3.setText("Order Total: $" + orderTotal);

            cont1.setText("Continue");

        });




        //Bottom Menu Items
        bottomMenu4.setPadding(new Insets(30, 30, 30, 30));
        bottomMenu4.setSpacing(30);

        //Go Back
        gb1.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        gb1.setMinWidth(200);
        gb1.setAlignment(Pos.CENTER);
        gb1.setOnAction(e ->{
            window.setScene(scene3);
            clearCart(f);

            orderTotal -= orderTotal;
            ordertot2.setText("Order Total: $" + defaultOrderTotal);
            cont1.setText("Skip");
        });

        //Cancel Order
        co1.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        co1.setMinWidth(200);
        co1.setAlignment(Pos.CENTER);
        co1.setOnAction(e -> {
            window.setScene(scene1);
            clearCart(f);

            orderTotal -= orderTotal;
            ordertot2.setText("Order Total: $" + defaultOrderTotal);
            cont1.setText("Skip");

        });


        //Skip/Continue
        cont1.setStyle("-fx-font: 20 arial; -fx-base: #7CD674");
        cont1.setMinWidth(200);
        cont1.setAlignment(Pos.CENTER);
        cont1.setOnAction(e -> window.setScene(scene5));



        bottomMenu4.getChildren().addAll(gb1, co1, cont1);




        //Right Menu - Image
        ImageView iv4 = new ImageView();
        Image step3 = new Image("step3.png");
        iv4.setImage(step3);
        iv4.setFitHeight(100);
        iv4.setFitWidth(100);

        rightMenu4.setPadding(new Insets(50, 40, 30, 30));
        rightMenu4.setSpacing(20);


        VBox vcart2 = new VBox();
        Label carttit2 = new Label("Shopping Cart");

        carttit2.setStyle("-fx-font: 15 arial;");
        ordertot2.setStyle("-fx-font: 15 arial;");

        vcart2.setMinHeight(175);
        vcart2.getChildren().addAll(carttit2, f, ordertot2);


        rightMenu4.setAlignment(Pos.CENTER);
        rightMenu4.getChildren().addAll(iv4, vcart2);

        exFlow.getChildren().addAll(ei1, ei2, ei3);


        /*=============FINE TUNE PAGE===============*/

        //Bottom Menu Buttons
        Button gb2 = new Button("Go Back");
        Button co2 = new Button("Cancel Order");
        Button cont2 = new Button("Skip");




        //Top Menu Items
        Label ftL1 = new Label("Fine Tune your drink! ");
        ftL1.setPadding(new Insets(30, 30, 30, 30));
        ftL1.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        topMenu5.getChildren().addAll(ftL1);

        buildCart(g);

        g.setItems(cartItems);



        //CENTER MENU GRID

        //LABELS
        Label Size = new Label("                ");
        Label sizeTop = new Label("    Size");
        Label Ice = new Label("     Ice");
        Label iceImage = new Label("            ");


        //SIZE
        /*
        Default (Small) = 1
        Medium = 2
        Large = 3
         */

        Image defaultSize = new Image ("size1.png");
        Image medSize = new Image ("size2.png");
        Image largeSize = new Image ("size3.png");
        Size.setGraphic(new ImageView(defaultSize));

        //Size Label
        sizeTop.setStyle("-fx-font: 20 arial;");
        GridPane.setConstraints(sizeTop, 1, 0);



        Button ftSI = new Button();
        ftSI.setStyle("-fx-background-image: url('plus.png')");
        ftSI.setMinSize(75, 37);

        ftSI.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (sizeNum == 1) {

                    Size.setGraphic(new ImageView(medSize));
                    sizeNum += 1;

                } else if (sizeNum == 2) {
                    sizeNum += 1;
                    Size.setGraphic(new ImageView(largeSize));
                }
            }
        });


        GridPane.setConstraints(ftSI, 1, 1);

        Size.setAlignment(Pos.CENTER);
        Size.setStyle("-fx-font: 16.5 arial;");


        GridPane.setConstraints(Size, 1, 2);
        Size.setMaxSize(35, 35);

        Button ftSD = new Button();
        ftSD.setStyle("-fx-background-image: url('minus.png')");
        ftSD.setMinSize(75, 37);
        ftSD.setOnAction(e -> {
            //Size can either be 2 or 3 for if
            if (sizeNum == 2){

                Size.setGraphic(new ImageView(defaultSize));
                sizeNum -= 1;
            }
            else if (sizeNum == 3){
                sizeNum -= 1;
                Size.setGraphic(new ImageView(medSize));
            }
        });
        GridPane.setConstraints(ftSD, 1, 3);


        //ICE
        /*
        Default (Small) = 1
        Medium = 2
        Large = 3
         */

        //Ice Label
        Ice.setStyle("-fx-font: 20 arial;");
        GridPane.setConstraints(Ice, 2, 0);

        Image defaultIce = new Image ("ice2.png");

        Image medIce = new Image ("ice1.png");
        Image largeIce = new Image ("ice3.png");
        iceImage.setGraphic(new ImageView(defaultIce));
        GridPane.setConstraints(iceImage, 2, 2);

        Button ftII = new Button();
        ftII.setStyle("-fx-background-image: url('plus.png')");
        ftII.setMinSize(75, 37);
        ftII.setOnAction(e ->{
            if (iceNum == 1) {

                iceImage.setGraphic(new ImageView(defaultIce));
                iceNum += 1;

            } else if (iceNum == 2) {
                iceNum += 1;
                iceImage.setGraphic(new ImageView(largeIce));
            }
        });
        GridPane.setConstraints(ftII, 2, 1);




        Button ftID = new Button();
        ftID.setStyle("-fx-background-image: url('minus.png')");
        ftID.setMinSize(75, 37);
        ftID.setOnAction(e -> {
            //Size can either be 2 or 3 for if
            if (iceNum == 2){

                iceImage.setGraphic(new ImageView(medIce));
                iceNum -= 1;
            }
            else if (iceNum == 3){
                iceNum -= 1;
                iceImage.setGraphic(new ImageView(defaultIce));
            }
        });
        GridPane.setConstraints(ftID, 2, 3);



        ftGrid.getChildren().addAll(sizeTop,ftSI, ftSD,Size, ftII, ftID, Ice, iceImage);



        //Bottom Menu Items
        bottomMenu5.setPadding(new Insets(30, 30, 30, 30));
        bottomMenu5.setSpacing(30);

        //Go Back
        gb2.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        gb2.setMinWidth(200);
        gb2.setAlignment(Pos.CENTER);


        //Cancel Order
        co2.setStyle("-fx-font: 20 arial; -fx-base: #FFC524");
        co2.setMinWidth(200);
        co2.setAlignment(Pos.CENTER);



        //Skip/Continue
        cont2.setStyle("-fx-font: 20 arial; -fx-base: #7CD674");
        cont2.setMinWidth(200);
        cont2.setAlignment(Pos.CENTER);



        bottomMenu5.getChildren().addAll(gb2, co2, cont2);




        //Right Menu - Image
        ImageView iv5 = new ImageView();
        Image step4 = new Image("step4.png");
        iv5.setImage(step4);
        iv5.setFitHeight(100);
        iv5.setFitWidth(100);

        rightMenu5.setPadding(new Insets(50, 40, 30, 30));
        rightMenu5.setSpacing(20);


        VBox vcart3 = new VBox();
        Label carttit3 = new Label("Shopping Cart");

        carttit3.setStyle("-fx-font: 15 arial;");
        ordertot3.setStyle("-fx-font: 15 arial;");

        vcart3.setMinHeight(175);
        vcart3.getChildren().addAll(carttit3, g, ordertot3);


        rightMenu5.setAlignment(Pos.CENTER);
        rightMenu5.getChildren().addAll(iv5, vcart3);





    }




    private void buildCart(ListView<String> c1){
        c1.setStyle("-fx-font: 13 arial; -fx-base: #FFC524");
        c1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void addCart(String item, ObservableList<String> l){
        l.add(item);
    }

    private void clearCart(ListView<String> c3) {
        c3.getItems().clear();
    }

    private double addOrderTotal (double currentValue, double addedValue){
        return currentValue + addedValue;
    }

    private double removeOrderTotal (double currentValue, double removedValue){
        return currentValue - removedValue;};
        }

/*
 public DoubleProperty ordertotal;

    public double getOrderTotal() {
        return ordertotal.get();
    }

    public void setOrderTotal(double newOrderTotal) {
        ordertotal.set(newOrderTotal);
    }

    public DoubleProperty OrderTotalProperty() {
        return ordertotal;
    }



        Button ei5 = new Button("More Whiskey");
        ei5.setMinWidth(300);
        ei5.setAlignment(Pos.CENTER);
        ei5.setMinHeight(150);
        ei5.setOnAction(e -> {

            DoubleProperty orderr = new SimpleDoubleProperty(orderTotal);
            ObservableDoubleValue orderrT = Bindings.createDoubleBinding(() -> {
                double t = orderr.get();
                return t;
            });

            System.out.println(orderrT.getValue());
        });


        *************************SET BACKGROUND FOR HELP MENU********************************

        BackgroundImage bg = new BackgroundImage(new Image("12.jpg",1000,600,false,true), BackgroundRepeat.NO_REPEAT
                                , BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        bord4.setBackground(new Background(bg));


        //None Button
         Button ei4 = new Button("None");
        ei4.setMinSize(200, 75);
        ei4.setAlignment(Pos.CENTER);
       /* ei4.setOnAction(e -> {
            ei1.setVisible(false);
            ei2.setVisible(false);
            ei3.setVisible(false);
        });*/ 