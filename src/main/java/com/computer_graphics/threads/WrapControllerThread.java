package com.computer_graphics.threads;

import java.util.concurrent.atomic.AtomicBoolean;

import com.computer_graphics.controller.gui.BlendController;
import com.computer_graphics.controller.gui.WrapController;
import com.computer_graphics.shapes.custom.ImageGroup;
import com.computer_graphics.transforms.logics.SmallLogics;
import com.computer_graphics.transforms.logics.VectorTransformations;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WrapControllerThread {

	public Worker<String> worker;
   	public AtomicBoolean shouldThrow = new AtomicBoolean(false);
  
  // 	private ThreadFill fillthread;
   	
  
   	public void checkConnection(final ImageGroup group)
   	{
   		worker = new Service<String>() {

			@Override
			protected Task<String> createTask() {
				// TODO Auto-generated method stub
				return new Task<String>() {
					
					@Override
					protected String call() throws Exception {
						// TODO Auto-generated method stub
						updateTitle("Checking Connection");
						updateMessage("Starting...");
						while(
						new SmallLogics().dimensionZeroCheck(group.getImage())==true)
							{
							Thread.sleep(100);
							};
					//	group.calculateDimensionForImage();
											
						return "Completed";
					}
				};
			}
		};
   	}
   	
   	public void convertImageViews(final ImageGroup source,final ImageGroup dest,final ImageGroup trans,final Double alpha,final WrapController wrap)
   	{
   		worker = new Service<String>() {

			@Override
			protected Task<String> createTask() {
				// TODO Auto-generated method stub
				return new Task<String>() {

					@Override
					protected String call() throws Exception {
						// TODO Auto-generated method stub
					final Image image = new VectorTransformations().applyTransform(source, dest, trans,alpha);
				
						Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
						wrap.setImageDimension(trans, image, alpha);
						}
					});
						
					return "Transformed";
					}
					
				};
			}
		};
   	}
	
   	
   	public void convertImageViews(final ImageGroup source,final ImageGroup dest,final ImageGroup trans,final Double alpha,final BlendController blend)
   	{
   		worker = new Service<String>() {

			@Override
			protected Task<String> createTask() {
				// TODO Auto-generated method stub
				return new Task<String>() {

					@Override
					protected String call() throws Exception {
						// TODO Auto-generated method stub
					final Image image = new VectorTransformations().applyTransformAplha(source, dest, trans,alpha);
					
				
						Platform.runLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
						blend.setImageDimension(trans, image, alpha);
	//					System.out.println("Image Set");
						}
					});
						
					return "Transformed";
					}
			
				};
			}
		};
   	}
}
