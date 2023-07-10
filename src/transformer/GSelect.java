package transformer;

import java.awt.Graphics2D;
import java.util.Vector;

import shapes.GShape;

public class GSelect extends GTransformer{

	private int px, py;
	
	public GSelect(GShape shape) {
		super(shape);
	}
	@Override
	public void initTransform(int x, int y, Graphics2D graphics2d) {
		px = x;
		py = y;
	}

	@Override
	public void keepTransform(int x, int y, Graphics2D graphics2d) {
		px = x;
		py = y;
	}

	@Override
	public void finalizeTransform(int x, int y, Graphics2D graphics2d, Vector<GShape> shapes) {
		// TODO Auto-generated method stub
		
	}

	

}
