package project1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Driver extends Application {
	private BorderPane bpBase = new BorderPane();

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Project 0");

		Scene scene = new Scene(bpBase, 500, 400);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
