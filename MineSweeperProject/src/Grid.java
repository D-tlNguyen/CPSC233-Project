// This program runs MineSweeper.
// Authors: Milan Karan, Dominic Nguyen, Sean Stacey, Luyi Wang Tom and David Yu
// Last updated: 06/01/2017

import java.util.Random;

// Grid 
public class Grid {
    private final boolean DEBUG = false;

	private final int MINE = -1;
    //private int[][] world = null;
    
    private Cell[][] world;
    
    //boolean[][] worldReveal = null;
    private int mines;
    private int gridx, gridy;
    
    /**
     * Precondition: Integers passed through 'i' and 'j' must be >= 0.
     * 
     */
    public Grid(int x, int y, int mines) {
    	if (DEBUG)
    		System.out.println("x: "+x+" y: "+y+" mines:"+mines);
    	
    	this.gridx = x;
    	this.gridy = y;
    	this.mines = mines;
    	setWorld();
    }
	public int getGridX() {
		return this.gridx;
	}

	public int getGridY() {
		return this.gridy;
	}

	public int getMines() {
		return this.mines;
	}

    /**
     * Precondition: Integers used for 'gridy' and 'gridx' must be >= 0..
     * Postcondition: Defines tiles as either "mine" or "safe".
     */
    public void setWorld() {
    	world = new Cell[gridy][gridx];
        boolean first = true;
        int minesCount = mines;
	
        while (minesCount > 0) {
        	for (int i = 0; i < gridy; i++) {
        		for (int j = 0; j < gridx; j++) {
        			
        			if (first)
        				world[i][j] = new Cell();
        			
        			int roll = new Random().nextInt(gridx * gridy);
        			if ((roll < mines) && (minesCount > 0)) {
        				if (world[i][j].getInfo() != MINE) {
        					world[i][j].setInfo(MINE);
        					minesCount--;
        					if (DEBUG)
        						System.out.println("Mine put at "+j+","+i+" Value:"+world[i][j].getInfo());
        				}
        			}
        			else {
        				if (first)
        					world[i][j].setInfo(0);
        			}
        		}	
            }
        	first = false;
        	if (DEBUG)
            	System.out.println("Finished Once");
        }
        if (DEBUG) {
        	System.out.println("Finished Generating Mines");
        	printWorld();
        }
        for (int i = 0; i < gridy; i++) {
            for (int j = 0; j < gridx; j++) {
                if (world[i][j].getInfo() != MINE) {
                    int mineCount = 0;
                    if (mineCountAt(i-1, j-1))    // row above
                        mineCount++;
                    if (mineCountAt(i-1, j  ))
                        mineCount++;
                    if (mineCountAt(i-1, j+1))
                        mineCount++;
                    if (mineCountAt(i  , j-1))  	  // left and right of center
                        mineCount++;
                    if (mineCountAt(i  , j+1))
                        mineCount++;
                    if (mineCountAt(i+1, j-1))    // row below
                        mineCount++;
                    if (mineCountAt(i+1, j  ))
                        mineCount++;
                    if (mineCountAt(i+1, j+1))
                        mineCount++;
                    world[i][j].setInfo(mineCount);
                    if (DEBUG)
                    	System.out.println("Cell "+j+","+i+"set to"+mineCount);
                }
            }
        }
        if (DEBUG) {
        	System.out.println("Finished Generating World");
        	printWorld();
        }
    }
    /**
     * Precondition: Index 'i' and 'j' must be between 0 and the maximum value (of y and x coordinates, respectively).
     * Postcondition: Checks for mines adjacent of center tile.
     */
    private boolean mineCountAt(int row, int col) {
    	boolean mineSpawn = false;
        if (isInsideGrid(row,col)) {
        	if (world[row][col].getInfo() == MINE)
        		mineSpawn = true;
        }
        else
        	mineSpawn = false;
        
        return mineSpawn;
    }
    
    private boolean isInsideGrid(int row, int col) {
        return ((row >= 0 && row < gridy) && (col >= 0 && col < gridx));
    }
    
