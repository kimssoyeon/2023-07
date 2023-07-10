package frames;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import main.GConstants.EShape;

public class GToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	private ButtonGroup buttonGroup;
	private EShape eSelectedShape;	// 상태 적어두기
	
	public EShape getESelectedShape() {
		return this.eSelectedShape;
	}
	
	public void resetESelectedShape() {
		this.buttonGroup.clearSelection();
		this.eSelectedShape = EShape.eSelect;
	}
	
	public GToolBar() {
		super();
		ActionHandler ActionHandler = new ActionHandler();
		buttonGroup = new ButtonGroup();
		for(EShape eShape : EShape.values()) {
			if (eShape != EShape.eSelect) {
				JRadioButton buttonShape = new JRadioButton(eShape.getName());
				this.add(buttonShape);
				buttonShape.setActionCommand(eShape.toString());
				buttonShape.addActionListener(ActionHandler);
				buttonGroup.add(buttonShape);
			}
		}
		resetESelectedShape();
	}
	private class ActionHandler implements ActionListener {			// 버튼에 달지 않고 툴바에 달아준다.
		
		public void actionPerformed(ActionEvent event) {
			eSelectedShape = EShape.valueOf(event.getActionCommand());
		}
		
	}
}
//JRadioButton btnRectangle = new JRadioButton(EButtonShape.eRectangle.getName());
//ButtonGroup buttonGroup = new ButtonGroup();
//this.add(btnRectangle);
//btnRectangle.setActionCommand(EButtonShape.eRectangle.toString());
//buttonGroup.add(btnRectangle);
//btnRectangle.addActionListener(ActionHandler);