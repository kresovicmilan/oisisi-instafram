package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

import model.Parametar;
import model.SoftverskaKompanija;
import model.SoftverskiProizvod;
import tree.ParametarTree;
import tree.SoftverskaKompanijaTree;
import tree.SoftverskiProizvodTree;
import view.MainFrame;

public class RemoveNodeAction extends AbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 518409763579979111L;

	public RemoveNodeAction() {
		
	}
	
	public RemoveNodeAction(String name) {
		super(name);
	}
	
	public RemoveNodeAction(String name, Icon icon) {
		super(name, icon);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		DefaultMutableTreeNode parentNodeView = (DefaultMutableTreeNode) MainFrame
				.getInstance().getTree().getLastSelectedPathComponent();
		
		if (parentNodeView != null && !parentNodeView.isRoot()) {
			if (parentNodeView instanceof SoftverskaKompanijaTree) {
				SoftverskaKompanijaTree skt = (SoftverskaKompanijaTree) parentNodeView;
				SoftverskaKompanija sk = skt.getK();
				sk.removeFromParent();
			} else if (parentNodeView instanceof SoftverskiProizvodTree) {
				SoftverskiProizvodTree spt = (SoftverskiProizvodTree) parentNodeView;
				SoftverskiProizvod sp = spt.getP();
				sp.removeFromParent();
			} else if (parentNodeView instanceof ParametarTree) {
				ParametarTree pt = (ParametarTree) parentNodeView;
				Parametar p = pt.getP();
				p.removeFromParent();
			}
			
			MainFrame.getInstance().getTreeModel().removeNodeFromParent(parentNodeView);
		}
	}

}
