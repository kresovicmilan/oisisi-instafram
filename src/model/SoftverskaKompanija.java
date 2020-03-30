package model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.soap.Node;

public class SoftverskaKompanija implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 196375240740458276L;
	
	private String ime;
	private String sediste;
	private String brZaposlenih;
	private String datumNastanka;
	private ArrayList<SoftverskiProizvod> proizvod;
	private Workspace parent;

	public SoftverskaKompanija(String ime, String sediste, String brZaposlenih, String datumNastanka) {
		super();
		this.ime = ime;
		this.sediste = sediste;
		this.brZaposlenih = brZaposlenih;
		this.datumNastanka = datumNastanka;
		this.proizvod = new ArrayList<SoftverskiProizvod>();
		this.parent = null;
	}
	
	public SoftverskaKompanija() {
		this.ime = null;
		this.sediste = null;
		this.brZaposlenih = null;
		this.datumNastanka = null;
		this.proizvod = new ArrayList<SoftverskiProizvod>();
		this.parent = null;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getSediste() {
		return sediste;
	}

	public void setSediste(String sediste) {
		this.sediste = sediste;
	}

	public ArrayList<SoftverskiProizvod> getProizvod() {
		return proizvod;
	}

	public void setProizvod(ArrayList<SoftverskiProizvod> proizvod) {
		this.proizvod = proizvod;
	}

	public Workspace getParent() {
		return parent;
	}

	public void setParent(Workspace parent) {
		this.parent = parent;
	}

	public String getBrZaposlenih() {
		return brZaposlenih;
	}

	public void setBrZaposlenih(String brZaposlenih) {
		this.brZaposlenih = brZaposlenih;
	}

	public String getDatumNastanka() {
		return datumNastanka;
	}

	public void setDatumNastanka(String datumNastanka) {
		this.datumNastanka = datumNastanka;
	}

	@Override
	public String toString() {
		return this.ime;
	}
	
	public void addProduct(SoftverskiProizvod p) {
		proizvod.add(p);
	}
	
	public void removeChild(SoftverskiProizvod child) {
		// TODO: eventualne provere da li je brisanje moguce
		this.proizvod.remove(child);
		child.setParent(null);
	}
	
	public void removeFromParent() {
		// pozovemo parenta da nas ukloni
		this.parent.removeChild(this);
	}
}
	
	
