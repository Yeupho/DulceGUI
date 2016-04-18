package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class EditThings {
	static ComboBox<String> combobox;
	static ComboBox<String> dankbox;
	static ComboBox<String> junkbox;
	static ComboBox<String> LocBox;
	static int Test;
	
	private static ObservableList<ObservableList> data;
	private static TableView tableview;

	public static void buildData(String InsertSql) {
		Connection c;
		data = FXCollections.observableArrayList();
		try {
			c = DBconnect.connect();
			// SQL FOR SELECTING ALL OF CUSTOMER
			String SQL = InsertSql;

			// ResultSet
			ResultSet rs = c.createStatement().executeQuery(SQL);

			/**********************************
			 * TABLE COLUMN ADDED DYNAMICALLY *
			 **********************************/
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				// We are using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j).toString());
							}
						});
				col.setCellFactory(TextFieldTableCell.forTableColumn());
				col.setEditable(true);
				col.setOnEditCommit(
			            new EventHandler<CellEditEvent<ObservableList, String>>() {
			                @Override
			                public void handle(CellEditEvent<ObservableList, String> t) {
			                    ((ObservableList) t.getTableView().getItems().get(
			                            t.getTablePosition().getRow())
			                            ).setAll(t.getNewValue());
			                }
			            }
			        );
				
				tableview.getColumns().addAll(col);
				System.out.println("Column [" + i + "] ");
			}
			
			/********************************
			 * Data added to ObservableList *
			 ********************************/
			while (rs.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					String notthere = "---";
					rs.getString(i);
					if (rs.wasNull()) {
						row.add(notthere);
					} else {
						row.add(rs.getString(i));
					}

				}
				System.out.println("Row [1] added " + row);
				data.add(row);
				
			}

			// FINALLY ADDED TO TableView
			tableview.setItems(data);
			tableview.setMaxHeight(200);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
	}

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
			combobox= new ComboBox<>();
			LocBox = new ComboBox<>();
			Label IndexOf = new Label();
			Label FirstName = new Label("First Name");
			Label LastName = new Label("Last Name");
			Label Phone = new Label("Phone Number");
			Label Role = new Label("Role");
			Label WorkAddress = new Label("Work Location: ");
			TextField InFirstName = new TextField();
			TextField InLastName = new TextField();
			TextField InPhone = new TextField();
			try {

				// This creates a connection to the
				Connection c = DBconnect.connect();
				String SQL = "SELECT Employee.EmployeeID, Employee.FirstName, Employee.LastName FROM Employee;";
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
					Name = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3);
					dankbox.getItems().add(Name);
				}

			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			try {
				String RoleList = null;
				
				Connection c = DBconnect.connect();
				String SQL = "SELECT Role.RoleID, Role.RoleName FROM Role;";
				ResultSet rs = c.createStatement().executeQuery(SQL);
				while (rs.next()) {
					RoleList = rs.getString(1) +" "+ rs.getString(2);
					combobox.getItems().add(RoleList);
					
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				String Address = null;
				
				Connection c = DBconnect.connect();
				String SQL = "SELECT Location.LocationID, Location.Address FROM Location;";
				ResultSet rs = c.createStatement().executeQuery(SQL);
				while (rs.next()) {
					Address = rs.getString(1) + ", " + rs.getString(2);
					LocBox.getItems().add(Address);
					
				}
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Button Update = new Button("Update");
			Button Commit = new Button("Commit Changes");
			SelectEm.getChildren().addAll(dankbox, Update, Commit);
			dankbox.getSelectionModel().getSelectedItem();

			CenterValue.getChildren().addAll(SelectEm, FirstName, InFirstName, LastName, InLastName, Phone, 
					InPhone, WorkAddress, LocBox, Role, combobox);

			Update.setOnAction(a -> {

				int Test = dankbox.getSelectionModel().getSelectedIndex();
				Test++;
				try {
					String Index;
					String First;
					String Last;
					String Phone1;
					String RoleSet = null; 
					String AddressSet = null;
					// This creates a connection to the
					Connection c = DBconnect.connect();
					String SQL = "SELECT * FROM Employee " + "WHERE EmployeeID =" + Test + ";";
					ResultSet rs = c.createStatement().executeQuery(SQL);
					rs.next();
					Index = rs.getString(2);
					First = rs.getString(2);
					Last = rs.getString(3);
					Phone1 = rs.getString(4);
					AddressSet = rs.getString(7);
					RoleSet = rs.getString(8);
					
					IndexOf.setText(Index);
					InFirstName.setText(First);
					InLastName.setText(Last);
					InPhone.setText(Phone1);
					Role.setText("RoleID: " + RoleSet);
					WorkAddress.setText("Work Location: " + AddressSet);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				System.out.println(Test);
				
				
				if (InFirstName != null && InLastName !=null && LocBox.getSelectionModel() !=null && 
						combobox.getSelectionModel() != null ){
				
					
					Commit.setOnAction(d->{						
						try {
							
							int ID1 = LocBox.getSelectionModel().getSelectedIndex();
							int ID2 = combobox.getSelectionModel().getSelectedIndex();
							int ID3 = dankbox.getSelectionModel().getSelectedIndex();
							ID1++;
							ID2++;
							ID3++;
							Connection c = DBconnect.connect();
							String SQL = "UPDATE Employee "
									+ "SET FirstName = '"+InFirstName.getText()+"', LastName = '"+InLastName.getText()+"', "
									+ "EmployeePhone = '"+InPhone.getText()+"', "
									+ "LocationID = '"+ID1+"', "
									+ "RoleID = '"+ID2+"' "
									+ "WHERE EmployeeID = "+ID3 +";";
							System.out.println(SQL);
							ResultSet rs = c.createStatement().executeQuery(SQL);

						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
					});
				}
				else {
					
				}
			});
			
		});
		/* =================Button Employee Account======================== */
		ButtEmpBankInfo.setOnAction(e -> {
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
			CenterValue.getChildren().add(SelectEm);

		});
		
		
		/*===========Update Employee Schedules ===============*/
		ButtEmpSched.setOnAction(e->{
			SelectEm.getChildren().clear();
			CenterValue.getChildren().clear();
			dankbox = new ComboBox<>();
			combobox= new ComboBox<>();
			LocBox = new ComboBox<>();
			Label IndexOf = new Label();
			Label FirstName = new Label("First Name");
			Label LastName = new Label("Last Name");
			Label Phone = new Label("Phone Number");
			Label Role = new Label("Role");
			
			
			
			try {

				// This creates a connection to the
				Connection c = DBconnect.connect();
				String SQL = "SELECT Employee.EmployeeID, Employee.FirstName, Employee.LastName FROM Employee;";
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
					Name = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3);
					dankbox.getItems().add(Name);
				}

			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			Button Update = new Button("Update");
			SelectEm.getChildren().addAll(dankbox, Update);
			CenterValue.getChildren().add(SelectEm);
			
			Update.setOnAction(a-> {
				int Test = dankbox.getSelectionModel().getSelectedIndex();
				Test++;
				String SQL1 = "SELECT WeekSchedule.Date, DayofWeekz.DayName, HoursWorked.ShiftName "
						+ "FROM WeekSchedule "
						+ "FULL OUTER JOIN Employee ON WeekSchedule.EmployeeID = Employee.EmployeeID "
						+ "FULL OUTER JOIN HoursWorked ON WeekSchedule.ShiftID = HoursWorked.ShiftID "
						+ "FULL OUTER JOIN DayofWeekz ON WeekSchedule.DayID = DayofWeekz.DayID "
						+ "WHERE Employee.EmployeeID = "+Test+" "
						+ "ORDER BY WeekSchedule.Date DESC; ";
				tableview = new TableView();
						
				buildData(SQL1);
				CenterValue.getChildren().clear();
				CenterValue.getChildren().addAll(SelectEm, tableview);
			});
		});
		Scene layout = new Scene(Main, 1000, 600);
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
							Label ChangeCon = new Label("*Change has been comitted.");
							ChangeCon.setStyle("-fx-text-fill: red;");
							CenterValue.getChildren().add(ChangeCon);
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
				Commit.setOnAction(b->{
					try {
						
						int ID1 = LocBox.getSelectionModel().getSelectedIndex();
						int ID2 = combobox.getSelectionModel().getSelectedIndex();
						int ID3 = dankbox.getSelectionModel().getSelectedIndex();
						ID1++;
						ID2++;
						ID3++;
						Connection c = DBconnect.connect();
						String SQL = "UPDATE Location "
								+ "SET Address = '"+InFirstName.getText()+"', RentCost = '"+InLastName.getText()+"', "
								+ "EmployeePhone = '"+InPhone.getText()+"' "

								+ "WHERE EmployeeID = "+ID3 +";";
						System.out.println(SQL);
						ResultSet rs = c.createStatement().executeQuery(SQL);

					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				});
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
