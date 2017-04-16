package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import engine.Tile.eTileState;
import gameSettings.Letter;

public class Board {
	public static final int MAX_SIZE = 50;
	public static final int MIN_SIZE = 5;
	private Tile[][] board;
	private int size;
	private List<Letter> nextTiles;
	
	public int getSize() {
		return size;
	}
	
	public Tile[][] getBoard() {
		return board;
	}
	
	public Board(int boardSize, List<Letter> nextChars) {
		nextTiles = nextChars;
		size = boardSize;
		board = new Tile[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = new Tile(getNextLetter(), i, j);
			}
		}
	}
	
	/*
	 * List of letters is sorted, so we get a random index and that way get a random letter
	 */
	private Letter getNextLetter() {
		Random random = new Random();
		int index = random.nextInt(nextTiles.size());
		
		Letter ret = nextTiles.get(index);
		nextTiles.remove(index);
		
		return ret;
	}

	public void flipTile(int n, int m, eTileState newState) throws IndexOutOfBoundsException {
		if ((n >= size) || (m >= size))
		{
			throw new IndexOutOfBoundsException("Out of board range");
		}
		
		board[n][m].setState(newState);
	}
	
	public int getTilesLeft() {
		return nextTiles.size();
	}
	
	public List<Tile> getFaceDownTiles() {
		List<Tile> ret = new ArrayList<Tile>();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j].getState() == eTileState.FACE_DOWN) {
					ret.add(board[i][j]);	
				}
			}
		}
		
		return ret;
	}

	public List<Tile> getFaceUpTiles() {
		List<Tile> ret = new ArrayList<Tile>();
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j].getState() == eTileState.FACE_UP) {
					ret.add(board[i][j]);	
				}
			}
		}
		
		return ret;
	}
	
	public void faceDownAllTiles() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j].setState(eTileState.FACE_DOWN);
			}
		}
		
	}
}
