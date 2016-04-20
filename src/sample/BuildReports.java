package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class BuildReports {

	private static ObservableList<ObservableList> data;
	private static TableView tableview; 
	
	public static void BuildRep (String RepName, String InsertSql, String OrderBy, String DateCol) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		tableview = new TableView();
		BorderPane layout = new BorderPane();
		layout.setStyle("-fx-background-color: #B09268");
		VBox TopBox = new VBox();
		HBox TopBox1 = new HBox();
		VBox CenterBox = new VBox();
		
		
		DatePicker SelectDate = new DatePicker();
		SelectDate.setOnAction(e -> {
			LocalDate date = SelectDate.getValue();
			System.out.println(date);
			});
		DatePicker SelectDate1 = new DatePicker();
		SelectDate1.setOnAction(e -> {
			LocalDate date = SelectDate1.getValue();
			System.out.println(date);
			});
		Label TopTitle = new Label(RepName);
		TopTitle.setStyle("-fx-font-size: 40;");
		TopTitle.setPadding(new Insets(20, 20, 20, 20));
		Label WordTo = new Label (" to ");
		Button Go = new Button("Go");
		
		TopBox.getChildren().addAll(TopTitle, TopBox1);
		TopBox1.getChildren().addAll(SelectDate, WordTo, SelectDate1, Go);
		
		TopBox1.setPadding(new Insets(0, 20, 20, 20));
		WordTo.setPadding(new Insets(0, 20, 0, 20));

		
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
					String notthere = "null";
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
			tableview.setItems(data);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		CenterBox.getChildren().add(tableview);
		Go.setOnAction(e->{
			CenterBox.getChildren().clear();
			LocalDate date = SelectDate.getValue();
			LocalDate date1 = SelectDate1.getValue();
			String AddWhere = " WHERE "+DateCol+" BETWEEN '"+date+" 00:00:000' AND '"+date1+" 00:00:000' "; 
			String SQL = InsertSql + AddWhere;
			data = FXCollections.observableArrayList();
			tableview = new TableView();
			try {
				 Connection d = DBconnect.connect();
				// SQL FOR SELECTING ALL OF CUSTOMER
				

				// ResultSet
				ResultSet rs = d.createStatement().executeQuery(SQL);

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
						String notthere = "null";
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
				tableview.setItems(data);
			} catch (Exception ea) {
				ea.printStackTrace();
				System.out.println("Error on Building Data");
			}
			CenterBox.getChildren().add(tableview);
		});
		HBox filler = new HBox();
		filler.setPadding(new Insets(10,10,10,10));
		layout.setLeft(filler);
		layout.setTop(TopBox);
		layout.setCenter(CenterBox);
		Scene scene = new Scene(layout, 950, 600);
		window.setScene(scene);
		window.show();
	}
	
	
	
	public static void BuildRep2 (String RepName, String InsertSql, String OrderBy, String DateCol) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		tableview = new TableView();
		BorderPane layout = new BorderPane();
		layout.setStyle("-fx-background-color: #B09268");
		VBox TopBox = new VBox();
		HBox TopBox1 = new HBox();
		VBox CenterBox = new VBox();
		
		
		DatePicker SelectDate = new DatePicker();
		SelectDate.setOnAction(e -> {
			LocalDate date = SelectDate.getValue();
			System.out.println(date);
			});
		DatePicker SelectDate1 = new DatePicker();
		SelectDate1.setOnAction(e -> {
			LocalDate date = SelectDate1.getValue();
			System.out.println(date);
			});
		Label TopTitle = new Label(RepName);
		TopTitle.setStyle("-fx-font-size: 40;");
		TopTitle.setPadding(new Insets(20, 20, 20, 20));
		Label WordTo = new Label (" to ");
		Button Go = new Button("Go");
		
		TopBox.getChildren().addAll(TopTitle, TopBox1);
		TopBox1.getChildren().addAll(SelectDate, WordTo, SelectDate1, Go);
		
		TopBox1.setPadding(new Insets(0, 20, 20, 20));
		WordTo.setPadding(new Insets(0, 20, 0, 20));

		
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
					String notthere = "null";
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
			tableview.setItems(data);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		CenterBox.getChildren().add(tableview);
		Go.setOnAction(e->{
			CenterBox.getChildren().clear();
			LocalDate date = SelectDate.getValue();
			LocalDate date1 = SelectDate1.getValue();
			String AddWhere = " WHERE "+DateCol+" BETWEEN '"+date+"' AND '"+date1+"' "; 
			String SQL = InsertSql + AddWhere;
			data = FXCollections.observableArrayList();
			tableview = new TableView();
			try {
				 Connection d = DBconnect.connect();
				// SQL FOR SELECTING ALL OF CUSTOMER
				

				// ResultSet
				ResultSet rs = d.createStatement().executeQuery(SQL);

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
						String notthere = "null";
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
				tableview.setItems(data);
			} catch (Exception ea) {
				ea.printStackTrace();
				System.out.println("Error on Building Data");
			}
			CenterBox.getChildren().add(tableview);
		});
		HBox filler = new HBox();
		filler.setPadding(new Insets(10,10,10,10));
		layout.setLeft(filler);
		layout.setTop(TopBox);
		layout.setCenter(CenterBox);
		Scene scene = new Scene(layout, 900, 600);
		window.setScene(scene);
		window.show();
	}
	public static void BuildRep3 (String RepName, String InsertSql, String OrderBy) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		tableview = new TableView();
		BorderPane layout = new BorderPane();
		layout.setStyle("-fx-background-color: #B09268");
		VBox TopBox = new VBox();
		HBox TopBox1 = new HBox();
		VBox CenterBox = new VBox();
		
		
		
		Label TopTitle = new Label(RepName);
		TopTitle.setStyle("-fx-font-size: 40;");
		TopTitle.setPadding(new Insets(20, 20, 20, 20));
		Label WordTo = new Label (" to ");
		Button Go = new Button("Go");
		
		TopBox.getChildren().addAll(TopTitle, TopBox1);
		
		
		TopBox1.setPadding(new Insets(0, 20, 20, 20));
		WordTo.setPadding(new Insets(0, 20, 0, 20));

		
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
					String notthere = "null";
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
			tableview.setItems(data);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		CenterBox.getChildren().add(tableview);
		Go.setOnAction(e->{
			CenterBox.getChildren().clear();
			
			
			String SQL = InsertSql;
			data = FXCollections.observableArrayList();
			tableview = new TableView();
			try {
				 Connection d = DBconnect.connect();
				// SQL FOR SELECTING ALL OF CUSTOMER
				

				// ResultSet
				ResultSet rs = d.createStatement().executeQuery(SQL);

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
						String notthere = "null";
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
				tableview.setItems(data);
			} catch (Exception ea) {
				ea.printStackTrace();
				System.out.println("Error on Building Data");
			}
			CenterBox.getChildren().add(tableview);
		});
		HBox filler = new HBox();
		filler.setPadding(new Insets(10,10,10,10));
		layout.setLeft(filler);
		layout.setTop(TopBox);
		layout.setCenter(CenterBox);
		Scene scene = new Scene(layout, 900, 600);
		window.setScene(scene);
		window.show();
	}
		
}