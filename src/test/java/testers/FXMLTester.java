package testers;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.computer_graphics.controller.gui.CanvasController;
import com.computer_graphics.controller.gui.TestController;

public class FXMLTester extends Application{

	@Override
	public void start(Stage primaryStage) {
		try {
			
				
			FXMLLoader loader = new FXMLLoader();
			
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			
			URL location = getClass().getResource("/TestFXML.fxml");
		
			
			FXMLLoader fxmlLoader = new FXMLLoader();
			
			fxmlLoader.setLocation(location);
			
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			
			StackPane root2 =  fxmlLoader.load(location.openStream());
			
			TestController test = fxmlLoader.getController();
		
			Scene scene = new Scene(root2,800,600,true);
		
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
