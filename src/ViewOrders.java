import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;

import javax.swing.*;

import java.util.*;

public class ViewOrders {
	static TableView<Products> table1;
	public static void display(){
		Stage window = new Stage();
		window.setTitle("Add Employee");
		window.initModality(Modality.APPLICATION_MODAL);
		
		
		 
		
		TableColumn<Products, String> nameColumn = new TableColumn<>("OrderName");
		nameColumn.setMinWidth(30);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Products, String> typeColumn = new TableColumn<>("Type");
		typeColumn.setMinWidth(30);
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
		
		TableColumn<Products, String> costColumn = new TableColumn<>("Cost");
		costColumn.setMinWidth(30);
		costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
		
		table1 = new TableView<>();
		table1.setItems(getProduct());
		table1.getColumns().addAll(nameColumn,typeColumn,costColumn);
		
		BorderPane ord_ViewOrd = new BorderPane();
		ord_ViewOrd.setCenter(table1);
		 Scene OrdViewScene1 = new Scene(ord_ViewOrd, 700, 700);
		 window.setScene(OrdViewScene1);
		 window.showAndWait();
	}
	public static ObservableList<Products> getProduct(){
		ObservableList<Products> products = FXCollections.observableArrayList();
		products.add(new Products("Avocado", "Smoothie", 3.75));
		products.add(new Products("Banana", "Smoothie", 3.75));
		products.add(new Products("Oolong Tea", "Hot Tea", 2.25));
		products.add(new Products("Thai Tea", "Milk Tea", 3.25));
		return products;
	}
}
