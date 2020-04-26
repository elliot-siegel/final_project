package final_project;

public abstract class ComputerPlayer extends Player {
	
	final String[] directions = {"left", "right", "up", "down"};
	
	public ComputerPlayer() {
		super();
	}
	
	@Override
	public void nextMove() {
		move(findBestMove());
	}
	
	public Box[] nextMoveSet() {
		Box[] boxes = new Box[4];
		for (int i = 0; i < boxes.length; i++) {
			boxes[i] = this.box.duplicate();
			boxes[i].moveSquares(this.directions[i]);
		}
		
		return boxes;
	}
	
	public abstract String findBestMove();

}
