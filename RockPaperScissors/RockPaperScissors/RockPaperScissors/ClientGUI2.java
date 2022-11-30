package RockPaperScissors;

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

public class ClientGUI2 extends JFrame {
	private JTextField IPField;
	private JTextField PortField;
	
	public ClientGUI2() {
		
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
		
		JButton ConnectButton = new JButton("CONNECT");
		connPanel.add(ConnectButton);
		
		JPanel movePanel = new JPanel();
		mainPanel.add(movePanel, BorderLayout.CENTER);
		movePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton ScissorsButton = new JButton("");
		ScissorsButton.setIcon(new ImageIcon(".//RockPaperScissors//scissors.png"));
		movePanel.add(ScissorsButton);
		
		JButton PaperButton = new JButton("");
		PaperButton.setIcon(new ImageIcon(".//RockPaperScissors//paper.png"));
		movePanel.add(PaperButton);
		
		JButton RockButton = new JButton("");
		RockButton.setIcon(new ImageIcon(".//RockPaperScissors//rock.png"));
		movePanel.add(RockButton);
		
		JPanel notifPanel = new JPanel();
		mainPanel.add(notifPanel, BorderLayout.SOUTH);
		
		JLabel InfoLabel = new JLabel("AMOGUS");
		notifPanel.add(InfoLabel);
		
	}

}
