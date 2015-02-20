package com.computer_graphics.controller.gui;

import sun.security.acl.WorldGroupImpl;
import antlr.FileLineFormatter;
import ch.qos.logback.core.pattern.color.BlackCompositeConverter;

import com.computer_graphics.constants.files.FileConstants;
import com.computer_graphics.shapes.custom.ArrowHead;
import com.computer_graphics.shapes.custom.ImageGroup;
import com.computer_graphics.transforms.logics.Transformations2D;
import com.computer_graphics.transforms.logics.Xform;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
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
	
//	final Group sourceGroup = new Group();
///	final Group destGroup = new Group();
	
	ImageGroup sourceImageGroup ;
	ImageGroup destImageGroup ;
	
	final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    final double cameraDistance = 30;
    

    
    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;
    
 //   int lineIndex = -1;
	
	@FXML
    private AnchorPane sourceAnchor;
	
	@FXML
    private AnchorPane destAnchor;
	
	@FXML
    private ImageView imageDest;


	@FXML
    private Button changeBut;
	
	@FXML
    private ImageView imageSource;
	
	

    @FXML
    void pressMe(ActionEvent event) {

    //	blackLine.setTranslateZ(3);
    //	anchorPane.setTranslateZ(-3);
    
    	System.out.println(imageSource.getImage().getHeight());
    	System.out.println(imageSource.getImage().getWidth());
    	
    	Dimension2D uvSource = new Dimension2D(imageSource.getFitWidth(), imageSource.getFitHeight());
    	Dimension2D uvDestination = new Dimension2D(imageSource.getImage().getWidth(), imageSource.getImage().getHeight());
    	System.out.println(new Transformations2D().getXYFromUV(uvSource, uvDestination, new Point2D(600, 280)
    	));
    }
	
	
	
	@FXML
    void initialize() {
		
	        
	  //          blackLine.setTranslateZ(10);
	            
	         
	            
	 Image image = new Image(FileConstants.SOURCE_IMAGE, true);     
	 imageSource.setImage(image);
	 sourceImageGroup = new ImageGroup(imageSource);
	 	 
	 Image image2 = new Image(FileConstants.DESTINATION_IMAGE_TEMPLATE, true);
	 imageDest.setImage(image2);
	 destImageGroup = new ImageGroup(imageDest);
	 
	
	System.out.println(imageSource.getFitHeight());
	System.out.println(imageSource.getFitWidth());
//	System.out.println(imagesource.getImage().getHeight());
//	sourceGroup.getChildren().add();
	
	sourceAnchor.getChildren().add(sourceImageGroup.getLines());
	destAnchor.getChildren().add(destImageGroup.getLines());
	
	handleMouse(sourceImageGroup);
	handleMouse(destImageGroup);
	
	
    }
	
	private void handleMouse(final ImageGroup parentNode) {
      
		parentNode.getImageView().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
         //       mousePosX = me.getX();
         //       mousePosY = me.getY();
                mouseOldX = me.getX();
                mouseOldY = me.getY();
                parentNode.addIndex();
                            
         //       System.out.println(mousePosX);
            }
        });
		parentNode.getImageView().setOnMouseDragged(new EventHandler<MouseEvent>() {
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
 
                	drawLine(parentNode.getIndex()	, parentNode.getLines());
                }
            
            }
        });
    }
	
	private void drawLine(int index,Node lines)
	{
		ArrowHead line = new ArrowHead();
	
		line.setPoints(mouseOldX, mouseOldY, mousePosX, mousePosY);

		
		
		try
		{
		//	Line lines = (Line)lineGroup.getChildren().get(lineIndex);
			((Group) lines).getChildren().set(index, line);
		
			
		}catch(IndexOutOfBoundsException ne)
		{
			
			((Group) lines).
			getChildren().add(line);
		}

	}
	
}
