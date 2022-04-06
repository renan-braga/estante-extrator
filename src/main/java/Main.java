package main.java;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;

public class Main
extends Application {
	public void start(Stage primaryStage) {
		try {
			System.out.println("arquivo");
			ClassLoader loader = getClass().getClassLoader();

			URL url = new URL(loader.getResource("main/resources/fxml/TelaPrincipal.fxml").toExternalForm());
			System.out.println(url);
			Parent root = FXMLLoader.load(url);
			Scene scene = new Scene(root, 700.0D, 475.0D);
			scene.getStylesheets().add(loader.getResource("main/resources/css/application.css").toExternalForm());
			primaryStage.getIcons().add(
					new Image(loader.getResource("main/resources/imagens/ares.png").toExternalForm()));
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


