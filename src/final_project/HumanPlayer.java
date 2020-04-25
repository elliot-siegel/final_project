package final_project;

import java.util.Scanner;

public class HumanPlayer extends Player {
	
	public HumanPlayer() {
		super();
	}

	@Override
	public void nextMove() {
		
		Scanner in = new Scanner(System.in);
		String direction;
		
		do {
			System.out.print("Direction to move in: ");
			direction = in.next().toLowerCase();
		} while(! (direction.equals("left") || direction.equals("right")
				|| direction.equals("up") || direction.equals("down")));
		
		//this possibly closes all System.in scanners, so it could cause issues
		in.close();
		System.out.println();
		
		move(direction);
		
	}
}
