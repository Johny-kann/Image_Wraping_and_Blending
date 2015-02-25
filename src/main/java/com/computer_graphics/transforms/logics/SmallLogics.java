package com.computer_graphics.transforms.logics;

import com.computer_graphics.shapes.custom.ImageGroup;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
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
	
	public Color addOpacityToColor(Color color,double alpha)
	{
		color = Color.color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
		return color;
	}
	
	public Point2D findPerpendicular(Point2D point)
	{
		return new Point2D(point.getY(), -point.getX());
	}
	
	public Point2D giveAlphaPoint(Point2D startP,Point2D endP,Double alpha)
	{
		return (startP.multiply(1-alpha)).add(endP.multiply(alpha));
	}
	
	public boolean dimensionZeroCheck(Image image)
	{
		return image.getHeight()==0?true:false;
	}
	
	public static int doubleToInt(Double x)
	{
		return (int)Math.round(x);
	}
	
	/**
	 * @param length
	 * @param dist
	 * @param offset
	 * @param Total Power
	 * @param length
	 * @return
	 */
	public Double calculateWeight(Double length,Double dist,Double a,Double b,Double p)
	{
		return Math.pow((Math.pow(length, p)/(a+dist)),b);
	}
	
	public Boolean isLineNumberSame(ImageGroup source,ImageGroup dest)
	{
		return source.getLines().getChildren().size()==dest.getLines().getChildren().size();
	}
	
}
