package final_project;

import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * 
 * Represents the "box" that the game is played in. The box is a 2d array
 * of integers that this class performs actions on.
 *
 */

public class Box {
	
	private int[][] array;
	private int totalSum;
	private Set<Integer> containsNum;
	private boolean hasLost;
	
	/**
	 * Creates the box from scratch
	 */
	  public Box() {
		  totalSum = 0;
		  hasLost = false;
		  array = new int[4][4];
		  containsNum = new HashSet<Integer>();
	  }
	  
	  /**
	   * Creates a new box given a pre-existing array
	   * @param array an array of integers to be passed into the box's array
	   */
	  public Box(int[][] array) {
		  this();
		  containsNum = new HashSet<Integer>();
		  
		  for(int i = 0; i < array.length; i++) {
			  for(int j = 0; j < array[i].length; j++) {
				  this.array[i][j] = array[i][j];
				  totalSum += array[i][j];
				  containsNum.add(array[i][j]);
			  }
		  }
	  }
	  
	  /**
	   * Adds a 2 or a 4 to a random, empty space on the board
	   * @return returns itself so that multiple random squares can be added at once
	   */
	  
	  public Box addRandomSquare() {
		  	
		  	/* 
		  	 * Creates an ArrayList of empty spaces so that the random number generator
		  	 * doesn't have to guess multiple times
		  	 */
		  	ArrayList<int[]> emptySpaces = new ArrayList<int[]>();
		  	
		  	for(int i = 0; i < array.length; i++) {
		  		for(int j = 0; j < array[i].length; j++) {
		  			if(array[i][j] == 0) {
		  				int[] location = {i,j};
		  				emptySpaces.add(location);
		  			}
		  		}
		  	}
		  	
		  	if(emptySpaces.size() == 0) {
		  		this.hasLost = true;
		  		return this;
		  	}
		  	
		  	//Pick a random, empty space
		  
	  		Random rand = new Random();
	  		int randNum = rand.nextInt(emptySpaces.size());
	  		int r1 = emptySpaces.get(randNum)[0];
	  		int c1 = emptySpaces.get(randNum)[1];

	                //2 or 4
	  		array[r1][c1] = (rand.nextInt(2) + 1) * 2;
	  		totalSum += array[r1][c1];
	  		containsNum.add(array[r1][c1]);

	  		return this;

	  	}
	  
	  /**
	   * Shifts the squares in the board left, right, up or down and combines them accordingly
	   * @param direction The direction to move the squares in
	   */
	  
	  public void moveSquares(String direction) {
		  int axis, dir;
		  switch(direction) {
		  	case "left":
		  		dir = 1;
		  		axis = 1;
		  		break;
		  	case "right":
		  		dir = -1;
		  		axis = 1;
		  		break;
		  	case "up":
		  		dir = 1;
		  		axis = -1;
		  		break;
		  	default:
		  		dir = -1;
		  		axis = -1;
		  		break;
		  }
		  
		  move(dir, axis);
		  add(dir, axis);
		  move(dir, axis);
		  
	  }
	  
	  /**
	   * Moves the squares in a specific direction so that there are no "gaps" in the board
	   * @param dir Determines whether to start at the beginning or end of a row or column
	   * @param axis Determines if the squares are moving along the x or y axis
	   */
	  
	  public void move(int dir, int axis) {
		  for(int a = 0; a < array.length; a++) {
			  for(int j = 0; j < array.length; j++) {
				  
				  int b = (dir == 1) ? j : (array.length - 1) - j;
				  int index1 = setIndex(a, b, axis, 0);
				  int index2 = setIndex(a, b, axis, 1);
				  
				  if(array[index1][index2] == 0) {
					  for(int c = b + dir; c < array.length && c >= 0; c += dir) {
						  
						  int index3 = setIndex(a, c, axis, 0);
						  int index4 = setIndex(a, c, axis, 1);
						  
						  if(array[index3][index4] != 0) {
							  array[index1][index2] = array[index3][index4];
							  array[index3][index4] = 0;
							  break;
						  }
					  }
				  }
			  }
		  }
	  }
	  
