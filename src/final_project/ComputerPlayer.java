package final_project;

import java.util.HashMap;
import java.util.Map;

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
	
	public abstract String findBestMove();

}
