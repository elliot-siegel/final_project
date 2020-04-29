package final_project;

import java.util.Map;

public class WeightedComp extends SimpleComp {
	
	private static final int BOX_SIZE = 4;
	
	public WeightedComp() {
		super();
	}

	@Override
	public int getBoxScore(Map<String, Box> boxes, String dir) {
		int sum = 0;
		Box b = boxes.get(dir);
		String d = dir;
		Box comp = this.box.duplicate();
		comp.move((d == "left" || d == "up")?1:-1, (d == "left" || d == "right")?1:-1);
		
		for(int r = 0; r < BOX_SIZE; r++) {
			for(int c = 0; c < BOX_SIZE; c++) {
				if(b.get(r, c) == 0 && comp.get(r, c) != 0) sum += comp.get(r, c);
			}
		}
		return sum;
	}

}
