package listeners;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import tree.ParametarTree;
import tree.SoftverskaKompanijaTree;
import tree.SoftverskiProizvodTree;
import view.MainFrame;

public class MyTreeListener implements TreeSelectionListener {
	
	public MyTreeListener() {
		super();
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode parentNodeView = (DefaultMutableTreeNode) MainFrame.getInstance().getTree().getLastSelectedPathComponent();
		MainFrame.getInstance().resetSvaPolja();
		
		if (parentNodeView == null) {
			return;
		} else if (parentNodeView instanceof SoftverskaKompanijaTree) {
			MainFrame.getInstance().displayKompanijaPanel(false, true, parentNodeView);
		} else if (parentNodeView instanceof SoftverskiProizvodTree) {
			MainFrame.getInstance().displayProizvodPanel(false, true, false, parentNodeView);
		} else if (parentNodeView instanceof ParametarTree){
			MainFrame.getInstance().displayParametarPanel(false, true, parentNodeView);
		}
		
	}
}
