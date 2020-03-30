package kontroleri;

import java.awt.Image;
import java.io.IOException;

import model.SoftverskaKompanija;
import model.SoftverskiProizvod;
import panels.ProizvodPanel;
import view.MainFrame;

public class SoftverskiProizvodKontroler {
	private SoftverskiProizvod proizvod;
	private ProizvodPanel proizvodPanel;
	
	public SoftverskiProizvodKontroler(SoftverskiProizvod proizvod, ProizvodPanel proizvodPanel) {
		setProizvod(proizvod);
		setProizvodPanel(proizvodPanel);
	}
	
	public String updateProizvod(String naziv, String verzija, Image logo, String datumNastanka) {
		if (naziv == null) {
			return MainFrame.getInstance().getResourceBundle().getString("msgPro");
		}
		
		naziv = naziv.trim();
		if (naziv.isEmpty()) {
			return MainFrame.getInstance().getResourceBundle().getString("msgPro");
		}
		
		if (verzija == null) {
			return MainFrame.getInstance().getResourceBundle().getString("msgProV");
		}
		
		verzija = verzija.trim();
		if (verzija.isEmpty()) {
			return MainFrame.getInstance().getResourceBundle().getString("msgProV");
		}
		
		if (logo == null) {
			return MainFrame.getInstance().getResourceBundle().getString("msgProL");
		}

		if (logo == null) {
			return MainFrame.getInstance().getResourceBundle().getString("msgProL");
		}
		
		proizvod.setIme(naziv);
		proizvod.setVerzija(verzija);
		proizvod.setLogo(logo);
		proizvod.setDatumNastanka(datumNastanka);
		
		return MainFrame.getInstance().getResourceBundle().getString("msgSuc");
	}

	public SoftverskiProizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(SoftverskiProizvod proizvod) {
		if (proizvod == null) {
			throw new NullPointerException();
		}
		this.proizvod = proizvod;
	}

	public ProizvodPanel getProizvodPanel() {
		return proizvodPanel;
	}

	public void setProizvodPanel(ProizvodPanel proizvodPanel) {
		if (proizvodPanel == null) {
			throw new NullPointerException();
		}
		
		this.proizvodPanel = proizvodPanel;
	}
	
	
}
