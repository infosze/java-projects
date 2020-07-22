package hu.ak_akademia.book4you;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("views/Cashier.fxml"));

			Image icon = new Image("hu/ak_akademia/book4you/views/img/icon.png");
			primaryStage.getIcons().add(icon);

			Scene scene = new Scene(root, 1024, 600);
			scene.getStylesheets().add(getClass().getResource("views/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("A&K Books Kft. – Book4You Software (B4Y)");

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
