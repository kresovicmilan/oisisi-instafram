package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;

import tree.ParametarTree;
import tree.SoftverskaKompanijaTree;
import tree.SoftverskiProizvodTree;
import tree.WorkspaceTree;
import view.MainFrame;

public class MyActionListenerAdding implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DefaultMutableTreeNode parentNodeView = (DefaultMutableTreeNode) MainFrame.getInstance().getTree().getLastSelectedPathComponent();

		MainFrame.getInstance().resetSvaPolja();
		
		if (parentNodeView == null || parentNodeView instanceof WorkspaceTree) {
			MainFrame.getInstance().displayKompanijaPanel(true, false);
		} else if (parentNodeView instanceof SoftverskaKompanijaTree){
			MainFrame.getInstance().displayProizvodPanel(true, false, true);
		} else if (parentNodeView instanceof SoftverskiProizvodTree || parentNodeView instanceof ParametarTree) {
			MainFrame.getInstance().displayParametarPanel(true, false);
		}
		
	}

}
