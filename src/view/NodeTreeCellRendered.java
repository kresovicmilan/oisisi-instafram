package view;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import tree.ParametarTree;
import tree.SoftverskaKompanijaTree;
import tree.SoftverskiProizvodTree;
import tree.WorkspaceTree;

public class NodeTreeCellRendered extends DefaultTreeCellRenderer {

	public NodeTreeCellRendered() {

		// TODO Auto-generated constructor stub
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		if (value instanceof DefaultMutableTreeNode) {
            	if (value instanceof WorkspaceTree) {
            		setIcon(new ImageIcon("images/Workspace.png"));	
            	} else if (value instanceof SoftverskaKompanijaTree) {
            		setIcon(new ImageIcon("images/SoftverskaKompanija.png"));
            	} else if (value instanceof SoftverskiProizvodTree) {
            		setIcon(new ImageIcon("images/SoftverskiProizvod.png"));
            	} else if (value instanceof ParametarTree) {
            		setIcon(new ImageIcon("images/Parametar.png"));
            	}

		}

		return this;
	}

}
