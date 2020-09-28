import javax.swing.*;
import javax.swing.JCheckBox;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class Settings {
	private static int difficulty = 0; //0 for easy, 1 for medium, 2 for hard
	private static int mode = 0; //0 for single, 1 for 2-player multiplayer, 2 for multiplayer against AI
	private static boolean aiMode = false;
	
	private JFrame settingsFrame = new JFrame("Settings");
	private JCheckBox aiCrazy = new JCheckBox("Make AI go crazy", false);
	private JRadioButton easyDifficulty = new JRadioButton("Easy", false);
	private JRadioButton mediumDifficulty = new JRadioButton("Medium", false);
	private JRadioButton hardDifficulty = new JRadioButton("Hard", false);
	private JRadioButton singlePlayer = new JRadioButton("Single Player", false);
	private JRadioButton multiPlayer = new JRadioButton("Multi Player", false);
	private JRadioButton multiPlayerAI = new JRadioButton("Multi Player AI", false);
	private JButton ok = new JButton("OK");
	private JButton cancel = new JButton("Cancel");

	public Settings() {
		JLabel title = new JLabel("Selections");
		ButtonGroup playerMode = new ButtonGroup();
		ButtonGroup difficulty = new ButtonGroup();
		ButtonGroup buttons = new ButtonGroup();
		difficulty.add(easyDifficulty);
		difficulty.add(mediumDifficulty);
		difficulty.add(hardDifficulty);
		playerMode.add(multiPlayer);
		playerMode.add(multiPlayerAI);
		playerMode.add(singlePlayer);
		buttons.add(ok);
		buttons.add(cancel);
		ok.addActionListener(new PressOk());
		cancel.addActionListener(new SettingsClose());
		easyDifficulty.addActionListener(new easySelected());
		mediumDifficulty.addActionListener(new mediumSelected());
		hardDifficulty.addActionListener(new hardSelected());
		aiCrazy.addActionListener(new aiCrazySelected());
		
		singlePlayer.addActionListener(new singlePlayerSelected());
		multiPlayer.addActionListener(new multiPlayerSelected());
		multiPlayerAI.addActionListener(new multiPlayerAISelected());
		
		aiCrazy.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		easyDifficulty.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		mediumDifficulty.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		hardDifficulty.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		singlePlayer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		multiPlayer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		multiPlayerAI.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		GroupLayout layout = new GroupLayout(settingsFrame.getContentPane());
		settingsFrame.getContentPane().setLayout(layout);
		easyDifficulty.setSelected(true);
		singlePlayer.setSelected(true);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(LEADING).addComponent(title).addComponent(singlePlayer)
						.addComponent(easyDifficulty))
				.addGroup(layout.createParallelGroup(LEADING).addComponent(aiCrazy)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(LEADING).addComponent(multiPlayer)
										.addComponent(mediumDifficulty))
								.addGroup(layout.createParallelGroup(LEADING).addComponent(multiPlayerAI)
										.addComponent(hardDifficulty))))
				.addGroup(layout.createParallelGroup(LEADING).addComponent(ok).addComponent(cancel)));
		layout.linkSize(SwingConstants.HORIZONTAL, ok, cancel);
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(BASELINE).addComponent(title).addComponent(aiCrazy).addComponent(ok))
				.addGroup(layout.createParallelGroup(LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(BASELINE).addComponent(singlePlayer)
										.addComponent(multiPlayer).addComponent(multiPlayerAI))
								.addGroup(layout.createParallelGroup(BASELINE).addComponent(easyDifficulty)
										.addComponent(mediumDifficulty).addComponent(hardDifficulty)))
						.addComponent(cancel)));
		settingsFrame.pack();
		settingsFrame.setVisible(true);
		settingsFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	private class aiCrazySelected implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JCheckBox tempCrazy = (JCheckBox)e.getSource();
			if (tempCrazy.isEnabled())
				aiMode = true;
			else
				aiMode = false;
		}
	}

	private class easySelected implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			difficulty = 0;
			System.out.println("Medium");
		}
	}

	private class mediumSelected implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			difficulty = 1;
			System.out.println("Medium");
		}
	}
	
	private class hardSelected implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			difficulty = 2;
			System.out.println("Medium");
		}
	}
	
	private class singlePlayerSelected implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mode = 0;
			System.out.println("Mode set to Single");
		}
	}
	private class multiPlayerSelected implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mode = 1;
			System.out.println("Mode set to Multi");
		}
	}
	private class multiPlayerAISelected implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			mode = 2;
			System.out.println("Mode set to MultiAI");
		}
	}

	private class SettingsClose implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Cancel Button Pressed");
			settingsFrame.dispose();
		}
	}

	private class PressOk implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("U pressed OK");
			settingsFrame.dispose();
			Game.reset();
			new Game(difficulty, mode, aiMode);
		}
	}

	public static int getMode() {
		return mode;
	}

	public static int getDifficulty() {
		return difficulty;
	}

	public static boolean getAiMode() {
		return aiMode;
	}
}