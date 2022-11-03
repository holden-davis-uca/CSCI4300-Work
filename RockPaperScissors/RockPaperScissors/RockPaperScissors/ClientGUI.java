package RockPaperScissors;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;

public class ClientGUI extends JFrame {
	public JTextField IPField;
	public JTextField PortField;
	public JLabel StatusLabel;
	public JLabel WinCount;
	public JLabel OpponentStatus;
	public BufferedImage rock;
	public BufferedImage paper;
	public BufferedImage scissors;
	public BufferedImage waiting;
	public JButton ConnectionButton;
	
	public ClientGUI() {
		
		try {
			rock = ImageIO.read(new File("Assets/rock.svg"));
			paper = ImageIO.read(new File("Assets/paper.svg"));
			scissors = ImageIO.read(new File("Assets/scissors.svg"));
			waiting = ImageIO.read(new File("Assets/waiting.svg"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel MasterPanel = new JPanel();
		getContentPane().add(MasterPanel);
		GridBagLayout gbl_MasterPanel = new GridBagLayout();
		gbl_MasterPanel.columnWidths = new int[] {30, 60, 60, 60, 60, 180, 60, 60, 60, 60, 60};
		gbl_MasterPanel.rowHeights = new int[] {30, 30, 30, 30, 60, 60, 60, 60, 60, 60, 60};
		gbl_MasterPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_MasterPanel.rowWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		MasterPanel.setLayout(gbl_MasterPanel);
		
		JPanel InfoPanel = new JPanel();
		GridBagConstraints gbc_InfoPanel = new GridBagConstraints();
		gbc_InfoPanel.fill = GridBagConstraints.BOTH;
		gbc_InfoPanel.insets = new Insets(0, 0, 5, 5);
		gbc_InfoPanel.gridx = 5;
		gbc_InfoPanel.gridy = 0;
		MasterPanel.add(InfoPanel, gbc_InfoPanel);
		InfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel ConnectionLabel = new JLabel("Server Connection Status: ");
		InfoPanel.add(ConnectionLabel);
		
		StatusLabel = new JLabel("DISCONNECTED");
		StatusLabel.setForeground(Color.RED);
		InfoPanel.add(StatusLabel);
		
		Panel ConnectionPanel = new Panel();
		GridBagConstraints gbc_ConnectionPanel = new GridBagConstraints();
		gbc_ConnectionPanel.gridheight = 2;
		gbc_ConnectionPanel.insets = new Insets(0, 0, 5, 5);
		gbc_ConnectionPanel.gridx = 1;
		gbc_ConnectionPanel.gridy = 1;
		MasterPanel.add(ConnectionPanel, gbc_ConnectionPanel);
		
		ConnectionButton = new JButton("Connect");
		ConnectionPanel.add(ConnectionButton);
		
		JPanel IPPanel = new JPanel();
		GridBagConstraints gbc_IPPanel = new GridBagConstraints();
		gbc_IPPanel.fill = GridBagConstraints.BOTH;
		gbc_IPPanel.insets = new Insets(0, 0, 5, 5);
		gbc_IPPanel.gridx = 5;
		gbc_IPPanel.gridy = 1;
		MasterPanel.add(IPPanel, gbc_IPPanel);
		IPPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel IPLabel = new JLabel("IP Address:");
		IPPanel.add(IPLabel);
		
		IPField = new JTextField();
		IPField.setColumns(10);
		IPPanel.add(IPField);
		
		JLabel WinDescription = new JLabel("Wins: ");
		GridBagConstraints gbc_WinDescription = new GridBagConstraints();
		gbc_WinDescription.insets = new Insets(0, 0, 5, 0);
		gbc_WinDescription.gridx = 10;
		gbc_WinDescription.gridy = 1;
		MasterPanel.add(WinDescription, gbc_WinDescription);
		
		JPanel PortPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) PortPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		GridBagConstraints gbc_PortPanel = new GridBagConstraints();
		gbc_PortPanel.insets = new Insets(0, 0, 5, 5);
		gbc_PortPanel.fill = GridBagConstraints.BOTH;
		gbc_PortPanel.gridx = 5;
		gbc_PortPanel.gridy = 2;
		MasterPanel.add(PortPanel, gbc_PortPanel);
		
		JLabel PortLabel = new JLabel("Port:");
		PortPanel.add(PortLabel);
		
		PortField = new JTextField();
		PortField.setColumns(10);
		PortPanel.add(PortField);
		
		WinCount = new JLabel("0");
		GridBagConstraints gbc_WinCount = new GridBagConstraints();
		gbc_WinCount.insets = new Insets(0, 0, 5, 0);
		gbc_WinCount.gridx = 10;
		gbc_WinCount.gridy = 2;
		MasterPanel.add(WinCount, gbc_WinCount);
		
		JPanel GamePanel = new JPanel();
		GridBagConstraints gbc_GamePanel = new GridBagConstraints();
		gbc_GamePanel.gridheight = 8;
		gbc_GamePanel.gridwidth = 11;
		gbc_GamePanel.fill = GridBagConstraints.BOTH;
		gbc_GamePanel.gridx = 0;
		gbc_GamePanel.gridy = 3;
		MasterPanel.add(GamePanel, gbc_GamePanel);
		GridBagLayout gbl_GamePanel = new GridBagLayout();
		gbl_GamePanel.columnWidths = new int[]{295, 86, 61, 0, 0};
		gbl_GamePanel.rowHeights = new int[]{14, 0, 0, 0};
		gbl_GamePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_GamePanel.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		GamePanel.setLayout(gbl_GamePanel);
		
		JLabel OpponentStatusLabel = new JLabel("Opponent Status:");
		GridBagConstraints gbc_OpponentStatusLabel = new GridBagConstraints();
		gbc_OpponentStatusLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_OpponentStatusLabel.insets = new Insets(0, 0, 5, 5);
		gbc_OpponentStatusLabel.gridx = 1;
		gbc_OpponentStatusLabel.gridy = 0;
		GamePanel.add(OpponentStatusLabel, gbc_OpponentStatusLabel);
		
		OpponentStatus = new JLabel("NONE");
		OpponentStatus.setForeground(Color.RED);
		GridBagConstraints gbc_OpponentStatus = new GridBagConstraints();
		gbc_OpponentStatus.insets = new Insets(0, 0, 5, 5);
		gbc_OpponentStatus.anchor = GridBagConstraints.NORTHWEST;
		gbc_OpponentStatus.gridx = 2;
		gbc_OpponentStatus.gridy = 0;
		GamePanel.add(OpponentStatus, gbc_OpponentStatus);
		
		Panel OpponentPanel = new Panel();
		GridBagConstraints gbc_OpponentPanel = new GridBagConstraints();
		gbc_OpponentPanel.gridheight = 2;
		gbc_OpponentPanel.gridx = 3;
		gbc_OpponentPanel.gridy = 1;
		GamePanel.add(OpponentPanel, gbc_OpponentPanel);
		GridBagLayout gbl_OpponentPanel = new GridBagLayout();
		gbl_OpponentPanel.columnWidths = new int[]{48, 0};
		gbl_OpponentPanel.rowHeights = new int[]{14, 0, 0, 0, 0};
		gbl_OpponentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_OpponentPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		OpponentPanel.setLayout(gbl_OpponentPanel);
		
		JLabel OpponentLabel = new JLabel("Opponent");
		GridBagConstraints gbc_OpponentLabel = new GridBagConstraints();
		gbc_OpponentLabel.fill = GridBagConstraints.BOTH;
		gbc_OpponentLabel.insets = new Insets(0, 0, 5, 0);
		gbc_OpponentLabel.gridx = 0;
		gbc_OpponentLabel.gridy = 0;
		OpponentPanel.add(OpponentLabel, gbc_OpponentLabel);
		
		Panel OMove1Panel = new Panel();
		GridBagConstraints gbc_OMove1Panel = new GridBagConstraints();
		gbc_OMove1Panel.insets = new Insets(0, 0, 5, 0);
		gbc_OMove1Panel.gridx = 0;
		gbc_OMove1Panel.gridy = 1;
		OpponentPanel.add(OMove1Panel, gbc_OMove1Panel);
		
		Panel OMove2Panel = new Panel();
		GridBagConstraints gbc_OMove2Panel = new GridBagConstraints();
		gbc_OMove2Panel.insets = new Insets(0, 0, 5, 0);
		gbc_OMove2Panel.gridx = 0;
		gbc_OMove2Panel.gridy = 2;
		OpponentPanel.add(OMove2Panel, gbc_OMove2Panel);
		
		Panel OMove3Panel = new Panel();
		GridBagConstraints gbc_OMove3Panel = new GridBagConstraints();
		gbc_OMove3Panel.gridx = 0;
		gbc_OMove3Panel.gridy = 3;
		OpponentPanel.add(OMove3Panel, gbc_OMove3Panel);
		
		JLabel WinnerStatus = new JLabel("PLAYER");
		WinnerStatus.setForeground(Color.GREEN);
		GridBagConstraints gbc_WinnerStatus = new GridBagConstraints();
		gbc_WinnerStatus.gridwidth = 2;
		gbc_WinnerStatus.insets = new Insets(0, 0, 0, 5);
		gbc_WinnerStatus.gridx = 1;
		gbc_WinnerStatus.gridy = 2;
		GamePanel.add(WinnerStatus, gbc_WinnerStatus);
		
		Panel PlayerPanel = new Panel();
		GridBagConstraints gbc_PlayerPanel = new GridBagConstraints();
		gbc_PlayerPanel.gridheight = 2;
		gbc_PlayerPanel.insets = new Insets(0, 0, 0, 5);
		gbc_PlayerPanel.gridx = 0;
		gbc_PlayerPanel.gridy = 1;
		GamePanel.add(PlayerPanel, gbc_PlayerPanel);
		GridBagLayout gbl_PlayerPanel = new GridBagLayout();
		gbl_PlayerPanel.columnWidths = new int[]{18, 0};
		gbl_PlayerPanel.rowHeights = new int[]{14, 0, 0, 0, 0};
		gbl_PlayerPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_PlayerPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		PlayerPanel.setLayout(gbl_PlayerPanel);
		
		JLabel PlayerLabel = new JLabel("You");
		GridBagConstraints gbc_PlayerLabel = new GridBagConstraints();
		gbc_PlayerLabel.insets = new Insets(0, 0, 5, 0);
		gbc_PlayerLabel.fill = GridBagConstraints.BOTH;
		gbc_PlayerLabel.gridx = 0;
		gbc_PlayerLabel.gridy = 0;
		PlayerPanel.add(PlayerLabel, gbc_PlayerLabel);
		
		Panel YMove1Panel = new Panel();
		GridBagConstraints gbc_YMove1Panel = new GridBagConstraints();
		gbc_YMove1Panel.insets = new Insets(0, 0, 5, 0);
		gbc_YMove1Panel.gridx = 0;
		gbc_YMove1Panel.gridy = 1;
		PlayerPanel.add(YMove1Panel, gbc_YMove1Panel);
		
		Panel YMove2Panel = new Panel();
		GridBagConstraints gbc_YMove2Panel = new GridBagConstraints();
		gbc_YMove2Panel.insets = new Insets(0, 0, 5, 0);
		gbc_YMove2Panel.gridx = 0;
		gbc_YMove2Panel.gridy = 2;
		PlayerPanel.add(YMove2Panel, gbc_YMove2Panel);
		
		Panel YMove3Panel = new Panel();
		GridBagConstraints gbc_YMove3Panel = new GridBagConstraints();
		gbc_YMove3Panel.gridx = 0;
		gbc_YMove3Panel.gridy = 3;
		PlayerPanel.add(YMove3Panel, gbc_YMove3Panel);
		
		JLabel WinnerLabel = new JLabel("Winner");
		GridBagConstraints gbc_WinnerLabel = new GridBagConstraints();
		gbc_WinnerLabel.gridwidth = 2;
		gbc_WinnerLabel.insets = new Insets(0, 0, 5, 5);
		gbc_WinnerLabel.gridx = 1;
		gbc_WinnerLabel.gridy = 1;
		GamePanel.add(WinnerLabel, gbc_WinnerLabel);
	}

}
