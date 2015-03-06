package edu.uta.designclass.component;

import java.awt.Color;
import java.awt.Graphics2D;

public class DCDRelationship extends DCDComponent {
	private DCDClass source;
	private DCDClass destination;
	private String type;

	
	
	
	
	@Override
	public void draw(Graphics2D graphic, int maxWidth, int y) {
		int tempCnt = 0;
		int colorCnt = 0;
		Color c;
		int srcHeight = source.getHeight();
		int srcWidth = source.getWidth();
		int srcCol = source.getCol();
		int srcRow = source.getRow();
		int srcTop = source.getyPos();
		int srcLeft = source.getxPos();
		int srcMid = srcTop + (srcHeight / 2);
		int srcRight = srcLeft + srcWidth;

		int tgtHeight = destination.getHeight();
		int tgtWidth = destination.getWidth();
		int tgtCol = destination.getCol();
		int tgtRow = destination.getRow();
		int tgtTop = destination.getyPos();
		int tgtLeft = destination.getxPos();
		int tgtMid = tgtTop + (tgtHeight / 2);
		int tgtRight = tgtLeft + tgtWidth;

		int wide = 15 + tempCnt;
		int high = 10 + tempCnt;
		int mid = 0 + tempCnt;

		if (colorCnt % 2 == 0)
			c = new Color(255, 0, 0);
		else if (colorCnt % 3 == 0) {
			c = new Color(255, 255, 0);
		} else if (colorCnt % 4 == 0) {
			c = new Color(0, 255, 0);
		} else if (colorCnt % 5 == 0) {
			c = new Color(0, 0, 255);
		} else if (colorCnt % 6 == 0) {
			c = new Color(30, 255, 30);
		} else {
			c = new Color(0, 0, 0);
		}
		changeColor(graphic, c);
		if ((srcCol != tgtCol) && (srcRow != tgtRow)) {
			drawLine(graphic, srcRight, srcMid + mid,
					srcLeft + maxWidth + wide, srcMid + mid);
			drawLine(graphic, srcLeft + maxWidth + wide, srcMid + mid, srcLeft
					+ maxWidth + wide, tgtTop - high);
			drawLine(graphic, srcLeft + maxWidth + wide, tgtTop - high,
					tgtRight + wide, tgtTop - high);
			drawLine(graphic, tgtRight + wide, tgtTop - high, tgtRight + wide,
					tgtMid + mid);
			drawLine(graphic, tgtRight + wide, tgtMid + mid, tgtRight, tgtMid
					+ mid);
			drawLine(graphic, tgtRight, tgtMid + mid, tgtRight + 3, tgtMid
					+ mid + 3);
			drawLine(graphic, tgtRight, tgtMid + mid, tgtRight + 3, tgtMid
					+ mid - 3);
			
		}
		if ((srcCol == tgtCol) && (srcRow != tgtRow)) {
			drawLine(graphic, srcRight, srcMid + mid,
					srcLeft + maxWidth + wide, srcMid + mid);
			if (srcRow < tgtRow) {
				drawLine(graphic, srcLeft + maxWidth + wide, srcMid + mid,
						srcLeft + maxWidth + wide, tgtTop - high);
				drawLine(graphic, srcLeft + maxWidth + wide, tgtTop - high,
						tgtRight + wide, tgtMid + mid);
			}
			if (srcRow > tgtRow) {
				drawLine(graphic, srcLeft + maxWidth + wide, srcMid + mid,
						srcLeft + maxWidth + wide, tgtMid + mid);
				drawLine(graphic, srcLeft + maxWidth + wide, tgtMid + mid,
						tgtRight + wide, tgtMid + mid);
			}
			drawLine(graphic, srcLeft + maxWidth + wide, tgtMid + mid,
					tgtRight, tgtMid + mid);
			drawLine(graphic, tgtRight, tgtMid + mid, tgtRight + 3, tgtMid
					+ mid + 3);
			drawLine(graphic, tgtRight, tgtMid + mid, tgtRight + 3, tgtMid
					+ mid - 3);
		}
		if ((srcCol != tgtCol) && (srcRow == tgtRow)) {
			drawLine(graphic, srcRight, srcMid + mid,
					srcLeft + maxWidth + wide, srcMid + mid);
			drawLine(graphic, srcLeft + maxWidth + wide, srcMid + mid, srcLeft
					+ maxWidth + wide, tgtTop - high);
			drawLine(graphic, srcLeft + maxWidth + wide, tgtTop - high,
					tgtRight + wide, tgtTop - high);
			drawLine(graphic, tgtRight + wide, tgtTop - high, tgtRight + wide,
					tgtMid + mid);
			drawLine(graphic, tgtRight + wide, tgtMid + mid, tgtRight, tgtMid
					+ mid);
			drawLine(graphic, tgtRight, tgtMid + mid, tgtRight + 3, tgtMid
					+ mid + 3);
			drawLine(graphic, tgtRight, tgtMid + mid, tgtRight + 3, tgtMid
					+ mid - 3);
		}
		
		// draw the relationship name
		
		drawString(graphic, type, srcLeft + maxWidth + wide, tgtTop - high);
		tempCnt = tempCnt + 3;
		colorCnt++;
	}

	public DCDClass getSource() {
		return source;
	}

	public void setSource(DCDClass source) {
		this.source = source;
	}

	public DCDClass getDestination() {
		return destination;
	}

	public void setDestination(DCDClass destination) {
		this.destination = destination;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
