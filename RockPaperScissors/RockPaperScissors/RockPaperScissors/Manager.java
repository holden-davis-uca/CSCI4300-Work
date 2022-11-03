package RockPaperScissors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class Manager {
	
	public static ManagerGUI gui;
	public static Socket p1s;
	public static Socket p2s;

	public static void main(String[] args) {
		
		gui = new ManagerGUI();
		gui.setTitle("Rock Paper Scissors - Manager");
		gui.ServerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (p1s.isConnected() || p2s.isConnected()){
						p1s.close();
						p2s.close();
						gui.P1StatusField.setText("NONE");
						gui.P2StatusField.setText("NONE");
					}
					else {
						p1s = new Socket("asdf", Integer.parseInt(gui.PortField.getText()));
						p2s = new Socket("asdf", Integer.parseInt(gui.PortField.getText()));

;					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		gui.setSize(1000, 700);
		gui.setResizable(false);
		gui.setVisible(true);

	}

}
