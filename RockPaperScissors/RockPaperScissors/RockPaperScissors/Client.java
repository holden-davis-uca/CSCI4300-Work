package RockPaperScissors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class Client {

	public static ClientGUI gui;
	
	public static void main(String[] args) {
		
		gui = new ClientGUI();
		gui.setTitle("Rock Paper Scissors - Client");
		gui.setSize(1000, 700);
		gui.setResizable(false);
		gui.setVisible(true);

	}
	public void connect()
	{
		
	}
	public void disconnect()
	{
		
	}
	public void play()
	{
		
	}
}
