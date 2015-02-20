package com.computer_graphics.controller.gui;

import sun.security.acl.WorldGroupImpl;
import antlr.FileLineFormatter;
import ch.qos.logback.core.pattern.color.BlackCompositeConverter;

import com.computer_graphics.constants.files.FileConstants;
import com.computer_graphics.shapes.custom.ArrowHead;
import com.computer_graphics.shapes.custom.ImageGroup;
import com.computer_graphics.threads.WrapControllerThread;
import com.computer_graphics.transforms.logics.SmallLogics;
import com.computer_graphics.transforms.logics.Transformations2D;
import com.computer_graphics.transforms.logics.VectorTransformations;
import com.computer_graphics.transforms.logics.Xform;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;

import javafx.concurrent.Service;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;
import javafx.util.Math;

public class WrapController {
	
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

    	 new VectorTransformations().applyTransform(sourceImageGroup, destImageGroup);
    			
    }
	
	
	
	@FXML
    void initialize() {
		
	 Image image = new Image(FileConstants.SOURCE_IMAGE, true);     
	 imageSource.setImage(image);
	 sourceImageGroup = new ImageGroup(imageSource);
//	 setImageDimension(sourceImageGroup);
	 
	 	 
	 Image image2 = new Image(FileConstants.DESTINATION_IMAGE_TEMPLATE, true);
	 imageDest.setImage(image2);
	 destImageGroup = new ImageGroup(imageDest);
//	 setImageDimension(destImageGroup);
	 
	
	sourceAnchor.getChildren().add(sourceImageGroup.getLines());
	destAnchor.getChildren().add(destImageGroup.getLines());
	
	handleMouse(sourceImageGroup);
	handleMouse(destImageGroup);
	
    }
	
	private void setImageDimension(ImageGroup imageG)
	{
		WrapControllerThread model = new WrapControllerThread();
        model.checkConnection(imageG);
        ((Service)model.worker).restart();
       
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
		ArrowHead line = new ArrowHead(mouseOldX, mouseOldY, mousePosX, mousePosY);
	
//		line.setPoints(mouseOldX, mouseOldY, mousePosX, mousePosY);

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
