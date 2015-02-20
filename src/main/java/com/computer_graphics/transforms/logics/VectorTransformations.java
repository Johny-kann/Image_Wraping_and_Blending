package com.computer_graphics.transforms.logics;

import com.computer_graphics.shapes.custom.ArrowHead;
import com.computer_graphics.shapes.custom.ImageGroup;

import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class VectorTransformations {

	public Point2D findUVFromDestination(Point2D X,Point2D P,Point2D Q)
	{
		SmallLogics sl = new SmallLogics();
		double u = (
				(X.subtract(P)).dotProduct(Q.subtract(P)))/
						(Math.pow(P.subtract(Q).magnitude(),2));
		
		double v = (X.subtract(P).dotProduct(
				sl.findPerpendicular(Q.subtract(P))))/(P.subtract(Q).magnitude());
		
		return new Point2D(u, v);
	}
	
	public Point2D findXFromUV(Point2D P1,Point2D Q1,Point2D UV)
	{
		SmallLogics sl = new SmallLogics();
		
	//	System.out.println(P1);
	//	System.out.println(Q1);
		Point2D third =  
		sl.findPerpendicular(Q1.subtract(P1)).multiply(UV.getY()/(Q1.subtract(P1).magnitude()));
		
		Point2D two = Q1.subtract(P1).multiply(UV.getX());
		
		Point2D one = P1;
		
		return one.add(two).add(third);
				
	}
	
	public Image applyTransform(ImageGroup source,final ImageGroup dest,final ImageGroup trans,final Double alpha)
	{
	SmallLogics sl = new SmallLogics();
		
		final WritableImage image = new WritableImage(
				SmallLogics.doubleToInt(source.getDimReal().getWidth()), 
				SmallLogics.doubleToInt(source.getDimReal().getHeight())
				);
		
		for(int i=0;i<image.getWidth();i++)
		{
			for(int j=0;j<image.getHeight();j++)
			{
				ArrowHead lindest = (ArrowHead)dest.getLines().getChildren().get(0);
			
				ArrowHead linsource = (ArrowHead)source.getLines().getChildren().get(0);
				
				Point2D Xsource = getXfromSource(
						new Point2D(i,j), 
			//			lindest.getStartP()
						sl.giveAlphaPoint(linsource.getStartP(), lindest.getStartP(), alpha),
						
			//			lindest.getEndP()
						sl.giveAlphaPoint(linsource.getEndP(), lindest.getEndP(), alpha), 
						linsource.getStartP(), 
						linsource.getEndP(), 
						dest.getDimUV(), 
						new Dimension2D(image.getWidth(), image.getHeight())
						, source.getDimUV(), 
						source.getDimReal());
				
				
				try
				{
					image.getPixelWriter().setColor(i, j, 
						source.getImage().getPixelReader().getColor(
								SmallLogics.doubleToInt(Xsource.getX()), 
								SmallLogics.doubleToInt(Xsource.getY())
								));
				}
				catch(IndexOutOfBoundsException ie)
				{
					image.getPixelWriter().setColor(i, j,						
									Color.WHITE);
				}
			}
		}
		
		
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				trans.getImageView().setImage(image);
				System.out.println(alpha);
				
			}
		});
		
	
		return image;
			
	}
	
	public Point2D getXfromSource(Point2D Xdest, Point2D Pdest, Point2D Qdest,Point2D Psource,Point2D Qsource,Dimension2D uvDest,Dimension2D xyDest
			,Dimension2D uvSource,Dimension2D xySource)
	{
		Transformations2D trans = new Transformations2D();
		Point2D uvPT = findUVFromDestination(Xdest, 
									trans.getXYFromUV(uvDest, xyDest, Pdest), 
									trans.getXYFromUV(uvDest, xyDest, Qdest));
	//	System.out.println(uvPT);
		return findXFromUV(
				trans.getXYFromUV(uvSource, xySource, Psource), 
				trans.getXYFromUV(uvSource, xySource, Qsource), 
				uvPT);
		
		
	}
}
