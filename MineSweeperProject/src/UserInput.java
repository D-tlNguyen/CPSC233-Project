// This program runs MineSweeper.
// Authors: Milan Karan, Dominic Nguyen, Sean Stacey, Luyi Wang Tom and David Yu
// Last updated: 06/01/2017

import java.util.Scanner;

/**
   Precondition: "Main.java" must be function.
   Postcondition: Accepts user input for coordinate selection.
 */
public class UserInput {
	private Scanner input = new Scanner(System.in);    
	 public String[] getInput() {
	        System.out.print("Your Selections: ");
	        String dimensionSelection = input.nextLine();
	        String[] coordinate = dimensionSelection.split(",");
	        return coordinate;
	 }
}
