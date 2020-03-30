package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public class InstalacionaKlasa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1165227963836199521L;
	
	private String welcomeTxt;
	private String licenceTxt;
	private String verzijaTxt;
	private String izvorTxt;
	private String imeProizvoda;
	private byte[] zipByte;
	private Properties prop;
	
	public InstalacionaKlasa(String welcomeTxt, String licenceTxt, String verzijaTxt, String izvorTxt, Properties prop, String imeProizvoda) throws IOException {
		super();
		this.welcomeTxt = welcomeTxt;
		this.licenceTxt = licenceTxt;
		this.verzijaTxt = verzijaTxt;
		this.izvorTxt = izvorTxt;
		this.imeProizvoda = imeProizvoda;
		this.prop = prop;
		
		File zipFile = new File(izvorTxt);
		this.zipByte = new byte[(int) zipFile.length()];
		FileInputStream fileInputStream = null;
		
		try {
		    fileInputStream = new FileInputStream(zipFile);
		    fileInputStream.read(this.zipByte);
		  } finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}
	}

	public Properties getProp() {
		return prop;
	}

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	public String getWelcomeTxt() {
		return welcomeTxt;
	}

	public void setWelcomeTxt(String welcomeTxt) {
		this.welcomeTxt = welcomeTxt;
	}

	public String getLicenceTxt() {
		return licenceTxt;
	}

	public void setLicenceTxt(String licenceTxt) {
		this.licenceTxt = licenceTxt;
	}

	public String getVerzijaTxt() {
		return verzijaTxt;
	}

	public void setVerzijaTxt(String verzijaTxt) {
		this.verzijaTxt = verzijaTxt;
	}

	public String getIzvorTxt() {
		return izvorTxt;
	}

	public void setIzvorTxt(String izvorTxt) {
		this.izvorTxt = izvorTxt;
	}

	public byte[] getZipByte() {
		return zipByte;
	}

	public String getImeProizvoda() {
		return imeProizvoda;
	}

	public void setImeProizvoda(String imeProizvoda) {
		this.imeProizvoda = imeProizvoda;
	}

	@Override
	public String toString() {
		return "InstalacionaKlasa [welcomeTxt=" + welcomeTxt + "\n, licenceTxt=" + licenceTxt + "\n, verzijaTxt="
				+ verzijaTxt + "\n, izvorTxt=" + izvorTxt + "\n, prop=" + prop + "\n, imeProizvoda=" + imeProizvoda + "]";
	}
	
	
}
