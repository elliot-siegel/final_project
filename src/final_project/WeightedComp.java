package final_project;

import java.util.Map;

/**
 * 
 * Extends SimpleComp so that the box score is calculated based on the sum of the numbers that get combined,
 * which effectively weights larger numbers being combined more heavily
 *
 */

public class WeightedComp extends SimpleComp {
	
	private static final int BOX_SIZE = 4;
	private static final double WEIGHT = 1.5;
	
	public WeightedComp() {
		super();
	}

	/**
	 * Calculates the score based on the sum of the numbers that get combined. If new numbers have
	 * been created, their weight is higher.
	 */
	@Override
	public int getBoxScore(Map<String, Box> boxes, String dir) {
		int sum = 0;
		Box b = boxes.get(dir);
		String d = dir;
		Box comp = this.box.duplicate();
		comp.move((d == "left" || d == "up")?1:-1, (d == "left" || d == "right")?1:-1);
		
		for(int r = 0; r < BOX_SIZE; r++) {
			for(int c = 0; c < BOX_SIZE; c++) {
				if(b.get(r, c) == 0 && comp.get(r, c) != 0) {
					
					// If a new number is created, its weight doubles.
					if(comp.containsNum(b.get(r, c))) sum += comp.get(r, c);
					else sum += comp.get(r, c) * WEIGHT;
				}
			}
		}
		return sum;
	}

}
