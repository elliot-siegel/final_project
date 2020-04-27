package final_project;

public class SimpleComp extends ComputerPlayer {

	public SimpleComp() {
		super();
	}

	@Override
	public String findBestMove() {
		Box[] boxes = super.nextMoveSet(this.box);
		String direction = "left";
		int bestBox = 0;
		
		for(int i = 0; i < boxes.length; i++) {
			
			int boxScore = getBoxScore(boxes, i);
			
			if(boxScore > bestBox) {
				bestBox = boxes[i].emptySpaces();
				direction = super.directions[i];
			}
		}
		
		System.out.println(direction);
		return direction;
	}
	
	public int getBoxScore(Box[] boxes, int i) {
		return boxes[i].emptySpaces();
	}

}
