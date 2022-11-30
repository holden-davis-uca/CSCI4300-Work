package RockPaperScissors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class Client {

	public static ClientGUI2 gui;
	
	public static void main(String[] args) {
		
		gui = new ClientGUI2();
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
