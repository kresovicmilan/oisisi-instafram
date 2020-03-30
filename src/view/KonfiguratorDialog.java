package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import CheckBoxSolution.CheckBoxNodeEditor;
import CheckBoxSolution.CheckBoxNodeRenderer;
import model.Parametar;
import panels.ProizvodPanel;
import tree.ParametarTree;
import tree.SoftverskiProizvodTree;
import tree.WorkspaceTree;

public class KonfiguratorDialog extends JDialog{
	
	private int screenHeight = MainFrame.getInstance().getSize().height*8/10;
	private int screenWidth = MainFrame.getInstance().getSize().width*8/10;
	//private final JPanel contentPanel = new JPanel();
	
	private JLabel lblIzvorZip;
	private JLabel lblWelcome;
	private JLabel lblLicence;
	private JLabel lblVerzija;
	
	private JTextField txtIzvor;
	private JTextArea txtWelcome;
	private JTextArea txtLicenca;
	private JTextField txtVerzija;
	private ArrayList<Parametar> listaParametara = new ArrayList<Parametar>();
	
	private DefaultMutableTreeNode rootofOurModel;
	private DefaultTreeModel treeModel;
	private JTree tree;
	
	public KonfiguratorDialog(Frame parent, String title, boolean modal, SoftverskiProizvodTree selektovaniCvor){
		super(parent, title, modal);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultLookAndFeelDecorated(true);
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(screenWidth, screenHeight);
		setLocationRelativeTo(parent);
		getContentPane().setLayout(new BorderLayout());
		
		InitTree();
		InitCenterPanel();
	}
	
	private void InitTree() {
		rootofOurModel = (DefaultMutableTreeNode) MainFrame.getInstance().getTree()
				.getLastSelectedPathComponent();
		
		treeModel =  new DefaultTreeModel(rootofOurModel);
		treeModel.setAsksAllowsChildren(true);
		
		tree = new JTree(treeModel) {
			
			@Override
			public boolean isPathEditable(TreePath path) {
				// Svi se mogu editovati sem root-a.
				if (path != null) {
					DefaultMutableTreeNode tn = (DefaultMutableTreeNode) path.getLastPathComponent();
					if (!tn.isRoot()) {
						return true;
					} else {
						return false;
					}
				}
				return false;
			}
		};
		
		tree.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		tree.setCellRenderer(new CheckBoxNodeRenderer());
		tree.setCellEditor(new CheckBoxNodeEditor(tree));
		tree.setEditable(true);
		expandAllNodes(tree);
	}
	
