package final_project;

public class Game {

	public static void main(String[] args) {
		
		Player p = new SimpleComp();
		while(!p.hasLost()) {
			p.nextMove();
			System.out.println();
		}

	}

}
