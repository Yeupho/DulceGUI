package sample;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class Okay {

    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(270);
        window.setMinHeight(220);

        Label label = new Label();
        label.setText(message);
        label.setStyle("-fx-font: 16.5 arial; -fx-base: #FFC524");

        Button closeButton = new Button("Okay");
        closeButton.setStyle("-fx-font: 16.5 arial; -fx-base: #FFC524");
        closeButton.setMinWidth(150);
        closeButton.setAlignment(Pos.CENTER);
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setStyle("-fx-background-color: #B09268");
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}