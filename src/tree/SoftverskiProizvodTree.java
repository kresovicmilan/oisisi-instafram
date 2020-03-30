package tree;

import javax.swing.tree.DefaultMutableTreeNode;
import model.SoftverskiProizvod;

public class SoftverskiProizvodTree extends DefaultMutableTreeNode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2038001446761125085L;
	private SoftverskiProizvod p;

	public SoftverskiProizvodTree(SoftverskiProizvod p) {
		this.p = p;
	}

	public SoftverskiProizvod getP() {
		return p;
	}

	public void setP(SoftverskiProizvod p) {
		this.p = p;
	}	
	
	@Override
	public String toString() {
		return p.getIme();
	}
}
