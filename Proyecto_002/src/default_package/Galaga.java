package default_package;

import javax.swing.*;

/**
 * @ Angelo Pujota
 * @ Galaga
 */

public class Galaga {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Panel panel = new Panel();
				panel.setVisible(true);
			}
		});
	}
}
