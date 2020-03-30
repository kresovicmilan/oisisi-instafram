package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import InstalacioniPaket.InstalacioniProzor;
import actions.AddNodeAction;
import actions.RemoveNodeAction;
import view.MainFrame;
import listeners.MyActionListenerAdding;
import listeners.MyActionListenerExit;
import listeners.MyActionListenerKonfigurator;
import listeners.MyActionListenerOpen;
import listeners.MyActionListenerSave;

public class Toolbar extends JToolBar {
	private JButton btnNew;
	private JButton btnOpen;
	private JButton btnClose;
	private JButton btnSave;
	private JButton btnSaveAs;
	private JButton btnExit;
	
	public Toolbar() {
		btnNew = new JButton(new ImageIcon("Images/icons8-add-40.png"));
		btnNew.setToolTipText("New");
		btnOpen = new JButton(new ImageIcon("Images/icons8-document-40.png"));
		btnOpen.setToolTipText("Open");
		btnClose = new JButton(new ImageIcon("Images/icons8-delete-40.png"));
		btnClose.setToolTipText("Remove");
		btnSave = new JButton(new ImageIcon("Images/icons8-save-40.png"));
		btnSave.setToolTipText("Save");
		btnSaveAs = new JButton(new ImageIcon("Images/icons8-save-all-40.png"));
		btnSaveAs.setToolTipText("Save as...");
		btnExit = new JButton(new ImageIcon("Images/icons8-exit-40.png"));
		btnExit.setToolTipText("Exit");
		
		btnNew.addActionListener(new MyActionListenerAdding());
		btnOpen.addActionListener(new MyActionListenerOpen());
		btnSave.addActionListener(new MyActionListenerSave());
		btnClose.addActionListener(new RemoveNodeAction());
		btnExit.addActionListener(new MyActionListenerExit());
		
		add(btnNew);
		add(btnOpen);
		add(btnClose);
		add(btnSave);
		add(btnSaveAs);
		add(btnExit);
		
		ucitajJezik();
		validate();
	}
	
	public void ucitajJezik() {
		btnNew.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("btnNew"));
		btnOpen.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("btnOpen"));
		btnClose.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("btnClose"));
		btnSave.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("btnSave"));
		btnSaveAs.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("btnSaveAs"));
		btnExit.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("btnExit"));
	}
	
	
}
