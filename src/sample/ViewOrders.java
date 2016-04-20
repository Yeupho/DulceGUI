package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import javafx.util.Callback;

import javax.swing.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.Duration;
import java.util.*;

public class ViewOrders {
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
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
	}

	public static void GeneralOrder() {

		String SQL = " SELECT Ticket.TicketID, OrderTable.OrderID, Drink.DrinkName, Ice.IceLevel, SugarLevel.AmountDesc, Size.SizeOptions, "
				+ " Temperature.TemperatureOption, Topping.ToppingName, TrantnOrder.Cost "
				+ " FROM OrderTable"
				+ " INNER JOIN Drink ON OrderTable.DrinkID = Drink.DrinkID "
				+ " INNER JOIN Ice ON OrderTable.Ice = Ice.Ice "
				+ " INNER JOIN SugarLevel ON OrderTable.Sugar = SugarLevel.LevelID"
				+ " INNER JOIN Size ON OrderTable.Size = Size.Size "
				+ " INNER JOIN Temperature ON OrderTable.Temperature = Temperature.Temperature "
				+ " INNER JOIN Topping ON OrderTable.Topping = Topping.ToppingID "
				+ " INNER JOIN TrantnOrder ON OrderTable.OrderID = TrantnOrder.OrderID"
				+ " INNER JOIN Ticket ON TrantnOrder.TicketID = Ticket.TicketID"
				+ " WHERE OrderTable.ORDERID IS NOT NULL"
				+ " ORDER BY Ticket.TicketID DESC;";

		String SQL2 = "SELECT DrinkType.TypeName, Drink.DrinkID, Drink.DrinkName, Drink.Cost " + "FROM Drink "
				+ "FULL OUTER JOIN DrinkType ON Drink.DrinkTypeID = DrinkType.DrinkTypeID "
				+ "ORDER BY DrinkType.TypeName;";
		/*String SQL3 = "SELECT DrinkType.TypeName, Drink.DrinkName, OrderTable.OrderID, OrderTable.Size, OrderTable.Topping "
				+ "FROM OrderTable "
				+ "FULL OUTER JOIN Drink ON OrderTable.DrinkID = Drink.DrinkID "
				+ "FULL OUTER JOIN DrinkType ON Drink.DrinkTypeID = DrinkType.DrinkTypeID; ";*/
		
		Stage window = new Stage();
		Label GeneralOrder = new Label("Order Reports");
		Label RandomInfo = new Label("<-Select one of the options to view in the left column");
		RandomInfo.setStyle("-fx-font-size: 20;");
		
		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.3, 0.3, 0.3));
		
		Button ButtGenOrd = new Button("Latest Orders");
			ButtGenOrd.setEffect(dropShadow);
			ButtGenOrd.setMinSize(150, 50);
			ButtGenOrd.setMaxSize(100, 50);
			ButtGenOrd.setStyle("" 
					+ "-fx-font-size: 15px;"  
					+ "-fx-background-radius:100; "
					+ "-fx-background-color: #FFC524");
		Button ButtViewIng = new Button("View Ingredients");
			ButtViewIng.setEffect(dropShadow);
			ButtViewIng.setMinSize(150, 50);
			ButtViewIng.setMaxSize(100, 50);
			ButtViewIng.setStyle("" 
					+ "-fx-font-size: 15px;"  
					+ "-fx-background-radius:100; "
					+ "-fx-background-color: #DAA9B5");
		/*Button ButtOrdType = new Button("Orders by Drinktype");
			ButtOrdType.setMinSize(150, 50);
			ButtOrdType.setMaxSize(100, 50);
			ButtOrdType.setStyle("" 
					+ "-fx-font-size: 13px;"  
					+ "-fx-background-radius:100; "
					+ "-fx-background-color: #7096AE");*/

		BorderPane layout = new BorderPane();
		VBox Left = new VBox();
		VBox CenterValue = new VBox();
		ScrollPane Scrolls = new ScrollPane();
		Scrolls.setContent(CenterValue);
		CenterValue.getChildren().addAll(RandomInfo);
		VBox Right = new VBox();
		Label Stuff = new Label("                 ");
		Right.minWidth(100);
		Right.getChildren().add(Stuff);
		layout.setRight(Right);
		//CSS Main 
		window.setTitle("Update Locations");
		window.initModality(Modality.APPLICATION_MODAL);
		layout.setStyle("-fx-background-color: #7096AE;");
		GeneralOrder.setStyle("-fx-font-size: 40;"
				+ "-fx-text-fill: white;");
		GeneralOrder.setPadding(new Insets(30, 30, 30, 30));
		
		
		ButtGenOrd.setOnAction(e -> {
			tableview = new TableView();
			buildData(SQL);
			CenterValue.getChildren().clear();
			CenterValue.getChildren().addAll(tableview);
		});
		ButtViewIng.setOnAction(e -> {
			tableview = new TableView();
			buildData(SQL2);
			CenterValue.getChildren().clear();
			CenterValue.getChildren().addAll(tableview);
		});
		/*ButtOrdType.setOnAction(e -> {
			tableview = new TableView();
			buildData(SQL3);
			CenterValue.getChildren().clear();
			CenterValue.getChildren().addAll(tableview);
		});*/
		Left.getChildren().addAll(ButtGenOrd, ButtViewIng);
		
		//CSS to set everything 
		Left.setPadding(new Insets(0, 10, 0, 10));
		HBox Bottom = new HBox();
		Bottom.setPadding(new Insets(0, 10, 30, 30));
		Button Close = new Button("Close");
		Close.setOnAction(e-> window.close());
		Close.setEffect(dropShadow);
		Close.setPrefWidth(100);
		Close.setStyle("" 
					+ "-fx-font-size: 20px;"
					+ "-fx-background-radius:50; "
					+ "-fx-background-color: #ff6961 ");
		Bottom.getChildren().add(Close);
		layout.setBottom(Bottom);
		
		layout.setTop(GeneralOrder);
		layout.setLeft(Left);
		layout.setCenter(CenterValue);
		// Main Scene
		Scene scene = new Scene(layout, 1000, 600);

		window.setScene(scene);
		window.show();
		//CSS Styles 
		
		
	}
}
