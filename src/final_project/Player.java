package final_project;

public abstract class Player {
	
	Box box;
	private boolean hasLost;
	
	public Player() {
		this.box = new Box();
		this.hasLost = false;
		box.addRandomSquare().addRandomSquare();
	}
	
	public void move(String direction) {
		box.moveSquares(direction);
		box.addRandomSquare();
		hasLost = box.hasLost();
	}
	
	public boolean hasLost() {
		return this.hasLost;
	}
	
	public int[][] getBoxContents() {
		return this.box.getArray();
	}
	
	public abstract int getScore();
	
	public abstract void nextMove();
}
