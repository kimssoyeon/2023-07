package main;

import shapes.GLine;
import shapes.GOval;
import shapes.GPolygon;
import shapes.GRectangle;
import shapes.GSelect;
import shapes.GShape;

public class GConstants {
	
	public class CMainFrame{
		static final int x = 200;
		static final int y = 100;
		static final int w = 600;
		static final int h = 400;
	}
	public enum EUserAction {
		e2Point,
		eNPoint;
	}
	public enum EAnchors{//cursor도 집어 넣어 놔 
		NW,
		NN,
		NE,
		EE,
		SE,
		SS,
		SW,
		WW,
		RR,
		MM
	}
	public enum EShape{
		eSelect("select", new GSelect(), EUserAction.e2Point),
		eRectangle("Rectangle", new GRectangle(), EUserAction.e2Point),//0번이자 string erec으로 나올 수 있게할 수 있음
		eOval("Oval", new GOval(), EUserAction.e2Point),
		eLine("Line", new GLine(), EUserAction.e2Point),
		ePolygon("Polygon", new GPolygon(), EUserAction.eNPoint);//유저 defined 순서
		
		private String name;
		private GShape gShape;
		private EUserAction eUserAction;
		
		private EShape(String name, GShape gShape, EUserAction eUserAction) {
			this.name = name;
			this.gShape = gShape;
			this.eUserAction = eUserAction;
		}
		
		public String getName() {
			return this.name;
		}
		public GShape getGShape() {
			return this.gShape;
		}
		public EUserAction getEUserAction() {
			return this.eUserAction;
		}
	}
	
	}
	
