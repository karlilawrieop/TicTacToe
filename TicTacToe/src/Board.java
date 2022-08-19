/*
Board is the class that models the Tic-Tac-Toe game board.
The constructor initialises the Cell array that will hold the Cell positions and
then creates a new Cell object to store in each of the positions in the array.
isDraw() and hasWon()methods are used to update the Game Status
(GameState.'player'_won, GameState.draw). The paint()method handles
drawing the Board grid and calls the cell.paint()method so Cells are drawn
 */

import java.awt.*;

public class Board {
	// grid line width
	public static final int GRID_WIDTH = 8;
	// grid line half width
	public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
	
	//2D array of ROWS-by-COLS Cell instances
	Cell [][] cells;
	
	/** Constructor to create the game board */
	public Board() {
		
	 //TODO: !DONE initialise the cells array using ROWS and COLS constants 
	cells = new Cell [GameMain.ROWS][GameMain.COLS]; // 
		
		for (int row = 0; row < GameMain.ROWS; ++row) { // Iterate through rows
			for (int col = 0; col < GameMain.COLS; ++col) { // Iterate through columns
				cells[row][col] = new Cell(row, col); // creates all 9 cells
			}
		}
	} // end of board constructor
	

	 /** Return true if it is a draw (i.e., no more EMPTY cells) */ 
	public boolean isDraw() { // start of isDraw method
		
		// TODO: Check whether the game has ended in a draw. 
		// Hint: Use a nested loop (see the constructor for an example). 
		// Check whether any of the cells content in the board grid are Player.Empty. 
		// If they are, it is not a draw.
		// Hint: Return false if it is not a draw, return true if there are no empty positions left
		
		for (int row = 0; row < GameMain.ROWS; ++row) { // Iterate through rows
			for (int col = 0; col < GameMain.COLS; ++col) { // Iterate through columns
				if (cells[row][col].content == Player.EMPTY) { // checking all 9 cells are empty
					return false; // game not a draw
				}	
			} 	
		}
		return true; // game is a draw
	} // end of isDraw method
	
	/** Return true if the current player "thePlayer" has won after making their move  */
	public boolean hasWon(Player thePlayer, int playerRow, int playerCol) { // start of hasWon method
		 // check if player has 3-in-that-row
		if(cells[playerRow][0].content == thePlayer && cells[playerRow][1].content == thePlayer && cells[playerRow][2].content == thePlayer )
			return true; 
		
		 // TODO: !DONE Check if the player has 3 in the playerCol.
		 // Hint: Use the row code above as a starting point, remember that it goes cells[row][column] 
		if(cells[playerCol][0].content == thePlayer && cells[playerCol][1].content == thePlayer && cells[playerCol][2].content == thePlayer )
			return true;
		
		 // 3-in-the-diagonal
		if( cells[0][0].content == thePlayer && cells[1][1].content == thePlayer && cells[2][2].content == thePlayer)
			return true;
		 
		// TODO: !DONE Check the diagonal in the other direction
		if( cells[0][2].content == thePlayer && cells[1][1].content == thePlayer && cells[2][0].content == thePlayer)
			return true;
		
		//no winner, keep playing
		return false;
	} // end of hasWon method
	
	/**
	 * Draws the grid (rows then columns) using constant sizes, then call on the
	 * Cells to paint themselves into the grid
	 */
	public void paint(Graphics g) {
		//draw the grid
		g.setColor(Color.gray);
		for (int row = 1; row < GameMain.ROWS; ++row) {          
			g.fillRoundRect(0, GameMain.CELL_SIZE * row - GRID_WIDTH_HALF,                
					GameMain.CANVAS_WIDTH - 1, GRID_WIDTH,                
					GRID_WIDTH, GRID_WIDTH);       
			}
		for (int col = 1; col < GameMain.COLS; ++col) {          
			g.fillRoundRect(GameMain.CELL_SIZE * col - GRID_WIDTH_HALF, 0,                
					GRID_WIDTH, GameMain.CANVAS_HEIGHT - 1,                
					GRID_WIDTH, GRID_WIDTH);
		}
		
		//Draw the cells
		for (int row = 0; row < GameMain.ROWS; ++row) {          
			for (int col = 0; col < GameMain.COLS; ++col) {  
				cells[row][col].paint(g);
			}
		}
	} // end of paint method
	
} // end of board class
