package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmOrder {

	static boolean answer;

	public static boolean display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		window.setMinHeight(250);

		Label l = new Label();
		l.setText(message);
		l.setStyle("-fx-font: 16.5 arial;");
		l.setAlignment(Pos.CENTER);

		Button yes = new Button("Yes");
		yes.setStyle("-fx-font: 16.5 arial; -fx-base: #FFC524");
		yes.setMinWidth(150);
		yes.setAlignment(Pos.CENTER);

		Button no = new Button("No");
		no.setStyle("-fx-font: 16.5 arial; -fx-base: #FFC524");
		no.setMinWidth(150);
		no.setAlignment(Pos.CENTER);

		yes.setOnAction(e -> {
			answer = true;
			window.close();
		});

		no.setOnAction(e -> {
			answer = false;
			window.close();
		});

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(1);

		GridPane.setConstraints(yes, 0, 2);

		GridPane.setConstraints(no, 2, 2);

		GridPane.setConstraints(l, 1, 1);

		Image disImg = new Image("img/tapioca.png");
		ImageView iv = new ImageView();
		iv.setImage(disImg);
		GridPane.setConstraints(iv, 1, 0);

		grid.setStyle("-fx-background-color: #B09268");
		grid.getChildren().addAll(l, yes, no, iv);
		grid.setAlignment(Pos.CENTER);

		Scene s = new Scene(grid);
		window.setScene(s);
		window.showAndWait();

		return answer;
	}
}