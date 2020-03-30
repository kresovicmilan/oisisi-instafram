package InstalacioniPaket;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		InstalacioniProzor ip;
		try {
			ip = new InstalacioniProzor();
			ip.setVisible(true);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
