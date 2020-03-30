package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

import tree.SoftverskiProizvodTree;
import view.KonfiguratorDialog;
import view.MainFrame;

public class MyActionListenerKonfigurator implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DefaultMutableTreeNode parentNodeView = (DefaultMutableTreeNode) MainFrame.getInstance().getTree()
				.getLastSelectedPathComponent();
		if(parentNodeView instanceof SoftverskiProizvodTree) {
			SoftverskiProizvodTree selektovaniCvor = (SoftverskiProizvodTree) parentNodeView;
			KonfiguratorDialog dialog = new KonfiguratorDialog(MainFrame.getInstance(), MainFrame.getInstance().getResourceBundle().getString("configName") + " (" + selektovaniCvor.getP().getIme() + ")", true, selektovaniCvor);
			dialog.setVisible(true);
		}
		
	}

}
