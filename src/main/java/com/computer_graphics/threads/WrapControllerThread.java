package com.computer_graphics.threads;

import java.util.concurrent.atomic.AtomicBoolean;

import com.computer_graphics.controller.gui.TestController;
import com.computer_graphics.shapes.custom.ImageGroup;
import com.computer_graphics.transforms.logics.SmallLogics;

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
						group.calculateDimensionForImage();
											
						return "Completed";
					}
				};
			}
		};

   	}
	
}
