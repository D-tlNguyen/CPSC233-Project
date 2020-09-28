import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
/*
 * Contains Game();
 * 		-> Grid(), printWorld() in Grid.java
 * 		-> UserInput() in UserInput.java
 *
 */
// Authors: Milan Karan, Dominic Nguyen, Sean Stacey, Luyi Wang Tom and David Yu
// Last updated: 06/01/2017

/**
 * Precondition: Integers passed through (x, y, mines) parameters must be >= 0.
 * Postcondition: Mediates information from arguments passed through parameters
 * to other methods.
 */
public class Game {

	private static JFrame frame;
	private JPanel game;

	private MenuBar menuBar;
	// private static Game game;

	private final boolean DEBUG = true;
	private final int MINE = -1;
	private final int BLANK = 0;
	private Grid grid;
	private JButton[][] buttons;

	private JButton face;

	private int gridx, gridy, mines, minesLeft;
	private boolean isSingleMode;
	private boolean isAIMode;
	private int difficulty, mode;
	private boolean AICrazy;

	private Player player1;
	private Player player2;
	private ComputerPlayer computer;
	private boolean isPlayer1Turn = true;
	private boolean hasMoved = false;

	private JPanel mainPanel, topPanel, topCenter, bottomPanel, bottomCenter;
	private JLabel topLeft, middleLeft, middleRight, topRight, bottomLeft, bottomRight;

