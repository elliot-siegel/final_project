package final_project;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * LookTwice extends the ComputerPlayer class. Its algorithm finds the best move based on the number of
 * squares that have been combined after two moves (i.e. the number of squares combined after moving
 * left and then right). Whichever move direction can combine the most squares upon the second move is
 * considered the best.
 *
 */

public class LookTwice extends ComputerPlayer {

	public LookTwice() {
		super();
	}

	@Override
	public String findBestMove() {
		
		Map<String, Box> m1 = super.nextMoveSet(this.box);
		Map<String, Map<String, Box>> boxes = new HashMap<String, Map<String, Box>>();
		for(int i = 0; i < m1.size(); i++) {
			Map<String, Box> m2 = super.nextMoveSet(m1.get(directions[i]));
			boxes.put(directions[i], m2);
		}
		
		String direction = "left";
		int bestBox = 0;
		
		for(int i = 0; i < directions.length; i++) {
			
			String dir = directions[i];
			
			if(m1.get(dir).emptySpaces() >= bestBox) {
				bestBox = m1.get(dir).emptySpaces();
				direction = dir;
			}
			
			for(int j = 0; j < directions.length; j++) {
				String dir2 = directions[j];
				if(boxes.get(dir).get(dir2).emptySpaces() > bestBox) {
					bestBox = boxes.get(dir).get(dir2).emptySpaces();
					direction = dir;
				}
			}
		}
		
		return direction;
	}

}
