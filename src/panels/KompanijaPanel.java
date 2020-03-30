package panels;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import actions.AddNodeAction;
import kontroleri.SoftverskaKompanijaKontroler;
import model.SoftverskaKompanija;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import tree.SoftverskaKompanijaTree;
import tree.WorkspaceTree;
import view.MainFrame;

public class KompanijaPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7837522060589852609L;
	
	private JPanel panUpiti;
	private JLabel lblNaziv;
	private JLabel lblSediste;
	private JLabel lblBrZaposlenih;
	private JLabel lblDatumNastanka;
	private JLabel naslov;
	private JTextField txtNaziv;
	private JTextField txtSediste;
	private JTextField txtBrZaposlenih;
	private JTextField txtDatumNastanka;
	private JButton btnDodaj;
	private JButton btnIzmeni;
	private JButton btnOdustani;
	private JPanel btn;
	
	private SoftverskaKompanija kompanija;
	private SoftverskaKompanijaKontroler kompanijaKontroler;
	
	public KompanijaPanel() {
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

		lblNaziv = new JLabel("Naziv kompanije: ");
		lblNaziv.setPreferredSize(dim);
		this.txtNaziv = new JTextField();
		txtNaziv.setPreferredSize(dim);
		
		lblSediste = new JLabel("Sediste kompanije: ");
		lblSediste.setPreferredSize(dim);
		this.txtSediste = new JTextField();
		txtSediste.setPreferredSize(dim);
		
		lblBrZaposlenih = new JLabel("Broj zaposlenih: ");
		lblBrZaposlenih.setPreferredSize(dim);
		this.txtBrZaposlenih = new JTextField();
		txtBrZaposlenih.setPreferredSize(dim);
		
		lblDatumNastanka = new JLabel("Datum nastanka: ");
		lblDatumNastanka.setPreferredSize(dim);
		this.txtDatumNastanka = new JTextField();
		txtDatumNastanka.setPreferredSize(dim);
		
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
		
		/*panUpiti.add(lblNaziv, BorderLayout.WEST);
		panUpiti.add(txtNaziv, BorderLayout.CENTER);
		panUpiti.add(lblSediste, BorderLayout.WEST);
		panUpiti.add(txtSediste, BorderLayout.CENTER);
		panUpiti.add(btn, BorderLayout.SOUTH);*/
		
		panUpiti.add(lblNaziv, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(txtNaziv, new GridBagConstraints(1, 0, 1, 1, 100, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(lblSediste, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(txtSediste, new GridBagConstraints(1, 1, 1, 1, 100, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(lblBrZaposlenih, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(txtBrZaposlenih, new GridBagConstraints(1, 2, 1, 1, 100, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(lblDatumNastanka, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(txtDatumNastanka, new GridBagConstraints(1, 3, 1, 1, 100, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(btn, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		naslov = new JLabel("Kompanija");
		naslov.setBackground(Color.WHITE);
		naslov.setOpaque(true);
		naslov.setFont(new Font("Ariel", Font.BOLD, 15));
		
		this.add(naslov, BorderLayout.NORTH);
		this.add(panUpiti, BorderLayout.CENTER);
		
		ucitajJezik();
		validate();
	}

	public SoftverskaKompanija getKompanija() {
		return kompanija;
	}

	public void setKompanija(SoftverskaKompanija kompanija) {
		this.kompanija = kompanija;
		kompanijaKontroler = null;
	}
	
	public boolean popuni() {
		kompanija.setIme(txtNaziv.getText());
		kompanija.setSediste(txtSediste.getText());
		kompanija.setBrZaposlenih(txtBrZaposlenih.getText());
		kompanija.setDatumNastanka(txtDatumNastanka.getText());
		
		if (kompanija.getIme().isEmpty()) {
			Window parent = SwingUtilities.getWindowAncestor(this);
			JOptionPane.showMessageDialog(parent, MainFrame.getInstance().getResourceBundle().getString("WarningMsgC"));
			return false;
		}
		
		return true;
	}
	
	public void izmeni() {
		DefaultMutableTreeNode parentNodeView = (DefaultMutableTreeNode) MainFrame
				.getInstance().getTree().getLastSelectedPathComponent();
		SoftverskaKompanijaTree selektovaniCvor = (SoftverskaKompanijaTree) parentNodeView;
		this.kompanija = selektovaniCvor.getK();
		
		if (kompanijaKontroler == null) {
			kompanijaKontroler = new SoftverskaKompanijaKontroler(kompanija, this);
		}
		
		String naziv = txtNaziv.getText();
		String sediste = txtSediste.getText();
		String brZaposlenih = txtBrZaposlenih.getText();
		String datumNastanka = txtDatumNastanka.getText();
		String msg = kompanijaKontroler.updateKompanija(naziv, sediste, brZaposlenih, datumNastanka);
		MainFrame.getInstance().updateTree(parentNodeView);
		Window parent = SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(parent, msg);
		this.kompanijaKontroler = null;
	}
	
	public void refreshView(DefaultMutableTreeNode parentNodeView) {
		SoftverskaKompanijaTree cvor = (SoftverskaKompanijaTree) parentNodeView;
		kompanija = cvor.getK();
		txtNaziv.setText(cvor.getK().getIme());
		txtSediste.setText(cvor.getK().getSediste());
		txtBrZaposlenih.setText(cvor.getK().getBrZaposlenih());
		txtDatumNastanka.setText(cvor.getK().getDatumNastanka());
	}
	
	public String getNaziv() {
		return txtNaziv.getText();
	}
	
	public JTextField getNazivKom() {
		return txtNaziv;
	}
	
	public JTextField getSediste() {
		return txtSediste;
	}
	
	public void zatvori() {
		txtNaziv.setText(null);
		txtSediste.setText(null);
		txtBrZaposlenih.setText(null);
		txtDatumNastanka.setText(null);
		this.setVisible(false);
	}

	public JButton getBtnDodaj() {
		return btnDodaj;
	}

	public JButton getBtnIzmeni() {
		return btnIzmeni;
	}
	
	public void ucitajJezik() {
		lblNaziv.setText(MainFrame.getInstance().getResourceBundle().getString("lblNaziv"));
		lblSediste.setText(MainFrame.getInstance().getResourceBundle().getString("lblSediste"));
		lblBrZaposlenih.setText(MainFrame.getInstance().getResourceBundle().getString("lblBrZaposlenih"));
		lblDatumNastanka.setText(MainFrame.getInstance().getResourceBundle().getString("lblDatumNastanka"));
		naslov.setText(MainFrame.getInstance().getResourceBundle().getString("naslovK"));
		btnDodaj.setText(MainFrame.getInstance().getResourceBundle().getString("btnDodaj"));
		btnIzmeni.setText(MainFrame.getInstance().getResourceBundle().getString("btnIzmeni"));
		btnOdustani.setText(MainFrame.getInstance().getResourceBundle().getString("btnOdustani"));
	}
	
}
