package tictactoe;

public interface Player{
	Move getMove(Board board);
	char getMark();
}
