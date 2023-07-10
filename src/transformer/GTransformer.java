package transformer;

import java.awt.Graphics2D;
import java.util.Vector;

import shapes.GShape;

abstract public class GTransformer {
	protected GShape shape;
	
	
	public GTransformer(GShape shape) {//transformer가 이거 가지고 작업해야하기 때문에 저장해두는 것이다.
		this.shape = shape;
	}

	abstract public void initTransform(int x, int y, Graphics2D graphics2D);

	abstract public void keepTransform(int x, int y, Graphics2D graphics2D);

	abstract public void finalizeTransform(int x, int y, Graphics2D graphics2D, Vector<GShape> shapes);
	public void continueTransform(int x, int y, Graphics2D graphics2D) {}

}

