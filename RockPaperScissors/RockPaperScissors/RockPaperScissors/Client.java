package RockPaperScissors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class Client {

	public static ClientGUI gui;
	public static Socket socket;
	
	public static void main(String[] args) {
		
		gui = new ClientGUI();
		gui.setTitle("Rock Paper Scissors - Client");
		gui.ConnectionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (socket.isConnected()){
						socket.close();
						gui.ConnectionButton.setText("Connect");
						gui.StatusLabel.setText("DISCONNECTED");
					}
					else {
						socket = new Socket(gui.IPField.getText(), Integer.parseInt(gui.PortField.getText()));
						gui.ConnectionButton.setText("Disconnect");
						gui.StatusLabel.setText("CONNECTED")
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
