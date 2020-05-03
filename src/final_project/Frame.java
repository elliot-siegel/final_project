package final_project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 * 
 * A window that runs the user interface for the game. An extension of JFrame from java.swing.JFrame
 *
 */

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel boxPanel;
	private JPanel menu;
	private JLabel score;
	private JLabel simpleStats;
	private JLabel lookStats;
	private JLabel weightedStats;
	private SquarePanel[][] squares;
	
	private boolean newGame;
	private int mode;
	
	public Frame() {
		newGame = false;
		mode = Game.PLAYNUM;
		
		this.setTitle("2048");
		this.setLayout(new FlowLayout());
		
		createBox();
		createMenu();
		
		this.add(boxPanel, FlowLayout.LEFT);
		this.add(menu, FlowLayout.CENTER);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.requestFocus();
		
		this.setVisible(true);
	}
	
	/**
	 * Creates the left side of the user interface, which contains the representation of the Box
	 */
	public void createBox() {
		boxPanel = new JPanel(new GridLayout(4, 4));
		
		squares = new SquarePanel[4][4];
		
		for(int i = 0; i < squares.length; i++) {
			for(int j = 0; j < squares[i].length; j++) {
				squares[i][j] = new SquarePanel(new GridBagLayout(), 0);
				boxPanel.add(squares[i][j]);
			}
		}
	}
	
	/**
	 * Creates the right side of the user interface, which contains the controls for the program
	 */
	public void createMenu() {
		menu = new JPanel(new GridLayout(6, 1));
		menu.setPreferredSize(new Dimension(500, 500));
		
		score = new JLabel("Score: 0");
		menu.add(labelCreator(score));
		
		JButton newGameB = new JButton("New Game");
		
		newGameB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newGame = true;
				Frame.this.requestFocus();
			}	
		});
		menu.add(newGameB);
		
		JButton testAlgorithms = new JButton("Test Algorithms");
		testAlgorithms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mode = Game.TESTNUM;
				Frame.this.requestFocus();
			}	
		});
		menu.add(testAlgorithms);
		
		simpleStats = new JLabel("");
		menu.add(labelCreator(simpleStats));
		lookStats = new JLabel("");
		menu.add(labelCreator(lookStats));
		weightedStats = new JLabel("");
		menu.add(labelCreator(weightedStats));

	}
	
	/**
	 * @param label The label to be initialized
	 * @return The label formatted and inside a JPanel
	 */
	public JPanel labelCreator(JLabel label) {
		JPanel wrapper = new JPanel();
		wrapper.setBackground(Color.WHITE);
		label.setFont(new Font("Sans Serif", Font.BOLD, 25));
		wrapper.add(label);
		
		return wrapper;
	}
	
	/**
	 * Sets the score on the screen to the specified number
	 * @param score 
	 */
	public void setScore(int score) {
		this.score.setText("Score: " + Integer.toString(score));
	}
	
	/**
	 * 
	 * @return whether or not a new game should be started
	 */
	public boolean startNewGame() {
		return newGame;
	}
	
	/**
	 * Sets the value of the variable that represents whether or not a new game should be started
	 * @param b a boolean
	 */
	public void setNewGame(boolean b) {
		newGame = b;
	}
	
	/**
	 * Sets the mode of the game. 0 = algorithm testing, 1 = user play
	 * @param mode an integer
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	/**
	 * 
	 * @return The mode that the game is in
	 */
	public int getMode() {
		return this.mode;
	}
	
	public void setStats(String s1, String s2, String s3) {
		simpleStats.setText(s1);
		lookStats.setText(s2);
		weightedStats.setText(s3);
	}
	
	/**
	 * Sets the boxes in the user interface to show the given array of numbers
	 * @param box a 2D array of integers
	 */
	public void setBoxes(int[][] box) {

		for(int i = 0; i < box.length; i++) {
			for(int j = 0; j < box[i].length; j++) {
				
				//Sometimes squares will be skipped over and not set to the proper value; this prevents that
				while(box[i][j] != squares[i][j].getN()) {
					if(box[i][j] != 0) squares[i][j].setNum(box[i][j]);
					else squares[i][j].setNum(0);
				}
			}
		}
	}
	
	/**
	 * 
	 * An extension of the JPanel class. Used to create the squares shown on the screen.
	 *
	 */
	private class SquarePanel extends JPanel{

		private static final long serialVersionUID = 1L;
		private JLabel number;
		private LineBorder border;
		private int n;
		
		public SquarePanel(GridBagLayout b, int number) {
			super(b);
			this.setPreferredSize(new Dimension(150, 150));
			this.border = new LineBorder(Color.BLACK, 2, true);
			if(number != 0) this.number = new JLabel(Integer.toString(number));
			else this.number = new JLabel("");
			
			this.number.setFont(new Font("Sans Serif", Font.BOLD, 25));
			
			this.add(this.number);
			this.setBackground(new Color(192, 192, 192));
			this.setSquareColor(number);
			
			this.n = number;
		}
		
		/**
		 * Sets the number displayed in the SquarePanel to n. If n = 0 then no number is displayed
		 * @param n an integer
		 */
		public void setNum(int n) {
			if(n!=0) number.setText(Integer.toString(n));
			else number.setText("");
			this.setSquareColor(n);
			this.n = n;
		}
		
		public int getN() {
			return this.n;
		}
		
		/**
		 * Sets the color of the square based on n. These colors were found online, they are similar to the colors
		 * used in the mobile game 2048.
		 * @param n an integer
		 */
		private void setSquareColor(int n) {
			this.setBorder(this.border);
			
			switch (n) {
			case 0:
				this.setBackground(new Color(192, 192, 192));
				this.setBorder(null);
				break;
			case 2:
				this.setBackground(new Color(250, 240, 230));
				break;
			case 4:
				this.setBackground(new Color(252, 230, 202));
				break;
			case 8:
				this.setBackground(new Color(237, 145, 33));
				break;
			case 16:
				this.setBackground(new Color(255, 128, 0));
				break;
			case 32:
				this.setBackground(new Color(255, 99, 71));
				break;
			case 64:
				this.setBackground(new Color(255, 0, 0));
				break;
			case 128:
				this.setBackground(new Color(255, 227, 132));
				break;
			case 256:
				this.setBackground(new Color(255, 255, 0));
				break;
			case 512:
				this.setBackground(new Color(255, 215, 0));
				break;
			case 1024:
				this.setBackground(new Color(255, 215, 0));
				break;
			case 2048:
				this.setBackground(new Color(255, 215, 0));
				break;
			case 4096:
				this.setBackground(new Color(128, 42, 42));
				break;
			default:
				this.setBackground(Color.WHITE);
			}
		}
		
	}
	
  
}