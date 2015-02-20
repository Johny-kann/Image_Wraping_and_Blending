package com.computer_graphics.transforms.logics;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Jama.Matrix;

public class SmallLogics {

	public Point2D findArrowPoint(Double x1,Double y1,Double x2,Double y2)
	{
		Double tempx = x2;
		Double tempy = y2;
		Double newTempx = (x1-x2)*0.2;
		Double newTempy = (y1-y2)*0.2;
	
		double px[] = {newTempx};
		double py[] = {newTempy};
	//	double p1[] = {1};
		Matrix mat = getPointsToMatrix(px, py);
		Matrix newMat = new Transformations2D().rotation(mat, 315.0);
		Point2D pt = new Point2D(newMat.getArray()[0][0]+tempx,newMat.getArray()[1][0]+tempy);
		return pt;
	}
	
	public Matrix getPointsToMatrix(double px[],double py[])
	{
		double p1[] = new double[px.length];
		
		for(int i=0;i<px.length;i++)
		{
			p1[i]=1;
		}
		
		Matrix mat = new Matrix(new double[][]{px,py,p1});
		
		return mat;
	}
	
	public Point2D findPerpendicular(Point2D point)
	{
		return new Point2D(point.getY(), -point.getX());
	}
	
	public boolean dimensionZeroCheck(Image image)
	{
		return image.getHeight()==0?true:false;
	}
	
	public static int doubleToInt(Double x)
	{
		return (int)Math.round(x);
	}
	
}
