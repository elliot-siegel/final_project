package final_project;

public abstract class Player {
	
	Frame frame;
	int score;
	
	public Player() {
		this.frame = new Frame();
		this.score = 0;
		frame.addRandomSquare().addRandomSquare();
	}
	
	public void move(String direction) {
		frame.moveSquares(direction);
		score = frame.sum();
	}
	
	public abstract void nextMove();
}
