package com.computer_graphics.shapes.custom;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageGroup extends Group{

	private int index;
	private ImageView imageView;
//	private Image image;
	private Group lines;
	private Group texts;
	
	
	public ImageGroup(ImageView imageV)
	{
		this.index=-1;
	//	this.image = imageV.getImage();
		this.imageView = imageV;
		this.lines = new Group();
		this.imageView.setPreserveRatio(false);
		this.texts = new Group();
		
		
	}
	
	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public Image getImage() {
		return imageView.getImage();
	}

	public void setImage(Image image) {
		imageView.setImage(image);
	}

	public Group getLines() {
		return lines;
	}

	public Group getTexts() {
		return texts;
	}
	
	public void setLines(Group lines) {
		this.lines = lines;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	
	
	public int getIndex()
	{
		return index;
	}
	
	public void addIndex()
	{
		index++;
	}
	

	public int getLineNumbers()
	{
		return lines.getChildren().size();
	}
	
	public Dimension2D getDimUV() {
		return new Dimension2D(imageView.getFitWidth(), imageView.getFitHeight());
	}

	
	public Dimension2D getDimReal() {
		return new Dimension2D(imageView.getImage().getWidth(), imageView.getImage().getHeight());
	}

	public void clearLines()
	{
		lines.getChildren().clear();
		texts.getChildren().clear();
		this.setIndex(-1);
	}
/*	public void calculateDimensionForImage() {
		// TODO Auto-generated method stub
	
	}*/

	
	
}
