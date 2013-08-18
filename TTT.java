package tictactoe;

import java.util.*;

/***The main class for tic tac toe.
*main method will create new games and run them to completion
*until the user decides to not play again.
*/

class TTT{
	public static void main (String args[]){
		
		while (true){ //run until System.exit(0)
			Game game = new Game();
			game.runGame(); //Run the entire game from creating players to end.
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Do you want to play again? [Y/N]");
			while (true){
				String input = scanner.nextLine();
				if (input.equalsIgnoreCase("Y")){
					break;//returns to outerloop and plays another game.
				}
				else if (input.equalsIgnoreCase("N")){
					System.exit(0);//exits program.
				}
				else {
					System.out.print("\n Please enter \"Y\" or \"N\":");//tries for valid input again. 
				}
			}
		}
	}
 }
