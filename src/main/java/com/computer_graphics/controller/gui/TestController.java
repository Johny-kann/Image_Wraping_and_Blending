package com.computer_graphics.controller.gui;

import sun.security.acl.WorldGroupImpl;
import antlr.FileLineFormatter;
import ch.qos.logback.core.pattern.color.BlackCompositeConverter;

import com.computer_graphics.constants.files.FileConstants;
import com.computer_graphics.shapes.custom.ArrowHead;
import com.computer_graphics.transforms.logics.Xform;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;

public class TestController {
	
	final Group root = new Group();
	final Group lineGroup = new Group();
	final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    final double cameraDistance = 30;
    
    Line blackLine = new Line();
    
    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;
    
    int lineIndex = 0;
	
	@FXML
    private AnchorPane anchorPane;

	@FXML
    private Button changeBut;
	
	@FXML
    private ImageView imagesource;

    @FXML
    void pressMe(ActionEvent event) {

    //	blackLine.setTranslateZ(3);
    //	anchorPane.setTranslateZ(-3);
    	blackLine.setTranslateX(50);
    	System.out.println(imagesource.getImage().getHeight());
    	System.out.println(imagesource.getImage().getWidth());
    }
	
	
	
	@FXML
    void initialize() {
		
	            blackLine.setStartX(1);
	            blackLine.setStartY(1);
	            blackLine.setEndX(20);
	            blackLine.setEndY(140);
	  //          blackLine.setTranslateZ(10);
	            
	         
	            
	 Image image = new Image(FileConstants.SOURCE_IMAGE, true);        

	 imagesource.setImage(image);
		root.getChildren().add(blackLine);
	//	root.getChildren().add(nsp);
		
	//	buildCamera();
		
	
	System.out.println(imagesource.getFitHeight());
	System.out.println(imagesource.getFitWidth());
	root.getChildren().add(lineGroup);
	anchorPane.getChildren().add(root);
	
	
	handleMouse(imagesource, root);
	
    }
	
	private void handleMouse(Node scene, final Node root) {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
         //       mousePosX = me.getX();
         //       mousePosY = me.getY();
                mouseOldX = me.getX();
                mouseOldY = me.getY();
                lineIndex++;
         //       System.out.println(mousePosX);
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
         
                mousePosX = me.getX();
                mousePosY = me.getY();
                mouseDeltaX = (mousePosX - mouseOldX); 
                mouseDeltaY = (mousePosY - mouseOldY); 
                
                double modifier = 1.0;
                double modifierFactor = 0.1;
                
                if (me.isControlDown()) {
                    modifier = 0.1;
                } 
                if (me.isShiftDown()) {
                    modifier = 10.0;
                }     
                if (me.isPrimaryButtonDown()) {
 
                	drawLine(lineIndex);
                }
            
            }
        });
    }
	
	private void drawLine(int index)
	{
		ArrowHead line = new ArrowHead();
	
		line.setPoints(mouseOldX, mouseOldY, mousePosX, mousePosY);
//		line.setStartX(mouseOldX);
//		line.setStartY(mouseOldY);
//		line.setEndX(mousePosX);
	//	line.setEndY(mousePosY);
		
		
		try
		{
		//	Line lines = (Line)lineGroup.getChildren().get(lineIndex);
			lineGroup.getChildren().set(index, line);
			
		}catch(IndexOutOfBoundsException ne)
		{
			
			lineGroup.getChildren().add(line);
		}

	}
	
}
