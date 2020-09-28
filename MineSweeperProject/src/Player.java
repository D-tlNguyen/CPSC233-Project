
public class Player {
	
	private int score;
	
	private String name;
	
	public Player(String name) {
		this.score = 0;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void scored() {
		score++;
	}
	
	public int getScore() {
		return score;
	}
	
	

}
