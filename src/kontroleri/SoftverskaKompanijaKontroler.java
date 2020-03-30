package kontroleri;

import model.SoftverskaKompanija;
import panels.KompanijaPanel;
import view.MainFrame;

public class SoftverskaKompanijaKontroler {
	private SoftverskaKompanija kompanija;
	private KompanijaPanel kompanijaPanel;
	
	public SoftverskaKompanijaKontroler(SoftverskaKompanija kompanija, KompanijaPanel kompanijaPanel) {
		setKompanija(kompanija);
		setKompanijaPanel(kompanijaPanel);
	}
	
	public String updateKompanija(String naziv, String sediste, String brZaposlenih, String datumNastanka) {
		if (naziv == null) {
			return MainFrame.getInstance().getResourceBundle().getString("msgComp");
		}
		
		naziv = naziv.trim();
		if (naziv.isEmpty()) {
			return MainFrame.getInstance().getResourceBundle().getString("msgComp");
		}
		
		kompanija.setIme(naziv);
		kompanija.setSediste(sediste);
		kompanija.setBrZaposlenih(brZaposlenih);
		kompanija.setDatumNastanka(datumNastanka);
		
		return MainFrame.getInstance().getResourceBundle().getString("msgSuc");
	}

	public SoftverskaKompanija getKompanija() {
		return kompanija;
	}

	public KompanijaPanel getKompanijaPanel() {
		return kompanijaPanel;
	}

	public void setKompanija(SoftverskaKompanija kompanija) {
		if (kompanija == null) {
			throw new NullPointerException();
		}
		this.kompanija = kompanija;
	}

	public void setKompanijaPanel(KompanijaPanel kompanijaPanel) {
		if (kompanijaPanel == null) {
			throw new NullPointerException();
		}
		this.kompanijaPanel = kompanijaPanel;
	}
}
