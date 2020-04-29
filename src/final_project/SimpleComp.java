package final_project;

import java.util.Map;

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
	
	public int getBoxScore(Map<String, Box> boxes, String dir) {
		return boxes.get(dir).emptySpaces();
	}

}
