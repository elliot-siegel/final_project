package final_project;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * ComputerPlayer is an abstract class used for Players that make moves based on an algorithm
 *
 */

public abstract class ComputerPlayer extends Player {
	
	final String[] directions = {"left", "right", "up", "down"};
	private int numMoves;
	
	public ComputerPlayer() {
		super();
		numMoves = 0;
	}
	
	@Override
	public void nextMove() {
		move(findBestMove());
		numMoves++;
	}
	
	/**
	 * 
	 * @param b The initial box to find the next move set of
	 * @return The set of moves (right, left, up, or down) in a Map<String, Box>,
	 * that maps the move direction to the box it creates
	 */
	
	public Map<String, Box> nextMoveSet(Box b) {
		Map<String, Box> boxes = new HashMap<String, Box>();
		for (int i = 0; i < directions.length; i++) {
			Box newB = b.duplicate();
			newB.moveSquares(this.directions[i]);
			boxes.put(this.directions[i], newB);
		}
		
		return boxes;
	}
	
	@Override
	public int getScore() {
		return this.numMoves;
	}
	
	@Override
	public void initialize() {
		super.initialize();
		numMoves = 0;
	}
	
	/**
	 * Finds the best move for the player to make next
	 * @return the String value of the best move (right, left, up, or down)
	 */
	
	public abstract String findBestMove();

}
