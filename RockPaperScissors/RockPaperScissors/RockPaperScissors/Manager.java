package RockPaperScissors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;

public class Manager {
	
	public static ManagerGUI gui;
	public static boolean running;
	public static ServerSocket listener;

	public static void main(String[] args) {
		
		gui = new ManagerGUI();
		gui.setTitle("Rock Paper Scissors - Manager");
		gui.setSize(1000, 700);
		gui.setResizable(false);
		gui.setVisible(true);
		running = true;
		while (running)
		{
		}

	}

}
