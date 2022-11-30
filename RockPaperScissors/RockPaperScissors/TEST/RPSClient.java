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
					if (!connected) {
						socket = new Socket(gui.IPField.getText(), Integer.parseInt(gui.PortField.getText()));
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						out = new PrintWriter(socket.getOutputStream());
						if (socket.isConnected())
							connected = true;
						gui.ConnectButton.setText("Disconnect");
						gui.InfoLabel.setText("Connected");
						gui.IPField.setEditable(false);
						gui.PortField.setEditable(false);
					} else {
						socket.close();
						connected = false;
						gui.ConnectButton.setText("Connect");
						gui.InfoLabel.setText("Disconnected");
						gui.IPField.setEditable(true);
						gui.PortField.setEditable(true);
					}
				} catch (Exception ex) {
					gui.InfoLabel.setText("Connection error");
					ex.printStackTrace();
				}
			}
		});
		gui.RockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("R");
				gui.RockButton.setEnabled(false);
				gui.PaperButton.setEnabled(false);
				gui.ScissorsButton.setEnabled(false);
			}
		});
		gui.PaperButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("P");
				gui.RockButton.setEnabled(false);
				gui.PaperButton.setEnabled(false);
				gui.ScissorsButton.setEnabled(false);
			}
		});
		gui.ScissorsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				out.println("S");
				gui.RockButton.setEnabled(false);
				gui.PaperButton.setEnabled(false);
				gui.ScissorsButton.setEnabled(false);
			}
		});
	}

	public void play() throws Exception {
		String serverresponse;
		try {
			serverresponse = in.readLine();
			System.out.println(serverresponse);
			if (serverresponse.startsWith("PLAYER")) {
				this.playernumber = serverresponse.charAt(6);
				gui.InfoLabel.setText("Connected - Waiting for Opponent");
			}
			while (true) {
				serverresponse = in.readLine();
				System.out.println(serverresponse);
				if (serverresponse.startsWith("READY")) {
					gui.InfoLabel.setText("Opponent Connected - Go!");
				} else if (serverresponse.startsWith("W")) {
					gui.InfoLabel.setText("Victory!");
					break;
				} else if (serverresponse.startsWith("D")) {
					gui.InfoLabel.setText("Defeat!");
					break;
				} else if (serverresponse.startsWith("T")) {
					gui.InfoLabel.setText("Tie!");
					break;
				}
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
		System.out.println("CLIENT PROGRAM STARTED");
		RPSClient client = new RPSClient();
		while (client.gui.isVisible()) {
			if (client.connected) {
				client.play();
				if (!client.rematch()) {
					break;
				} else {
					client.gui.RockButton.setEnabled(true);
					client.gui.PaperButton.setEnabled(true);
					client.gui.ScissorsButton.setEnabled(true);
					client.gui.InfoLabel.setText("Go!");
				}
			}
		}
	}
}
