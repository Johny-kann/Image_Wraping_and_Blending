package com.computer_graphics.controller;

import java.net.URL;

import com.computer_graphics.constants.files.FileConstants;
import com.computer_graphics.controller.gui.CanvasController;
import com.computer_graphics.controller.gui.WrapperController;
import com.computer_graphics.logs.LogsImage;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;


/**
 * @author Janakiraman 
 * Main class used to start the application
 *
 */
public class Main extends Application {
@Override
public void start(Stage primaryStage) {
	try {
		
			
		FXMLLoader loader = new FXMLLoader();
		
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		
		URL location = getClass().getResource(FileConstants.FXML_ROOT);
	
		FXMLLoader fxmlLoader = new FXMLLoader();
		
		fxmlLoader.setLocation(location);
		
		fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
		
		new LogsImage();
		
		StackPane root2 =  fxmlLoader.load(location.openStream());
		

		WrapperController 
//		BlendController 
		test = fxmlLoader.getController();
	
		Scene scene = new Scene(root2,800,600,true);
	
		primaryStage.setTitle("Johny's Wrapper");
		primaryStage.setFullScreen(true);
	
		
		primaryStage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.ENTER, KeyCombination.ALT_DOWN)
		
		
		);
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
