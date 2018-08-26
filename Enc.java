package GUI.Enc;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
public class Enc {
	JButton encrypt;
	JTextArea textarea1,textarea2;
	JTextField textfield;
	public static String[] shift(String[] array) {
		int c = 0;
		while(c < 26) {
			if((int)(array[c].charAt(0)+1) == 91) {
				array[c] = "A";
				c++;
				continue;
			}
			array[c] = ""+(char)(array[c].charAt(0)+1);
			c++;
		}
		return array;
	}
	public static void main(String[] ar) {
		new Enc();
	}
	public Enc() {
		JFrame frame = new JFrame("Encryption");
		frame.setSize(500, 520);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		encrypt = new JButton("Encrypt/Decrypt!");
		encrypt.setBounds(285,10,200,40);
		encrypt.setFont(new Font("Ariel",Font.PLAIN,20));
		encrypt.setFocusPainted(false);
		textarea1 = new JTextArea("Plain Text Here!");
		textarea1.setBounds(12,60,470,200);
		textarea1.setFont(new Font("Ariel",Font.PLAIN,20));
		textarea1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		textarea1.setLineWrap(true);
		textarea1.setWrapStyleWord(true);
	    textarea2 = new JTextArea("Cipher Text Here!");
	    textarea2.setBounds(12,270,470,200);
	    textarea2.setFont(new Font("Ariel",Font.PLAIN,20));
	    textarea2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(10, 10, 10, 10)));
	    textarea2.setLineWrap(true);
	    textarea2.setWrapStyleWord(true);
	    textfield = new JTextField("I II III");
	    textfield.setFont(new Font("Ariel",Font.PLAIN,20));
	    textfield.setBounds(130,10,150,40);
	    JLabel label = new JLabel("Enter States:");
	    label.setFont(new Font("Ariel",Font.PLAIN,20));
	    label.setBounds(10,10,150,40);
	    Actionlistener al = new Actionlistener();
	    encrypt.addActionListener(al);
	    panel.add(label);
	    panel.add(textfield);
	    panel.add(encrypt);
	    panel.add(textarea1);
		panel.add(textarea2);
		frame.add(panel);
		frame.setVisible(true);
	}
	private class Actionlistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String[] states;
			if(e.getSource() == encrypt) {
				states = textfield.getText().split(" ");
				UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Arial",Font.PLAIN,20)));
				UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial",Font.PLAIN,20)));
				int one = 0,two = 0,three = 0;
				String[] swap = {"U","T","W","D","R","B","G","Q","E","M","N","S","K","A","C","L","Z","P","X","V","O","H","I","J","Y","F"};
				String[] scrambler1 = {"G","V","Q","X","I","J","M","H","W","S","E","O","K","T","Z","F","A","N","D","P","Y","L","C","B","U","R"};//State 1
				String[] scrambler2 = {"G","N","S","J","R","H","K","L","A","B","E","M","X","F","U","P","Y","D","W","T","Q","I","O","Z","C","V"};//State 1
				String[] scrambler3 = {"H","R","A","U","T","N","P","M","S","X","E","V","B","Y","Z","D","G","C","W","L","J","F","K","Q","O","I"};//State 1
				String[] reflector = {"X","B","F","Y","E","C","K","R","Z","O","G","L","V","U","J","P","T","H","W","Q","N","M","S","A","D","I"};
				try {
					one = Integer.parseInt(states[0]);
					two = Integer.parseInt(states[1]);
					three = Integer.parseInt(states[2]);
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please Enter Valid States!");
				}
				if(one > 25 || two > 25 || three > 25)
					JOptionPane.showMessageDialog(null, "Please Enter Valid States!");
				String plaintext = textarea1.getText().toUpperCase(),ciphertext = "";
				int c = 0;
				while(c++ < one)
					scrambler1 = shift(scrambler1);
				c = 0;
				while(c++ < two)
					scrambler2 = shift(scrambler2);
				c = 0;
				while(c++ < three)
					scrambler3 = shift(scrambler3);
				c = 0;
				while(c < plaintext.length()) {
					if(plaintext.charAt(c) == ' ') {
						ciphertext = ciphertext+" ";
						c++;
					}
					int a = plaintext.charAt(c)-65;
					int b = swap[a].charAt(0)-65;
					int d = scrambler1[b].charAt(0)-65;
					int f = scrambler2[d].charAt(0)-65;
					int g = scrambler3[f].charAt(0)-65;
					String h = reflector[g];
					int c2 = 0;
					while(c2 < 26) {
						if(h.equals(scrambler3[c2])) {
							int c3 = 0;
							char i = (char)(c2+65);
							while(c3 < 26) {
								if(i == scrambler2[c3].charAt(0)) {
									int c4 = 0;
									char j = (char)(c3+65);
									while(c4 < 26) {
										if(j == scrambler1[c4].charAt(0)) {
											int c5 = 0;
											char k = (char)(c4+65);
											while(c5 < 26) {
												if(k == swap[c5].charAt(0)) {
													ciphertext = ciphertext+(char)(c5+65);
													break;
												}
												c5++;
											}
											break;
										}
										c4++;
									}
									break;
								}
								c3++;
							}
							break;
						}
						c2++;
					}
					c++;
					scrambler1 = shift(scrambler1);
					if(c%26 == 0)
						scrambler2 = shift(scrambler2);
					if(c%676 == 0)
						scrambler3 = shift(scrambler3);
				}
				textarea2.setText(ciphertext);
			}
		}
	}
}
