package tictactoe;

/***A Move object is a move of tic tac toe.
*it contains a String to be used for the board (HashMap)
*and a mark to make on that board.
*/

public class Move {
	private String key;
	private char mark;
	
	//Constructor
	public Move(String mapKey, char marking) {
		key = mapKey;
		mark = marking;
	}

	public String getKey() {
		return key;
	}
	
	public char getMark() {
		return mark;
	}
}
