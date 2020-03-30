package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import actions.AddNodeAction;
import kontroleri.ParametarKontroler;
import kontroleri.SoftverskaKompanijaKontroler;
import kontroleri.SoftverskiProizvodKontroler;
import model.Parametar;
import model.SoftverskiProizvod;
import tree.ParametarTree;
import tree.SoftverskaKompanijaTree;
import view.MainFrame;

public class ParametarPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5246226300805209883L;
	
	private JPanel panUpiti;
	private JLabel lblNaziv;
	private JLabel lblVrednost;
	private JLabel naslov;
	private JTextField txtNaziv;
	private JTextField txtVrednost;
	private JButton btnDodaj;
	private JButton btnIzmeni;
	private JButton btnOdustani;
	private JPanel btn;
	
	private Parametar parametar;
	private ParametarKontroler parametarKontroler;
	
	public ParametarPanel() {
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
		
		lblNaziv = new JLabel("Naziv parametra: ");
		lblNaziv.setPreferredSize(dim);
		this.txtNaziv = new JTextField();
		txtNaziv.setPreferredSize(dim);
		
		lblVrednost = new JLabel("Vrednost: ");
		lblVrednost.setPreferredSize(dim);
		this.txtVrednost = new JTextField();
		txtVrednost.setPreferredSize(dim);
		
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
		
		panUpiti.add(lblNaziv, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(txtNaziv, new GridBagConstraints(1, 0, 1, 1, 100, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(lblVrednost, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(txtVrednost, new GridBagConstraints(1, 1, 1, 1, 100, 0, GridBagConstraints.WEST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		panUpiti.add(btn, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.EAST,
				GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		naslov = new JLabel("Parametar");
		naslov.setBackground(Color.WHITE);
		naslov.setOpaque(true);
		naslov.setFont(new Font("Ariel", Font.BOLD, 15));
		
		this.add(naslov, BorderLayout.NORTH);
		this.add(panUpiti, BorderLayout.CENTER);
		
		ucitajJezik();
		validate();
		
	}

	public Parametar getParametar() {
		return parametar;
	}

	public void setParametar(Parametar parametar) {
		this.parametar = parametar;
	}
	
	public boolean popuni() {
		parametar.setIme(txtNaziv.getText());
		parametar.setVrednost(txtVrednost.getText());
		
		if (parametar.getIme().isEmpty() || parametar.getVrednost().isEmpty()) {
			Window parent = SwingUtilities.getWindowAncestor(this);
			JOptionPane.showMessageDialog(parent, MainFrame.getInstance().getResourceBundle().getString("WarningMsgPP"));
			return false;
		}
		
		return true;
	}
	
	public void izmeni() {
		DefaultMutableTreeNode parentNodeView = (DefaultMutableTreeNode) MainFrame
				.getInstance().getTree().getLastSelectedPathComponent();
		ParametarTree selektovaniCvor = (ParametarTree) parentNodeView;
		this.parametar = selektovaniCvor.getP();
		
		if (parametarKontroler == null) {
			parametarKontroler = new ParametarKontroler(parametar, this);
		}
		
		String naziv = txtNaziv.getText();
		String vrednost = txtVrednost.getText();
		String msg = parametarKontroler.updateParametar(naziv, vrednost);
		MainFrame.getInstance().updateTree(parentNodeView);
		Window parent = SwingUtilities.getWindowAncestor(this);
		JOptionPane.showMessageDialog(parent, msg);
		this.parametarKontroler = null;
	}
	
	public void refreshView(DefaultMutableTreeNode parentNodeView) {
		ParametarTree cvor = (ParametarTree) parentNodeView;
		parametar = cvor.getP();
		txtNaziv.setText(cvor.getP().getIme());
		txtVrednost.setText(cvor.getP().getVrednost());
	}
	
	public String getNaziv() {
		return txtNaziv.getText();
	}
	
	public void zatvori() {
		txtNaziv.setText(null);
		txtVrednost.setText(null);
		this.setVisible(false);
	}
	
	public JButton getBtnDodaj() {
		return btnDodaj;
	}

	public JButton getBtnIzmeni() {
		return btnIzmeni;
	}
	
	public void ucitajJezik() {
		lblNaziv.setText(MainFrame.getInstance().getResourceBundle().getString("lblnazivPP"));
		lblVrednost.setText(MainFrame.getInstance().getResourceBundle().getString("lblVrednost"));
		naslov.setText(MainFrame.getInstance().getResourceBundle().getString("naslovPP"));
		btnDodaj.setText(MainFrame.getInstance().getResourceBundle().getString("btnDodaj"));
		btnIzmeni.setText(MainFrame.getInstance().getResourceBundle().getString("btnIzmeni"));
		btnOdustani.setText(MainFrame.getInstance().getResourceBundle().getString("btnOdustani"));
	}
}
