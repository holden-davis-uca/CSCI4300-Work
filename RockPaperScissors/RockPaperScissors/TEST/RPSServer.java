package TEST;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

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
						System.out.println("RPSServer() - Connect button selected, starting server");
						socket = new ServerSocket(Integer.parseInt(gui.PortField.getText()));
						if (!socket.isClosed())
							running = true;
						gui.ConnectButton.setText("Stop Server");
						gui.InfoLabel.setText("Server Started");
						gui.PortField.setEditable(false);
					} else {
						System.out.println("RPSServer() - Connect button selected, stopping server");
						socket.close();
						running = false;
						gui.ConnectButton.setText("Start Server");
						gui.InfoLabel.setText("Server Stopped");
						gui.PortField.setEditable(true);
					}
				} catch (Exception ex) {
					System.out.println("RPSServer() - Unknown connection error");
					gui.InfoLabel.setText("Connection error");
					ex.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) throws Exception {
		System.out.println("main() - SERVER PROGRAM STARTED");
		RPSServer server = new RPSServer();
		//
		server.gui.PortField.setText("7274");
		server.gui.ConnectButton.doClick();
		//
		while (true) {
			if (server.running) {
				System.out.println("main() - Server started and running - creating game instance");
				Game game = new Game();
				Game.Player player1 = game.new Player(server.socket.accept(), 1);
				Game.Player player2 = game.new Player(server.socket.accept(), 2);
				player1.setOpponent(player2);
				player2.setOpponent(player1);
				player1.start();
				player2.start();
			}
			TimeUnit.SECONDS.sleep(1);
		}
	}
}

class Game {

	private char p1move;
	private char p2move;

	public boolean hasWinner(int playernum) {
		if (playernum == 1) {
			System.out.println("hasWinner() - Did player " + playernum + " win: " + (this.p1move == 'R' && this.p2move == 'S' || this.p1move == 'P' && this.p2move == 'R'
					|| this.p1move == 'S' && this.p2move == 'P'));
			return (this.p1move == 'R' && this.p2move == 'S' || this.p1move == 'P' && this.p2move == 'R'
					|| this.p1move == 'S' && this.p2move == 'P');
		} else
			System.out.println("hasWinner() - Did player " + playernum + " win: " + (this.p2move == 'R' && this.p1move == 'S' || this.p2move == 'P' && this.p1move == 'R'
					|| this.p2move == 'S' && this.p1move == 'P'));
			return (this.p2move == 'R' && this.p1move == 'S' || this.p2move == 'P' && this.p1move == 'R'
					|| this.p2move == 'S' && this.p1move == 'P');
	}

	public boolean tied() {
		System.out.println("tied() - Tie: " + (p1move == 'R' && p2move == 'R' || p1move == 'P' && p2move == 'P' || p1move == 'S' && p2move == 'S'));
		return (p1move == 'R' && p2move == 'R' || p1move == 'P' && p2move == 'P' || p1move == 'S' && p2move == 'S');
	}

	public boolean shoot(Player player, char decision) {
		System.out.println("shoot() - Player " + player.playernumber + " just moved " + decision);
		if (player.playernumber == 1)
			p1move = decision;
		else
			p2move = decision;
		player.opponent.otherPlayerMoved();
		return true;
	}

	class Player extends Thread {
		int playernumber;
		Player opponent;
		Socket socket;
		BufferedReader in;
		PrintWriter out;

		public Player(Socket socket, int number) {
			System.out.println("PLAYER " +  this.playernumber +  " THREAD ==> Player() - Player connected, assigning player number " + number);
			this.socket = socket;
			this.playernumber = number;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				out.println("N" + number);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void setOpponent(Player opponent) {
			this.opponent = opponent;
		}

		public void otherPlayerMoved() {
			out.println("O");
			out.println(hasWinner(this.playernumber) ? "D" : tied() ? "T" : "W");
			System.out.println("PLAYER " +  this.playernumber +  " THREAD ==> otherPlayerMoved() - " + this.playernumber + " win: " + (hasWinner(this.playernumber) ? "D" : tied() ? "T" : ""));
		}

		public void run() {
			try {
				out.println("G");
				System.out.println("PLAYER " +  this.playernumber +  " THREAD ==> run() - Sending Go sequence to player " + this.playernumber);
				while (true) {
					String move = in.readLine();
					if (move.startsWith("M")) {
						if (shoot(this, move.charAt(1))) {
							System.out.println("PLAYER " +  this.playernumber +  " THREAD ==> run()  - Player " + this.playernumber + " chose " + move.charAt(1));
							out.println(hasWinner(this.playernumber) ? "W" : tied() ? "T" : "D");
						}
					} else if (move.startsWith("Q"))
						System.out.println("PLAYER " +  this.playernumber +  " THREAD ==> run()  - Player " + this.playernumber + " chose to quit");
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