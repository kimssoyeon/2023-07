package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import shapes.GShape;

public class GRotator extends GTransformer {

	private AffineTransform affineTransform;
	private int px, py;

	public GRotator(GShape shape) {
		super(shape);
		this.affineTransform = new AffineTransform();
		}

	@Override
	public void initTransform(int x, int y, Graphics2D graphics2d) {
		this.px = x;
		this.py = y;
	}

	@Override
	public void keepTransform(int x, int y, Graphics2D graphics2d) {
		this.shape.draw(graphics2d);
		this.affineTransform.setToRotation(px, py, shape.getShape().getBounds().getCenterX(), shape.getShape().getBounds().getCenterY());
		Shape transformedShape = this.affineTransform.createTransformedShape(this.shape.getShape());
		this.shape.setShape(transformedShape);
		this.shape.draw(graphics2d);
		this.px = x;
		this.py = y;
	}

	@Override
	public void finalizeTransform(int x, int y, Graphics2D graphics2d, Vector<GShape> shapes) {
		this.shape.setSelected(true);
		this.shape.draw(graphics2d);
	}

}
