package frames;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import main.GConstants.EAnchors;
import main.GConstants.EShape;
import main.GConstants.EUserAction;
import shapes.GShape;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GRotator;
import transformer.GResizer;
import transformer.GSelect;
import transformer.GTransformer;

//import GToolBar.EButtonShape;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private enum EDrawingEvent{
		eStart,
		eMoving,
		eCont,
		eEnd
	}
	
	private enum EDrawingState{
		eIdle,
		eTransforming
		//두개의 상태가 있음
	}
	private EDrawingState eDrawingState;
	private Vector<GShape> shapes;
	private GShape currentShape;
	
	private Cursor cursor;
	//잠깐잠깐 쓸 포인터이다.
	private GToolBar toolBar;
	
	public void setToolBar(GToolBar toolBar) {
		this.toolBar = toolBar;
	}
	private GTransformer transformer;
	
	public GDrawingPanel() {
		super();
		this.eDrawingState = EDrawingState.eIdle;
		this.shapes = new Vector<GShape>();
		this.currentShape = null;
		
		this.setBackground(Color.white);
		
		MouseEventHandler mouseHandler = new MouseEventHandler();//
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
	}
	
	public void paint(Graphics graphics) {//shape을 저장하고 화면을 다시 켰을 때 그대로 화면을 그려준다.
		super.paint(graphics); // 내 할 일 하고 실행해줄게, 해주는 것이 좋음 원래 역할 실행
		for(GShape shape : this.shapes) {
			shape.draw((Graphics2D) graphics);
		}
		//여기에 저장해라 근데 여기가 맞나
		
	}
	
//	public GShape onShape(Point point) {//onshape은 앵커를 받아와야함...?
//		for(GShape shape : shapes) {
//			if(shape.onShape(point))
//				return shape;//하나씩 꺼내서 물어본다. 
//		}
//		return null;
//	}
	private EAnchors onShape(int x, int y) {
		for(GShape gShape : this.shapes){//루핑돌아서 currentshape으로 만들어야함
			EAnchors eAnchor = gShape.onShape(x, y);
			if(eAnchor!= null) {
				this.currentShape = gShape;
				return eAnchor;
			}
		}
		return null;
	}
	private void clearSelection() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void initTransforming(int x, int y) {//어떤 transformer를 쓸 지 선택을 하는 것이다. 
		Graphics2D graphics2D = (Graphics2D) this.getGraphics();
		if (this.toolBar.getESelectedShape()== EShape.eSelect) {
				EAnchors eSelectedAnchor = this.onShape(x, y);
				if(eSelectedAnchor == null) {
					this.clearSelection();
					this.transformer = new GSelect(this.currentShape);//이 도형을 그려라
					this.transformer.initTransform(x, y, graphics2D);
					
					//transformer중에서 selector라는 트렌스포머가 선택되어있어야 한다.
				}else {
					switch (eSelectedAnchor) {
					case MM:
						this.transformer = new GMover(this.currentShape);//이 도형을 그려라
						this.transformer.initTransform(x, y, graphics2D);
						break;
					case RR://rotator
						this.transformer = new GRotator(this.currentShape);
						this.transformer.initTransform(x, y, graphics2D);
						break;
					default://resizer
						this.transformer = new GResizer(this.currentShape);
						this.transformer.initTransform(x, y, graphics2D);
						break;
					}
				}
				
		}else {
			
			if(this.toolBar.getESelectedShape().getEUserAction() == EUserAction.e2Point) {
				this.currentShape = this.toolBar.getESelectedShape().getGShape().clone();//그림 도구가 선택되었으면 
				this.transformer = new GDrawer(this.currentShape);//이 도형을 그려라
				this.transformer.initTransform(x, y, graphics2D);//그리기 시작해라
			}else {
				this.currentShape = this.toolBar.getESelectedShape().getGShape().clone();//그림 도구가 선택되었으면 
				this.transformer = new GDrawer(this.currentShape);//이 도형을 그려라
				this.transformer.initTransform(x, y, graphics2D);
			}
		}
	}
	public void keepTransforming(int x, int y) {
		Graphics2D graphics2D = (Graphics2D) this.getGraphics();
		graphics2D.setXORMode(this.getBackground());
		this.transformer.keepTransform(x, y, graphics2D);
	}
	public void continueTransforming(int x, int y) {
		Graphics2D graphics2D = (Graphics2D) this.getGraphics();
		this.transformer.continueTransform(x, y, graphics2D);
	}
	public void finalizeTransforming(int x, int y) {
		Graphics2D graphics2D = (Graphics2D) this.getGraphics();
		//unselect current selected shapes 
		this.transformer.finalizeTransform(x, y, graphics2D, this.shapes);//파라미터가 늘어나는대신 if가 없어짐
		this.toolBar.resetESelectedShape();
		
	}//얘가 끝나고 나면 select가 된 상태가 되어 있어야한다. & shape vector로 추가가 되어있어야함

	private void changeCursors(int x, int y) {
		EAnchors eAnchor = this.onShape(x, y);
		if(eAnchor == null) {
		cursor = new Cursor(Cursor.DEFAULT_CURSOR);
		}else {
			switch (eAnchor) {
			case MM:
				cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
				break;
			case RR:
				cursor = new Cursor(Cursor.HAND_CURSOR);//그림 그리면 5점
				break;
			case NW:
				cursor = new Cursor(Cursor.NW_RESIZE_CURSOR);
				break;
			case NN:
				cursor = new Cursor(Cursor.N_RESIZE_CURSOR);
				break;
			case NE:
				cursor = new Cursor(Cursor.NE_RESIZE_CURSOR);
				break;
			case EE:
				cursor = new Cursor(Cursor.E_RESIZE_CURSOR);
				break;
			case SE:
				cursor = new Cursor(Cursor.SE_RESIZE_CURSOR);
				break;
			case SS:
				cursor = new Cursor(Cursor.S_RESIZE_CURSOR);
				break;
			case SW:
				cursor = new Cursor(Cursor.SW_RESIZE_CURSOR);
				break;
			case WW:
				cursor = new Cursor(Cursor.W_RESIZE_CURSOR);
				break;
			default:
				cursor = new Cursor(Cursor.DEFAULT_CURSOR);
				break;
			}
		}
		this.setCursor(cursor);
	}
	
	private class MouseEventHandler implements MouseListener, MouseMotionListener {
		

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		private void mouse1Clicked(MouseEvent e) {
			if(eDrawingState == EDrawingState.eIdle) {
				initTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.eTransforming;
			}else if(eDrawingState == EDrawingState.eTransforming) {
				continueTransforming(e.getX(), e.getY());
			}
		}

		
		@Override
		public void mouseMoved(MouseEvent e) {
			if(eDrawingState == EDrawingState.eTransforming) {
				keepTransforming(e.getX(), e.getY());
				}else if(eDrawingState == EDrawingState.eIdle) {
					changeCursors(e.getX(), e.getY());
				}
		}

		

		@Override
		public void mousePressed(MouseEvent e) {
			if(eDrawingState == EDrawingState.eIdle) {
				initTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.eTransforming;
			}
		}
		private void mouse2Clicked(MouseEvent e) {
			if(eDrawingState == EDrawingState.eTransforming) {
				finalizeTransforming(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if(eDrawingState == EDrawingState.eTransforming) {//transform이 drawing 등 다 해당됨
				keepTransforming(e.getX(), e.getY());
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if(eDrawingState == EDrawingState.eTransforming) {
				finalizeTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
		}

		
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		
	}
}
