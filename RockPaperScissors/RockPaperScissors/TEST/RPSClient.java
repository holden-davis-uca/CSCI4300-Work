package TEST;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RPSClient {

	private ClientGUI2 gui;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private int playernumber;
	private boolean connected;

	public RPSClient() throws Exception {
		gui = new ClientGUI2();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.ConnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!connected)
					{
						socket = new Socket(gui.IPField.getText(), Integer.parseInt(gui.PortField.getText()));
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						out = new PrintWriter(socket.getOutputStream());
						if (socket.isConnected()) connected = true;
						gui.ConnectButton.setText("Disconnect");
						gui.InfoLabel.setText("Connected");
						gui.IPField.setEditable(false);
						gui.PortField.setEditable(false);
						gui.PaperButton.setEnabled(true);
						gui.RockButton.setEnabled(true);
						gui.ScissorsButton.setEnabled(true);
					}
					else {
						socket.close();
						connected = false;
						gui.ConnectButton.setText("Connect");
						gui.InfoLabel.setText("Disconnected");
						gui.IPField.setEditable(true);
						gui.PortField.setEditable(true);
						gui.PaperButton.setEnabled(false);
						gui.RockButton.setEnabled(false);
						gui.ScissorsButton.setEnabled(false);
					}
				} catch (Exception ex) {
					gui.InfoLabel.setText("Connection error");
					ex.printStackTrace();
				}
			}
		});
	}

	public void play() throws Exception {
		String serverresponse;
		try {
			serverresponse = in.readLine();
			if (serverresponse.startsWith("PLAYER")) {
				this.playernumber = serverresponse.charAt(6);
			}
			while (true) {
				serverresponse = in.readLine();
				System.out.println(serverresponse);
				// VALID MOVE - no need to know, just draw client side
				// OPPONENT MOVED
				// VICTORY
				// DEFEAT
				// TIE
				// MESSAGE
				break;
			}
			out.println("Q");
			System.out.println("Q");
		} finally {
			socket.close();
		}
	}

	private boolean rematch() {
		int response = JOptionPane.showConfirmDialog(gui, "Rematch?", "HELP", JOptionPane.YES_NO_OPTION);
		gui.dispose();
		return response == JOptionPane.YES_OPTION;
	}

	public static void main(String[] args) throws Exception {
			RPSClient client = new RPSClient();
			while (client.connected) {
				client.play();
				if (!client.rematch()) {
					break;
				}
			}
		}
	}
