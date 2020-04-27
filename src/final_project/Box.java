package final_project;

import java.util.Random;
import java.util.ArrayList;

public class Box {
	
	private int[][] array;
	private int totalSum;
	private boolean hasLost;
	
	  public Box() {
		  totalSum = 0;
		  hasLost = false;
		  array = new int[4][4];
	  }
	  
	  public Box(int[][] array) {
		  this();
		  
		  for(int i = 0; i < array.length; i++) {
			  for(int j = 0; j < array[i].length; j++) {
				  this.array[i][j] = array[i][j];
				  totalSum += array[i][j];
			  }
		  }
	  }
	  
	  public Box addRandomSquare() {
		  	
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
		  
	  		Random rand = new Random();
	  		int randNum = rand.nextInt(emptySpaces.size());
	  		int r1 = emptySpaces.get(randNum)[0];
	  		int c1 = emptySpaces.get(randNum)[1];

	                //2 or 4
	  		array[r1][c1] = (rand.nextInt(2) + 1) * 2;
	  		totalSum += array[r1][c1];

	  		return this;

	  	}
	  
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
						  array[index3][index4] = 0;
						  j++;
					  }
				  }
			  }
		  }
	  }
	  
	  public int setIndex(int a, int b, int axis, int order) {
		  if(axis == 1) {
			  if(order == 0) return a;
			  else return b;
		  } else {
			  if(order == 0) return b;
			  else return a;
		  }
	  }
	  
	  public Box duplicate() {
		  return new Box(this.array);
	  }
	  
	  public int emptySpaces() {
		  int count = 0;
		  
		  for(int i = 0; i < array.length; i++) {
			  for(int j = 0; j < array[i].length; j++) {
				  if(array[i][j] == 0) count++;
			  }
		  }
		  
		  return count;
	  }
	  
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
	  
	  public boolean hasLost() {
		  return this.hasLost;
	  }
	  
	  public int get(int r, int c) {
		  return array[r][c];
	  }
}
