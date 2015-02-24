package com.computer_graphics.controller.gui;

import com.computer_graphics.constants.files.FileConstants;
import com.computer_graphics.shapes.custom.ArrowHead;
import com.computer_graphics.shapes.custom.ImageGroup;
import com.computer_graphics.threads.WrapControllerThread;
import com.computer_graphics.transforms.logics.Xform;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class WrapController {
	
//	final Group sourceGroup = new Group();
///	final Group destGroup = new Group();
	
	private WrapController myClass;
	private ImageGroup sourceImageGroup ;
	private ImageGroup destImageGroup ;
	private ImageGroup transImageGroup;
	
/*	final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    final double cameraDistance = 30;
    
*/
    
    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    private double mouseDeltaX;
    private double mouseDeltaY;
    
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
	 private AnchorPane transAnchor;
	 
	 @FXML
	 private ImageView imageTrans;
	 
	 @FXML
	 private Slider transSlider;

	 private WrapControllerThread convertThread;
	 
	 public Double newTrans=1.0;
	 
    @FXML
    void pressMe(ActionEvent event) {
   
        convertThread.convertImageViews(sourceImageGroup, destImageGroup, destImageGroup,1.0, myClass);
        
        ((Service)convertThread.worker).restart();
    			
    }
	
    @FXML
    void resetImages(ActionEvent event) {

    	sourceImageGroup.clearLines();
    	
    	destImageGroup.clearLines();
    	
    	Image image2 = new Image(FileConstants.WRAP_DESTINATION_IMAGE_TEMPLATE, true);
   	 	destImageGroup.setImage(image2);
   	 	
   	 	
   	 Image image3 = new Image(FileConstants.WRAP_DESTINATION_IMAGE_TEMPLATE, true);
   	transImageGroup.setImage(image3);
   	
   	
 //  	 transSlider.valueProperty().set(0.0);
    	
    	
    }
	
	@FXML
    void initialize() {
		
		myClass = this;
	convertThread = new WrapControllerThread();
	 Image image = new Image(FileConstants.WRAP_SOURCE_IMAGE, true);     
	 imageSource.setImage(image);
	 sourceImageGroup = new ImageGroup(imageSource);
//	 setImageDimension(sourceImageGroup);
	 
	 	 
	 Image image2 = new Image(FileConstants.WRAP_DESTINATION_IMAGE_TEMPLATE, true);
	 imageDest.setImage(image2);
	 destImageGroup = new ImageGroup(imageDest);
//	 setImageDimension(destImageGroup);
	 
	 Image image3 = new Image(FileConstants.WRAP_DESTINATION_IMAGE_TEMPLATE, true);
	 imageTrans.setImage(image3);
	 transImageGroup = new ImageGroup(imageTrans);
	 
	
	settingChildrenFromImageGroupToRoot(sourceImageGroup, sourceAnchor);
	settingChildrenFromImageGroupToRoot(destImageGroup, destAnchor);
	settingChildrenFromImageGroupToRoot(transImageGroup, transAnchor);
	
	 
//	sourceAnchor.getChildren().add(sourceImageGroup.getLines());
//	sourceAnchor.getChildren().add(sourceImageGroup.getTexts());
//	destAnchor.getChildren().add(destImageGroup.getLines());
//	destAnchor.getChildren().add(e)
//	transAnchor.getChildren().add(transImageGroup.getLines());
	
	handleMouse(sourceImageGroup);
	handleMouse(destImageGroup);
	iniListeners();
    }
	
	
	private void settingChildrenFromImageGroupToRoot(ImageGroup img,Node root)
	{
		((AnchorPane)root).getChildren().add(img.getLines());
		((AnchorPane)root).getChildren().add(img.getTexts());
	}
	
	public void setImageDimension(ImageGroup group,Image image,Double alpha)
	{
		
		if(alpha.equals(newTrans))
		{
			group.getImageView().setImage(image);
			
			
		}
       
	}
	
	private void iniListeners()
	{
		transSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, final Number new_val) {
               
            	Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
			//			WrapControllerThread model = new WrapControllerThread();
						if(convertThread.worker.isRunning())
						{
					//		System.out.println("True");
							convertThread.worker.cancel();
						}
						
						newTrans = new_val.doubleValue();
				        convertThread.
				        convertImageViews(sourceImageGroup, destImageGroup, transImageGroup,new_val.doubleValue(), myClass);
				        
				        ((Service)convertThread.worker).restart();
					
					}
				});
            	
            }
            	
		});
		
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
 
                	drawLine(parentNode.getIndex()	, parentNode.getLines(),parentNode.getTexts());
                }
            
            }
        });
    }
	
	private void drawLine(int index,Node lines,Node texts)
	{
		ArrowHead line = new ArrowHead(mouseOldX, mouseOldY, mousePosX, mousePosY);
		Text text = new Text(mouseOldX, mouseOldY, index+"");
//		line.setPoints(mouseOldX, mouseOldY, mousePosX, mousePosY);

		try
		{
		//	Line lines = (Line)lineGroup.getChildren().get(lineIndex);
			((Group) lines).getChildren().set(index, line);
			((Group)texts).getChildren().set(index, text);
		//	System.out.println(((Group)texts).getChildren().size());
		
			
		}catch(IndexOutOfBoundsException ne)
		{
			
			((Group) lines).
			getChildren().add(line);
			
			((Group)texts).getChildren().add(text);
		//	System.out.println(((Group)texts).getChildren().size());
		
		}

	}
	
}
