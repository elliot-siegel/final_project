package final_project;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel boxPanel;
	SquarePanel[][] squares;
	
	public Frame() {
		this.setTitle("2048");
		this.setSize(500, 500);
		this.setLayout(new BorderLayout());
		boxPanel = new JPanel(new GridLayout(4, 4));
		
		squares = new SquarePanel[4][4];
		
		for(int i = 0; i < squares.length; i++) {
			for(int j = 0; j < squares[i].length; j++) {
				squares[i][j] = new SquarePanel();
				boxPanel.add(squares[i][j]);
			}
		}
		
		this.add(boxPanel, BorderLayout.CENTER);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		//drawUI();
	}
	
	private class SquarePanel extends JPanel{

		
		
	}
}