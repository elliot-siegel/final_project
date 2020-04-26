package final_project;

public abstract class Player {
	
	Box box;
	int score;
	
	public Player() {
		this.box = new Box();
		this.score = 0;
		box.addRandomSquare().addRandomSquare();
	}
	
	public void move(String direction) {
		box.moveSquares(direction);
		score = box.sum();
	}
	
	public abstract void nextMove();
}
