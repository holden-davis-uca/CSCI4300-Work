package RockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RPSPlayer extends Thread {
	
	public int playernum;
	public RPSPlayer otherplayer;
	public Socket socket;
	public BufferedReader input;
	public PrintWriter output;
	
	public RPSPlayer(Socket socket, int playernum) {
		this.socket = socket;
		this.playernum = playernum;
		try {
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
	}
}
