package final_project;

public abstract class Player {
	
	Box box;
	int score;
	private boolean hasLost;
	
	public Player() {
		this.box = new Box();
		this.hasLost = false;
		this.score = 0;
		box.addRandomSquare().addRandomSquare();
		box.print();
	}
	
	public void move(String direction) {
		box.moveSquares(direction);
		box.addRandomSquare();
		box.print();
		score = box.sum();
		hasLost = box.hasLost();
	}
	
	public boolean hasLost() {
		return this.hasLost;
	}
	
	public int[][] getBoxContents() {
		return this.box.getArray();
	}
	
	public abstract void nextMove();
}
