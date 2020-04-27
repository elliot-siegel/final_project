package final_project;

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
	
	public Box[] nextMoveSet(Box b) {
		Box[] boxes = new Box[4];
		for (int i = 0; i < boxes.length; i++) {
			boxes[i] = b.duplicate();
			boxes[i].moveSquares(this.directions[i]);
		}
		
		return boxes;
	}
	
	public abstract String findBestMove();

}
