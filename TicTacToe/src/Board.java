import java.awt.*; // import all java classes for creating GUI

public class Board { // start of Board class
	
	// grid line width
	public static final int GRID_WIDTH = 8;
	// grid line half width
	public static final int GRID_WIDTH_HALF = GRID_WIDTH / 2;
	
	//2D array of ROWS-by-COLS Cell instances
	Cell [][] cells;
	
	/** Constructor to create the game board */
	public Board() { // start of Board Constructor
		
	 // initialise the cells array using ROWS and COLS constants 
	cells = new Cell [GameMain.ROWS][GameMain.COLS]; // 
		
		for (int row = 0; row < GameMain.ROWS; ++row) { // Iterate through rows
			for (int col = 0; col < GameMain.COLS; ++col) { // Iterate through columns
				cells[row][col] = new Cell(row, col); // creates all 9 cells
			}
		}
	} // end of board constructor

	 /** Return true if it is a draw (i.e., no more EMPTY cells) */ 
	public boolean isDraw() { // start of isDraw method
		
		// Check whether the game has ended in a draw. 
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
		
		// check if player has 3-in-that-column	
		if(cells[0][playerCol].content == thePlayer && cells[1][playerCol].content == thePlayer && cells[2][playerCol].content == thePlayer )
			return true; 
		
		 // check if player has 3-in-the-diagonal from top left to bottom right
		if( cells[0][0].content == thePlayer && cells[1][1].content == thePlayer && cells[2][2].content == thePlayer)
			return true;
		
		// check if player has 3-in-the-diagonal from top right to bottom left
		if( cells[0][2].content == thePlayer && cells[1][1].content == thePlayer && cells[2][0].content == thePlayer)
			return true;
		
		//no winner, keep playing
		return false;
	} // end of hasWon method
	
	/** Draws the grid (rows then columns) using constant sizes, then call on the
	 * Cells to paint themselves into the grid */
	public void paint(Graphics g) {
		//draw the grid
		g.setColor(Color.gray);
		// draw the rows
		for (int row = 1; row < GameMain.ROWS; ++row) {          
			g.fillRoundRect(0, GameMain.CELL_SIZE * row - GRID_WIDTH_HALF,                
					GameMain.CANVAS_WIDTH - 1, GRID_WIDTH,                
					GRID_WIDTH, GRID_WIDTH);       
			}
		// draw the columns
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
