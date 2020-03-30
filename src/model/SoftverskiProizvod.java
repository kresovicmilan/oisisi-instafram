package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;

import javax.imageio.ImageIO;

public class SoftverskiProizvod implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4914008610494657546L;
	
	private String ime;
	private String verzija;
	private SoftverskaKompanija parent;
	private transient Image logo;
	private String datumNastanka;
	private ArrayList<Parametar> parametar;
	private String imgString;
	
	public SoftverskiProizvod(String ime, String verzija, SoftverskaKompanija parent, Image logo, String datumNastanka) {
		this.ime = ime;
		this.verzija = verzija;
		this.parent = parent;
		this.logo = logo;
		this.datumNastanka = datumNastanka;
		if (logo != null) {
			try {
				imgString = imageToString(logo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.parametar = new ArrayList<Parametar>();
	}
	
	public SoftverskiProizvod() {
		this.ime = null;
		this.verzija = null;
		this.logo = null;
		this.datumNastanka = null;
		this.parametar = new ArrayList<Parametar>();
		}
	
	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
		try {
			setImgString(imageToString(logo));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getVerzija() {
		return verzija;
	}

	public void setVerzija(String verzija) {
		this.verzija = verzija;
	}

	public SoftverskaKompanija getParent() {
		return parent;
	}

	public void setParent(SoftverskaKompanija parent) {
		this.parent = parent;
	}

	public ArrayList<Parametar> getParametar() {
		return parametar;
	}

	public void setParametar(ArrayList<Parametar> parametar) {
		this.parametar = parametar;
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
		this.parent.removeChild(this);
	}
	
	public static String imageToString (Image img) throws IOException {
		BufferedImage originalImage = imageToBufferedImage(img);
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos );
		
		byte[] bytes = baos.toByteArray();
		String str = Base64.getEncoder().encodeToString(bytes);
		
		return str;
	}
	
	public static BufferedImage imageToBufferedImage(Image im) {
	     BufferedImage bi = new BufferedImage
	        (im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_RGB);
	     Graphics bg = bi.getGraphics();
	     bg.drawImage(im, 0, 0, null);
	     bg.dispose();
	     return bi;
	  }

	public void setImgString(String imgString) {
		this.imgString = imgString;
	}

	public String getImgString() {
		return imgString;
	}
	
}
