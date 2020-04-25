package final_project;

import java.util.Scanner;

public class HumanPlayer extends Player {
	
	public HumanPlayer() {
		super();
	}

	@Override
	public void nextMove() {
		System.out.print("Direction to move in: ");
		Scanner in = new Scanner(System.in);
		String direction = in.next();
		
		//this possibly closes all System.in scanners, so it could cause issues
		in.close();
		System.out.println();
		
		move(direction);
		
	}
}
