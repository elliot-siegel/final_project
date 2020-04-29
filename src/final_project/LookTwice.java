package final_project;

import java.util.Map;

public class LookTwice extends ComputerPlayer {

	public LookTwice() {
		super();
	}

	@Override
	public String findBestMove() {
		
		Map<String, Box> m1 = super.nextMoveSet(this.box);
		Box[] set1 = new Box[directions.length];
		for(int i = 0; i < set1.length; i++) set1[i] = m1.get(directions[i]);
		Box[][] boxes = new Box[4][4];
		for(int i = 0; i < set1.length; i++) {
			Map<String, Box> m2 =  super.nextMoveSet(set1[i]);
			for(int j = 0; j < set1.length; j++) boxes[i][j] = m2.get(directions[j]);
		}
		
		String direction = "left";
		int bestBox = 0;
		
		for(int i = 0; i < boxes.length; i++) {
			
			if(set1[i].emptySpaces() >= bestBox) {
				bestBox = set1[i].emptySpaces();
				direction = super.directions[i];
			}
			
			for(int j = 0; j <boxes[i].length; j++) {
				if(boxes[i][j].emptySpaces() > bestBox) {
					bestBox = boxes[i][j].emptySpaces();
					direction = super.directions[i];
				}
			}
		}
		
		return direction;
	}

}
