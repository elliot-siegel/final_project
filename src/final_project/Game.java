package final_project;

public class Game {

	public static final int TESTNUM = 0;
	public static final int PLAYNUM = 1;
	private static final int TEST_ITERATIONS = 100;
	
	public static void main(String[] args) {
		
		Frame f = new Frame();
		Player pHuman = new HumanPlayer(f);
		f.setBoxes(pHuman.getBoxContents());
		
		while(true) {
			if(f.startNewGame()) {
				pHuman = new HumanPlayer(f);
				f.setBoxes(pHuman.getBoxContents());
				f.setNewGame(false);
				f.setScore(pHuman.getScore());
			}
			
			if(f.getMode() == PLAYNUM) {
				if(!pHuman.hasLost()) {
					f.requestFocus();
					pHuman.nextMove();
					f.setBoxes(pHuman.getBoxContents());
					f.setScore(pHuman.getScore());
				} else {
					f.setNewGame(true);
				}
			} else {
				int[] results = testAlgorithms();
				f.setMode(PLAYNUM);
				String l1 = "Simple Alg. Move Count: " + results[0];
				String l2 = "LookTwice Alg. Move Count: " + results[1];
				String l3 = "Weighted Alg. Move Count: " + results[2];
				f.setStats(l1, l2, l3);
			}
		}

	}
	
	public static int[] testAlgorithms() {
		int simpleAvg = testAlg(new SimpleComp());
		int lookAvg = testAlg(new LookTwice());
		int weightedAvg = testAlg(new WeightedComp());
		
		return new int[] {simpleAvg, lookAvg, weightedAvg};
	}
	
	public static int testAlg(Player p) {
		int avg;
		int score = 0;
		for(int i = 0; i < TEST_ITERATIONS; i++) {
			while(!p.hasLost()) {
				p.nextMove();
			}
			score += p.getScore();
		}
		
		avg = score / TEST_ITERATIONS;
		return avg;
	}

}
