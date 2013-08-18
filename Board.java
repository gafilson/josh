package tictactoe;
import java.util.*;

/**A Board object is the board of Tic Tac Toe.
*It consists of a Map of 9 String keys ("1.1" to "3.3") corresponding to char values,
*a List of remaining moves (all 9 keys to start),
*and a List of 8 possible lines of 3-in-a-rows.
*/
public class Board{
	private Map <String, Character> boardMap = new HashMap<String, Character>();
	private List<String> legalMoves = new ArrayList<String>();
	private List<List<String>> lines = new ArrayList<List<String>>();
	
	//Constructor
	public Board(){
		List<String>diag1 = new ArrayList<String>();//top left to bottom right
		List<String>diag2 = new ArrayList<String>();//bottom left to top right
		
		for (int i=1; i <= 3; i++) {
			diag1.add(i + "." + i); //1.1, 2.2, and 3.3
			diag2.add(4 - i + "." + i);	//3.1, 2.2, and 1.3
			for (int j=1; j <= 3; j++) {	
				boardMap.put(i+"."+j, '-'); //Map of 9 keys, 1.1 to 3.3 and values set to '-'
				legalMoves.add(i + "." + j); //List of 9 possible moves(keys)
				
				//Add rows and columns
				if (i == 1) { // we're on a left edge so add a row
					List<String> line = new ArrayList<String>();
					for (int k=1; k <= 3; k++){
						line.add(j + "." + k);
					}	
					lines.add(line);
				}
				if (j == 1) { // we're on a top edge so add a column
					List<String> line = new ArrayList<String>();
					for (int k=1; k <= 3; k++){
						line.add(k + "." + i);
					}
					lines.add(line);
				}
			}	
		}
		lines.add(diag1);
		lines.add(diag2);
		
	}//end Constructor	
	
	public List<String> getLegalMoves(){
		return legalMoves;
	}//end getLegalMoves() Method
	
	public void print(){ //prints the current board.
		System.out.println(" " + boardMap.get("1.1") + " " + boardMap.get("1.2") + " " + boardMap.get("1.3"));
		System.out.println(" " + boardMap.get("2.1") + " " + boardMap.get("2.2") + " " + boardMap.get("2.3"));
		System.out.println(" " + boardMap.get("3.1") + " " + boardMap.get("3.2") + " " + boardMap.get("3.3"));
		System.out.println();
	}//end print() Method
	
	public MoveResult submitMove(Move proposedMove){
		String input = proposedMove.getKey();
		char mark = proposedMove.getMark();
		MoveResult moveResult = null;
		
		if (!(legalMoves.contains(input))){
			moveResult = MoveResult.REJECTED;
			return moveResult; //Move already taken, not on board, or not correct format.
		}
		for (List<String> line : lines){
			if (line.contains(input)){	
				if (boardMap.get(line.get((line.indexOf(input)+1)%3)).equals(mark) &&
				       boardMap.get(line.get((line.indexOf(input)+2)%3)).equals(mark)){
				
					moveResult = MoveResult.WINNING;	
					return moveResult; //other two marks in a line are the same.
				}
			}
		}
		if (legalMoves.size() == 1){
			moveResult = MoveResult.CATS_GAME;
			return moveResult;//If it's the last legal move and it wasn't WINNING.
		}
		else {
			moveResult = MoveResult.ACCEPTED;
			return moveResult;//If it's not REJECTED, WINNING or the last move.
		}
	}//end submitMove() Method
	
	public void makeMove(Move legalMove){
		boardMap.put(legalMove.getKey(),legalMove.getMark());
	}//end makeMove() Method
}
