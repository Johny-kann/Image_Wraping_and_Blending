package com.computer_graphics.controller.gui;

import com.computer_graphics.constants.files.FileConstants;
import com.computer_graphics.navigator.WrapperNavigator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class WrapperController {

	 @FXML
	    private Button butWrap;

	    @FXML
	    private Button butBlend;

	    @FXML
	    private StackPane contentPane;

	    @FXML
	    void blendAction(ActionEvent event) {
	    	WrapperNavigator.setContentPane(FileConstants.FXML_BLEND);
	    	
	    }

	    @FXML
	    void wrapAction(ActionEvent event) {
	    	WrapperNavigator.setContentPane(FileConstants.FXML_WRAP);
	    }

	    @FXML
	    void initialize() {
	    	
	    	WrapperNavigator.wrapper = this;
	    	
	    }
	    
		public void setContentPane(Node node) {
			// TODO Auto-generated method stub
			contentPane.getChildren().setAll(node);
		}

}
