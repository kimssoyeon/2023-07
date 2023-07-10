package shapes;

import java.awt.Polygon;

public class GPolygon extends GShape{
	private int px, py;
	public GPolygon() {	
		
	}
	@Override
	public GShape clone() {
		// TODO Auto-generated method stub
		return new GPolygon();//복제라기 보단 새로 만든 것이다.
	}
	@Override
	public void setShape(int x1, int y1, int x2, int y2){//원점잡기
		this.shape = new Polygon();
//		this.shape = new PolyLine();//만들어야함....?
		Polygon polygon = ((Polygon)shape);
		polygon.addPoint(x1, y1);
		polygon.addPoint(x2, y2);
	}
	public void addPoint(int x, int y) {
		Polygon polygon = ((Polygon)shape);
		polygon.addPoint(x, y);
	}
	@Override
	public void resizePoint(int x, int y) {//마지막 점이 결정이 되는 애
		Polygon polygon = (Polygon)shape;
		polygon.xpoints[polygon.npoints-1] = x;
		polygon.ypoints[polygon.npoints-1] = y;
	}
	@Override
	public void setpoint(int x, int y) {
		px = x;
		py = y;
	}
	
	@Override
	public void movePoint(int x, int y) {
		Polygon polygon = (Polygon)shape;
		Polygon newPolygon = new Polygon();
		for(int i = 0; i<polygon.npoints; i++) {
			newPolygon.addPoint(polygon.xpoints[i] + x-px, polygon.ypoints[i] + y-py);
//			newPolygon.xpoints[i] = polygon.xpoints[i] + x-px;
//			newPolygon.ypoints[i] = polygon.ypoints[i] + y-py;
		}
		this.shape = newPolygon;
		px = x;
		py = y;
	}
	
	
}
