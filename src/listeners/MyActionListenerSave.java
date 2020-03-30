package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import tree.WorkspaceTree;
import view.MainFrame;

public class MyActionListenerSave implements ActionListener  {

	@Override
	public void actionPerformed(ActionEvent e) {
		WorkspaceTree root = MainFrame.getInstance().getRootofOurModel();
		String putanja = "";
		
		JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(MainFrame.getInstance().getResourceBundle().getString("saveFile"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("IFT files", "ift");
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        	putanja = "" + chooser.getSelectedFile();
        }
        
        if (!putanja.toLowerCase().endsWith(".ift")) {
        	putanja = putanja + ".ift";
        }
        
        File treeFile = new File(putanja);
        
        ObjectOutputStream treeOutput = null;
		try {
			treeOutput = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(treeFile)));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			treeOutput.writeObject(root);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				treeOutput.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
        //System.out.println(putanja);
        
	}

}
