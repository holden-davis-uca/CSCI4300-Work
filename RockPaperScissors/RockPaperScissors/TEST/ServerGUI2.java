package TEST;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ServerGUI2 extends JFrame {
	
	public JTextField PortField;
	public JLabel InfoLabel;
	public JButton ConnectButton;
	public JButton P1MoveButton;
	public JButton P2MoveButton;
	
	public ServerGUI2() {
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel connPanel = new JPanel();
		mainPanel.add(connPanel, BorderLayout.NORTH);
		
		JLabel PortLabel = new JLabel("Port: ");
		connPanel.add(PortLabel);
		
		PortField = new JTextField();
		connPanel.add(PortField);
		PortField.setColumns(10);
		
		ConnectButton = new JButton("Start Server");
		connPanel.add(ConnectButton);
		
		JPanel movePanel = new JPanel();
		mainPanel.add(movePanel);
		
		P1MoveButton = new JButton("New button");
		movePanel.add(P1MoveButton);
		
		P2MoveButton = new JButton("New button");
		movePanel.add(P2MoveButton);
		
		JPanel notifPanel = new JPanel();
		mainPanel.add(notifPanel, BorderLayout.SOUTH);
		
		InfoLabel = new JLabel("Server Stopped");
		InfoLabel.setToolTipText("");
		notifPanel.add(InfoLabel);
		
		this.setTitle("Rock, Paper, Scissors Server");
		this.setSize(700, 350);
		this.setResizable(false);
		this.setVisible(true);
	}

}
