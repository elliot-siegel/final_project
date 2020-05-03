package final_project;

/**
 * 
 * This class contains the main method and runs the game. The game is our own
 * version of the mobile game 2048.
 *
 */

public class Game {

	public static final int TESTNUM = 0;
	public static final int PLAYNUM = 1;
	private static final int TEST_ITERATIONS = 100;
	
	public static void main(String[] args) {
		
		//the user interface
		Frame f = new Frame();
		
		//the user controlled player
		Player pHuman = new HumanPlayer(f);
		
		f.setBoxes(pHuman.getBoxContents());
		f.setScore(pHuman.getScore());
		
		while(true) {
			
			//Reset the values of the user controlled player and the user interface
			if(f.startNewGame()) {
				pHuman.initialize();
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
	
	/**
	 * Tests the 3 computer algorithms
	 * @return an array of integers that represent the number of moves
	 * each algorithm was able to make before losing, on average
	 */
	public static int[] testAlgorithms() {
		int simpleAvg = testAlg(new SimpleComp());
		int lookAvg = testAlg(new LookTwice());
		int weightedAvg = testAlg(new WeightedComp());
		
		return new int[] {simpleAvg, lookAvg, weightedAvg};
	}
	
	/**
	 * 
	 * @param p a player
	 * @return The average number of moves p was able to make before losing
	 */
	public static int testAlg(Player p) {
		int avg;
		int score = 0;
		for(int i = 0; i < TEST_ITERATIONS; i++) {
			p.initialize();
			while(!p.hasLost()) {
				p.nextMove();
			}
			score += p.getScore();
		}
		
		avg = score / TEST_ITERATIONS;
		return avg;
	}

}
