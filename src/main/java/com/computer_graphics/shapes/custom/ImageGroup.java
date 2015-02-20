package com.computer_graphics.shapes.custom;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageGroup extends Group{

	private int index;
	private ImageView imageView;
	private Image image;
	private Group lines;
	
	
	public ImageGroup(ImageView imageV)
	{
		this.index=-1;
		this.image = imageV.getImage();
		this.imageView = imageV;
		this.lines = new Group();
		this.imageView.setPreserveRatio(false);
		
		
		
	}
	
	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Group getLines() {
		return lines;
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

	public Dimension2D getDimUV() {
		return new Dimension2D(imageView.getFitWidth(), imageView.getFitHeight());
	}

	
	public Dimension2D getDimReal() {
		return new Dimension2D(image.getWidth(), image.getHeight());
	}

	
/*	public void calculateDimensionForImage() {
		// TODO Auto-generated method stub
	
	}*/
	
}