package main.java;

import java.io.File;
import java.net.URI;
import java.net.URL;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main
extends Application {
	public void start(Stage primaryStage) {
		try {
			URL url = Main.class.getResource("/TelaPrincipal.fxml");
			System.out.println(url);
			Parent root = FXMLLoader.load(url);
			Scene scene = new Scene(root, 700.0D, 475.0D);
			scene.getStylesheets().add("application.css");
			System.out.println(new File("imagens/ares.png").toURI().toString());
			primaryStage.getIcons().add(new Image(new File("ares.png").toURI().toString()));
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public static void main(String[] args) {
		launch(args);
	}
}


