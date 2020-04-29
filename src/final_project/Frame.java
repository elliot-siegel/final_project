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
	
	public JPanel labelCreator(JLabel label) {
		JPanel wrapper = new JPanel();
		wrapper.setBackground(Color.WHITE);
		label.setFont(new Font("Sans Serif", Font.BOLD, 25));
		wrapper.add(label);
		
		return wrapper;
	}
	
	public void setScore(int score) {
		this.score.setText("Score: " + Integer.toString(score));
	}
	
	public boolean startNewGame() {
		return newGame;
	}
	
	public void setNewGame(boolean b) {
		newGame = b;
	}
	
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	public int getMode() {
		return this.mode;
	}
	
	public void setStats(String s1, String s2, String s3) {
		simpleStats.setText(s1);
		lookStats.setText(s2);
		weightedStats.setText(s3);
	}
	
	public void setBoxes(int[][] box) {

		for(int i = 0; i < box.length; i++) {
			for(int j = 0; j < box[i].length; j++) {
				while(box[i][j] != squares[i][j].getN()) {
					if(box[i][j] != 0) squares[i][j].setNum(box[i][j]);
					else squares[i][j].setNum(0);
				}
			}
		}
	}
	
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
		
		public void setNum(int n) {
			if(n!=0) number.setText(Integer.toString(n));
			else number.setText("");
			this.setSquareColor(n);
			this.n = n;
		}
		
		public int getN() {
			return this.n;
		}
		
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