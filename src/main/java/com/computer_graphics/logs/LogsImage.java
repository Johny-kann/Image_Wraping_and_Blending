package com.computer_graphics.logs;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.computer_graphics.constants.files.FileConstants;

public class LogsImage {


		public static final Logger mainLogs = Logger.getLogger("Main Log");
		
		public LogsImage()
		{
			try {
				SimpleFormatter formatter = new SimpleFormatter();
				
				mainLogs.addHandler(new FileHandler(FileConstants.WRAP_DESTINATION_IMAGE_TEMPLATE));
				
		//		  FileHandler fh = new FileHandler(FilesList.LOG_ACCURACY);
				
				mainLogs.getHandlers()[0].setFormatter(formatter);
				
				
				
				mainLogs.setUseParentHandlers(false);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



