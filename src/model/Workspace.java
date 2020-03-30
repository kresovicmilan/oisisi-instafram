package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Workspace implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5357277164556329643L;
	
	private String ime;
	private ArrayList<SoftverskaKompanija> kompanija;
	
	public Workspace(String ime) {
		this.ime = ime;
		this.kompanija = new ArrayList<SoftverskaKompanija>();
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public ArrayList<SoftverskaKompanija> getKompanija() {
		return kompanija;
	}

	public void setKompanija(ArrayList<SoftverskaKompanija> kompanija) {
		this.kompanija = kompanija;
	}

	@Override
	public String toString() {
		return this.ime;
	}
	
	public void addCompany(SoftverskaKompanija k) {
		kompanija.add(k);
	}
	
	public void removeChild(SoftverskaKompanija child) {
		// TODO: eventualne provere da li je brisanje moguce
		this.kompanija.remove(child);
		child.setParent(null);
	}
	
	
}
