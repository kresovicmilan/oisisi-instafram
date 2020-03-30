package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import model.Parametar;
import model.SoftverskaKompanija;
import model.SoftverskiProizvod;
import model.Workspace;
import panels.KompanijaPanel;
import panels.ParametarPanel;
import panels.ProizvodPanel;
import tree.ParametarTree;
import tree.SoftverskaKompanijaTree;
import tree.SoftverskiProizvodTree;
import tree.WorkspaceTree;
import view.MainFrame;

import view.MainFrame;

public class AddNodeAction extends AbstractAction {

		/**
	 * 
	 */
	private static final long serialVersionUID = -186891392924721243L;

		public AddNodeAction() {
			
		}
		
		public AddNodeAction(String name) {
			super(name);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			save();
		}
		
	public void save() {
		// preuzecemo selektovani cvor u stablu
		DefaultMutableTreeNode parentNodeView = (DefaultMutableTreeNode) MainFrame.getInstance().getTree()
				.getLastSelectedPathComponent();
		// Pocetna vrednost za vidljivost
		// KompanijaPanel.getInstance().setVisible(false);

		if (parentNodeView instanceof SoftverskaKompanijaTree) {
			SoftverskaKompanijaTree selektovaniCvor = (SoftverskaKompanijaTree) parentNodeView;
			SoftverskaKompanija selektovaniModel = selektovaniCvor.getK();
			
			SoftverskiProizvod p = new SoftverskiProizvod();
			ProizvodPanel pp = MainFrame.getInstance().getProizvodPanel();
			pp.setProizvod(p);
			
			if (!(pp.popuni())) {
				return;
			}

			selektovaniModel.addProduct(p);
			p.setParent(selektovaniModel);
			
			pp.zatvori();
			
			SoftverskiProizvodTree noviProizvodCvor = new SoftverskiProizvodTree(p);
			// System.out.println(p.getIme());

			DefaultTreeModel model = MainFrame.getInstance().getTreeModel();
			model.insertNodeInto(noviProizvodCvor, parentNodeView, parentNodeView.getChildCount());

			JTree tree = MainFrame.getInstance().getTree();
			tree.setSelectionPath(new TreePath(noviProizvodCvor.getPath()));
			tree.scrollPathToVisible(new TreePath(noviProizvodCvor.getPath()));
		} else if (parentNodeView instanceof SoftverskiProizvodTree) {
			SoftverskiProizvodTree selektovaniCvor = (SoftverskiProizvodTree) parentNodeView;
			SoftverskiProizvod selektovaniModel = selektovaniCvor.getP();

			Parametar p = new Parametar();
			ParametarPanel pp = MainFrame.getInstance().getParametarPanel();
			pp.setParametar(p);
			
			if (!(pp.popuni())) {
				return;
			}

			selektovaniModel.addParameter(p);
			p.setParent(selektovaniModel);
			
			pp.zatvori();
			
			ParametarTree noviParametarCvor = new ParametarTree(p);

			DefaultTreeModel model = MainFrame.getInstance().getTreeModel();
			model.insertNodeInto(noviParametarCvor, parentNodeView, parentNodeView.getChildCount());

			JTree tree = MainFrame.getInstance().getTree();
			tree.setSelectionPath(new TreePath(noviParametarCvor.getPath()));
			tree.scrollPathToVisible(new TreePath(noviParametarCvor.getPath()));
		} else if (parentNodeView instanceof ParametarTree) {
			ParametarTree selektovaniCvor = (ParametarTree) parentNodeView;

			Parametar selektovaniModel = selektovaniCvor.getP();

			Parametar p = new Parametar();
			ParametarPanel pp = MainFrame.getInstance().getParametarPanel();
			pp.setParametar(p);

			if (!(pp.popuni())) {
				return;
			}

			selektovaniModel.addParameter(p);
			p.setParent(selektovaniModel);

			pp.zatvori();

			ParametarTree noviParametarCvor = new ParametarTree(p);

			DefaultTreeModel model = MainFrame.getInstance().getTreeModel();
			model.insertNodeInto(noviParametarCvor, parentNodeView, parentNodeView.getChildCount());

			JTree tree = MainFrame.getInstance().getTree();
			tree.setSelectionPath(new TreePath(noviParametarCvor.getPath()));
			tree.scrollPathToVisible(new TreePath(noviParametarCvor.getPath()));
			
		} else {
			WorkspaceTree selektovaniCvor = MainFrame.getInstance().getRootofOurModel();
			Workspace selektovaniModel = selektovaniCvor.getWs();

			SoftverskaKompanija k = new SoftverskaKompanija();
			KompanijaPanel kp = MainFrame.getInstance().getKompanijaPanel();
			kp.setKompanija(k);
			
			if (!(kp.popuni())) {
				return;
			}
			
			selektovaniModel.addCompany(kp.getKompanija()); // Dodajem u parenta WS
			k.setParent(selektovaniModel); // U kompaniju stavljam gde mi se nalazi parent
			
			kp.zatvori();

			SoftverskaKompanijaTree novaKompanijaCvor = new SoftverskaKompanijaTree(k);

			DefaultTreeModel model = MainFrame.getInstance().getTreeModel();
			model.insertNodeInto(novaKompanijaCvor, selektovaniCvor, selektovaniCvor.getChildCount());

			// Postavljanje da bude selektovan novododati cvor
			JTree tree = MainFrame.getInstance().getTree();
			tree.setSelectionPath(new TreePath(novaKompanijaCvor.getPath()));
			tree.scrollPathToVisible(new TreePath(novaKompanijaCvor.getPath()));
			//MainFrame.getInstance().getTree().scrollPathToVisible(new TreePath(novaKompanijaCvor.getPath()));

			// Otvaranje edita i unosa odmah
			// KompanijaPanel.getInstance().setVisible(true);
		}
	}
}
