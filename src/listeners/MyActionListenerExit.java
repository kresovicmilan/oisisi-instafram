package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import view.MainFrame;

public class MyActionListenerExit implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frame = new JFrame();
		int code = JOptionPane.showConfirmDialog(frame, MainFrame.getInstance().getResourceBundle().getString("exitMsg"), MainFrame.getInstance().getResourceBundle().getString("exitDialog"), JOptionPane.YES_NO_OPTION);
		
		if (code != JOptionPane.YES_OPTION) {
			frame.dispose();
		} else {
			System.exit(0);
		}
	}

}
