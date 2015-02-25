package com.computer_graphics.transforms.logics;

import com.computer_graphics.constants.notations.PaperNotations;
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
	
	public Point2D findXFromUV(Point2D PSource,Point2D QSource,Point2D UV)
	{
		SmallLogics sl = new SmallLogics();
		
	//	System.out.println(P1);
	//	System.out.println(Q1);
		Point2D third =  
		sl.findPerpendicular(QSource.subtract(PSource)).multiply(UV.getY()/(QSource.subtract(PSource).magnitude()));
		
		Point2D two = QSource.subtract(PSource).multiply(UV.getX());
		
		Point2D one = PSource;
		
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
				try
				{
					Point2D Xsource = getXAveragingAllLines(source, dest, trans, alpha, i, j, image);
					
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
		return image;
	}

		public Image applyTransformAplha(ImageGroup source,final ImageGroup dest,final ImageGroup trans,final Double alpha)
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
					try
					{
						Point2D Xsource = getXAveragingAllLines(source, dest, trans, alpha, i, j, image);
						
						Point2D XDest = getXAveragingAllLines(dest, source, trans, 1-alpha, i, j, image);
						
			/*			image.getPixelWriter().setColor(i, j, sl.mergeColor(
							source.getImage().getPixelReader().getColor(
									SmallLogics.doubleToInt(Xsource.getX()), 
									SmallLogics.doubleToInt(Xsource.getY())
									),
							dest.getImage().getPixelReader().getColor(SmallLogics.doubleToInt(XDest.getX()), SmallLogics.doubleToInt(XDest.getY()))
									,1-alpha));
									*/
						
						image.getPixelWriter().setColor(i, j, 
								sl.mergeColor(
										sl.setColor(source.getImage().getPixelReader(), Xsource), 
										sl.setColor(dest.getImage().getPixelReader(), XDest), 
										alpha));
						
					}
					catch(IndexOutOfBoundsException ie)
					{
						
						image.getPixelWriter().setColor(i, j, 						
										Color.WHITE);
					}
				}
			}

		
		return image;

	}
	
	/**
	 * Computes X by finding the weighted average of all the lines employed by the paper
	 * @return Point X in source
	 */
	public Point2D getXAveragingAllLines(ImageGroup source, ImageGroup destination, ImageGroup trans, Double alpha,int x,int y,Image image)
	{
		SmallLogics sl = new SmallLogics();
		
		Point2D Xi = new Point2D(x, y);
		
		Point2D dSum = new Point2D(0, 0);
		Double weightSum = 0.0;
		
		if(sl.isLineNumberSame(source, destination))
		{
			
		
		
	/*	for(int i=0;i<destination.getLineNumbers();i++)
		{
			ArrowHead lindest = (ArrowHead)destination.getLines().getChildren().get(i);
			
			ArrowHead linsource = (ArrowHead)source.getLines().getChildren().get(i);
			
		/*	Point2D Xsource = getXfromSource(
					new Point2D(x,y), 
		
					sl.giveAlphaPoint(linsource.getStartP(), lindest.getStartP(), alpha),
						
					sl.giveAlphaPoint(linsource.getEndP(), lindest.getEndP(), alpha), 
					linsource.getStartP(), 
					linsource.getEndP(), 
					destination.getDimUV(), 
					new Dimension2D(image.getWidth(), image.getHeight())
					, source.getDimUV(), 
					source.getDimReal());
			
			Point2D displacement = Xsource.subtract(Xi);
			Double shortDistance = findUVFromDestination(Xsource, linsource.getStartP(), linsource.getEndP()).getY();
			Double length = linsource.getStartP().distance(linsource.getEndP());
			Double weight = sl.calculateWeight(length, 
					shortDistance,
					PaperNotations.P_BIEBER_A_CONSTANT, 
					PaperNotations.P_BIEBER_B_CONSTANT,
					PaperNotations.P_BIEBER_P_CONSTANT);
			
			weight = 1.0;
			dSum = dSum.add(displacement.multiply(weight));
			
			weightSum+=weight;
			*/
			Point2D Xsource = getXAveragingAllLines(Xi, source,
					
					destination,
					
					destination.getDimUV(),
					new Dimension2D(image.getWidth(), image.getHeight()),
					source.getDimUV(),
					source.getDimReal(), 
					source.getLineNumbers(), alpha);
		
	
	//	Point2D weightedXsource = dSum.multiply(1/weightSum);
		
	/*	ArrowHead lindest = (ArrowHead)destination.getLines().getChildren().get(0);
		
		ArrowHead linsource = (ArrowHead)source.getLines().getChildren().get(0);
		
		Point2D Xsource = getXfromSource(
				new Point2D(x,y), 
	
				sl.giveAlphaPoint(linsource.getStartP(), lindest.getStartP(), alpha),
					
				sl.giveAlphaPoint(linsource.getEndP(), lindest.getEndP(), alpha), 
				linsource.getStartP(), 
				linsource.getEndP(), 
				destination.getDimUV(), 
				new Dimension2D(image.getWidth(), image.getHeight())
				, source.getDimUV(), 
				source.getDimReal());
				
				*/
		return Xsource; 
		
		}
		else
		{
			System.out.println("Line numbers not equal");
			return null;
			
		}
		
	}
	
	public Point2D getXAveragingAllLines(Point2D X,ImageGroup source,
			ImageGroup destination,
			Dimension2D uvDest,Dimension2D xyDest,Dimension2D uvSource,Dimension2D xySource, int noOfLines,Double alpha)
	{
		SmallLogics sl = new SmallLogics();
		Transformations2D trans = new Transformations2D();
		
		Point2D dSum = new Point2D(0, 0);
		Double weightSum = 0.0;
		
	
		
		for(int i=0;i<noOfLines;i++)
		{
		ArrowHead linDest = (ArrowHead)destination.getLines().getChildren().get(i);
			
		ArrowHead linSource = (ArrowHead)source.getLines().getChildren().get(i);
			
			Point2D Xsource = getXfromSource(
					X, 
		
					sl.giveAlphaPoint(linSource.getStartP(), linDest.getStartP(), alpha),
						
					sl.giveAlphaPoint(linSource.getEndP(), linDest.getEndP(), alpha), 
					linSource.getStartP(), 
					linSource.getEndP(), 
					uvDest, 
					xyDest
					, uvSource, 
					xySource);
		
			
			Point2D displacement = Xsource.subtract(X);
			Double shortDistance = shortDistance(
					
					trans.getXYFromUV(uvDest, xyDest, linDest.getStartP()), 
					trans.getXYFromUV(uvDest, xyDest, linDest.getEndP()),
					X
					);
			Double length = linDest.getStartP().distance(linDest.getEndP());
			Double weight = sl.calculateWeight(length, 
					shortDistance,
					PaperNotations.P_BIEBER_A_CONSTANT, 
					PaperNotations.P_BIEBER_B_CONSTANT,
					PaperNotations.P_BIEBER_P_CONSTANT);
			
			
		//	weight = 1.0;
			dSum = dSum.add(displacement.multiply(weight));
			
			weightSum+=weight;
			
		}
	Point2D weightedX = X.add(dSum.multiply(1/weightSum));
	return weightedX;
	}
	
	public Double shortDistance(Point2D linStart,Point2D linEnd,Point2D X)
	{
		return Math.abs(findUVFromDestination(X, linStart, linEnd).getY());
	}
	
	public Point2D getXfromSource(Point2D Xdest, Point2D Pdest, Point2D Qdest,Point2D Psource,Point2D Qsource,Dimension2D uvDest,Dimension2D xyDest
			,Dimension2D uvSource,Dimension2D xySource)
	{
		Transformations2D trans = new Transformations2D();
		Point2D uvPT = findUVFromDestination(Xdest, 
									trans.getXYFromUV(uvDest, xyDest, Pdest), 
									trans.getXYFromUV(uvDest, xyDest, Qdest));
		
	
	
		Point2D temp =  findXFromUV(
				trans.getXYFromUV(uvSource, xySource, Psource), 
				trans.getXYFromUV(uvSource, xySource, Qsource), 
				uvPT);
	
		return temp;
		
	}
}
