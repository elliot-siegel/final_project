package final_project;

import java.util.Map;

/**
 * 
 * SimpleComp extends ComputerPlayer. It uses a simple algorithm that considers the number of
 * squares that will be combined if it moves in a given direction to find the best move.
 *
 */

public class SimpleComp extends ComputerPlayer {

	public SimpleComp() {
		super();
	}

	@Override
	public String findBestMove() {
		Map<String, Box> boxes = super.nextMoveSet(this.box);
		String direction = "left";
		int bestBox = 0;
		
		for(int i = 0; i < directions.length; i++) {
			
			int boxScore = getBoxScore(boxes, directions[i]);
			
			if(boxScore > bestBox) {
				bestBox = boxes.get(directions[i]).emptySpaces();
				direction = super.directions[i];
			}
		}
		
		return direction;
	}
	
	/**
	 * 
	 * @param boxes A Map<String, Box> of move directions with the boxes they create
	 * @param dir The direction to get the score of
	 * @return The score of the box
	 */
	public int getBoxScore(Map<String, Box> boxes, String dir) {
		return boxes.get(dir).emptySpaces();
	}

}
