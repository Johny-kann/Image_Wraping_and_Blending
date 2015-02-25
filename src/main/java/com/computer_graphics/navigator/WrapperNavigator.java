package com.computer_graphics.navigator;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import com.computer_graphics.controller.gui.BlendController;
import com.computer_graphics.controller.gui.WrapController;
import com.computer_graphics.controller.gui.WrapperController;

public class WrapperNavigator {

	public static WrapperController wrapper;
	
	
	public static void setContentPane(String fxml)
	{
		try {
      
            wrapper.setContentPane((Node)FXMLLoader.load(
                    WrapperNavigator.class.getResource(
                            fxml
                        )));
          
           
        } catch (IOException e) {
            e.printStackTrace();
        }
	}


		
}
