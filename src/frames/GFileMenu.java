package frames;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import main.SaveFile;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	private JMenuItem itemNew; 
	
	public GFileMenu(String title) {
		super(title);
		
		itemNew = new JMenuItem("new");
		this.add(itemNew);
		
	}
	
}
