package RockPaperScissors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ManagerGUI extends JFrame {
	public JTextField PortField;
	public JLabel StatusLabel;
	public JLabel P1StatusField;
	public JLabel P2StatusField;
	public BufferedImage rock;
	public BufferedImage paper;
	public BufferedImage scissors;
	public BufferedImage waiting;
	public JButton ServerButton;
	
	public ManagerGUI() {
		
		try {
		rock = ImageIO.read(new File("Assets/rock.svg"));
		paper = ImageIO.read(new File("Assets/paper.svg"));
		scissors = ImageIO.read(new File("Assets/scissors.svg"));
		waiting = ImageIO.read(new File("Assets/waiting.svg"));
		
	} catch (IOException e) {
		e.printStackTrace();
	}
		
		JPanel MasterPanel = new JPanel();
		getContentPane().add(MasterPanel, BorderLayout.NORTH);
		GridBagLayout gbl_MasterPanel = new GridBagLayout();
		gbl_MasterPanel.columnWidths = new int[]{0, 0};
		gbl_MasterPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_MasterPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_MasterPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		MasterPanel.setLayout(gbl_MasterPanel);
		
		JPanel InfoPanel = new JPanel();
		GridBagConstraints gbc_InfoPanel = new GridBagConstraints();
		gbc_InfoPanel.insets = new Insets(0, 0, 5, 0);
		gbc_InfoPanel.fill = GridBagConstraints.BOTH;
		gbc_InfoPanel.gridx = 0;
		gbc_InfoPanel.gridy = 0;
		MasterPanel.add(InfoPanel, gbc_InfoPanel);
		
		JLabel ConnectionLabel = new JLabel("Server Status: ");
		InfoPanel.add(ConnectionLabel);
		
		StatusLabel = new JLabel("OFFLINE");
		StatusLabel.setForeground(Color.RED);
		InfoPanel.add(StatusLabel);
		
		JPanel PortPanel = new JPanel();
		GridBagConstraints gbc_PortPanel = new GridBagConstraints();
		gbc_PortPanel.insets = new Insets(0, 0, 5, 0);
		gbc_PortPanel.fill = GridBagConstraints.BOTH;
		gbc_PortPanel.gridx = 0;
		gbc_PortPanel.gridy = 1;
		MasterPanel.add(PortPanel, gbc_PortPanel);
		
		JLabel PortLabel = new JLabel("Port:");
		PortPanel.add(PortLabel);
		
		PortField = new JTextField();
		PortPanel.add(PortField);
		PortField.setColumns(10);
		
		JPanel StatusPanel = new JPanel();
		GridBagConstraints gbc_StatusPanel = new GridBagConstraints();
		gbc_StatusPanel.insets = new Insets(0, 0, 5, 0);
		gbc_StatusPanel.fill = GridBagConstraints.BOTH;
		gbc_StatusPanel.gridx = 0;
		gbc_StatusPanel.gridy = 2;
		MasterPanel.add(StatusPanel, gbc_StatusPanel);
		
		JLabel P1StatusLabel = new JLabel("P1 Status: ");
		StatusPanel.add(P1StatusLabel);
		
		P1StatusField = new JLabel("NONE");
		P1StatusField.setForeground(Color.RED);
		StatusPanel.add(P1StatusField);
		
		ServerButton = new JButton("Start Server");
		StatusPanel.add(ServerButton);
		
		JLabel P2StatusLabel = new JLabel("P2Status");
		StatusPanel.add(P2StatusLabel);
		
		P2StatusField = new JLabel("CONNECTED");
		P2StatusField.setForeground(Color.RED);
		StatusPanel.add(P2StatusField);
		
		JPanel GamePanel = new JPanel();
		GridBagConstraints gbc_GamePanel = new GridBagConstraints();
		gbc_GamePanel.gridheight = 6;
		gbc_GamePanel.insets = new Insets(0, 0, 5, 0);
		gbc_GamePanel.fill = GridBagConstraints.BOTH;
		gbc_GamePanel.gridx = 0;
		gbc_GamePanel.gridy = 3;
		MasterPanel.add(GamePanel, gbc_GamePanel);
		GridBagLayout gbl_GamePanel = new GridBagLayout();
		gbl_GamePanel.columnWidths = new int[]{204, 10, 10, 0};
		gbl_GamePanel.rowHeights = new int[]{139, 0};
		gbl_GamePanel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_GamePanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		GamePanel.setLayout(gbl_GamePanel);
		
		JPanel P1Panel = new JPanel();
		GridBagConstraints gbc_P1Panel = new GridBagConstraints();
		gbc_P1Panel.anchor = GridBagConstraints.NORTHWEST;
		gbc_P1Panel.insets = new Insets(0, 0, 0, 5);
		gbc_P1Panel.gridx = 0;
		gbc_P1Panel.gridy = 0;
		GamePanel.add(P1Panel, gbc_P1Panel);
		GridBagLayout gbl_P1Panel = new GridBagLayout();
		gbl_P1Panel.columnWidths = new int[]{208, 0};
		gbl_P1Panel.rowHeights = new int[]{14, 28, 0, 0, 0};
		gbl_P1Panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_P1Panel.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		P1Panel.setLayout(gbl_P1Panel);
		
		JLabel P1Label = new JLabel("Player 1");
		P1Label.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_P1Label = new GridBagConstraints();
		gbc_P1Label.fill = GridBagConstraints.BOTH;
		gbc_P1Label.insets = new Insets(0, 0, 5, 0);
		gbc_P1Label.gridx = 0;
		gbc_P1Label.gridy = 0;
		P1Panel.add(P1Label, gbc_P1Label);
		
		JPanel P1Move1Panel = new JPanel();
		GridBagConstraints gbc_P1Move1Panel = new GridBagConstraints();
		gbc_P1Move1Panel.fill = GridBagConstraints.BOTH;
		gbc_P1Move1Panel.insets = new Insets(0, 0, 5, 0);
		gbc_P1Move1Panel.gridx = 0;
		gbc_P1Move1Panel.gridy = 1;
		P1Panel.add(P1Move1Panel, gbc_P1Move1Panel);
		
		JPanel P1Move2Panel = new JPanel();
		GridBagConstraints gbc_P1Move2Panel = new GridBagConstraints();
		gbc_P1Move2Panel.insets = new Insets(0, 0, 5, 0);
		gbc_P1Move2Panel.fill = GridBagConstraints.BOTH;
		gbc_P1Move2Panel.gridx = 0;
		gbc_P1Move2Panel.gridy = 2;
		P1Panel.add(P1Move2Panel, gbc_P1Move2Panel);
		
		JPanel P1Move3Panel = new JPanel();
		GridBagConstraints gbc_P1Move3Panel = new GridBagConstraints();
		gbc_P1Move3Panel.fill = GridBagConstraints.BOTH;
		gbc_P1Move3Panel.gridx = 0;
		gbc_P1Move3Panel.gridy = 3;
		P1Panel.add(P1Move3Panel, gbc_P1Move3Panel);
		
		JPanel P2Panel = new JPanel();
		GridBagConstraints gbc_P2Panel = new GridBagConstraints();
		gbc_P2Panel.anchor = GridBagConstraints.NORTHWEST;
		gbc_P2Panel.gridx = 2;
		gbc_P2Panel.gridy = 0;
		GamePanel.add(P2Panel, gbc_P2Panel);
		GridBagLayout gbl_P2Panel = new GridBagLayout();
		gbl_P2Panel.columnWidths = new int[]{184, 0};
		gbl_P2Panel.rowHeights = new int[]{14, 0, 0, 0, 0};
		gbl_P2Panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_P2Panel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		P2Panel.setLayout(gbl_P2Panel);
		
		JLabel P2Label = new JLabel("Player 2");
		P2Label.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_P2Label = new GridBagConstraints();
		gbc_P2Label.fill = GridBagConstraints.BOTH;
		gbc_P2Label.insets = new Insets(0, 0, 5, 0);
		gbc_P2Label.gridx = 0;
		gbc_P2Label.gridy = 0;
		P2Panel.add(P2Label, gbc_P2Label);
		
		JPanel P2Move1Panel = new JPanel();
		GridBagConstraints gbc_P2Move1Panel = new GridBagConstraints();
		gbc_P2Move1Panel.insets = new Insets(0, 0, 5, 0);
		gbc_P2Move1Panel.fill = GridBagConstraints.BOTH;
		gbc_P2Move1Panel.gridx = 0;
		gbc_P2Move1Panel.gridy = 1;
		P2Panel.add(P2Move1Panel, gbc_P2Move1Panel);
		
		JPanel P2Move2Panel = new JPanel();
		GridBagConstraints gbc_P2Move2Panel = new GridBagConstraints();
		gbc_P2Move2Panel.insets = new Insets(0, 0, 5, 0);
		gbc_P2Move2Panel.fill = GridBagConstraints.BOTH;
		gbc_P2Move2Panel.gridx = 0;
		gbc_P2Move2Panel.gridy = 2;
		P2Panel.add(P2Move2Panel, gbc_P2Move2Panel);
		
		JPanel P2Move3Panel = new JPanel();
		GridBagConstraints gbc_P2Move3Panel = new GridBagConstraints();
		gbc_P2Move3Panel.fill = GridBagConstraints.BOTH;
		gbc_P2Move3Panel.gridx = 0;
		gbc_P2Move3Panel.gridy = 3;
		P2Panel.add(P2Move3Panel, gbc_P2Move3Panel);
	}

}
