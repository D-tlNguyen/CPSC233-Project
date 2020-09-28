
public class Cell {

	private boolean DEBUG = false;

	private int info;
	private boolean hidden;
	private boolean flagged;

	public void setInfo(int info) {
		this.info = info;
	}

	public boolean isHidden() {
		return this.hidden;
	}

	public boolean isRevealed() {
		return !(this.hidden);
	}

	public int reveal() {
		this.hidden = false;
		return this.info;
	}

	public void hide() {
		this.hidden = true;
	}

	public int getInfo() {
		return this.info;
	}

	public void toggleFlag() {
		if (this.flagged) {
			if (DEBUG)
				System.out.println("Unflagging...");
			this.flagged = false;
		} else {
			if (DEBUG)
				System.out.println("Flagging...");
			this.flagged = true;
		}
	}

	public boolean isFlagged() {
		return this.flagged;
	}

	public Cell(int info, boolean hidden) {
		if (DEBUG)
			System.out.println("New Cell: "+info+", "+hidden);
		this.setInfo(info);

		if (hidden)
			this.hide();
		else
			this.reveal();

		this.flagged = false;
	}

	public Cell() {
		this(0, true);
	}

}
