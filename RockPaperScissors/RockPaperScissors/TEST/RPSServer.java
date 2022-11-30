package TEST;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class RPSServer {

	private ServerGUI2 gui;
	private ServerSocket socket;
	private boolean running;

	public RPSServer() throws Exception {
		gui = new ServerGUI2();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.ConnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!running) {
						socket = new ServerSocket(Integer.parseInt(gui.PortField.getText()));
						if (!socket.isClosed())
							running = true;
						gui.ConnectButton.setText("Stop Server");
						gui.InfoLabel.setText("Server Started");
						gui.PortField.setEditable(false);
					} else {
						socket.close();
						running = false;
						gui.ConnectButton.setText("Start Server");
						gui.InfoLabel.setText("Server Stopped");
						gui.PortField.setEditable(true);
					}
				} catch (Exception ex) {
					gui.InfoLabel.setText("Connection error");
					ex.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) throws Exception {
		System.out.println("SERVER PROGRAM STARTED");
		RPSServer server = new RPSServer();
		try {
			while (server.gui.isVisible()) {
				if (server.running) {
					Game game = new Game();
					Game.Player player1 = game.new Player(server.socket.accept(), 1);
					Game.Player player2 = game.new Player(server.socket.accept(), 2);
					player1.setOpponent(player1);
					player2.setOpponent(player1);
					player1.start();
					player2.start();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

class Game {

	private char p1move;
	private char p2move;

	public boolean hasWinner(int playernum) {
		if (playernum == 1) {
			return (this.p1move == 'R' && this.p2move == 'S' || this.p1move == 'P' && this.p2move == 'R'
					|| this.p1move == 'S' && this.p2move == 'P');
		} else
			return (this.p2move == 'R' && this.p1move == 'S' || this.p2move == 'P' && this.p1move == 'R'
					|| this.p2move == 'S' && this.p1move == 'P');
	}

	public boolean tied() {
		return (p1move == 'R' && p2move == 'R' || p1move == 'P' && p2move == 'P' || p1move == 'S' && p2move == 'S');
	}

	public void shoot(Player player, char decision) {
		if (player.playernumber == 1)
			p1move = decision;
		else
			p2move = decision;
		player.otherPlayerMoved();
	}

	class Player extends Thread {
		int playernumber;
		Player opponent;
		Socket socket;
		BufferedReader in;
		PrintWriter out;

		public Player(Socket socket, int number) {
			System.out.println("PLAYER " + number + "CONNECTED");
			this.socket = socket;
			this.playernumber = number;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());
				out.println("PLAYER " + number);
				System.out.println("PLAYER " + number);
				out.println("WAIT");
				System.out.println("WAIT");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void setOpponent(Player opponent) {
			this.opponent = opponent;
		}

		public void otherPlayerMoved() {
			out.println("O");
			System.out.println("O");
			out.println(hasWinner(this.playernumber) ? "W" : tied() ? "T" : "D");
			System.out.println(hasWinner(this.playernumber) ? "W" : tied() ? "T" : "D");
		}

		public void run() {
			try {
				out.println("READY");
				System.out.println("READY");
				while (true) {
					String move = in.readLine();
					System.out.println(move);
					if (move.startsWith("M")) {
						shoot(this, move.charAt(1));
						out.println(hasWinner(this.playernumber) ? "W" : tied() ? "T" : "D");
						System.out.println(hasWinner(this.playernumber) ? "W" : tied() ? "T" : "D");
					} else if (move.startsWith("Q"))
						return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}