package InstalacioniPaket;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;







import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import view.InstalacionaKlasa;
import view.KonfiguratorDialog;
import view.MainFrame;

public class InstalacioniProzor extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4115337179960144725L;
	
	private int screenHeight = MainFrame.getInstance().getSize().height*6/10;
	private int screenWidth = MainFrame.getInstance().getSize().width*7/10;
	private JPanel welcomePanel = new JPanel();
	private JPanel licencePanel = new JPanel();
	private JPanel sourcePanel = new JPanel();
	private JPanel endPanel = new JPanel();
	private JPanel browsePanel = new JPanel();
	private JPanel languagePanel = new JPanel();
	
	private JLabel lblJezik;
	private JRadioButton srp;
	private JRadioButton eng;
	private JButton btnOdustaniJezik;
	private JButton btnSledeceJezik;
	
	private JButton btnPrethodnoBrowse;
	
	private ResourceBundle rs;
	private InstalacionaKlasa podaci = null;
	
	public InstalacioniProzor() throws FileNotFoundException, IOException, ClassNotFoundException {	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setDefaultLookAndFeelDecorated(true);
		
		Locale.setDefault(new Locale("sr", "RS"));
		rs = ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
		
		//setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(screenWidth, screenHeight);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new GridBagLayout());
		
		InitLanguagePanel();
		add(languagePanel);
		languagePanel.setVisible(true);
	}
	
	public void InitPanel() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		
		/*InitBrowsePanel();
		add(browsePanel);
		browsePanel.setVisible(true);*/
		
		InitWelcomePanel();
		add(welcomePanel);
		welcomePanel.setVisible(false);
		
		InitLicencePanel();
		add(licencePanel);
		licencePanel.setVisible(false);
		
		InitSourcePanel();
		add(sourcePanel);
		sourcePanel.setVisible(false);
		
		InitEndPanel();
		add(endPanel);
		endPanel.setVisible(false);
	}
	
	public void InitLanguagePanel() {
		languagePanel.setLayout(new GridBagLayout());
		
		JPanel languageChooser = new JPanel();
		languageChooser.setLayout(new GridBagLayout());
		languageChooser.setPreferredSize(new Dimension(screenWidth*85/100, screenHeight*70/100));
		
		lblJezik = new JLabel("Izaberite jezik");
		srp = new JRadioButton("Srpski", true);
		srp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(srp.isSelected()) {
					Locale.setDefault(new Locale("sr", "RS"));
					rs = ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
					ucitajJezik();
				}
			}
		});
		eng = new JRadioButton("Engleski");
		eng.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(eng.isSelected()) {
					Locale.setDefault(new Locale("en", "US"));
					rs = ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
					ucitajJezik();
				}
			}
		});
		
		ButtonGroup group = new ButtonGroup();
		group.add(srp);
		group.add(eng);
		
		JPanel buttons = new JPanel();
		buttons.add(srp);
		buttons.add(eng);
		
		languageChooser.add(lblJezik, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 5, screenHeight*40/100, 10), 0, 0));
		languageChooser.add(buttons, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 0, screenHeight*40/100, 5), 0, 0));
		
		JPanel buttonsDonji = new JPanel();
		btnOdustaniJezik = new JButton("Odustani");
		btnOdustaniJezik.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InstalacioniProzor.this.dispose();
			}
		});

		btnSledeceJezik = new JButton("Sledece");
		btnSledeceJezik.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				languagePanel.setVisible(false);
				browsePanel = new JPanel();
				InitBrowsePanel();
				add(browsePanel);
				welcomePanel = new JPanel();
				licencePanel = new JPanel();
				sourcePanel = new JPanel();
				endPanel = new JPanel();
				displayPanels(true, false, false, false, false);
			}
		});
		
		buttonsDonji.add(btnOdustaniJezik);
		buttonsDonji.add(btnSledeceJezik);
		
		
		languagePanel.add(languageChooser, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		languagePanel.add(buttonsDonji, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
	}
	
	public void InitBrowsePanel() {
		browsePanel.setLayout(new GridBagLayout());
		
		JPanel sourceChooser = new JPanel();
		sourceChooser.setLayout(new GridBagLayout());
		sourceChooser.setPreferredSize(new Dimension(screenWidth*85/100, screenHeight*70/100));
		
		JLabel lblOdrediste = new JLabel("Setup [love]");
		JTextField txtOdrediste = new JTextField();
		txtOdrediste.setPreferredSize(new Dimension(screenWidth*40/100, screenHeight*5/100));
		
		JButton btnBrowse = new JButton("...");
		btnBrowse.setPreferredSize(new Dimension(screenWidth*5/100, screenHeight*5/100));
		btnBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Love files", "love");
		        chooser.setFileFilter(filter);
		        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		        chooser.setAcceptAllFileFilterUsed(false);
		        
		        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		        	txtOdrediste.setText("" + chooser.getSelectedFile());
		        }
			}
		});
		
		sourceChooser.add(lblOdrediste, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 5, screenHeight*40/100, 10), 0, 0));
		sourceChooser.add(txtOdrediste, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 0, screenHeight*40/100, 5), 0, 0));
		sourceChooser.add(btnBrowse, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 0, screenHeight*40/100, 5), 0, 0));
		
		JPanel buttons = new JPanel();
		JButton btnOdustani = new JButton(rs.getString("btnOdustani"));
		btnOdustani.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InstalacioniProzor.this.dispose();
			}
		});

		JButton btnSledece = new JButton(rs.getString("btnSledece"));
		btnSledece.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(txtOdrediste.getText().equals("")) {
					greska(true, false, false);
				} else {
					try {
						ucitavanjeDatoteke(txtOdrediste.getText());
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					welcomePanel = new JPanel();
					licencePanel = new JPanel();
					sourcePanel = new JPanel();
					endPanel = new JPanel();
					InitPanel();
					displayPanels(false, true, false, false, false);
				}
			}
		});
		
		btnPrethodnoBrowse = new JButton(rs.getString("btnPrethodno"));
		btnPrethodnoBrowse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				languagePanel.setVisible(true);
				displayPanels(false, false, false, false, false);
			}
		});
		
		buttons.add(btnOdustani);
		buttons.add(btnPrethodnoBrowse);
		buttons.add(btnSledece);
		
		
		
		browsePanel.add(sourceChooser, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		browsePanel.add(buttons, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
	}
	
	public void InitWelcomePanel() {
		welcomePanel.setLayout(new GridBagLayout());
		
		JTextArea welcomeText = new JTextArea(podaci.getWelcomeTxt());
		welcomeText.setWrapStyleWord(true);
		welcomeText.setLineWrap(true);
		welcomeText.setOpaque(false);
		welcomeText.setEditable(false);
		welcomeText.setFocusable(false);
		welcomeText.setBackground(UIManager.getColor("Label.background"));
		welcomeText.setFont(UIManager.getFont("Label.font").deriveFont(14f));
		welcomeText.setBorder(UIManager.getBorder("Label.border"));
		welcomeText.setPreferredSize(new Dimension(screenWidth*85/100, screenHeight*70/100));
		
		JPanel buttons = new JPanel();
		JButton btnOdustani = new JButton(rs.getString("btnOdustani"));
		btnOdustani.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InstalacioniProzor.this.dispose();
			}
		});
		
		JButton btnSledece = new JButton(rs.getString("btnSledece"));
		btnSledece.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				displayPanels(false, false, true, false, false);
			}
		});
		
		JButton btnPrethodno = new JButton(rs.getString("btnPrethodno"));
		btnPrethodno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayPanels(true, false, false, false, false);
			}
		});
		
		buttons.add(btnOdustani);
		buttons.add(btnPrethodno);
		buttons.add(btnSledece);
		
		welcomePanel.add(welcomeText, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(30, 30, 5, 5), 0, 0));
		welcomePanel.add(buttons, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
	}
	
	public void InitLicencePanel() {
		licencePanel.setLayout(new GridBagLayout());
		
		JPanel licenceCheck = new JPanel();
		licenceCheck.setLayout(new GridLayout(2, 1));
		licenceCheck.setPreferredSize(new Dimension(screenWidth*85/100, screenHeight*70/100));
		
		JTextArea licenceText = new JTextArea(podaci.getLicenceTxt());
		JScrollPane spLicence = new JScrollPane(licenceText);
		licenceText.setWrapStyleWord(true);
		licenceText.setLineWrap(true);
		licenceText.setEditable(false);
		licenceText.setFocusable(true);
		licenceText.setPreferredSize(new Dimension(screenWidth*85/100, screenHeight*90/100));
		
		JButton btnSledece = new JButton(rs.getString("btnSledece"));
		JCheckBox checkBox = new JCheckBox(rs.getString("saglasnostLicence"));
		checkBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				btnSledece.setEnabled(!btnSledece.isEnabled());
			}
		});
		licenceCheck.add(spLicence);
		licenceCheck.add(checkBox);
		
		JPanel buttons = new JPanel();
		JButton btnOdustani = new JButton(rs.getString("btnOdustani"));
		btnOdustani.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InstalacioniProzor.this.dispose();
			}
		});
		
		JButton btnPrethodno = new JButton(rs.getString("btnPrethodno"));
		btnPrethodno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayPanels(false, true, false, false, false);
			}
		});

		btnSledece.setEnabled(false);
		btnSledece.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(checkBox.isSelected()) {
					displayPanels(false, false, false, true, false);
				} else {
					greska(false, true, false);
				}
			}
		});
		
		buttons.add(btnOdustani);
		buttons.add(btnPrethodno);
		buttons.add(btnSledece);
		
		/*licencePanel.add(licenceText, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(30, 30, 5, 5), 0, 0));
		licencePanel.add(checkBox, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 30, 5, 5), 0, 0));
		licencePanel.add(buttons, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));*/
		
		licencePanel.add(licenceCheck, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		licencePanel.add(buttons, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		
	}
	
	public void InitSourcePanel() {
		sourcePanel.setLayout(new GridBagLayout());
		
		JPanel sourceChooser = new JPanel();
		sourceChooser.setLayout(new GridBagLayout());
		sourceChooser.setPreferredSize(new Dimension(screenWidth*85/100, screenHeight*70/100));
		
		JLabel lblOdrediste = new JLabel(rs.getString("source"));
		JTextField txtOdrediste = new JTextField();
		txtOdrediste.setPreferredSize(new Dimension(screenWidth*60/100, screenHeight*5/100));
		
		JButton btnBrowse = new JButton("...");
		btnBrowse.setPreferredSize(new Dimension(screenWidth*5/100, screenHeight*5/100));
		btnBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
		        chooser.setDialogTitle(rs.getString("chooseFolder"));
		        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		        chooser.setAcceptAllFileFilterUsed(false);
		        
		        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		        	txtOdrediste.setText("" + chooser.getSelectedFile());
		        }
			}
		});
		
		sourceChooser.add(lblOdrediste, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(0, 5, screenHeight*40/100, 10), 0, 0));
		sourceChooser.add(txtOdrediste, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 0, screenHeight*40/100, 5), 0, 0));
		sourceChooser.add(btnBrowse, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 0, screenHeight*40/100, 5), 0, 0));
		
		/*sourceChooser.add(lblOdrediste, BorderLayout.WEST);
		sourceChooser.add(txtOdrediste, BorderLayout.WEST);
		sourceChooser.add(btnBrowse, BorderLayout.WEST);*/
		
		JPanel buttons = new JPanel();
		JButton btnOdustani = new JButton(rs.getString("btnOdustani"));
		btnOdustani.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InstalacioniProzor.this.dispose();
			}
		});
		
		JButton btnPrethodno = new JButton(rs.getString("btnPrethodno"));
		btnPrethodno.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayPanels(false, false, true, false, false);
			}
		});

		JButton btnSledece = new JButton(rs.getString("btnSledece"));
		btnSledece.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(txtOdrediste.getText().equals("")) {
					greska(false, false, true);
				} else {
					String putanja = "";
					try {
						putanja = zipToFile(txtOdrediste.getText());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						unzipovanje(new File(txtOdrediste.getText()), putanja);
						displayPanels(false, false, false, false, true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					File brisanje = new File(putanja);
					brisanje.delete();
				}
			}
		});
		
		buttons.add(btnOdustani);
		buttons.add(btnPrethodno);
		buttons.add(btnSledece);
		
		
		
		sourcePanel.add(sourceChooser, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		sourcePanel.add(buttons, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
		
	}
	
	public void InitEndPanel() {
		endPanel.setLayout(new GridBagLayout());
		
		JTextArea endText = new JTextArea(rs.getString("uspeh"));
		endText.setWrapStyleWord(true);
		endText.setLineWrap(true);
		endText.setOpaque(false);
		endText.setEditable(false);
		endText.setFocusable(false);
		endText.setBackground(UIManager.getColor("Label.background"));
		endText.setFont(UIManager.getFont("Label.font").deriveFont(14f));
		endText.setBorder(UIManager.getBorder("Label.border"));
		endText.setPreferredSize(new Dimension(screenWidth*85/100, screenHeight*70/100));
		
		JButton btnKraj = new JButton(rs.getString("end"));
		btnKraj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				InstalacioniProzor.this.dispose();
			}
		});
		
		endPanel.add(endText, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST,
				GridBagConstraints.NONE, new Insets(30, 30, 5, 5), 0, 0));
		endPanel.add(btnKraj, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
	}
	
	public void displayPanels(boolean browse, boolean welcome, boolean licence, boolean source, boolean end) {
		browsePanel.setVisible(browse);
		welcomePanel.setVisible(welcome);
		licencePanel.setVisible(licence);
		sourcePanel.setVisible(source);
		endPanel.setVisible(end);
	}
	
	public void greska(boolean browse, boolean licence, boolean source) {
		Window parent = SwingUtilities.getWindowAncestor(this);
		
		if (licence) {
			JOptionPane.showMessageDialog(parent, rs.getString("WarningLicence"));
		} else if (source) {
			JOptionPane.showMessageDialog(parent, rs.getString("WarningSource"));
		} else if (browse) {
			JOptionPane.showMessageDialog(parent, rs.getString("WarningSetup"));
		}
	}
	
	public String zipToFile(String putanja) throws IOException {
		String zipovano = putanja + File.separator + "zipovano.zip";
		FileUtils.writeByteArrayToFile(new File(zipovano), podaci.getZipByte());
		return zipovano;
	}
	
	public void unzipovanje(File outputDir, String putanja) throws IOException {
		ZipFile zipFile = new ZipFile(putanja);
		try {
			Enumeration<? extends ZipEntry> entries = zipFile.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				File entryDestination = new File(outputDir, entry.getName());
				if (entry.isDirectory()) {
					entryDestination.mkdirs();
				} else {
					entryDestination.getParentFile().mkdirs();
					InputStream in = zipFile.getInputStream(entry);
					OutputStream out = new FileOutputStream(entryDestination);
					IOUtils.copy(in, out);
					IOUtils.closeQuietly(in);
					out.close();
				}
			}
		} finally {
			zipFile.close();
		}
	}
	
	public void ucitavanjeDatoteke(String putanja) throws FileNotFoundException, IOException, ClassNotFoundException {
		File setupFile = new File(putanja);
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(setupFile)));
		try {
			podaci = (InstalacionaKlasa) ois.readObject();
			//System.out.println(podaci);
		} finally {
			ois.close();
		}
		
		setTitle(podaci.getImeProizvoda() + " " + podaci.getVerzijaTxt());
	}
	
	public void ucitajJezik() {
		lblJezik.setText(rs.getString("lblJezik"));
		srp.setText(rs.getString("srpski"));
		eng.setText(rs.getString("engleski"));
		btnOdustaniJezik.setText(rs.getString("btnOdustani"));
		btnSledeceJezik.setText(rs.getString("btnSledece"));
	}
	
}
