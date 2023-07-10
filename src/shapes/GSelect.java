package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GSelect extends GRectangle{
	public GSelect(){
	}
		//이미 스윙이 가지고 있음 shape이라는 interface를 가지고 있음 
		
	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D)graphics;
		graphics2D.setColor(Color.cyan);
		graphics2D.draw(shape);
	}
//	public abstract void setShape(int x1, int y1, int x2, int y2);
//	public abstract void setPoint(int x1, int y1);
}