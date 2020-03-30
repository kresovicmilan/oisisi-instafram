package tree;

import javax.swing.tree.DefaultMutableTreeNode;

import model.Parametar;

public class ParametarTree extends DefaultMutableTreeNode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8115431507488538448L;
	private Parametar p;
	public ParametarTree(Parametar p) {
		super();
		this.p = p;
	}

	public Parametar getP() {
		return p;
	}

	public void setP(Parametar p) {
		this.p = p;
	}
	
	@Override
	public String toString() {
		return p.getIme();
	}
	
}
