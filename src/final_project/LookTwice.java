package final_project;

public class LookTwice extends ComputerPlayer {

	public LookTwice() {
		super();
	}

	@Override
	public String findBestMove() {
		
		Box[] set1 = super.nextMoveSet(this.box);
		Box[][] boxes = new Box[4][4];
		for(int i = 0; i < set1.length; i++) {
			boxes[i] = super.nextMoveSet(set1[i]);
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
		
		System.out.println(direction);
		return direction;
	}

}
