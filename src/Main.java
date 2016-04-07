
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import javafx.stage.Stage;

import javax.swing.*;

import java.util.*;

public class Main extends Application{
    Stage window;

    public static void main(String[] args){
    	
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Dulce Tapioca");




        /*==================LOGIN GRID=================*/
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setStyle("-fx-background-color: #B09268");
        grid.setAlignment(Pos.CENTER);


        /*==================WINDOW DISPLAY=================
         * This sets up the scenes and shows the windows. */
        Scene logMenu = new Scene(grid, 400, 450);
        window.setScene(logMenu);
        window.show();


        /*==================SCENE 1 - CUSTOMER FIRST PAGE=================*/
        BorderPane Anchor = new BorderPane();


        VBox inception = new VBox();
        inception.setAlignment(Pos.CENTER);
        inception.setStyle("-fx-background-color: #B09268");

        Scene scene1 = new Scene(Anchor, 1360, 900);



        /*==================SCENE 2 - CUSTOMER SECOND PAGE=================*/
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

        Scene scene3 = new Scene(bord3,1360,900);

        bord3.setTop(topMenu3);
        bord3.setBottom(bottomMenu3);
        bord3.setCenter(smoothFlow);
        bord3.setRight(rightMenu3);

        /*==================Confirmation Box=================*/
        // Refer to ConfirmOrder class

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
        GridPane.setConstraints(logButt, 1,4);

        Image disImg = new Image("img/tapioca.png");
        ImageView iv1 = new ImageView();
        iv1.setImage(disImg);
        GridPane.setConstraints(iv1, 1,0);

        grid.getChildren().addAll(userLabel, userIn, passLabel, passIn, logButt, iv1);




        /*@@@@@@@@@@@@EMPLOYEE Main Menu@@@@@@@@@@@@@@@@@@
 * /
 */
        
                
/*@@@@@@@@@@@@@@@@@@@ EMPLOYEE Menu Bar + TreeList @@@@@@@@@@@@@@@@@@@
 * This is the menu bar that will go into ALL of the pages of the Employee GUI 
 * The tree list is a quick and simple navigation 
*/     
        MenuBar menus = new MenuBar();
        menus.setStyle(
        		"-fx-background-color: #b78345;"
        		+ "-fx-font-size:30;"
        		+ "-fx-stroke: black;"
        		+ "-fx-stroke-width: 3;"
        		+ "-fx-text-fill: white;");
        Menu menuFile = new Menu("File");
        MenuItem menFNew = new MenuItem("New...");
        MenuItem menFRef = new MenuItem("Refresh");
        MenuItem menFProp = new MenuItem("Properties");
        MenuItem menFLogout = new MenuItem("Log out");
        MenuItem menFexit = new MenuItem("Close");
        menFexit.setOnAction(e-> window.close());
        menuFile.getItems().addAll(menFNew, menFRef, menFProp, menFLogout, menFexit);
        
        Menu menuAdd = new Menu("Add");
        MenuItem menAItems = new MenuItem("Menu Item");
        MenuItem menAEmployee = new MenuItem("Employee");
        MenuItem menALocation = new MenuItem("Location");
        menuAdd.getItems().addAll(menAEmployee, menAItems, menALocation);
        
        menAEmployee.setOnAction(e-> AddEmployee.display());
        menAItems.setOnAction(e-> AddEmployee.displayOrder());
        menALocation.setOnAction(e-> AddEmployee.displayLocation());
        menFLogout.setOnAction(e-> window.setScene(logMenu));
        
        
        Menu menuView = new Menu("View");
        MenuItem menVOrd = new MenuItem("Order Reports");
        menVOrd.setOnAction(e->ViewOrders.display());
        menuView.getItems().add(menVOrd);
        menuView.getItems().add(new MenuItem("Employee Reports"));
        menuView.getItems().add(new MenuItem("Location Reports"));
        
        Menu menuHelp = new Menu("Help");
        menuHelp.getItems().add(new MenuItem("About Team Solar"));
        menuHelp.getItems().add(new MenuItem("Documentation"));
        menuHelp.getItems().add(new MenuItem());
        menus.getMenus().addAll(menuFile, menuAdd, menuView, menuHelp);
        /*============================TREES=======================*/
       /* TreeView<String> tree;
        TreeItem<String> MainRoot = new TreeItem<>("Root");
        MainRoot.setExpanded(false);
        
        //Title Sections
        TreeItem<String> secA = new TreeItem<>("Orders");
        TreeItem<String> secB = new TreeItem<>("Employee");
        TreeItem<String> secC = new TreeItem<>("Reports");
        MainRoot.getChildren().addAll(secA, secB, secC);
        
        //Subsection A
        TreeItem<String> subA = new TreeItem<>("Ingredients");
        TreeItem<String> subB = new TreeItem<>("View Orders");
        secA.getChildren().addAll(subA, subB);
        
        TreeItem<String> subC = new TreeItem<>("View Employees");
        TreeItem<String> subD = new TreeItem<>("Add Employee");
        TreeItem<String> subE = new TreeItem<>("Edit Employee");
        secB.getChildren().addAll(subC, subD, subE);
        
        TreeItem<String> subF = new TreeItem<>("Sales");
        TreeItem<String> subG = new TreeItem<>("Inventory");
        TreeItem<String> subH = new TreeItem<>("Other Things");
        secC.getChildren().addAll(subF, subG, subH);
        tree = new TreeView<>(MainRoot);
        tree.setShowRoot(false);
        tree.setMaxWidth(150);
        tree.setMaxHeight(300);*/
 
/*==============================Employee Center Display=======================================*/
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
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        
        FlowPane EmpFlow1 = new FlowPane();
        EmpFlow1.setStyle("-fx-background-color: #B09268");
        EmpFlow1.setVgap(20);
        EmpFlow1.setHgap(20);
        EmpFlow1.setPrefWrapLength(800);
        EmpFlow1.setPadding(new Insets(20,0,0,20));
        FlowPane EmpFlow2 = new FlowPane();
        EmpFlow2.setStyle("-fx-background-color: #B09268");
        EmpFlow2.setVgap(20);
        EmpFlow2.setHgap(20);
        EmpFlow2.setPrefWrapLength(800);
        EmpFlow2.setPadding(new Insets(20,0,0,20));
        FlowPane EmpFlow3 = new FlowPane();
        EmpFlow3.setStyle("-fx-background-color: #B09268");
        EmpFlow3.setVgap(20);
        EmpFlow3.setHgap(20);
        EmpFlow3.setPrefWrapLength(800);
        EmpFlow3.setPadding(new Insets(20,0,0,20));
        
        Button ButtVOrd = new Button("View Order");
        ButtVOrd.setEffect(dropShadow);
        ButtVOrd.setMinSize(200, 100);
        ButtVOrd.setMaxSize(200, 100);
        ButtVOrd.setPadding(new Insets(30, 20 , 30, 20));
        ButtVOrd.setStyle(""
        		+ "-fx-font-size: 20px;"
        		+ "-fx-border-radius: 50; "
        		+ "-fx-background-radius: 50; "
        		+ "-fx-background-color: #DECC8C");
        Button ButtVEmp = new Button("View Employee Report");
        ButtVEmp.setEffect(dropShadow);
        ButtVEmp.setMinSize(200, 100);
        ButtVEmp.setMaxSize(200, 100);
        ButtVEmp.setPadding(new Insets(30, 20 , 30, 20));
        ButtVEmp.setStyle(""
        		+ "-fx-font-size: 14px;"
        		+ "-fx-border-radius: 50; "
        		+ "-fx-background-radius: 50; "
        		+ "-fx-background-color: #F28A99");
        Button ButtVLoc = new Button("View Location Report");
        ButtVLoc.setEffect(dropShadow);
        ButtVLoc.setMinSize(200, 100);
        ButtVLoc.setMaxSize(200, 100);
        ButtVLoc.setPadding(new Insets(30, 20 , 30, 20));
        ButtVLoc.setStyle(""
        		+ "-fx-font-size: 14px;"
        		+ "-fx-border-radius: 50; "
        		+ "-fx-background-radius: 50; "
        		+ "-fx-background-color: #D0B040");
        Button ButtAddIngr = new Button("Add Ingredient");
        ButtAddIngr.setEffect(dropShadow);
        ButtAddIngr.setMinSize(200, 100);
        ButtAddIngr.setMaxSize(200, 100);
        ButtAddIngr.setPadding(new Insets(30, 20 , 30, 20));
        ButtAddIngr.setOnAction(e->AddEmployee.displayOrder());
        ButtAddIngr.setStyle(""
        		+ "-fx-font-size: 20px;"
        		+ "-fx-border-radius: 50; "
        		+ "-fx-background-radius: 50; "
        		+ "-fx-background-color: #DE986D");
        Button ButtAddemp = new Button("Add Employee");
        ButtAddemp.setEffect(dropShadow);
        ButtAddemp.setMinSize(200, 100);
        ButtAddemp.setMaxSize(200, 100);
        ButtAddemp.setPadding(new Insets(30, 20 , 30, 20));
        ButtAddemp.setOnAction(e-> AddEmployee.display());
        ButtAddemp.setStyle(""
        		+ "-fx-font-size: 20px;"
        		+ "-fx-border-radius: 50; "
        		+ "-fx-background-radius: 50; "
        		+ "-fx-background-color: #AB6890");
        Button ButtAddLoc = new Button("Add Location");
        ButtAddLoc.setEffect(dropShadow);
        ButtAddLoc.setMinSize(200, 100);
        ButtAddLoc.setMaxSize(200, 100);
        ButtAddLoc.setPadding(new Insets(30, 20 , 30, 20));
        ButtAddLoc.setOnAction(e-> AddEmployee.displayLocation());
        ButtAddLoc.setStyle(""
        		+ "-fx-font-size: 20px;"
        		+ "-fx-border-radius: 50; "
        		+ "-fx-background-radius: 50; "
        		+ "-fx-background-color: #68AB9F");
        Button ButtEditIngr = new Button("Edit Ingredient");
        ButtEditIngr.setEffect(dropShadow);
        ButtEditIngr.setMinSize(200, 100);
        ButtEditIngr.setMaxSize(200, 100);
        ButtEditIngr.setPadding(new Insets(30, 20 , 30, 20));
        ButtEditIngr.setStyle(""
        		+ "-fx-font-size: 20px;"
        		+ "-fx-border-radius: 50; "
        		+ "-fx-background-radius: 50; "
        		+ "-fx-background-color: #7096AE");
        Button ButtUpEmp = new Button("Update Employee");
        ButtUpEmp.setEffect(dropShadow);
        ButtUpEmp.setMinSize(200, 100);
        ButtUpEmp.setMaxSize(200, 100);
        ButtUpEmp.setPadding(new Insets(30, 20 , 30, 20));
        ButtUpEmp.setStyle(""
        		+ "-fx-font-size: 15px;"
        		+ "-fx-border-radius: 50; "
        		+ "-fx-background-radius: 50; "
        		+ "-fx-background-color: #DAA9B5");
        Button ButtUpLoc = new Button("Update Location");
        ButtUpLoc.setEffect(dropShadow);
        ButtUpLoc.setMinSize(200, 100);
        ButtUpLoc.setMaxSize(200, 100);
        ButtUpLoc.setPadding(new Insets(30, 20 , 30, 20));
        ButtUpLoc.setStyle(""
        		+ "-fx-font-size: 20px;"
        		+ "-fx-border-radius: 50; "
        		+ "-fx-background-radius: 50; "
        		+ "-fx-background-color: #AFE197");
        
        
        EmpFlow1.getChildren().addAll(ButtVOrd, ButtAddIngr, ButtEditIngr);
        EmpFlow2.getChildren().addAll(ButtVEmp, ButtAddemp, ButtUpEmp);
        EmpFlow3.getChildren().addAll(ButtVLoc, ButtAddLoc, ButtUpLoc);
        EmpFlow.getChildren().addAll(EmpFlow1a, EmpFlow1, EmpFlow2a, EmpFlow2, EmpFlow3a, EmpFlow3);
/*@@@@@@@@@@@@@@@@@@@@EMPLYOEE Main Page @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 * First page of the Employee GUI, contains List to add data, edit data, display form, display reports */        
        
        BorderPane empMenu1 = new BorderPane();
        VBox EmpLeftSet = new VBox();  
        EmpLeftSet.setStyle("-fx-background-color: #B09268");
        Image disImg1 = new Image("img/tapioca.png");
        ImageView iv12 = new ImageView();
        iv12.setImage(disImg1);
        EmpLeftSet.getChildren().add(iv12);
        empMenu1.setTop(menus);
        empMenu1.setLeft(EmpLeftSet);
        empMenu1.setCenter(EmpFlow);
        Scene EmpScene = new Scene(empMenu1, 1100, 700);
        


       
        
        
/*==================================CONDITIONAL LOGIN==============================================*/
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
                    Okay.display("Incorrect Login", "Bakaaaaa~~~ Please verify your credentials");
                    System.out.println("looololol, try 'Employee' in both fields or 'Customer' in both fields");

                }

            }

        });



