package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Parametar implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2069057437403266888L;
	
	private String ime;
	private String vrednost;
	private Object parent;
	private boolean checked;
	private ArrayList<Parametar> parametar;
	
	public Parametar(String ime, String vrednost, Object parent, Boolean checked) {
		this.ime = ime;
		this.vrednost = vrednost;
		this.parent = parent;
		this.parametar = new ArrayList<Parametar>();
		this.checked = checked;
	}
	
	public Parametar() {
		this.ime = null;
		this.vrednost = null;
		this.parametar = new ArrayList<Parametar>();
		this.checked = false;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}
	
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getVrednost() {
		return vrednost;
	}

	public void setVrednost(String vrednost) {
		this.vrednost = vrednost;
	}

	public Object getParent() {
		return parent;
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}

	public ArrayList<Parametar> getParametar() {
		return parametar;
	}

	public void setParametar(ArrayList<Parametar> parametar) {
		this.parametar = parametar;
	}
	
	public boolean isChecked() {
		return checked;
	}

	@Override
	public String toString() {
		return this.ime;
	}
	
	public void addParameter(Parametar p) {
		parametar.add(p);
	}
	
	public void removeChild(Parametar child) {
		// TODO: eventualne provere da li je brisanje moguce
		this.parametar.remove(child);
		child.setParent(null);
	}
	
	public void removeFromParent() {
		// pozovemo parenta da nas ukloni
		if (parent instanceof Parametar) {
			((Parametar) this.parent).removeChild(this);
		} else {
			((SoftverskiProizvod) this.parent).removeChild(this);
		}
	}
}
