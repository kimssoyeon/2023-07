package transformer;

import java.awt.Graphics2D;
import java.util.Vector;

import shapes.GShape;

public class GResizer extends GTransformer {
	private int px, py;
	
	public GResizer(GShape shape) {
		super(shape);
	}

	@Override
	public void initTransform(int x, int y, Graphics2D graphics2d) {
		this.px = x;
		this.py = y;
	}

	@Override
	public void keepTransform(int x, int y, Graphics2D graphics2d) {
		this.shape.resizer(x, y, graphics2d);
		this.px = x;
		this.py = y;
		
	}

	@Override
	public void finalizeTransform(int x, int y, Graphics2D graphics2d, Vector<GShape> shapes) {
		this.shape.setSelected(true);
		this.shape.draw(graphics2d);
	}

}
