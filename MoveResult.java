package tictactoe;

/***These are the 4 possible results after
*submitting a move to the board:
*REJECTED: Not a legal move (off the board, invalid string, or move was already made).
*WINNING: Move results in 3 in a row for the current player.
*CATS_GAME: Move does not result in 3 in a row, but is the last legal move.
*ACCEPTED: Anything else. A legal move that doesn't end the game.
*/

public enum MoveResult {
	REJECTED, WINNING, CATS_GAME, ACCEPTED;
}
