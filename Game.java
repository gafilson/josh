package tictactoe;
import java.util.*;
import tictactoe.*;

/***A Game object is a single game of Tic Tac Toe
*It has 2 Player objects (either HumanPlayer or ComputerPlayer)
*a Board object to hold the current state of the board.
*and methods for creating players, and running a game.
*/
public class Game{
	public Player player1;
	public Player player2;
	public Player currentPlayer;
	public Board board;
	
	//constructor
	Game(){
	}
	
	public Player choosePlayer(char mark){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Player "+ mark + ": Human or CPU? [H/C]:");//Prompt for Human or CPU
		while (true){
			String input = scanner.nextLine();
			if (input.equalsIgnoreCase("H")){
				return new HumanPlayer(mark);
			}
			/*else if (input.equalsIgnoreCase("C")){
				return new ComputerPlayer(mark);
			}*/ //will activate this after I write ComputerPlayer class.
			else {
				System.out.print("\n Please enter \"H\" or \"C\":"); 
			}
		}
	}//end choosePlayer()
	
	public Player switchPlayer(Player currentPlayer){
		if (currentPlayer.equals(player1)){
			return player2;
		}
		else {
			return player1;
		}
	}//end switchPlayer()
	
	
	public void setRandomCurrentPlayer(){
		Random r = new Random();
		int i = r.nextInt();
		if (i%2 == 0){
			this.currentPlayer = this.player1;
		}
		else {
			this.currentPlayer = this.player2;
		}
	}
	
	
	/*The runGame method will work through an entire game.
	From creating players through end game conditions.*/
	public void runGame(){
		//Setup Section:
		board = new Board();
		this.player1 = choosePlayer('X');
		this.player2 = choosePlayer('O');
		Move proposedMove;
		MoveResult moveResult;
		
		
		setRandomCurrentPlayer();
		
		Outerloop: while (true){
			proposedMove = this.currentPlayer.getMove(board);//get input from Human or run algorithm for computer
			moveResult = board.submitMove(proposedMove);//check move against board to see if it's REJECTED, WINNING, etc.
			
			switch (moveResult) {
				case REJECTED: break; //If it's rejected (not a legal move) break and start while loop over.
				case WINNING: board.makeMove(proposedMove);
							  board.print();
							  System.out.println(currentPlayer.getMark() + " is the winner!");
							  break Outerloop; //ends current game.
				case CATS_GAME: board.print();
								System.out.println("Cat's Game");
							    break Outerloop; //ends current game.	
				case ACCEPTED: board.makeMove(proposedMove);
							   board.getLegalMoves().remove(proposedMove.getKey());//Remove the made move from the list of legal moves.
							   currentPlayer = switchPlayer(currentPlayer); //switch players, get new input.
			}
		}
	}
	
}	
