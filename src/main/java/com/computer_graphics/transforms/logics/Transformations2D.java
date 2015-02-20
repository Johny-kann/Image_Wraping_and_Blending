package com.computer_graphics.transforms.logics;

import com.sun.javafx.geom.Dimension2D;

import javafx.geometry.Point2D;
import Jama.Matrix;

/**
 * @author Janakiraman
 * 
 * Classes that contains all the transformation matrixes for 2D graphics
 *
 */
public class Transformations2D {
	
	public Matrix rotation(Matrix mat,double theta)
	{
		double[] px = new double[]{Math.cos(theta), -Math.sin(theta),0};
		double[] py = new double[]{Math.sin(theta), Math.cos(theta),0};
		double[] p3 = new double[]{0,0,1};
		
		Matrix rot = new Matrix(new double[][]{px,py,p3});
		
		return rot.times(mat);
	}
	
	public Matrix translation(Matrix mat,double tx,double ty)
	{
		double[] p1 = new double[]{1,0,tx};
		double[] p2 = new double[]{0,1,ty};
		double[] p3 = new double[]{0,0,1};
		
		Matrix trans = new Matrix(new double[][]{p1,p2,p3});
		
		return trans.times(mat);
	}
	
	public Matrix shearX(Matrix mat,double shear)
	{
		double[] p1 = new double[]{1,shear,0};
		double[] p2 = new double[]{0,1,0};
		double[] p3 = new double[]{0,0,1};
		
		Matrix trans = new Matrix(new double[][]{p1,p2,p3});
		
		return trans.times(mat);
	}
	
	public Matrix shearY(Matrix mat,double shear)
	{
		double[] p1 = new double[]{1,0,0};
		double[] p2 = new double[]{shear,1,0};
		double[] p3 = new double[]{0,0,1};
		
		Matrix trans = new Matrix(new double[][]{p1,p2,p3});
		
		return trans.times(mat);
	}
	
	public Matrix translate(Matrix mat,double shear)
	{
		double[] p1 = new double[]{1,0,0};
		double[] p2 = new double[]{shear,1,0};
		double[] p3 = new double[]{0,0,1};
		
		Matrix trans = new Matrix(new double[][]{p1,p2,p3});
		
		return trans.times(mat);
	}
	
	public Matrix ScaleX(Matrix mat,double shear)
	{
		double[] p1 = new double[]{shear,0,0};
		double[] p2 = new double[]{0,1,0};
		double[] p3 = new double[]{0,0,1};
		
		Matrix trans = new Matrix(new double[][]{p1,p2,p3});
		
		return trans.times(mat);
	}
	
	public Matrix ScaleY(Matrix mat,double shear)
	{
		double[] p1 = new double[]{1,0,0};
		double[] p2 = new double[]{0,shear,0};
		double[] p3 = new double[]{0,0,1};
		
		Matrix trans = new Matrix(new double[][]{p1,p2,p3});
		
		return trans.times(mat);
	}
	
	
	public Point2D getXYFromUV(javafx.geometry.Dimension2D uvSource,javafx.geometry.Dimension2D uvDestination,Point2D point)
	{
		
		Point2D convertedPoint = new Point2D(
				uvDestination.getWidth()*point.getX()/uvSource.getWidth(),
				uvDestination.getHeight()*point.getY()/uvSource.getHeight()
				); 
		return convertedPoint;
	}
	
}
