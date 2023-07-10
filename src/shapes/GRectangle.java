package shapes;
import java.awt.Rectangle;

 	public class GRectangle extends GShape{
	//이미 스윙이 가지고 있음 shape이라는 interface를 가지고 있음 
 		private int px, py;//현재의 점
		public GRectangle() {	
		}
		@Override
		public GShape clone() {
			// TODO Auto-generated method stub
			return new GRectangle();//복제라기 보단 새로 만든 것이다.
		}
 		@Override
 		public void setShape(int x1, int y1, int x2, int y2){
 			this.shape = new Rectangle(x1, y1, x2-x1, y2-y1);
 		}
		@Override
		public void resizePoint(int x2, int y2) {
			Rectangle rectangle = (Rectangle)shape;
			rectangle.setFrame(
					rectangle.getX(), rectangle.getY(), 
					x2-rectangle.getX(), y2-rectangle.getY());
		}
		@Override
		public void setpoint(int x, int y) {
			this.px = x;
			this.py = y;
		}
		
		@Override
		public void movePoint(int x, int y) {
			Rectangle rectangle = ((Rectangle)shape);
			rectangle.setLocation(rectangle.x + x-px, rectangle.y + y-py);//rectan-은 현재의 원점이고 델타로 더해줌
			this.px = x;
			this.py = y;
			
			
		}
		
		
 	}
		
		
