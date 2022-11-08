package RockPaperScissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientCLI {
	
	public Socket socket;
	public BufferedReader instream;
	public PrintWriter outstream;
	public Boolean finished;
	public String move;
	
	public ClientCLI(String addr, String port) throws Exception {
		socket = new Socket(addr, Integer.parseInt(port));
		System.out.println("Connected to " + addr + " on " + port);
		instream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		outstream = new PrintWriter(socket.getOutputStream());
		System.out.print("Move R/P/S: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		move = reader.readLine();
		outstream.println(move);
	}

	public void play() throws Exception {
		String response;
		try {
			while (true) {
				response = instream.readLine();
				if (response.startsWith("W")) {
					System.out.println("You won!");
					break;
				}
				else if (response.startsWith("L")) {
					System.out.println("You lost!");
					break;
				}
				else if (response.startsWith("T")) {
					System.out.println("You tied!");
					break;
				}
				
			}
			outstream.println("D");
		} finally {
			socket.close();
		}
		
	}
	
	public boolean rematch() {
		try {
			System.out.print("Rematch? Y/N ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String response = reader.readLine();
			return response.equals("Y");
		} catch (IOException e) {
			e.printStackTrace();
			return rematch();
		}
	}
	
	public static void main(String[] args) throws Exception{
		while (true)
		{
			System.out.println("STARTING CLIENT");
			System.out.print("Enter IP: ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String addr = reader.readLine();
			System.out.print("Enter Port: ");
			String port = reader.readLine();
			ClientCLI client = new ClientCLI(addr, port);
			client.play();
			if (!client.rematch())
			{
				System.out.println("STOPPING CLIENT");
				break;
			}
		}	
	}

}
