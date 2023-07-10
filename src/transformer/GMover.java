package transformer;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import shapes.GShape;

public class GMover extends GTransformer {//o

	private AffineTransform affineTransform;
	private int px, py;
	
	public GMover(GShape shape) {
		super(shape);
		this.affineTransform = new AffineTransform();
		//trasnlation이라는 affine transform jdk가서 찾아서 보기
	}

	@Override
	public void initTransform(int x, int y, Graphics2D graphics2D) {
		px = x;
		py = y;
	}

	@Override
	public void keepTransform(int x, int y, Graphics2D graphics2D) {
		this.shape.draw(graphics2D);
		this.affineTransform.setToTranslation(x-px, y-py);
		Shape transformedShape = this.affineTransform.createTransformedShape(this.shape.getShape());
		this.shape.setShape(transformedShape);;
		this.shape.draw(graphics2D);
		px = x;
		py = y;
		
	}

	@Override
	public void finalizeTransform(int x, int y, Graphics2D graphics2D, Vector<GShape> shapes) {
//		shapes.add(this.shape);
		this.shape.setSelected(true);
		this.shape.draw(graphics2D);
		
	}

}
