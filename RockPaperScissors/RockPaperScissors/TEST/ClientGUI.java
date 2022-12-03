package TEST;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;

public class ClientGUI extends JFrame {
	
	public JTextField IPField;
	public JTextField PortField;
	public JLabel InfoLabel;
	public JButton ConnectButton;
	public JButton ScissorsButton;
	public JButton PaperButton;
	public JButton RockButton;
	
	public ClientGUI() {
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel connPanel = new JPanel();
		mainPanel.add(connPanel, BorderLayout.NORTH);
		
		JLabel IPLabel = new JLabel("IP:");
		connPanel.add(IPLabel);
		
		IPField = new JTextField();
		connPanel.add(IPField);
		IPField.setColumns(10);
		
		JLabel PortLabel = new JLabel("PORT: ");
		connPanel.add(PortLabel);
		
		PortField = new JTextField();
		connPanel.add(PortField);
		PortField.setColumns(10);
		
		ConnectButton = new JButton("CONNECT");
		connPanel.add(ConnectButton);
		
		JPanel movePanel = new JPanel();
		mainPanel.add(movePanel, BorderLayout.CENTER);
		movePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		ScissorsButton = new JButton("");
		ScissorsButton.setIcon(new ImageIcon(".//RockPaperScissors//scissors.png"));
		ScissorsButton.setEnabled(false);
		movePanel.add(ScissorsButton);
		
		PaperButton = new JButton("");
		PaperButton.setIcon(new ImageIcon(".//RockPaperScissors//paper.png"));
		PaperButton.setEnabled(false);
		movePanel.add(PaperButton);
		
		RockButton = new JButton("");
		RockButton.setIcon(new ImageIcon(".//RockPaperScissors//rock.png"));
		RockButton.setEnabled(false);
		movePanel.add(RockButton);
		
		JPanel notifPanel = new JPanel();
		mainPanel.add(notifPanel, BorderLayout.SOUTH);
		
		InfoLabel = new JLabel("Disconnected");
		notifPanel.add(InfoLabel);
		
		this.setTitle("Rock, Paper, Scissors Client");
		this.setSize(700, 350);
		this.setResizable(false);
		this.setVisible(true);
		
	}
}
