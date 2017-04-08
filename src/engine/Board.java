package engine;

import java.util.List;

import engine.Tile.eTileState;

public class Board {
	public static final int MAX_SIZE = 50;
	public static final int MIN_SIZE = 5;
	private Tile[][] board;
	private int size;
	private List<Character> nextTiles;
	
	public int getSize() {
		return size;
	}
	
	public Tile[][] getBoard() {
		return board;
	}
	
	public Board(int n, List<Character> nextChars) {
		nextTiles = nextChars;
		board = new Tile[n][n];
	}
	
	public void flipTile(int n, int m, eTileState newState) throws IndexOutOfBoundsException {
		if ((n >= size) || (m >= size))
		{
			throw new IndexOutOfBoundsException("Out of board range");
		}
		
		board[n][m].setState(newState);
	}
}
