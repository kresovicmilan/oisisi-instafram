package view;

import java.awt.Color;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Timer;
import java.awt.Dimension; 
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import actions.AddNodeAction;
import listeners.MyTreeListener;
import listeners.MyWindowListener;
import model.SoftverskaKompanija;
import model.Workspace;
import panels.KompanijaPanel;
import panels.ParametarPanel;
import panels.ProizvodPanel;
import tree.SoftverskaKompanijaTree;
import tree.WorkspaceTree;
import view.NodeTreeCellRendered;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6573923851927499642L;
	
	private int screenHeight;
	private int screenWidth;
	private static MainFrame instance = null; 
	private JTree tree;
	private DefaultTreeModel treeModel;
	private WorkspaceTree rootofOurModel = null;
	
	private JPanel panRight;
	private KompanijaPanel kp;
	private ProizvodPanel pp;
	private ParametarPanel ppl;
	private Menu menuBar;
	private Toolbar menuTool;
	private JLabel lbdatum;
	private JLabel msg;
	private DateFormat dateFormat;
	private String date;
	private ResourceBundle resourceBundle;
	
	/*static {
		WorkspaceTree ws = null;
		instance = new MainFrame(ws);
	}*/

	public MainFrame() {
		
		Locale.setDefault(new Locale("sr", "RS"));
		resourceBundle = ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
		
		ImageIcon img = new ImageIcon("Images/InstaFram.png");
		setIconImage(img.getImage());
		/*this.rootofOurModel = ws;
		ImageIcon img = new ImageIcon("Images/InstaFram.png");
		setIconImage(img.getImage());
		initialise(); */
	}
	
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
			instance.initialise();
		}
		return instance;
	}
	
	public static MainFrame getInstance(WorkspaceTree ws) {
		instance = new MainFrame();
		instance.setRootofOurModel(ws);
		instance.initialise();
		return instance;
	} 
	
	private void initialise() {
		InitTree();
		InitFrame();
		InitMenu();
		InitTopToolbar();
		InitCenterPanel();
		InitBotPanel();
	}
	
	public void InitTree() {
		if (rootofOurModel == null) {
			Workspace ws = new Workspace("Radni prostor");
			this.rootofOurModel = new WorkspaceTree(ws);
		}
		
		treeModel = new DefaultTreeModel(rootofOurModel);
		treeModel.setAsksAllowsChildren(true);
		
		tree = new JTree(treeModel) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -8492880515003190453L;

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
		
		tree.setCellRenderer(new NodeTreeCellRendered());
		tree.addTreeSelectionListener(new MyTreeListener());
		tree.setEditable(true);
		expandAllNodes(tree);
		
	}

	
	public void InitFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultLookAndFeelDecorated(true);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		screenHeight = screenSize.height;
		screenWidth = screenSize.width;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("InstaFram");
		setSize(screenWidth/2, screenHeight*8/10);
		setLocationRelativeTo(null);
		
		addWindowListener(new MyWindowListener());
	}
	
	public void InitMenu() {
		menuBar = new Menu();
		this.setJMenuBar(menuBar);
	}
	
	public void InitTopToolbar() {
		menuTool = new Toolbar();
		this.add(menuTool, BorderLayout.NORTH);
	}
	
	public void InitCenterPanel() {
		JScrollPane panLeft = new JScrollPane(tree);
		panLeft.setBackground(Color.WHITE);
		panLeft.setPreferredSize(new Dimension(screenWidth/2, screenHeight/2));
		panLeft.setMinimumSize(new Dimension(200, 150));
		
		this.panRight = new JPanel();
		panRight.setBackground(Color.WHITE);
		panRight.setPreferredSize(new Dimension(screenWidth/2, screenHeight/2));
		panRight.setMinimumSize(new Dimension(100,100));
		
		kp = new KompanijaPanel();
		panRight.add(kp);
		
		pp = new ProizvodPanel();
		panRight.add(pp);
		
		ppl = new ParametarPanel();
		panRight.add(ppl);
		
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panLeft, panRight);
		add(sp, BorderLayout.CENTER);
	}
	
	public void InitBotPanel() {
		JPanel panBot = new JPanel(new BorderLayout());
		panBot.setPreferredSize(new Dimension(20, 20));
		
		msg = new JLabel("  " + "Dobrodosli!", JLabel.LEFT);
		add(panBot, BorderLayout.SOUTH);
		
		dateFormat = DateFormat.getDateInstance();
		date = dateFormat.format(new Date());
		
		lbdatum = new JLabel();
		lbdatum.setText(date);
		
		ClockPanel vreme = new ClockPanel();
	    panBot.add(msg, BorderLayout.WEST);
	    panBot.add(lbdatum, BorderLayout.EAST);
	}
	
	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeigth(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public JTree getTree() {
		return tree;
	}

	public DefaultTreeModel getTreeModel() {
		return treeModel;
	}

	public JPanel getPanRight() {
		return panRight;
	}

	public WorkspaceTree getRootofOurModel() {
		return rootofOurModel;
	}

	public KompanijaPanel getKompanijaPanel() {
		return kp;
	}
	
	
	public ProizvodPanel getProizvodPanel() {
		return pp;
	}

	public ParametarPanel getParametarPanel() {
		return ppl;
	}

	public void updateTree(DefaultMutableTreeNode node) {
		treeModel.nodeChanged(node);
	}
	
	public void resetSvaPolja() {
		getKompanijaPanel().zatvori();
		getProizvodPanel().zatvori();
		getParametarPanel().zatvori();
	}
	
	public void displayKompanijaPanel(boolean dodaj, boolean izmeni) {
		getKompanijaPanel().getBtnDodaj().setVisible(dodaj);
		getKompanijaPanel().getBtnIzmeni().setVisible(izmeni);
		
		getKompanijaPanel().setVisible(true);
	}
	
	public void displayProizvodPanel(boolean dodaj, boolean izmeni, boolean ponisti) {
		getProizvodPanel().getBtnDodaj().setVisible(dodaj);
		getProizvodPanel().getBtnPonisti().setVisible(ponisti);
		getProizvodPanel().getBtnIzmeni().setVisible(izmeni);
		getProizvodPanel().getBtnKonfigurisi().setVisible(izmeni);
		
		getProizvodPanel().setVisible(true);
	}
	
	public void displayParametarPanel(boolean dodaj, boolean izmeni) {
		getParametarPanel().getBtnDodaj().setVisible(dodaj);
		getParametarPanel().getBtnIzmeni().setVisible(izmeni);
		
		getParametarPanel().setVisible(true);
	}
	
	public void displayKompanijaPanel(boolean dodaj, boolean izmeni, DefaultMutableTreeNode cvor) {
		getKompanijaPanel().getBtnDodaj().setVisible(dodaj);
		getKompanijaPanel().getBtnIzmeni().setVisible(izmeni);
		
		getKompanijaPanel().refreshView(cvor);
		
		getKompanijaPanel().setVisible(true);
	}
	
	public void displayProizvodPanel(boolean dodaj, boolean izmeni, boolean ponisti, DefaultMutableTreeNode cvor) {
		getProizvodPanel().getBtnDodaj().setVisible(dodaj);
		getProizvodPanel().getBtnPonisti().setVisible(ponisti);
		getProizvodPanel().getBtnIzmeni().setVisible(izmeni);
		getProizvodPanel().getBtnKonfigurisi().setVisible(izmeni);
		
		getProizvodPanel().refreshView(cvor);
		
		getProizvodPanel().setVisible(true);
	}
	
	public void displayParametarPanel(boolean dodaj, boolean izmeni, DefaultMutableTreeNode cvor) {
		getParametarPanel().getBtnDodaj().setVisible(dodaj);
		getParametarPanel().getBtnIzmeni().setVisible(izmeni);
		
		getParametarPanel().refreshView(cvor);
		
		getParametarPanel().setVisible(true);
	}

	public void setTree(JTree tree) {
		this.tree = tree;
	}

	public void setTreeModel(DefaultTreeModel treeModel) {
		this.treeModel = treeModel;
	}

	public void setRootofOurModel(WorkspaceTree rootofOurModel) {
		this.rootofOurModel = rootofOurModel;
	}

	public static void setInstance(MainFrame instance) {
		MainFrame.instance = instance;
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
	
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public void setResourceBundle(ResourceBundle resourceBundle) {
		this.resourceBundle = resourceBundle;
	}
	
	public void changeLanguage() {	
		resourceBundle = ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
		setTitle(resourceBundle.getString("naslovApp"));
		
		dateFormat = DateFormat.getDateInstance();
		date = dateFormat.format(new Date());
		lbdatum.setText(date);
		msg.setText("  " + getResourceBundle().getString("welcome"));
		
		UIManager.put("OptionPane.yesButtonText", resourceBundle.getObject("yesOption"));
		UIManager.put("OptionPane.noButtonText", resourceBundle.getObject("noOption"));
		UIManager.put("OptionPane.okButtonText", resourceBundle.getObject("okOption"));
		UIManager.put("OptionPane.cancelButtonText", resourceBundle.getObject("cancelOption"));
		
		menuBar.ucitajJezik();
		menuTool.ucitajJezik();
		kp.ucitajJezik();
		pp.ucitajJezik();
		ppl.ucitajJezik();

	}
}
