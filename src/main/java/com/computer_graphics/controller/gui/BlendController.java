package com.computer_graphics.controller.gui;

import com.computer_graphics.constants.files.FileConstants;
import com.computer_graphics.shapes.custom.ArrowHead;
import com.computer_graphics.shapes.custom.ImageGroup;
import com.computer_graphics.threads.WrapControllerThread;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class BlendController {

	private BlendController myClass;
	private ImageGroup sourceImageGroup ;
	private ImageGroup destImageGroup ;
	private ImageGroup transImageGroup;
	private ImageGroup transImageGroup2;
	
	private ImageGroup resultGroup;
	
	 private double mousePosX;
	    private double mousePosY;
	    private double mouseOldX;
	    private double mouseOldY;
	    private double mouseDeltaX;
	    private double mouseDeltaY;
	    
	    private WrapControllerThread convertThreadSource;
	    private WrapControllerThread convertThreadDestination;
		 
		 public Double newTrans=1.0;
	
	
	@FXML
	    private AnchorPane sourceAnchor;

	    @FXML
	    private ImageView imageSource;

	    @FXML
	    private AnchorPane transAnchor;

	    @FXML
	    private AnchorPane destAnchor;

	    @FXML
	    private ImageView imageTrans;

	    @FXML
	    private Button changeBut;

	    @FXML
	    private ImageView imageDest;

	    @FXML
	    private Slider transSlider;
	    
	    @FXML
	    private AnchorPane transAnchor1;
	    
	    @FXML
	    private ImageView imageTrans1;

	    @FXML
	    void pressMe(ActionEvent event) {

	    	   
	        convertThreadSource.convertImageViews(sourceImageGroup, destImageGroup, destImageGroup,1.0, myClass);
	        
	        ((Service)convertThreadSource.worker).restart();

	    }

	    @FXML
	    void resetImages(ActionEvent event) {

	    	sourceImageGroup.clearLines();
	    	
	    	destImageGroup.clearLines();
	    	
	    	Image image2 = new Image(FileConstants.BLEND_DESTINATION_IMAGE, true);
	   	 	destImageGroup.setImage(image2);
	   	 imageDest.setImage(image2);
	   	 	
	   	 Image image3 = new Image(FileConstants.BLEND_DESTINATION_IMAGE, true);
	    	resultGroup.setImage(image3);
	    	imageTrans.setImage(image3);
	    	
	   	 
	    }
	    
	    
	    @FXML
	    void initialize() {
			
		myClass = this;
		convertThreadSource = new WrapControllerThread();
		convertThreadDestination = new WrapControllerThread();
		
		
		
		 Image image = new Image(FileConstants.BLEND_SOURCE_IMAGE, true);     
		 imageSource.setImage(image);
		 sourceImageGroup = new ImageGroup(imageSource);

		 
		 	 
		 Image image2 = new Image(FileConstants.BLEND_DESTINATION_IMAGE, true);
		 imageDest.setImage(image2);
		 destImageGroup = new ImageGroup(imageDest);

		 
//		 Image image3 = new Image(FileConstants.BLEND_SOURCE_IMAGE, true);
//		 imageTrans.setImage(image3);
//		 transImageGroup = new ImageGroup(imageTrans);
		 
//		 Image image4 = new Image(FileConstants.BLEND_DESTINATION_IMAGE, true);
//		 imageTrans1.setImage(image4);
//		 transImageGroup2 = new ImageGroup(imageTrans1);
		 
		 Image image3 = new Image(FileConstants.BLEND_SOURCE_IMAGE, true);
		 imageTrans.setImage(image3);
		 resultGroup = new ImageGroup(imageTrans);
		
		settingChildrenFromImageGroupToRoot(sourceImageGroup, sourceAnchor);
		settingChildrenFromImageGroupToRoot(destImageGroup, destAnchor);
		settingChildrenFromImageGroupToRoot(resultGroup, transAnchor);
//		settingChildrenFromImageGroupToRoot(transImageGroup, transAnchor);
//		settingChildrenFromImageGroupToRoot(transImageGroup2, transAnchor1);
		
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
	    	if(alpha.equals(newTrans) || alpha.equals(1-newTrans))
			{
				group.
				getImageView().
				setImage(image);
			}
	 }
	    
	    private void handleMouse(final ImageGroup parentNode) {
	        
			parentNode.getImageView().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
         
                mouseOldX = me.getX();
                mouseOldY = me.getY();
                parentNode.addIndex();
                            
        
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
	
	    private void iniListeners()
		{
			transSlider.valueProperty().addListener(new ChangeListener<Number>() {
	            public void changed(ObservableValue<? extends Number> ov,
	                    Number old_val, final Number new_val) {
	               
	            	Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							
						/*	if(convertThreadSource.worker.isRunning())
							{
				
								convertThreadSource.worker.cancel();
							}
							
							newTrans = new_val.doubleValue();
					        convertThreadSource.
					        convertImageViews(sourceImageGroup, destImageGroup, transImageGroup,new_val.doubleValue(), myClass);
					        ((Service)convertThreadSource.worker).restart();
							
				
							
					        if(convertThreadDestination.worker.isRunning())
							{
				
								convertThreadDestination.worker.cancel();
							}
							
							newTrans = 1 - new_val.doubleValue();
					        convertThreadDestination.
					        convertImageViews( destImageGroup,sourceImageGroup, transImageGroup2,newTrans, myClass);
					        ((Service)convertThreadDestination.worker).restart();
				*/
							newTrans = new_val.doubleValue();
							runThread(convertThreadSource, sourceImageGroup, destImageGroup, resultGroup, new_val.doubleValue());
//							runThread(convertThreadDestination, destImageGroup, sourceImageGroup, transImageGroup2, 1-new_val.doubleValue());
						}
					});
	            	
	            }
	            	
			});
			
		}
	    
	   
	   private void runThread(WrapControllerThread thread,ImageGroup sGroup,ImageGroup dGroup,ImageGroup tGroup,Double alpha)
	   {
	//	   System.out.println("Inside run");
		   try
		   {
		   if(thread.worker.isRunning())
			{

				thread.worker.cancel();
			}
			
	//		newTrans = new_val.doubleValue();
	        thread.
	        convertImageViews(sGroup, dGroup, tGroup,alpha, myClass);
	        ((Service)thread.worker).restart();
		   }catch(NullPointerException ne)
		   {
			   thread.
		        convertImageViews(sGroup, dGroup, tGroup,alpha, myClass);
			   ((Service)thread.worker).restart();
		   }
	   }
	    
	private void drawLine(int index,Node lines,Node texts)
	{
		ArrowHead line = new ArrowHead(mouseOldX, mouseOldY, mousePosX, mousePosY);
		Text text = new Text(mouseOldX, mouseOldY, index+"");


		try
		{
	
			((Group) lines).getChildren().set(index, line);
			((Group)texts).getChildren().set(index, text);
	
		
			
		}catch(IndexOutOfBoundsException ne)
		{
			
			((Group) lines).
			getChildren().add(line);
			
			((Group)texts).getChildren().add(text);
				
		}

	}
	
}
