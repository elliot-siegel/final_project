package final_project;

public class SimpleComp extends ComputerPlayer {

	public SimpleComp() {
		super();
	}

	@Override
	public String findBestMove() {
		Box[] boxes = super.nextMoveSet();
		String direction;
		int bestBox = 16;
		
		for(int i = 0; i < boxes.length; i++) {
			if(boxes[i].filledSpaces() < bestBox) {
				bestBox = boxes[i].filledSpaces();
				direction = super.directions[i];
			}
		}
		
		return direction;
	}

}
