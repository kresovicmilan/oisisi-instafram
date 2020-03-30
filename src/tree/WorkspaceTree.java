package tree;

import javax.swing.tree.DefaultMutableTreeNode;
import model.Workspace;

public class WorkspaceTree extends DefaultMutableTreeNode{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9032361489118253890L;
	private Workspace ws;
	
	public WorkspaceTree(Workspace ws) {
		super();
		this.ws = ws;
	}

	public Workspace getWs() {
		return ws;
	}

	public void setWs(Workspace ws) {
		this.ws = ws;
	}

	@Override
	public String toString() {
		return ws.getIme();
	}
	
}
