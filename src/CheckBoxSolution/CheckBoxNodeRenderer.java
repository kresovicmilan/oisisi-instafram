package CheckBoxSolution;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import model.Parametar;
import model.SoftverskiProizvod;
import tree.ParametarTree;
import tree.SoftverskiProizvodTree;

public class CheckBoxNodeRenderer implements TreeCellRenderer {
	
	private JCheckBox renderer = new JCheckBox();
	private final DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();
	private Parametar data = null;
	
	private final Color selectionForeground, selectionBackground;
	private final Color textForeground, textBackground;
	
	public CheckBoxNodeRenderer() {
		final Font fontValue = UIManager.getFont("Tree.font");
		
		if (fontValue != null)
			renderer.setFont(fontValue);
		
		final Boolean focusPainted = (Boolean) UIManager.get("Tree.drawsfocusBorderAroundIcon");
		renderer.setFocusPainted(focusPainted != null && focusPainted);
		
		selectionForeground = UIManager.getColor("Tree.selectionForeground");
		selectionBackground = UIManager.getColor("Tree.selectionBackground");
		textForeground = UIManager.getColor("Tree.textForeground");
		textBackground = UIManager.getColor("Tree.textBackground");
	}
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		
		Component returnValue;
		
		if (value instanceof ParametarTree) {
			DefaultMutableTreeNode parentNodeView = (DefaultMutableTreeNode) value;
			ParametarTree node = (ParametarTree) parentNodeView;
			data = node.getP();
			renderer.setText(data.getIme());
			renderer.setSelected(data.isChecked());
		} else{
			return defaultRenderer.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		}
		
		
		if(selected) {
			renderer.setForeground(selectionForeground);
			renderer.setBackground(selectionBackground);
		} else {
			renderer.setForeground(textForeground);
			renderer.setBackground(textBackground);
		}
		
		returnValue = renderer;
		
		return returnValue;
	}
	
	protected JCheckBox getRenderer() {
		return renderer;
	}
	
	protected Parametar getData() {
		return data;
	}

}