	public void InitCenterPanel() {
		JScrollPane panLeft = new JScrollPane(tree);
		//panLeft.setBackground(Color.WHITE);
		panLeft.setPreferredSize(new Dimension(screenWidth*35/100, screenHeight/2));
		panLeft.setMinimumSize(new Dimension(200, 150));
		panLeft.setBorder(new EmptyBorder(screenHeight*5/100, 5, screenHeight*5/100, 5));
		add(panLeft, BorderLayout.WEST);
		
		JPanel panTop = new JPanel();
		panTop.setBorder(new EmptyBorder(screenHeight*10/100, 5, screenHeight*5/100, 5));
		JPanel panRight = new JPanel();
		//panRight.setPreferredSize(new Dimension(screenWidth/2, screenHeight/2));
		panRight.setMinimumSize(new Dimension(100,100));
		
		panTop.add(panRight, BorderLayout.NORTH);
		add(panTop, BorderLayout.CENTER);
		
		panRight.setLayout(new GridBagLayout());
		
		lblIzvorZip = new JLabel(MainFrame.getInstance().getResourceBundle().getString("lblIzvorZip"));
		lblWelcome = new JLabel(MainFrame.getInstance().getResourceBundle().getString("lblWelcome"));
		lblLicence = new JLabel(MainFrame.getInstance().getResourceBundle().getString("lblLicence"));
		lblVerzija = new JLabel(MainFrame.getInstance().getResourceBundle().getString("lblVerzija"));
		
		txtIzvor = new JTextField();
		txtIzvor.setPreferredSize(new Dimension(screenWidth*30/100, screenHeight*5/100));
		
		txtWelcome = new JTextArea();
		JScrollPane spWelcome = new JScrollPane(txtWelcome);
		txtWelcome.setLineWrap(true);
		txtWelcome.setWrapStyleWord(true);
		spWelcome.setPreferredSize(new Dimension(screenWidth*35/100, screenHeight*2/10));
		
		txtLicenca = new JTextArea();
		JScrollPane spLicenca = new JScrollPane(txtLicenca);
		txtLicenca.setLineWrap(true);
		txtLicenca.setWrapStyleWord(true);
		spLicenca.setPreferredSize(new Dimension(screenWidth*35/100, screenHeight*2/10));
		
		txtVerzija = new JTextField();
		txtVerzija.setPreferredSize(new Dimension(screenWidth*35/100, screenHeight*5/100));
		txtVerzija.setText(((SoftverskiProizvodTree) rootofOurModel).getP().getVerzija());
		
		JButton btnBrowse = new JButton("...");
		btnBrowse.setPreferredSize(new Dimension(screenWidth*5/100, screenHeight*5/100));
		btnBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
		        chooser.setDialogTitle(MainFrame.getInstance().getResourceBundle().getString("chooseFile"));
		        FileNameExtensionFilter filter = new FileNameExtensionFilter("Zip files", "zip");
		        chooser.setFileFilter(filter);
		        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        chooser.setAcceptAllFileFilterUsed(false);
		        
		        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		        	txtIzvor.setText("" + chooser.getSelectedFile());
		        }
			}
		});
		
		JPanel browse = new JPanel();
		browse.add(txtIzvor);
		browse.add(btnBrowse);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton btnExport = new JButton(MainFrame.getInstance().getResourceBundle().getString("export"));
		btnExport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!(txtIzvor.getText().equals("") || txtLicenca.getText().equals("") || txtWelcome.getText().equals(""))) {
					try {
						setupFiles();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					KonfiguratorDialog.this.dispose();
				} else {
					greska();
				}
			}
		});
		
		JButton btnCancel = new JButton(MainFrame.getInstance().getResourceBundle().getString("btnOdustani"));
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				KonfiguratorDialog.this.dispose();
				
			}
		});
		
		buttons.add(btnExport);
		buttons.add(btnCancel);
		buttons.setPreferredSize(new Dimension(screenWidth*35/100, screenHeight*5/100));
		
		panRight.add(lblIzvorZip, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panRight.add(browse, new GridBagConstraints(1, 0, 1, 1, 20, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		//panRight.add(btnBrowse, new GridBagConstraints(2, 0, 1, 1, 100, 0, GridBagConstraints.CENTER,
				//GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panRight.add(lblWelcome, new GridBagConstraints(0, 1, 1, 1, 20, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panRight.add(spWelcome, new GridBagConstraints(1, 1, 1, 1, 20, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panRight.add(lblLicence, new GridBagConstraints(0, 2, 1, 1, 20, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panRight.add(spLicenca, new GridBagConstraints(1, 2, 1, 1, 20, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panRight.add(lblVerzija, new GridBagConstraints(0, 3, 1, 1, 20, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panRight.add(txtVerzija, new GridBagConstraints(1, 3, 1, 1, 20, 0, GridBagConstraints.CENTER,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panRight.add(buttons, new GridBagConstraints(1, 4, 1, 1, 20, 0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		
		//JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panLeft, panRight);
		//add(sp, BorderLayout.CENTER);
	}
	
	private void expandAllNodes(JTree tree) {
	    int j = tree.getRowCount();
	    int i = 0;
	    while(i < j) {
	        tree.expandRow(i);
	        i += 1;
	        j = tree.getRowCount();
	    }
	}
	
	private void dodajListuParametara (DefaultMutableTreeNode root) {
		if (root.getChildCount() >= 0) {
			for (Enumeration e = root.children(); e.hasMoreElements();) {
				ParametarTree node = (ParametarTree) e.nextElement();
				Parametar p = node.getP();
				if (p.isChecked())
					listaParametara.add(p);
				dodajListuParametara(node);
			}
		}
	}
	
	public void setupFiles() throws IOException, FileNotFoundException, ClassNotFoundException {
		Path adresa = Paths.get(System.getProperty("user.dir") + File.separator + "InstalacijeProizvoda");
		if (!Files.exists(adresa)) {
			new File(adresa.toString()).mkdir();
		} 
		
		String putanjaProizvoda = adresa.toString() + File.separator + ((SoftverskiProizvodTree) rootofOurModel).getP().getIme();
		if (Files.exists(Paths.get(putanjaProizvoda))) {
		    new File(putanjaProizvoda).delete();
		} 
		new File(putanjaProizvoda).mkdir();
		
		File setupFile = new File(putanjaProizvoda + File.separator + "setup.love");
		//System.out.println(putanjaProizvoda);
		File propertiesFile = new File(putanjaProizvoda + File.separator + "properties.ini");
		Properties prop = new Properties();
		dodajListuParametara(rootofOurModel);
		

		for(Parametar p : listaParametara)
			prop.put(p.getIme(), p.getVrednost());
		
		InstalacionaKlasa podaci = new InstalacionaKlasa(getTxtWelcome().getText(), getTxtLicenca().getText(), getTxtVerzija().getText(), getTxtIzvor().getText(), prop, ((SoftverskiProizvodTree) rootofOurModel).getP().getIme());
		
		BufferedWriter propertiesUpis = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(propertiesFile)));
		
		try {
			for(Parametar p : listaParametara) {
				propertiesUpis.write(p.getIme() + "=" + p.getVrednost());
				propertiesUpis.newLine();
			}
			
		} finally {
			propertiesUpis.close();
		}
		
		ObjectOutputStream setupOutput = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(setupFile)));
		try {
			setupOutput.writeObject(podaci);
		} finally {
			setupOutput.close();
		}
		
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(setupFile)));
		try {
			InstalacionaKlasa ik = (InstalacionaKlasa) ois.readObject();
			//System.out.println(ik);
		} finally {
			ois.close();
		}
		
	}

	public JTextField getTxtIzvor() {
		return txtIzvor;
	}

	public JTextArea getTxtWelcome() {
		return txtWelcome;
	}

	public JTextArea getTxtLicenca() {
		return txtLicenca;
	}

	public JTextField getTxtVerzija() {
		return txtVerzija;
	}
	
	public void greska() {
		Window parent = SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(parent, MainFrame.getInstance().getResourceBundle().getString("WarningMsgKonf"));
	}

}