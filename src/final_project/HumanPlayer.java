package final_project;

import java.util.Scanner;

public class HumanPlayer extends Player {
	
	private Scanner in = new Scanner(System.in);
	
	public HumanPlayer() {
		super();
	}

	@Override
	public void nextMove() {
		
		String direction;
		
		do {
			System.out.print("Direction to move in: ");
			direction = in.next().toLowerCase();
		} while(! (direction.equals("left") || direction.equals("right")
				|| direction.equals("up") || direction.equals("down")));
		
		System.out.println();
		
		move(direction);
		
	}
}
