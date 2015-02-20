package com.computer_graphics.shapes.custom;

import com.computer_graphics.transforms.logics.SmallLogics;
import com.sun.javafx.geom.Line2D;

import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ArrowHead extends Polyline {

	private Point2D startP;
	private Point2D endP;
	
	public ArrowHead(Double startX,Double startY,Double endX,Double endY)
	{
		Point2D pt = new SmallLogics().findArrowPoint(startX, startY, endX, endY);
		
		startP = new Point2D(startX, startY);
		endP = new Point2D(endX, endY);
		
		getPoints().addAll(new Double[]{
				startX , startY,
				endX , endY,
				pt.getX() , pt.getY()});
	}

	
	public void setPoints(Double startX,Double startY,Double endX,Double endY)
	{
		Point2D pt = new SmallLogics().findArrowPoint(startX, startY, endX, endY);
		
		getPoints().setAll(new Double[]{
				startX , startY,
				endX , endY,
				pt.getX() , pt.getY()});
	}


	public Point2D getStartP() {
		return startP;
	}


	public void setStartP(Point2D startP) {
		this.startP = startP;
	}


	public Point2D getEndP() {
		return endP;
	}


	public void setEndP(Point2D endP) {
		this.endP = endP;
	}
	
}
