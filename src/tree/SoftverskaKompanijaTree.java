package tree;

import javax.swing.tree.DefaultMutableTreeNode;

import model.SoftverskaKompanija;

public class SoftverskaKompanijaTree extends DefaultMutableTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5461767508137855474L;
	private SoftverskaKompanija k;
	
	public SoftverskaKompanijaTree(SoftverskaKompanija k) {
		super();
		this.k = k;
	}

	public SoftverskaKompanija getK() {
		return k;
	}

	public void setK(SoftverskaKompanija k) {
		this.k = k;
	}

	@Override
	public String toString() {
		return k.getIme();
	}
	
}
