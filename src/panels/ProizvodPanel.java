package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import actions.AddNodeAction;
import kontroleri.SoftverskaKompanijaKontroler;
import kontroleri.SoftverskiProizvodKontroler;
import listeners.MyActionListenerKonfigurator;
import model.SoftverskiProizvod;
import tree.SoftverskaKompanijaTree;
import tree.SoftverskiProizvodTree;
import view.MainFrame;

public class ProizvodPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5067395216056305641L;
	
	private JPanel panUpiti;
	private JLabel lblNaziv;
	private JLabel lblVerzija;
	private JLabel lblLogo;
	private JLabel lblDatumNastanka;
	private JTextField txtNaziv;
	private JTextField txtVerzija;
	private JTextField txtDatumNastanka;
	private JLabel naslov;
	private JButton btnDodaj;
	private JButton btnIzmeni;
	private JButton btnOdustani;
	private JButton btnUpload;
	private JButton btnPonisti;
	private JButton btnKonfigurisi;
	private JPanel btn;
	
	private SoftverskiProizvod proizvod;
	private SoftverskiProizvodKontroler proizvodKontroler;
	
	public ProizvodPanel() {
		this.setVisible(false);
		
		initPanel();
	}
	
	public void initPanel() {
		this.setLayout(new BorderLayout());
		Dimension dim = new Dimension(120, 20);
		
		this.panUpiti = new JPanel();
		panUpiti.setLayout(new GridBagLayout());
		panUpiti.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panUpiti.setSize(getPreferredSize());
		panUpiti.setBackground(Color.WHITE);
		
		lblNaziv = new JLabel("Naziv proizvoda: ");
		lblNaziv.setPreferredSize(dim);
		this.txtNaziv = new JTextField();
		txtNaziv.setPreferredSize(dim);
		
		lblVerzija = new JLabel("Verzija proizvoda: ");
		lblVerzija.setPreferredSize(dim);
		this.txtVerzija = new JTextField();
		txtVerzija.setPreferredSize(dim);
		
		lblDatumNastanka = new JLabel("Datum dodavanja: ");
		lblDatumNastanka.setPreferredSize(dim);
		this.txtDatumNastanka = new JTextField();
		txtDatumNastanka.setPreferredSize(dim);
		
		lblLogo = new JLabel();
		lblLogo.setBorder(BorderFactory.createEtchedBorder());
		lblLogo.setPreferredSize(new Dimension(126,85));
		
		btnUpload = new JButton("Postavi logo");
		btnUpload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uploadLogo();
			}
		});
		
		btnPonisti = new JButton("Ponisti sliku");
		btnPonisti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteLogo();
			}
		});
		
		btn = new JPanel();
		btnDodaj = new JButton(new AddNodeAction("Dodaj"));
		btnDodaj.setVisible(false);
		btnOdustani = new JButton("Odustani");
		btnOdustani.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				zatvori();
				MainFrame.getInstance().getTree().clearSelection();
			}
		});
		btnIzmeni = new JButton("Izmeni");
		btnIzmeni.setVisible(false);
		btnIzmeni.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				izmeni();
			}
		});
		
		btn.add(btnDodaj);
		btn.add(btnIzmeni);
		btn.add(btnOdustani);
		btn.setBackground(Color.WHITE);
		
		btnKonfigurisi = new JButton();
		btnKonfigurisi.setVisible(false);
		btnKonfigurisi.setToolTipText("Otvori konfigurator");
		btnKonfigurisi.setIcon(new ImageIcon("images/Konfigurisi.png"));
		btnKonfigurisi.addActionListener(new MyActionListenerKonfigurator());
		
		panUpiti.add(lblNaziv, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(txtNaziv, new GridBagConstraints(1, 0, 1, 1, 100, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(lblVerzija, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(txtVerzija, new GridBagConstraints(1, 1, 1, 1, 100, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(btn, new GridBagConstraints(1, 6, 1, 1, 0, 0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(btnKonfigurisi, new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(lblLogo, new GridBagConstraints(0, 2, 2, 1, 100, 100, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(15, 5, 5, 5), 126, 85));
		panUpiti.add(btnUpload, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(btnPonisti, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(lblDatumNastanka, new GridBagConstraints(0, 4, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(txtDatumNastanka, new GridBagConstraints(1, 4, 1, 1, 100, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		naslov = new JLabel("Proizvod");
		naslov.setBackground(Color.WHITE);
		naslov.setOpaque(true);
		naslov.setFont(new Font("Ariel", Font.BOLD, 15));
		
		this.add(naslov, BorderLayout.NORTH);
		
		add(panUpiti, BorderLayout.CENTER);
		
		ucitajJezik();
		validate();
	}

	public SoftverskiProizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(SoftverskiProizvod proizvod) {
		this.proizvod = proizvod;
	}
	
	public void uploadLogo() {
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			try {
				File f = fc.getSelectedFile();
				Image bi = ImageIO.read(f);
				lblLogo.setIcon(new ImageIcon(bi.getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH)));
			} catch (Exception e) {
		    } 
		}
	}
	
	public void deleteLogo() {
		lblLogo.setIcon(null);
	}
	
	public boolean popuni() {
		proizvod.setIme(txtNaziv.getText());
		proizvod.setVerzija(txtVerzija.getText());
		if (lblLogo.getIcon() == null) {
			Window parent = SwingUtilities.getWindowAncestor(this);
			JOptionPane.showMessageDialog(parent, MainFrame.getInstance().getResourceBundle().getString("WarningMsgP"));
			return false;
		}
		
		Image logo = ((ImageIcon) lblLogo.getIcon()).getImage();
		proizvod.setLogo(logo);
		proizvod.setDatumNastanka(txtDatumNastanka.getText());
		
		if (proizvod.getIme().isEmpty() || proizvod.getVerzija().isEmpty() || proizvod.getLogo() == null) {
			Window parent = SwingUtilities.getWindowAncestor(this);
			JOptionPane.showMessageDialog(parent, MainFrame.getInstance().getResourceBundle().getString("WarningMsgP"));
			return false;
		}
		
		return true;
	}
	
	public void izmeni() {
		DefaultMutableTreeNode parentNodeView = (DefaultMutableTreeNode) MainFrame
				.getInstance().getTree().getLastSelectedPathComponent();
		SoftverskiProizvodTree selektovaniCvor = (SoftverskiProizvodTree) parentNodeView;
		this.proizvod = selektovaniCvor.getP();
		
		if (proizvodKontroler == null) {
			proizvodKontroler = new SoftverskiProizvodKontroler(proizvod, this);
		}
		
		String naziv = txtNaziv.getText();
		String verzija = txtVerzija.getText();
		Image logo = ((ImageIcon) lblLogo.getIcon()).getImage();
		String datumNastanka = txtDatumNastanka.getText();
		String msg = proizvodKontroler.updateProizvod(naziv, verzija, logo, datumNastanka);
		MainFrame.getInstance().updateTree(parentNodeView);
		Window parent = SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(parent, msg);
		this.proizvodKontroler = null;
	}
	
	public void refreshView(DefaultMutableTreeNode parentNodeView) {
		SoftverskiProizvodTree cvor = (SoftverskiProizvodTree) parentNodeView;
		proizvod = cvor.getP();
		txtNaziv.setText(cvor.getP().getIme());
		txtVerzija.setText(cvor.getP().getVerzija());
		Image slika = cvor.getP().getLogo();
		if (!(slika == null)) {
			Icon logo = new ImageIcon(slika);
			lblLogo.setIcon(logo);
		} else {
			Icon logo = new ImageIcon(stringToImage(cvor.getP().getImgString()));
			lblLogo.setIcon(logo);
		}
		txtDatumNastanka.setText(cvor.getP().getDatumNastanka());
	}
	
	public String getNaziv() {
		return txtNaziv.getText();
	}
	
	public void zatvori() {
		txtNaziv.setText(null);
		txtVerzija.setText(null);
		deleteLogo();
		txtDatumNastanka.setText(null);
		this.setVisible(false);
	}

	public JButton getBtnDodaj() {
		return btnDodaj;
	}

	public JButton getBtnIzmeni() {
		return btnIzmeni;
	}
	
	public JButton getBtnPonisti() {
		return btnPonisti;
	}
	
	public JButton getBtnKonfigurisi() {
		return btnKonfigurisi;
	}
	
	public Image stringToImage(String encoded) {
		byte []bytes = Base64.getDecoder().decode(encoded); 
		BufferedImage img = null;
		try {
			img = ImageIO.read(new ByteArrayInputStream(bytes));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Image image = (Image) img;
		return image;
	}
	
	public void ucitajJezik() {
		lblNaziv.setText(MainFrame.getInstance().getResourceBundle().getString("lblnazivP"));
		lblVerzija.setText(MainFrame.getInstance().getResourceBundle().getString("lblVerzija"));
		lblDatumNastanka.setText(MainFrame.getInstance().getResourceBundle().getString("lblDatumNastanka"));
		naslov.setText(MainFrame.getInstance().getResourceBundle().getString("naslovP"));
		btnDodaj.setText(MainFrame.getInstance().getResourceBundle().getString("btnDodaj"));
		btnIzmeni.setText(MainFrame.getInstance().getResourceBundle().getString("btnIzmeni"));
		btnOdustani.setText(MainFrame.getInstance().getResourceBundle().getString("btnOdustani"));
		btnUpload.setText(MainFrame.getInstance().getResourceBundle().getString("btnUpload"));
		btnPonisti.setText(MainFrame.getInstance().getResourceBundle().getString("btnPonisti"));
		btnKonfigurisi.setToolTipText(MainFrame.getInstance().getResourceBundle().getString("btnKonfigurisi"));
	}
	
}
