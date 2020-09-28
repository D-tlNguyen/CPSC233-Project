
public class ComputerPlayer extends Player {
	
	private boolean aiCrazy;
	private Cell[][] maphack;
	
	public ComputerPlayer(String name, boolean aiCrazy) {
		super(name);
		this.aiCrazy = aiCrazy;
		// TODO Auto-generated constructor stub
	}
	
	public void maphack (Cell[][] maphack, int row, int col) {
		if(!aiCrazy)
			return;
		else
			this.maphack = maphack.clone();
		
	}

}
