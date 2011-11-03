import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	//commit test!
	//Tjenare!!!!
	//test från github.com
	//huhuhu
	// JOAKIM �R B�ST
	//OMLKMÖLKLJLKMLKMLKMLKMLKMLKMLKMKLM

public static void main(String[] args) {
		JFrame frame = new JFrame("Solaris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Orc orcpanel = new Orc();

		frame.getContentPane().add(orcpanel);
		frame.setSize(new Dimension(306, 328));
		frame.setVisible(true);
		frame.setResizable(false);

	}

}
