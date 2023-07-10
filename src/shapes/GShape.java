package shapes;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.io.Serializable;

import main.GConstants.EAnchors;
import transformer.GMover;
import transformer.GRotator;
import transformer.GResizer;

	abstract public class GShape implements Serializable{//자동으로 read write함수로 바꿔준다. 코드는 저장 안하고 위에 3개 변수만 저장 표준화된 코드로 바꿔서 해줌
		//protected int x1, y1, x2, y2;
		protected Shape shape; //어떤 도형이던 간에 점의 인터페이스로 계산을 해주는 것이다. 도형을 점의 집합으로 변형을 시켜줄 수 있다.처음에 만들 때에는 rec이나 oval로 만들었지만
		//shape으로 들어오면 그 것이 사라짐...
		private GAnchors gAnchors;
		private boolean bSelected;
		
		
		public Shape getShape() {return this.shape;}
		public void setShape(Shape shape) {this.shape = shape;}
		
		public GShape(){
			this.bSelected = false;
			this.gAnchors = new GAnchors();
		}
		abstract public GShape clone();//밑에 애들이 알아서 만들어라
		
		
		public boolean onShape(Point p) {
			return shape.contains(p);
			
		}
		public void draw(Graphics2D graphics2D) {
//			//graphics.drawRect(x1, y1, x2-x1, y2-y1);
//			Graphics2D graphics2d = (Graphics2D)graphics;
			graphics2D.draw(shape);
			if(this.bSelected) {
				this.gAnchors.draw(graphics2D, this.shape.getBounds());//둘러싸고 있는 네모를 주고 네모를 그려라
			}
		}
	
		public void setSelected(boolean bSelected) {
			this.bSelected = bSelected;
		}
		public EAnchors onShape(int x, int y) {
			if(this.bSelected) {
				EAnchors eAnchor = this.gAnchors.onShape(x, y);
				if(eAnchor != null) {
					return eAnchor;
				}
			}
			if(this.shape.contains(x, y)) {
				return EAnchors.MM;
			}
			return null;
		}
		public void resizer(int x, int y, Graphics2D graphics2d) {
			EAnchors eSelectedAnchor = this.onShape(x, y);
			double px = x;
			double py = y;
			double h = this.shape.getBounds().height;
			double w = this.shape.getBounds().width;
			switch (eSelectedAnchor) {
			case EE:
				this.shape.getBounds().width=(int) (this.shape.getBounds().width-(x - px));
				this.shape.getBounds().height=0;
				break;
			case NE:
				this.shape.getBounds().setBounds(x, y, x, y);
				this.shape.getBounds().height=(int) (this.shape.getBounds().height-(y - py));
				break;
			case NN:
				this.shape.getBounds().width=0;
				this.shape.getBounds().height=(int) (this.shape.getBounds().height-(y - py));
				break;
			case NW:
				this.shape.getBounds().width= (int) (this.shape.getBounds().width+x - px);
				this.shape.getBounds().height=(int) (this.shape.getBounds().height-(y - py));
				break;
			case WW:
				this.shape.getBounds().width=(int) (this.shape.getBounds().width+x-px);
				this.shape.getBounds().height=0;
				break;
			case SW:
				this.shape.getBounds().width=(int) (this.shape.getBounds().width+x - px);
				this.shape.getBounds().height=(int) (this.shape.getBounds().height+y - py);
				break;
			case SS:
				this.shape.getBounds().width=0;
				this.shape.getBounds().height=(int) (this.shape.getBounds().height+y - py);
				break;
			case SE:
				this.shape.getBounds().width=(int) (this.shape.getBounds().width-(x - px));
				this.shape.getBounds().height=(int) (this.shape.getBounds().height+y - py);
				break;
			default:
				break;
			}
			this.setShape(shape);
				

		}
		
		public abstract void setShape(int x1, int y1, int x2, int y2);
		public abstract void resizePoint(int x, int y);
		public abstract void movePoint(int x, int y);
		public abstract void setpoint(int x, int y);
		public void addPoint(int x, int y) {}
		
		
		
		
		
	}

