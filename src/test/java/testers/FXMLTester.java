package testers;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import com.computer_graphics.constants.files.FileConstants;
import com.computer_graphics.controller.gui.BlendController;
import com.computer_graphics.controller.gui.CanvasController;
import com.computer_graphics.controller.gui.WrapController;
import com.computer_graphics.logs.LogsImage;

public class FXMLTester extends Application{

	@Override
	public void start(Stage primaryStage) {
		try {
			
				
			FXMLLoader loader = new FXMLLoader();
			
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			
			URL location = getClass().getResource(FileConstants.FXML_BLEND);
		
			FXMLLoader fxmlLoader = new FXMLLoader();
			
			fxmlLoader.setLocation(location);
			
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			
			new LogsImage();
			
			StackPane root2 =  fxmlLoader.load(location.openStream());
			
	
//			WrapController 
			BlendController 
			test = fxmlLoader.getController();
		
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
