package RockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ManagerCLI {
	public int p1move;

	public static void main(String[] args) throws Exception {
		System.out.println("STARTING MANAGER");
		System.out.print("Enter port: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int serverport = 0;
		try {
			serverport = Integer.parseInt(reader.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Listening on " + serverport + "...");
		ServerSocket server = new ServerSocket(serverport);
		try {
			while (true) {
				RPSGame game = new RPSGame();
				
				RPSGame.ConnectedClient client1 = game.new ConnectedClient(server.accept(), 1);
				System.out.println("Client 1 connected");
				RPSGame.ConnectedClient client2 = game.new ConnectedClient(server.accept(), 2);
				System.out.println("Client 2 connected");
				client1.start();
				client2.start();
			}
		} finally {
			System.out.println("STOPPING MANAGER");
			server.close();
		}
	}
}

class RPSGame {
	char p1move;
	char p2move;
	
	//0 if p1, 1 if p2, -1 if tie
	public int winner() {
		if (p1move == 'R')
		{
			if (p2move == 'R')
				return -1;
			else if (p2move == 'S')
				return 0;
			else
				return 1;
		}
		else if (p1move == 'P')
		{
			if (p2move == 'P')
				return -1;
			else if (p2move == 'R')
				return 0;
			else
				return 1;
		}
		else
		{
			if (p2move == 'S')
				return -1;
			else if (p2move == 'R')
				return 0;
			else
				return 1;
		}
	}

	class ConnectedClient extends Thread {

		Socket socket;
		int playernumber;
		BufferedReader instream;
		PrintWriter outstream;

		public ConnectedClient(Socket socket, int playernumber) {
			this.socket = socket;
			this.playernumber = playernumber;
			try {
				instream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				outstream = new PrintWriter(socket.getOutputStream());
				if (playernumber == 1)
					outstream.println("You are player " + playernumber + " , waiting for opponent...");
				else
					outstream.println("You are player " + playernumber);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			try {
				outstream.println("Players 1 and 2 connected, starting game!");
				while (true) {
					String command = instream.readLine();
					if (command.startsWith(command)) {

					} else if (command.startsWith("D")) {
						return;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}