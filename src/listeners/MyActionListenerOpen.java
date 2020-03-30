package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import model.Workspace;
import tree.WorkspaceTree;
import view.InstalacionaKlasa;
import view.MainFrame;
import view.NodeTreeCellRendered;

public class MyActionListenerOpen implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String putanja = "";
		JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(MainFrame.getInstance().getResourceBundle().getString("chooseFile"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("IFT files", "ift");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        	putanja = "" + chooser.getSelectedFile();
        }
        
        File treeFile = new File(putanja);
        
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(treeFile)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			WorkspaceTree root = (WorkspaceTree) ois.readObject();
			MainFrame.getInstance().setVisible(false);
			MainFrame.getInstance(root);
			MainFrame.getInstance().setVisible(true);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
		}
		
	}

}
