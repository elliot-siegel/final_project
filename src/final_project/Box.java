package final_project;

import java.util.Random;

public class Box {
	
	private int[][] array;
	
	  public Box() {
		  	this.addRandomSquare().addRandomSquare();
	  }
	  
	  public Box addRandomSquare() {
	  		Random rand = new Random();
	  		int r1 = rand.nextInt(array.length);
	  		int c1 = rand.nextInt(array[r1].length);
	  		int r2, c2;
	                //select a place
	  		do {
	  			r2 = rand.nextInt(array.length);
	  			c2 = rand.nextInt(array[r1].length);
	  		} while (r1 == r2 && c1 == c2);
	                //2 or 4
	  		array[r1][c1] = (rand.nextInt(2) + 1) * 2;
	  		array[r2][c2] = (rand.nextInt(2) + 1) * 2;
	  		
	  		return this;

	  	}
}
