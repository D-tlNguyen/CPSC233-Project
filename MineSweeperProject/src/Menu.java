// This program runs MineSweeper.
// Authors: Milan Karan, Dominic Nguyen, Sean Stacey, Luyi Wang Tom and David Yu
// Last updated: 06/01/2017

import java.util.Scanner;

public class Menu {
	private Scanner input = new Scanner(System.in);
    
    /**
	Precondition: "Main.java" must call "Menu()".
	Postcondition: Initializes start menu.
     */
    public boolean startMenu() {
        boolean isRunning = true;	 // if false, execution of program stops.

        while (isRunning) {
            System.out.println("Please make your selection:");
            System.out.println("1. New Game ");
            System.out.println("2. Settings ");
            System.out.println("3. Quit");
            System.out.print("Selection: ");
            String selection = input.nextLine();
            if (selection.equals("1")) {
                playerMode();
            }
	    else if (selection.equals("2")) {
                settings();
            }
	    else if (selection.equals("3")) {
                System.out.print("Thank you for playing Minesweeper!!");
                isRunning = false;
            }
	    else {
                System.out.println("Invalid input! Please select one of the (3) options above.");
            }
        }
        return isRunning;
    }

    /**
       Precondition: "Start menu" navigation must be functional.
       Postcondition: 'Playermode' navigation menu is available to user. 
     */
    public void playerMode() {
        System.out.println("Please select player mode:");
        System.out.println("1. Single player");
        System.out.println("2. Multiplayer");
        System.out.println("3. Return to the previous menu");
        System.out.print("Selection: ");
        String playerSelection = input.nextLine();
        if (playerSelection.equals("1")) {
            singlePlayerDifficulty();
        }
	else if (playerSelection.equals("2")) {
            multiPlayerDifficulty();
        }
	else if (playerSelection.equals("3")) {
            return;
        }
	else {
            System.out.println("Invalid input! Please select one of the (3) options above.");
            playerMode();
        }
    }

    /**
       Precondition: "Start menu" navigation must be functional.
       Postcondition: 'Difficulty' navigation menu is available to user; grid size determined by user input.  
     */
    public void singlePlayerDifficulty() {
    	while (true) {
    		System.out.println("Please choose your Difficulty:");
    		System.out.println("1. Easy");
    		System.out.println("2. Normal");
    		System.out.println("3. Hard");
	    	System.out.println("4. Return to the previous menu");
	    	System.out.print("Selection: ");
	    	String difficultySelection = input.nextLine();
	    	if (difficultySelection.equals("1")) {
	    		//Game game = new Game(9, 9, 10);
	    		//game.play();
	    	}
	    	else if (difficultySelection.equals("2")) {
	    		//Game game = new Game(16, 16, 40);
	    		//game.play();
	    	}
	    	else if (difficultySelection.equals("3")) {
	    		//Game game = new Game(30, 16, 99);
	    		//game.play();
	    	}
	    	else if (difficultySelection.equals("4")) {
	    		return;
	    	}
	    	else {
	    		System.out.println("Invalid input! Please select one of the (4) options above.");
	    	}
    	}	
    }

    /**
       Will be implemented in a future iteration. Redundant.
    */
    public void multiPlayerDifficulty() {
		while (true) {
			System.out.println("Please choose your Difficulty:");
			System.out.println("1. Easy");
			System.out.println("2. Normal");
			System.out.println("3. Hard");
			System.out.println("4. Return to the previous menu");
			System.out.print("Selection: ");
			String difficultySelection = input.nextLine();
			if (difficultySelection.equals("1")) {
			    //Game game = new Game(9, 9, 10);    // (int x, int y, # of mines)
			    //game.play();
			}
			else if (difficultySelection.equals("2")) {
			    //Game game = new Game(16, 16, 40);
			    //game.play();
			}
			else if (difficultySelection.equals("3")) {
			   //Game game = new Game(30, 16, 99);
			    //game.play();
			}
			else if (difficultySelection.equals("4")) {
			    return;
			}
			else {
			    System.out.println("Invalid input! Please select one of the (4) options above.");
			}
		}
    }

    /**
       Precondition: "Start menu" navigation must be functional.
       Postcondition: "Settings" navigation menu is available to user.
     */
    public void settings() {
    	while (true) {
	    System.out.println("In settings, you can have the ability to adjust the difficulty\n of the game based on.");
	    System.out.println("Here are some options available: ");
            System.out.println("1. Grid dimension style");
            System.out.println("2. Make the AI go crazy.");
            System.out.println("3. Return to the previous menu");
            System.out.print("Selection: ");
            String settingsSelection = input.nextLine();
	    
	    if (settingsSelection.equals("1"))
            	System.out.println("*WILL BE IMPLEMENTED IN A FUTURE ITERATION*");
	    else if (settingsSelection.equals("2"))
            	System.out.println("*WILL BE IMPLEMENTED IN A FUTURE ITERATION*");
	    else if (settingsSelection.equals("3"))
		    return;
	    else
                System.out.println("Invalid input! Please select one of the (3) options above.");
    	}
    }
}


