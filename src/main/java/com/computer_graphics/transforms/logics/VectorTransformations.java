package com.computer_graphics.transforms.logics;

import com.computer_graphics.shapes.custom.ImageGroup;

import javafx.geometry.Point2D;

public class VectorTransformations {

	public Point2D findUVForDestination(Point2D X,Point2D P,Point2D Q)
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
		
		Point2D third =  
		sl.findPerpendicular(Q1.subtract(P1)).multiply(UV.getY()/(Q1.subtract(P1).magnitude()));
		
		Point2D two = Q1.subtract(P1);
		
		Point2D one = P1;
		
		return one.add(two).add(third);
				
	}
	
	public void applyTransform(ImageGroup ref,ImageGroup generate)
	{
		
	}
}
