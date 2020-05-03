package final_project;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * The HumanPlayer class extends Player class. It makes moves on its Box
 * by adding a keyListener to a Frame, and moving when the arrow keys are used
 *
 */

public class HumanPlayer extends Player implements KeyListener {
	
	private static final int LEFT_KEY = 37;
	private static final int UP_KEY = 38;
	private static final int RIGHT_KEY = 39;
	private static final int DOWN_KEY = 40;
	
	private String key;
	
	//canGo needs to be volatile, so that key events can make changes inside a while loop
	private volatile boolean canGo;
	Frame f;
	
	public HumanPlayer() {
		super();
	}
	
	public HumanPlayer(Frame f) {
		super();
		f.addKeyListener(this);
		this.f = f;
		canGo = false;
	}

	@Override
	public void nextMove() {
		
		String moveKey = null;
		while(!canGo) {
			moveKey = key;
			
			//Breaks the loop if the game mode changes or if a new game is started
			if(f.startNewGame() || f.getMode() != Game.PLAYNUM) {
				moveKey = "";
				break;
			}
		}
		
		canGo = false;
		if(!moveKey.equals("")) move(moveKey);
	}
	
	@Override
	public int getScore() {
		return this.box.sum();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		key = "";
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case LEFT_KEY: 
			key = "left"; 
			break;
		case UP_KEY: 
			key = "up"; 
			break;
		case RIGHT_KEY:
			key = "right"; 
			break;
		case DOWN_KEY: 
			key = "down"; 
			break;
		}
		
		if(!key.equals("")) canGo = true;
	}
}
