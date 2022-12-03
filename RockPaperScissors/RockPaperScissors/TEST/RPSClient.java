package TEST;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class RPSClient {

	private ClientGUI gui;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private int playernumber;
	private boolean connected;

	public RPSClient() throws Exception {
		gui = new ClientGUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.ConnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!connected) {
						System.out.println("RPSClient() - Connect button selected, connecting to server");
						socket = new Socket(gui.IPField.getText(), Integer.parseInt(gui.PortField.getText()));
						in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						out = new PrintWriter(socket.getOutputStream(), true);
						if (socket.isConnected())
							connected = true;
						gui.ConnectButton.setText("Disconnect");
						gui.InfoLabel.setText("Connected");
						gui.IPField.setEditable(false);
						gui.PortField.setEditable(false);
					} else {
						System.out.println("RPSClient() - Connect button selected, disconnecting from server");
						connected = false;
						gui.ConnectButton.setText("Connect");
						gui.InfoLabel.setText("Disconnected");
						gui.IPField.setEditable(true);
						gui.PortField.setEditable(true);
					}
				} catch (Exception ex) {
					System.out.println("RPSClient() - Unknown connection error");
					gui.InfoLabel.setText("Connection error");
					ex.printStackTrace();
				}
			}
		});
		gui.RockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("RPSClient() - Rock selected");
				out.println("MR");
				gui.RockButton.setEnabled(false);
				gui.PaperButton.setEnabled(false);
				gui.ScissorsButton.setEnabled(false);
				gui.InfoLabel.setText("You selected Rock; Waiting for Opponent");
			}
		});
		gui.PaperButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("RPSClient() - Paper selected");
				out.println("MP");
				gui.RockButton.setEnabled(false);
				gui.PaperButton.setEnabled(false);
				gui.ScissorsButton.setEnabled(false);
				gui.InfoLabel.setText("You selected Paper; Waiting for Opponent");
			}
		});
		gui.ScissorsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("RPSClient() - Scissors selected");
				out.println("MS");
				gui.RockButton.setEnabled(false);
				gui.PaperButton.setEnabled(false);
				gui.ScissorsButton.setEnabled(false);
				gui.InfoLabel.setText("You selected Scissors; Waiting for Opponent");
			}
		});
	}

	public void play() throws Exception {
		String serverresponse;
		try {
			serverresponse = in.readLine();
			if (serverresponse.startsWith("N")) {
				System.out.println("play() - server sent N");
				this.playernumber = Integer.parseInt(serverresponse.substring(1));
				System.out.println("play() - designated player " + this.playernumber);
				gui.setTitle("RPS Client Player " + this.playernumber);
				gui.InfoLabel.setText("Connected - Waiting for Opponent");
			}
			while (true) {
				serverresponse = in.readLine();
				if (serverresponse != null) {
					System.out.println("play() - server sent " + serverresponse);
					if (serverresponse.startsWith("G")) {
						System.out.println("play() - server sent G");
						gui.InfoLabel.setText("Opponent Connected - Go!");
						gui.RockButton.setEnabled(true);
						gui.PaperButton.setEnabled(true);
						gui.ScissorsButton.setEnabled(true);

					} else if (serverresponse.startsWith("W")) {
						System.out.println("play() - server sent W");
						gui.InfoLabel.setText("Victory!");
						TimeUnit.SECONDS.sleep(1);
						break;
					} else if (serverresponse.startsWith("D")) {
						System.out.println("play() - server sent D");
						gui.InfoLabel.setText("Defeat!");
						TimeUnit.SECONDS.sleep(1);
						break;
					} else if (serverresponse.startsWith("T")) {
						System.out.println("play() - server sent T");
						gui.InfoLabel.setText("Tie!");
						TimeUnit.SECONDS.sleep(1);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean rematch() {
		System.out.println("rematch() - Prompting");
		int response = JOptionPane.showConfirmDialog(gui, "Do you wish to play again?", "Rematch",
				JOptionPane.YES_NO_OPTION);
		return response == JOptionPane.YES_OPTION;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("CLIENT PROGRAM STARTED");
		RPSClient client = new RPSClient();
		//
		client.gui.IPField.setText("192.168.0.225");
		client.gui.PortField.setText("7274");
		client.gui.ConnectButton.doClick();
		//
		while (true) {
			if (client.connected) {
				System.out.println("main() - client connected; starting play");
				client.play();
				if (!client.rematch()) {
					System.out.println("main() - user declined rematch");
					client.out.println("Q");
					break;
				} else {
					System.out.println("main() - user accepted rematch");
					client.gui.RockButton.setEnabled(true);
					client.gui.PaperButton.setEnabled(true);
					client.gui.ScissorsButton.setEnabled(true);
					client.gui.InfoLabel.setText("Go!");
				}
			}
			TimeUnit.SECONDS.sleep(1);
		}
	}
}