/*=================CUSTOMER FIRST PAGE==================
 * First Page is the Welcome Page for Customers
 * -Displays "WELCOME TO DULCE TAPIOCA and "Began Order" button
 * -Start Button takes User to Customer Second Page*/
        Label lab2 = new Label("Welcome");
        lab2.setPadding(new Insets (10, 10, 10, 10));
        lab2.setStyle("-fx-font: 40 arial;");

        Label lab3 = new Label("to");
        lab3.setPadding(new Insets (10, 10, 10, 10));
        lab3.setStyle("-fx-font: 40 arial;");

        Label lab4 = new Label("Dulce Tapioca!");
        lab4.setPadding(new Insets (10, 10, 10, 10));
        lab4.setStyle("-fx-font: 40 arial;");

        Button start = new Button("Begin Order");
        start.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");
        inception.getChildren().addAll(lab2, lab3, lab4,start);
        Anchor.setCenter(inception);

        Pane can1 = new Pane();
        can1.setPrefSize(0, 100);
        Anchor.setLeft(can1);
        inception.setAlignment(Pos.CENTER);
        Anchor.setStyle("");


        Scene scene2 = new Scene(bordPane,1360,900);
        start.setOnAction(e-> window.setScene(scene2));


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
        ButtonA.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        ButtonA.setMinWidth(300);
        ButtonA.setAlignment(Pos.CENTER);

        Button ButtonB = new Button("Cancel Order");
        ButtonB.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        ButtonB.setMinWidth(300);
        ButtonB.setAlignment(Pos.CENTER);

        bottomMenu.getChildren().addAll(ButtonA, ButtonB);

        //Right Menu Items
        ImageView iv2 = new ImageView();
        iv2.setImage(disImg);

        rightMenu.setPadding(new Insets(50, 40, 30, 30));
        rightMenu.setSpacing(20);
        rightMenu.getChildren().add(iv2);

        //Scene 2 Center Menu Items
        centerMenu.setPadding(new Insets(50, 40, 30, 30));
        centerMenu.setSpacing(40);

        Button ButtonD = new Button("Smoothie");
        ButtonD.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");
        ButtonD.setMinWidth(300);
        ButtonD.setAlignment(Pos.CENTER);
        ButtonD.setOnAction(e-> window.setScene(scene3));

        Button ButtonE = new Button("Tea");
        ButtonE.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");
        ButtonE.setMinWidth(300);
        ButtonE.setAlignment(Pos.CENTER);
        ButtonE.setOnAction(e-> {
            boolean ans = ConfirmOrder.display("Print Order", "Would you like to print your order?");

            if (ans == true) {
                Okay.display("Order Complete", "Printing Order");

                window.setScene(scene1);
            } else {
                window.setScene(scene2);
            }
        });

        Button ButtonF = new Button("Coffee");
        ButtonF.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");
        ButtonF.setMinWidth(300);
        ButtonF.setAlignment(Pos.CENTER);

        Button ButtonG = new Button("Frio");
        ButtonG.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");
        ButtonG.setMinWidth(300);
        ButtonG.setAlignment(Pos.CENTER);

        Button ButtonH = new Button("Milk Tea");
        ButtonH.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");
        ButtonH.setMinWidth(300);
        ButtonH.setAlignment(Pos.CENTER);

        centerMenu.getChildren().addAll(ButtonD, ButtonE, ButtonF, ButtonG, ButtonH);

        ButtonA.setOnAction(e-> window.setScene(scene1));
        //Button B always go back to main menu, NOTE: ADD CONFIRMATION scene to this-
        ButtonB.setOnAction(e-> window.setScene(scene1));


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

        //Bottom Menu Items
        bottomMenu3.setPadding(new Insets(30, 30, 30, 30));
        bottomMenu3.setSpacing(30);

        Button ButtonA3 = new Button("Go Back");
        ButtonA3.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        ButtonA3.setMinWidth(300);
        ButtonA3.setAlignment(Pos.CENTER);

        Button ButtonB3 = new Button("Cancel Order");
        ButtonB3.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        ButtonG.setMinWidth(300);
        ButtonG.setAlignment(Pos.CENTER);


        Button ButtonC3 = new Button("Continue");
        ButtonC3.setStyle("-fx-font: 35 arial; -fx-base: #7CD674");
        ButtonC3.setMinWidth(300);
        ButtonC3.setAlignment(Pos.CENTER);

        bottomMenu3.getChildren().addAll(ButtonA3, ButtonB3, ButtonC3);

        ButtonB3.setOnAction(e-> window.setScene(scene1));
        ButtonA3.setOnAction(e-> window.setScene(scene2));

        //Right Menu - Image
        ImageView iv3 = new ImageView();
        iv3.setImage(disImg);
        rightMenu3.setPadding(new Insets(50, 40, 30, 30));
        rightMenu3.setSpacing(20);
        rightMenu3.getChildren().add(iv3);


        //Scene 2 Center Menu Items

        Button flavorFlave = new Button ("Poop");
        flavorFlave.setMinWidth(300);
        flavorFlave.setAlignment(Pos.CENTER);
        flavorFlave.setMinHeight(150);

        Button flavorFlave2 = new Button("More Poop");
        flavorFlave2.setMinWidth(300);
        flavorFlave2.setAlignment(Pos.CENTER);
        flavorFlave2.setMinHeight(150);

        Button flavor3 = new Button("Even More Poop");
        flavor3.setMinWidth(300);
        flavor3.setAlignment(Pos.CENTER);
        flavor3.setMinHeight(150);

        Button sf4 = new Button("Fresh Avocado");
        sf4.setMinWidth(300);
        sf4.setAlignment(Pos.CENTER);
        sf4.setMinHeight(150);

        Button sf5 = new Button("Fresh Strawberry");
        sf5.setMinWidth(300);
        sf5.setAlignment(Pos.CENTER);
        sf5.setMinHeight(150);

        Button sf6 = new Button("Fresh Mango");
        sf6.setMinWidth(300);
        sf6.setAlignment(Pos.CENTER);
        sf6.setMinHeight(150);

        Button sf7 = new Button("Fresh Banana");
        sf7.setMinWidth(300);
        sf7.setAlignment(Pos.CENTER);
        sf7.setMinHeight(150);

        Button sf8 = new Button("Fresh Poop");
        sf8.setMinWidth (300);
        sf8.setAlignment(Pos.CENTER);
        sf8.setMinHeight(150);

        Button sf9 = new Button("Fresh Raspberry");
        sf9.setMinWidth(300);
        sf9.setAlignment(Pos.CENTER);
        sf9.setMinHeight(150);

        Button sf10 = new Button("Fresh Raspberry");
        sf10.setMinWidth(300);
        sf10.setAlignment(Pos.CENTER);
        sf10.setMinHeight(150);

        Button sf11 = new Button("Fresh Raspberry");
        sf11.setMinWidth(300);
        sf11.setAlignment(Pos.CENTER);
        sf11.setMinHeight(150);

        Button sf12 = new Button("Fresh Raspberry");
        sf12.setMinWidth(300);
        sf12.setAlignment(Pos.CENTER);
        sf12.setMinHeight(150);

        smoothFlow.getChildren().addAll(flavorFlave, flavorFlave2, flavor3, sf4, sf5, sf6, sf7, sf8, sf9, sf10, sf11,
                sf12);

        //centerMenu3.getChildren().addAll(cb1, temp1, rb1, rb2, rb3);
    }

	public void mouseClick(MouseEvent mouseEvent){
		//TreeItem<String> item = tree.getSelectionModel().getSelectedItem();
		//System.out.println(item.getValue());
	}

}

/*
/******************* THIS SPOT IS RESERVERED FOR USEFUL JUNK ***********************/
  /*centerMenu3.setPadding(new Insets(50, 40, 30, 30));
        centerMenu3.setSpacing(10);
        ChoiceBox cb1 = new ChoiceBox();
        cb1.getItems().addAll("Smoothies", "Testing", "Is", "Fun", "But", "Smoothies", "Go", "Here");
        cb1.getSelectionModel().selectFirst();
        cb1.setTooltip(new Tooltip("Select your smoothie"));
        cb1.setMinWidth(300);
        cb1.setStyle("-fx-font: 30 arial; -fx-base: #FFC524");
        Label temp1 = new Label("Temperature");
        temp1.setPadding(new Insets(40, 10, 40, 10));
        temp1.setStyle("-fx-font: 35 arial; -fx-base: #FFC524");
        RadioButton rb1 = new RadioButton("Cold");
        RadioButton rb2 = new RadioButton("Normal");
        RadioButton rb3 = new RadioButton("Hot");*/