	  /**
	   * Adds squares that have the same number if they are next to each other, 
	   * according to the direction that the squares are moving
	   * @param dir Determines whether to start at the beginning or end of a row or column
	   * @param axis Determines if the squares are moving along the x or y axis
	   */
	  
	  public void add(int dir, int axis) {
		  for(int a = 0; a < array.length; a++) {
			  for(int j = 0; j < array.length - 1; j++) {
				  
				  int b = (dir == 1) ? j : (array.length - 1) - j;
				  int index1 = setIndex(a, b, axis, 0);
				  int index2 = setIndex(a, b, axis, 1);
				  
				  if(array[index1][index2] != 0) {
					  int index3 = setIndex(a, b + dir, axis, 0);
					  int index4 = setIndex(a, b + dir, axis, 1);
				  
					  if(array[index1][index2] == array[index3][index4]) {
						  array[index1][index2] *= 2;
						  containsNum.add(array[index1][index2]);
						  array[index3][index4] = 0;
						  j++;
					  }
				  }
			  }
		  }
	  }
	  
	  /**
	   * In the add and move methods, <code>int a</code> corresponds to the rows of <code> array </code>
	   * and <code>int b</code> corresponds to the columns. If the board is moving up or down, this method
	   * reverses it, so <code>a</code> corresponds to columns and <code>b</code> to rows.
	   * @param a the first value
	   * @param b the second value
	   * @param axis the axis along which the squares are moving
	   * @param order 0 or 1. Whether the value being returned should
	   * 		correspond to a or b
	   * @return a or b, depending on the order and the axis
	   */
	  private int setIndex(int a, int b, int axis, int order) {
		  if(axis == 1) {
			  if(order == 0) return a;
			  else return b;
		  } else {
			  if(order == 0) return b;
			  else return a;
		  }
	  }
	  
	  /**
	   * 
	   * @return a new Box with the same values as this
	   */
	  
	  public Box duplicate() {
		  return new Box(this.array);
	  }
	  
	  /**
	   * 
	   * @return The number of empty spaces in the box
	   */
	  
	  public int emptySpaces() {
		  int count = 0;
		  
		  for(int i = 0; i < array.length; i++) {
			  for(int j = 0; j < array[i].length; j++) {
				  if(array[i][j] == 0) count++;
			  }
		  }
		  
		  return count;
	  }
	  
	  /**
	   * @return The total sum of the squares in the box
	   */
	  public int sum() {
		  return this.totalSum;
	  }
	  
	  public void print() {
		  for(int i = 0; i < array.length; i++) {
			  for(int j = 0; j < array[i].length; j++) {
				  System.out.printf("%5d", array[i][j]);
			  }
			  System.out.println();
			  System.out.println();
		  }
	  }
	  
	  /**
	   * @return if random squares are to be added to the box, but there are no available
	   * spaces, then the game is lost
	   */
	  public boolean hasLost() {
		  return this.hasLost;
	  }
	  
	  /**
	   * @param r row
 	   * @param c column
	   * @return the value in the box of that row and column
	   */
	  public int get(int r, int c) {
		  return array[r][c];
	  }
	  
	  /**
	   * @return The array representing the box
	   */
	  public int[][] getArray() {
		  int[][] newArray = new int[4][4];
		  
		  for(int i = 0; i < array.length; i++) {
			  for(int j = 0; j < array[i].length; j++) {
				  newArray[i][j] = array[i][j];
			  }
		  }
		  
		  return newArray;
	  }
	  
	  /**
	   * @param n a number
	   * @return whether or not n has been created in the game yet
	   */
	  public boolean containsNum(int n) {
		  return containsNum.contains(n);
	  }
}
