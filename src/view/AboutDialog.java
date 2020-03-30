package view;

import java.awt.Frame;

import javax.swing.JDialog;

public class AboutDialog extends JDialog{
	
	public AboutDialog(Frame parent, String title, boolean modal){
		super(parent, title, modal);

		setSize(MainFrame.getInstance().getSize().width/2, MainFrame.getInstance().getSize().height/2);
		setLocationRelativeTo(parent);
		
		
		
	}

}
