package kontroleri;

import model.Parametar;
import panels.ParametarPanel;
import view.MainFrame;

public class ParametarKontroler {
	private Parametar parametar;
	private ParametarPanel parametarPanel;
	
	public ParametarKontroler(Parametar parametar, ParametarPanel parametarPanel) {
		setParametar(parametar);
		setParametarPanel(parametarPanel);
	}
	
	public String updateParametar(String naziv, String vrednost) {
		if (naziv == null) {
			return MainFrame.getInstance().getResourceBundle().getString("msPar");
		}
		
		naziv = naziv.trim();
		if (naziv.isEmpty()) {
			return MainFrame.getInstance().getResourceBundle().getString("msgPar");
		}
		
		parametar.setIme(naziv);
		parametar.setVrednost(vrednost);
		
		return MainFrame.getInstance().getResourceBundle().getString("msgSuc");
	}

	public Parametar getParametar() {
		return parametar;
	}

	public void setParametar(Parametar parametar) {
		if (parametar == null) {
			throw new NullPointerException();
		}
		
		this.parametar = parametar;
	}

	public ParametarPanel getParametarPanel() {
		return parametarPanel;
	}

	public void setParametarPanel(ParametarPanel parametarPanel) {
		if (parametarPanel == null) {
			throw new NullPointerException();
		}
		
		this.parametarPanel = parametarPanel;
	}
	
	
}
