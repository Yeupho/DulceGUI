package sample; 

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.stage.Stage;

import javax.swing.*;

import java.util.*;

public class About {
	
	public static void display(String title){
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(400);
		window.setMinHeight(550);
		
		Label label = new Label();
		label.setText("Employee GUI for Solar Group\n"
				+ "Version: Dulce.1 Release (1.8.0)\n"
				+ "Build id: 20160421-420\n"
				+ "(c) Team Solar contributors 2015."); 		
		
		label.setStyle("-fx-font-size: 20px");
		label.setTextAlignment(TextAlignment.CENTER);
		
		Button closeButton = new Button("Close");
		closeButton.setOnAction(e->window.close());
		
		VBox layout = new VBox(20);
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setStyle("-fx-background-color: #B09268");
        Image disImg1 = new Image("TeamLogo.png");
        ImageView iv12 = new ImageView();
        iv12.setImage(disImg1);
        layout.getChildren().add(iv12);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);	
		
		Scene scene = new Scene(layout); 
		window.setScene(scene);
		window.showAndWait();
	}
}