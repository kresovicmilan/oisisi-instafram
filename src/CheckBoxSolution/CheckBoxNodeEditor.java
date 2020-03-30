package CheckBoxSolution;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreePath;

import CheckBoxSolution.CheckBoxNodeRenderer;
import model.Parametar;
import tree.ParametarTree;

public class CheckBoxNodeEditor extends AbstractCellEditor implements TreeCellEditor {

	private static final long serialVersionUID = 4924542923401284077L;

	private final CheckBoxNodeRenderer renderer = new CheckBoxNodeRenderer();

	private final JTree theTree;

	public CheckBoxNodeEditor(final JTree tree) {
		theTree = tree;
	}

	@Override
	public Object getCellEditorValue() {
		Parametar checkBoxData = renderer.getData();
		JCheckBox checkBox = renderer.getRenderer();
		
		checkBoxData.setChecked(checkBox.isSelected());
		return checkBoxData;
	}

	@Override
	public boolean isCellEditable(final EventObject event) {
		
		if (!(event instanceof MouseEvent))
			return false;
		final MouseEvent mouseEvent = (MouseEvent) event;

		final TreePath path = theTree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
		if (path == null)
			return false;

		final Object node = path.getLastPathComponent();
		if (!(node instanceof DefaultMutableTreeNode))
			return false;
		
		if (node instanceof ParametarTree) {
			return true;
		}
		
		return false;
	}

	@Override
	public Component getTreeCellEditorComponent(final JTree tree, final Object value, final boolean selected,
			final boolean expanded, final boolean leaf, final int row) {

		final Component editor = renderer.getTreeCellRendererComponent(tree, value, true, expanded, leaf, row, true);

		// editor always selected / focused
		final ItemListener itemListener = new ItemListener() {

			@Override
			public void itemStateChanged(final ItemEvent itemEvent) {
				if (stopCellEditing()) {
					fireEditingStopped();
				}
			}
		};
		if (editor instanceof JCheckBox) {
			JCheckBox check = (JCheckBox) editor;
			check.addItemListener(itemListener);
		}

		return editor;
	}
}
