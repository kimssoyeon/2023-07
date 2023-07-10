package main;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import frames.GDrawingPanel;
import frames.GMenuBar;
import frames.GToolBar;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private GMenuBar gMenuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;
	
	public GMainFrame() {//생성자
		//actinitialize
		this.setLocation(GConstants.CMainFrame.x, GConstants.CMainFrame.y);//바꿔
		this.setSize(GConstants.CMainFrame.w, GConstants.CMainFrame.h);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//child components
		
		this.gMenuBar = new GMenuBar();
		this.setJMenuBar(gMenuBar);
		
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		
		this.toolBar = new GToolBar();
		this.add(this.toolBar, BorderLayout.NORTH);//aggregation hierarcy create
//		ToolBar = new ToolBar();
		
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);//만들어서 자식으로 등록
		//association 자식관의 관계만듬 / 연결을 작게 할 수록 복잡도가 줄어든다. 복잡도, 규칙적이지 않다 최소 부모자식관계 직접 연결해주는게 좋음 서로 소통하는것이 좋음
		this.drawingPanel.setToolBar(toolBar);//드로잉 패널과 툴 바가 연결됨
}
	
}
