package edu.uta.designclass.component;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;

public abstract class DCDComponent {
	
	public abstract void draw(Graphics2D graphic,int x, int y);

	public void fill(Graphics2D graphic,Shape shape) {
		graphic.fill(shape);
	}

	public boolean drawImage(Graphics2D graphic,Image image, int x, int y) {
		boolean result = graphic.drawImage(image, x, y, null);
		return result;
	}
	public void changeColor(Graphics2D graphic,Color c) {
		graphic.setColor(c);
	}
	public void drawString(Graphics2D graphic,String text, int x, int y) {
		graphic.drawString(text, x, y);
	}
	public void drawBlock(Graphics2D graphic,int w, int h, int x, int y) {
		drawLine(graphic,x, y, x, y + h);
		drawLine(graphic,x, y + h, x + w, y + h);
		drawLine(graphic,x + w, y + h, x + w, y);
		drawLine(graphic,x + w, y, x, y);
	}
	public void drawLine(Graphics2D graphic,int x1, int y1, int x2, int y2) {
		graphic.drawLine(x1, y1, x2, y2);
	}
}
