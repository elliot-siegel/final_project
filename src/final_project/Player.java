package final_project;

/**
 * 
 * The Player class is an abstract class that performs actions on 
 * a Box class in order to run the game of 2048
 *
 */

public abstract class Player {
	
	Box box;
	private boolean hasLost;
	
	public Player() {
		this.initialize();
	}
	
	public void move(String direction) {
		box.moveSquares(direction);
		box.addRandomSquare();
		hasLost = box.hasLost();
	}
	
	public boolean hasLost() {
		return this.hasLost;
	}
	
	/**
	 * 
	 * @return An array containing the contents of the player's box
	 */
	public int[][] getBoxContents() {
		return this.box.getArray();
	}
	
	/**
	 * This method resets a player object as if it had just been created
	 */
	
	public void initialize() {
		this.box = new Box();
		this.hasLost = false;
		box.addRandomSquare().addRandomSquare();
	}
	
	public abstract int getScore();
	
	public abstract void nextMove();
}
