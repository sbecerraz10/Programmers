package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.Event;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static Event event;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Index.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		event = new Event();
		launch(args);
	}

	public static Event getEvent() {
		return event;
	}

	public static void setEvent(Event event) {
		Main.event = event;
	}
	
	
	
}