	public Game(int difficulty, int mode, boolean aiMode) {

		this.difficulty = difficulty;
		this.mode = mode;
		this.AICrazy = aiMode;

		switch (difficulty) {
		case 0:
			gridx = 9;
			gridy = 9;
			mines = 11;
			break;
		case 1:
			gridx = 16;
			gridy = 16;
			mines = 41;
			break;
		case 2:
			gridx = 30;
			gridy = 16;
			mines = 99;
			break;
		default:
			gridx = 9;
			gridy = 9;
			mines = 10;
		}

		switch (mode) {
		case 0: // Single Player, default
			isSingleMode = true;
			isAIMode = false;
			break;
		case 1: // Two human players
			isSingleMode = false;
			isAIMode = false;
			break;
		case 2: // Two player, one is AI
			isSingleMode = false;
			isAIMode = true;
			break;
		default:
			isSingleMode = true;
			isAIMode = false;
		}

		this.minesLeft = mines; // capacity control for mines
		if (DEBUG) {
			System.out.println("Mode:" + this.mode);

		}
		frame = new JFrame("MineSweeper²");
		game = new JPanel();
		game.setSize(400 * gridx / 9, 400 * gridy / 9);
		buttons = new JButton[gridy][gridx];
		game.setLayout(new GridLayout(gridy, gridx));
		grid = new Grid(gridx, gridy, mines); // dimensions of grid

		for (int i = 0; i < gridy; i++) {
			for (int j = 0; j < gridx; j++) {
				JButton button = new JButton();
				game.add(button);
				buttons[i][j] = button;
				button.addMouseListener(new ButtonManager(i, j));
			}
		}
		if (DEBUG) {
			System.out.println("Generating Face...");
		}
		ClassLoader cl = this.getClass().getClassLoader();
		this.face = new JButton(new ImageIcon(cl.getResource("img/face-smile.png")));
		this.face.addActionListener(new FaceManager());
		if (DEBUG) {
			System.out.println("Face generated");
		}

		player1 = new Player("Player 1");
		player2 = new Player("Player 2");
		computer = new ComputerPlayer("Bob Bot", aiMode);
		computer.maphack(grid.getWorld(), gridy, gridx);

		menuBar = new MenuBar();
		// Main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		frame.getContentPane().add(mainPanel);
		frame.setJMenuBar(menuBar.createMenuBar());

		// top Panel
		topPanel = new JPanel(new BorderLayout());
		mainPanel.add(topPanel, BorderLayout.NORTH);
		topLeft = new JLabel();
		if (mode != 0) {
			topLeft.setText("P1's Turn");
		} else {
			topLeft.setText("Single Player");
		}

		topPanel.add(topLeft, BorderLayout.WEST);
		topCenter = new JPanel(new BorderLayout());
		topPanel.add(topCenter);
		middleLeft = new JLabel();
		middleLeft.setText("            ");
		topCenter.add(middleLeft, BorderLayout.WEST);
		topCenter.add(face, BorderLayout.CENTER);
		middleRight = new JLabel();
		middleRight.setText("           ");
		topCenter.add(middleRight, BorderLayout.EAST);
		topRight = new JLabel();
		topRight.setText("Mines Left: " + getMinesLeft());
		topPanel.add(topRight, BorderLayout.EAST);

		// Middle panel
		mainPanel.add(game);

		// Bottom Panel
		bottomPanel = new JPanel(new BorderLayout());
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		bottomLeft = new JLabel();
		bottomLeft.setText("Player1 Score");
		bottomPanel.add(bottomLeft, BorderLayout.WEST);
		bottomCenter = new JPanel();
		bottomCenter.setBackground(Color.WHITE);
		bottomPanel.add(bottomCenter);
		bottomRight = new JLabel();
		bottomRight.setText("Player2 Score");
		bottomPanel.add(bottomRight, BorderLayout.EAST);
		if (mode > 0) {
			bottomLeft.setBackground(Color.pink);
			bottomLeft.setOpaque(true);
		}

		// JFrame Stuff
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400 * gridx / 9, 500 * gridy / 9);
		frame.setLocationRelativeTo(null);
		// frame.setResizable(false);
		frame.setVisible(true);

	}

	public static void reset() {
		frame.setVisible(false);
		frame = null;
	}

	/**
	 * mouseListener for left and right click;
	 * 
	 * @author Sean (06/11/2017)
	 */
	/*
	 * MouseListener mouseListener = new MouseAdapter() { // left button //
	 * functionality;
	 * 
	 * @Override public void mousePressed(MouseEvent e) { JButton button =
	 * (JButton) e.getSource(); // int col = (int)
	 * button.getClientProperty("column"); // identifies // column int row =
	 * (int) button.getClientProperty("row"); // and row clicked; int gridValue
	 * = grid.getCellInfo(row, col); int modifiers = e.getModifiers();
	 * 
	 * if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
	 * if (flagStatus.getFlag() != true) { // if not flag placed, // reveal;
	 * button.setEnabled(false); if (gridValue == -1) button.setText("X"); //
	 * button.setPressedIcon(mine.png); else if (gridValue == 0) {
	 * button.setText(Integer.toString(gridValue)); //
	 * button.setPressedIcon(0.png); clickAdj(row, col); } else {
	 * button.setText(Integer.toString(gridValue)); //
	 * button.setPressedIcon(numeral.png); } } else
	 * System.out.println("Must remove flag to press.");
	 * 
	 * }
	 * 
	 * if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
	 * // right // button // functionality; if (flagStatus.getFlag() == false) {
	 * flagStatus.setFlag(true); button.setText("!"); //
	 * button.setPressedIcon(flag.png); } else if (flagStatus.getFlag() == true)
	 * { flagStatus.setFlag(false); button.setText(" "); }
	 * 
	 * } } };
	 */

	private void uncover(int row, int col) {
		boolean firstMove;
		if (!hasMoved) {
			hasMoved = true;
			firstMove = true;
		} else {
			firstMove = false;
		}

		if (!this.buttons[row][col].isEnabled()) {
			if (DEBUG)
				System.out.println("Already Activated!");
			return;
		}
		ClassLoader cl = this.getClass().getClassLoader();
		Icon exploded = new ImageIcon(cl.getResource("img/bang.png"));
		Icon exploded2 = new ImageIcon(cl.getResource("img/warning.png"));
		Icon cool = new ImageIcon(cl.getResource("img/face-cool.png"));
		Icon sad = new ImageIcon(cl.getResource("img/face-sad.png"));

		if (!this.buttons[row][col].isEnabled() && !this.checkWon()) {
			// reset face icon
			this.face.setIcon(cool);
			return;
		}

		if (this.grid.hasFlag(row, col)) {
			return;
		}

		this.grid.reveal(row, col);

		this.buttons[row][col].setEnabled(false);

		// add the revealed image to button
		switch (this.grid.getCellInfo(row, col)) {
		case MINE:
			if (mode > 0) {
				if (isPlayer1Turn) {
					this.buttons[row][col].setIcon(exploded);
					this.buttons[row][col].setDisabledIcon(exploded);
				} else {
					this.buttons[row][col].setIcon(exploded2);
					this.buttons[row][col].setDisabledIcon(exploded2);
				}
				if (isPlayer1Turn) {
					player1.scored();
				} else {
					if (mode < 2) {
						player2.scored();
					} else {
						computer.scored();
					}
				}
				hasMoved = false;
				minesLeft--;
				update();
				return;
			} else {
				this.buttons[row][col].setIcon(exploded);
				this.buttons[row][col].setDisabledIcon(exploded);
				this.face.setIcon(sad);
				this.lose(row, col);
			}
			break;

		case BLANK:
			// propagate for blank squares
			if (isInsideGrid(row - 1, col - 1))
				this.uncover(row - 1, col - 1);

			if (isInsideGrid(row, col - 1))
				this.uncover(row, col - 1);

			if (isInsideGrid(row + 1, col - 1))
				this.uncover(row + 1, col - 1);

			if (isInsideGrid(row + 1, col + 1))
				this.uncover(row + 1, col + 1);

			if (isInsideGrid(row, col + 1))
				this.uncover(row, col + 1);

			if (isInsideGrid(row - 1, col + 1))
				this.uncover(row - 1, col + 1);

			if (isInsideGrid(row + 1, col))
				this.uncover(row + 1, col);

			if (isInsideGrid(row - 1, col))
				this.uncover(row - 1, col);

			this.face.setIcon(cool);
			if (mode == 0) {
				if (this.checkWon()) {
					win();
				}
			}
			break;

		default:
			this.buttons[row][col].setText(this.grid.getCellInfo(row, col) + "");
			this.face.setIcon(cool);
			if (mode == 0) {
				if (this.checkWon()) {
					win();
				}
			}
		}

		if (firstMove) {
			hasMoved = false;
			isPlayer1Turn = !isPlayer1Turn;
			if (DEBUG) {
				System.out.println("\t\t\tTurn switch");
			}
		}
		if (DEBUG) {
			if (isPlayer1Turn)
				System.out.println("It is now player1's turn");
			else
				System.out.println("It is now player2's turn");

			System.out.println("Player 1: " + player1.getScore() + "Player 2: " + player2.getScore());
		}
		update();
	}

	private void win() {
		ClassLoader cl = this.getClass().getClassLoader();
		Icon mine = new ImageIcon(cl.getResource("img/mine.png"));

		Icon win = new ImageIcon(cl.getResource("img/face-win.png"));
		this.face.setIcon(win);

		for (int i = 0; i < this.gridy; i++) {
			for (int j = 0; j < this.gridx; j++) {
				this.buttons[i][j].setEnabled(false);

				if (!this.grid.hasFlag(i, j) && this.grid.getCellInfo(i, j) == MINE) {
					this.buttons[i][j].setIcon(mine);
					this.buttons[i][j].setDisabledIcon(mine);
				}

			}
		}
		if (mode == 0) {
			JOptionPane info = new JOptionPane();
			info.showMessageDialog(null, "You win!");
		}

	}

	private void lose(int row, int col) {
		this.showAllMines(row, col);
	}

	private boolean checkWon() {
		if (mode == 0) {
			for (int i = 0; i < this.gridy; i++) {
				for (int j = 0; j < this.gridx; j++) {

					if (this.buttons[i][j].isEnabled() && this.grid.getCellInfo(i, j) != MINE) {
						return false;
					}
				}
			}
			return true;
		}
		return false;

	}

	private boolean checkLost() {
		for (int i = 0; i < this.gridy; i++) {
			for (int j = 0; j < this.gridx; j++) {
				if (mode == 0) {
					if (!this.buttons[i][j].isEnabled() && this.grid.getCellInfo(i, j) == MINE) {
						return true;
					}
				}

			}
		}
		return false;
	}

	public Grid getGrid() {
		return grid;
	}

	public int getMinesLeft() {
		return minesLeft;
	}

	public JButton[][] getButtons() {
		return buttons;
	}

	private boolean isInsideGrid(int row, int col) {
		return ((col >= 0 && col < this.gridx) && (row >= 0 && row < this.gridy));
	}

	private void showAllMines(int row, int col) {
		// show all mines EXCEPT for the one at row,col
		if (DEBUG)
			System.out.println("Row: " + row + "Col:" + col);

		ClassLoader cl = this.getClass().getClassLoader();
		Icon mine = new ImageIcon(cl.getResource("img/mine.png"));
		Icon badFlag = new ImageIcon(cl.getResource("img/warning.png"));

		for (int i = 0; i < this.gridy; i++) {
			for (int j = 0; j < this.gridx; j++) {

				this.buttons[i][j].setEnabled(false);

				if (this.grid.getCellInfo(i, j) == MINE && (!(i == row && j == col)) && !this.grid.hasFlag(i, j)) {
					// if has a mine and not a flag and is not the exploded mine
					this.buttons[i][j].setIcon(mine);
					this.buttons[i][j].setDisabledIcon(mine);
				} else if (this.grid.getCellInfo(i, j) != MINE && this.grid.hasFlag(i, j)) {
					// if a flag was misplaced
					this.buttons[i][j].setIcon(badFlag);
					this.buttons[i][j].setDisabledIcon(badFlag);
				}

			}
		}
	}

	private void toggleFlag(int row, int col) {

		this.grid.toggleFlag(row, col);

	}

	private void update() {

		if (mode != 0) {

			int p1 = player1.getScore();
			int p2 = player2.getScore();
			int comp = computer.getScore();

			bottomLeft.setText("Player 1: " + String.valueOf(p1));
			bottomRight.setText("Player 2: " + String.valueOf(p2));

			if (isPlayer1Turn) {
				topLeft.setText("P1's Turn");
				bottomLeft.setBackground(Color.pink);
				bottomLeft.setOpaque(true);
				bottomRight.setBackground(Color.gray);
				bottomRight.setOpaque(false);
			} else {
				topLeft.setText("P2's Turn");
				bottomLeft.setBackground(Color.gray);
				bottomLeft.setOpaque(false);
				bottomRight.setBackground(Color.pink);
				bottomRight.setOpaque(true);
			}

			if (p1 > mines / 2) {
				JOptionPane info = new JOptionPane();
				info.showMessageDialog(null, "Player 1 Wins!!! Get rekt");
				lose(gridy, gridx);

			} else if (p2 > mines / 2) {
				JOptionPane info = new JOptionPane();
				info.showMessageDialog(null, "Player 2 Wins!!! Get rekt");
				lose(gridy, gridx);
			}
		}

		topRight.setText("Mines Left: " + String.valueOf(minesLeft));

	}

	class ButtonManager implements MouseListener {
		private int row;
		private int col;

		public ButtonManager(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (checkLost() || checkWon()) {
				if (DEBUG) {
					System.out.println("You already lost!");
				}
				return;
			}

			if (e.getButton() == MouseEvent.BUTTON1) {
				// right mouse button uncovers that button

				if (DEBUG)
					System.out.println("Left mouse button pressed at coords " + this.row + "," + this.col);

				Game.this.uncover(this.row, this.col);
			}

			else if (e.getButton() == MouseEvent.BUTTON3) {
				// right mouse button toggles flag

				if (DEBUG)
					System.out.println("Right mouse button pressed at coords " + this.row + "," + this.col);
				if (mode > 0) {
					return;
				}

				// if button is disabled, do nothing
				if (!buttons[row][col].isEnabled()) {
					return;
				}

				Game.this.toggleFlag(this.row, this.col);

				if (grid.hasFlag(this.row, this.col)) {
					ClassLoader cl = this.getClass().getClassLoader();
					buttons[row][col].setIcon(new ImageIcon(cl.getResource("img/flag.png")));
					buttons[row][col].setDisabledIcon(new ImageIcon(cl.getResource("img/flag.png")));
					minesLeft--;
				} else {
					buttons[row][col].setIcon(null);
					minesLeft++;
				}
			}

			if (!buttons[row][col].isEnabled() && !checkWon()) {
				ClassLoader cl = this.getClass().getClassLoader();
				// reset face icon
				face.setIcon(new ImageIcon(cl.getResource("img/face-cool.png")));
				return;
			}

			update();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (checkLost() || checkWon()) {
				return;
			}

			if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON2) {
				ClassLoader cl = this.getClass().getClassLoader();
				Icon worried = new ImageIcon(cl.getResource("img/face-worried.png"));

				face.setIcon(worried);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}
	}

	class FaceManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Game.this.reset();
			new Game(difficulty, mode, AICrazy);
		}

	}

	public static void main(String[] args) {
		// System.out.println("Welcome to Minesweeper!");
		// Menu menu = new Menu();
		// menu.startMenu();

		new Game(0, 0, false);
	}

}