    /**
     * Precondition: Integers passed through 'gridx' and 'gridy' must be >= 0.
     * Postcondition: Displays the grid with all tiles revealed.
     */
    public void printWorld() {
    		for (int i = 0; gridx > i; i++)
    		    System.out.print(" _");
    		
    		System.out.println();
    		
    		for (int i = 0; gridy > i; i++) {
    		    System.out.print("|");
    		    for (int j = 0; gridx > j; j++) {
    		    	if (world[i][j].getInfo() == MINE)                    // spawns mine
    		    		System.out.print("*");
    		    	else
    		    		System.out.print(world[i][j].getInfo());    	  // spawns safe tile
    		    	System.out.print("|");
    		    	}
    		    System.out.println();
       		}		
    }	
    
    /**
     * Precondition: Integers passed through 'i' and 'j' must be >= 0.
     * Postcondition: Prints the skeleton of the grid. Mine data is generated and planted within grid.
     */
    public void printPlayerWorld() {
        for (int i = 0; gridx > i; i++) {
            System.out.print(" _");
        }
        
        System.out.println();
        
        for (int i = 0; gridy > i; i++) {
            System.out.print("|");
            for (int j = 0; gridx > j; j++) {
                if (world[i][j].getInfo() == -1) {
                    if (world[i][j].isRevealed())
                        System.out.print("x");
                    else
                        System.out.print("_");
                }
                else if (world[i][j].isRevealed())
                    System.out.print(world[i][j]);
                else
                    System.out.print("_");
                System.out.print("|");
            }
            System.out.println();
        }
    }
      
   


    /**
     * Precondition: Variables 'i' and 'j' must be between 0 and the maximum (of y and x, respectively).
     * Postcondition: Confirms user input is within acceptable boundaries.
     */



    /**
     * Precondition: Integers passed through 'i' and 'j' must be >= 0.
     * Postcondition: Reveals tile at coordinate specified by user.
     */
	public void reveal(int i, int j) {
		System.out.println("Revealing: " + j + "," + i);
		if (world[i][j].isRevealed() || world[i][j].isFlagged()) {
			if (DEBUG)
				System.out.println("Already activated or Flagged!");
			return;
		} 
		else {
			int reveal = world[i][j].reveal();
			if (world[i][j].getInfo() == -1) {
				System.out.println("You hit a mine!");
			}
			else {
				System.out.println("You hit a safe tile!");
				if (world[i][j].getInfo() == 0)
					revealAdj(i, j);
			}
		}
		return;
	}
	
	/**
	 * Precondition: Integers passed through 'i' and 'j' must be >= 0.
	 * Postcondition: Reveals tiles adjacent to center tile.
	 */
	private void revealAdj(int row, int col) {
		if (isInsideGrid(row - 1, col - 1))    // row above
			reveal(row - 1, col - 1);
		if (isInsideGrid(row - 1, col))
			reveal(row - 1, col);
		if (isInsideGrid(row - 1, col + 1))
			reveal(row - 1, col + 1);
		if (isInsideGrid(row, col - 1))       // left and right of center
			reveal(row, col - 1);
		if (isInsideGrid(row, col + 1))
			reveal(row, col + 1);
		if (isInsideGrid(row + 1, col - 1))   // row below 
			reveal(row + 1, col - 1);
		if (isInsideGrid(row + 1, col))
			reveal(row + 1, col);
		if (isInsideGrid(row + 1, col + 1))
			reveal(row + 1, col + 1);
	}
	
	public void toggleFlag(int row, int col) {
		
		if (this.isRevealed(row, col)) {
			if (DEBUG) {
				System.out.println("Cannot flag revealed tile");
			}
		}
		else {
			this.world[row][col].toggleFlag();
		}
		
		
	}
	
	public boolean isRevealed(int row, int col) {
		return this.world[row][col].isRevealed();
	}

	public boolean hasFlag(int row, int col)
	{	
		return this.world[row][col].isFlagged();
	}
	
	
	public int getCellInfo (int row, int col) {
		return world[row][col].getInfo();
	}
	
	public int getRow() {
		return gridy;
	}
	
	public int getColumn() {
		return gridx;
	}
	
	public Cell[][] getWorld() {
		return world;
	}
}
