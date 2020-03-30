package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import actions.RemoveNodeAction;
import view.MainFrame;
import view.AboutDialog;
import listeners.MyActionListenerAdding;
import listeners.MyActionListenerExit;
import listeners.MyActionListenerKonfigurator;
import listeners.MyActionListenerOpen;
import listeners.MyActionListenerSave;

public class Menu extends JMenuBar {
	
	private JMenu mFile;
	private JMenu mEdit;
	private JMenu mTool;
	private JMenu mMore;
	private JMenu mExport;
	private JMenu mImport;
	private JMenu mJezik;
	private JMenuItem miNew;
	private JMenuItem miOpen;
	private JMenuItem miClose;
	private JMenuItem miSave;
	private JMenuItem miSaveAs;
	private JMenuItem miExport1;
	private JMenuItem miExport2;
	private JMenuItem miImport1;
	private JMenuItem miImport2;
	private JMenuItem miExit;
	private JMenuItem miAbout;
	private JMenuItem miHelp;
	private JCheckBoxMenuItem mSrpski;
	private JCheckBoxMenuItem mEngleski;
	
	public Menu() {
		mFile = new JMenu("File");
		mFile.setMnemonic(KeyEvent.VK_F);
		mEdit = new JMenu("Edit");
		mEdit.setMnemonic(KeyEvent.VK_V);
		mTool = new JMenu("Tool");
		mTool.setMnemonic(KeyEvent.VK_T);
		mMore = new JMenu("More");
		
		//Pravljenje menija File
		miNew = new JMenuItem("New", new ImageIcon("Images/icons8-add-40.png"));
		miNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		miNew.addActionListener(new MyActionListenerAdding());

		miOpen = new JMenuItem("Open", new ImageIcon("Images/icons8-document-40.png"));
		miOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		miOpen.addActionListener(new MyActionListenerOpen());
		
		miClose = new JMenuItem("Remove", new ImageIcon("Images/icons8-delete-40.png"));
		miClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		miClose.addActionListener(new RemoveNodeAction());

		miSave = new JMenuItem("Save", new ImageIcon("Images/icons8-save-40.png"));
		miSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		miSave.addActionListener(new MyActionListenerSave());

		miSaveAs = new JMenuItem("Save As...", new ImageIcon("Images/icons8-save-all-40.png"));
		miSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | java.awt.Event.SHIFT_MASK));
		
		mExport = new JMenu("Export");
		miExport1 = new JMenuItem("Item1");
		miExport2 = new JMenuItem("Item2");
		mExport.add(miExport1);
		mExport.add(miExport2);
		
		mImport = new JMenu("Import");
		miImport1 = new JMenuItem("Item1");
		miImport2 = new JMenuItem("Item2");
		mImport.add(miImport1);
		mImport.add(miImport2);
		
		miExit = new JMenuItem("Exit", new ImageIcon("Images/icons8-exit-40.png"));
		miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK | java.awt.Event.SHIFT_MASK));
		miExit.addActionListener(new MyActionListenerExit());
		
		//Ubacivanje u meni za File
		mFile.add(miNew);		
		mFile.add(miOpen);
		mFile.addSeparator();
		
		mFile.add(miClose);
		mFile.addSeparator();
		
		mFile.add(miSave);
		mFile.add(miSaveAs);
		mFile.addSeparator();
		
		mFile.add(mExport);
		mFile.add(mImport);
		mFile.addSeparator();
		
		mFile.add(miExit);
		
		//Pravljenje menija Helpa
		miAbout = new JMenuItem("About", new ImageIcon("Images/icons8-info-40.png"));
		miHelp = new JMenuItem("Help", new ImageIcon("Images/icons8-help-40.png"));
		
		miAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AboutDialog dialog=new AboutDialog(MainFrame.getInstance(), MainFrame.getInstance().getResourceBundle().getString("about"), true);
				dialog.setVisible(true);
				
				
			}
		});
		
		mSrpski = new JCheckBoxMenuItem("Srpski");
		mSrpski.setSelected(true);
		mSrpski.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (mSrpski.isSelected()) {
					Locale.setDefault(new Locale("sr", "RS"));
					MainFrame.getInstance().changeLanguage();
					mEngleski.setSelected(false);
				}else {
					mSrpski.setSelected(true);
				}
			}
			
		});
		
		mEngleski = new JCheckBoxMenuItem("Engleski");
		mEngleski.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (mEngleski.isSelected()) {
					Locale.setDefault(new Locale("en", "US"));
					MainFrame.getInstance().changeLanguage();
					mSrpski.setSelected(false);
				}else {
					mEngleski.setSelected(true);
				}
			}
			
		});
		
		mJezik = new JMenu("Language");
		
		mJezik.add(mSrpski);
		mJezik.add(mEngleski);
		
		//Ubacivanje u meni za Help
		mMore.add(miAbout);
		mMore.add(miHelp);
		
		//Dodavanje u MenuBar
		add(mFile);
		add(mEdit);
		add(mTool);
		add(mMore);
		add(mJezik);
		
		ucitajJezik();
		validate();
		
	}
	
	public void ucitajJezik() {
		mFile.setText(MainFrame.getInstance().getResourceBundle().getString("file"));
		mEdit.setText(MainFrame.getInstance().getResourceBundle().getString("edit"));
		mTool.setText(MainFrame.getInstance().getResourceBundle().getString("tool"));
		mMore.setText(MainFrame.getInstance().getResourceBundle().getString("more"));
		mExport.setText(MainFrame.getInstance().getResourceBundle().getString("export"));
		mImport.setText(MainFrame.getInstance().getResourceBundle().getString("import"));
		mJezik.setText(MainFrame.getInstance().getResourceBundle().getString("jezik"));
		miNew.setText(MainFrame.getInstance().getResourceBundle().getString("new"));
		miOpen.setText(MainFrame.getInstance().getResourceBundle().getString("open"));
		miClose.setText(MainFrame.getInstance().getResourceBundle().getString("close"));
		miSave.setText(MainFrame.getInstance().getResourceBundle().getString("save"));
		miSaveAs.setText(MainFrame.getInstance().getResourceBundle().getString("saveas"));
		miExport1.setText(MainFrame.getInstance().getResourceBundle().getString("export1"));
		miExport2.setText(MainFrame.getInstance().getResourceBundle().getString("export2"));
		miImport1.setText(MainFrame.getInstance().getResourceBundle().getString("import1"));
		miExport2.setText(MainFrame.getInstance().getResourceBundle().getString("import2"));
		miExit.setText(MainFrame.getInstance().getResourceBundle().getString("exit"));
		miAbout.setText(MainFrame.getInstance().getResourceBundle().getString("about"));
		miHelp.setText(MainFrame.getInstance().getResourceBundle().getString("help"));
		mSrpski.setText(MainFrame.getInstance().getResourceBundle().getString("srpski"));
		mEngleski.setText(MainFrame.getInstance().getResourceBundle().getString("engleski"));
	
	}
}


