package com.computer_graphics.shapes.custom;

import com.computer_graphics.transforms.logics.SmallLogics;
import com.sun.javafx.geom.Line2D;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ArrowHead extends Polyline {

	
	public ArrowHead()
	{
	
	}

	
	public void setPoints(Double startX,Double startY,Double endX,Double endY)
	{
		Point2D pt = new SmallLogics().findArrowPoint(startX, startY, endX, endY);
		
		getPoints().addAll(new Double[]{
				startX , startY,
				endX , endY,
				pt.getX() , pt.getY()});
	}
	
}
