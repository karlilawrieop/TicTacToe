import java.awt.BasicStroke;	// import BasicStroke Class
import java.awt.Color;			// import Color Class
import java.awt.Graphics;		// import Graphics Class
import java.awt.Graphics2D;		// import Graphics2D Class

public class Cell { // start of cell constructor
	
    //content of this cell (empty, cross, nought)
	Player content;
	//row and column of this cell
	int row, col;
	
	/** Constructor to initialise this cell with the specified row and col */
	public Cell(int row, int col) {
		
		// Initialise the variables row, col 
		this.row = row;
		this.col = col;

		// call the method that sets the cell content to EMPTY
		clear();
		 
	} // end of Cell constructor
	

	/** Paint itself on the graphics canvas, given the Graphics context g */ 
	public void paint(Graphics g) { // start of paint method
		
		//Graphics2D allows setting of pen's stroke size
		Graphics2D graphic2D = (Graphics2D) g;
		graphic2D.setStroke(new BasicStroke(GameMain.SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		//draw the symbol in the position
		int x1 = col * GameMain.CELL_SIZE + GameMain.CELL_PADDING; 				// column
		int y1 = row * GameMain.CELL_SIZE + GameMain.CELL_PADDING;				// rows
		
		// draws content for X player
		if (content == Player.CROSS) {
			graphic2D.setColor(Color.RED);
			int x2 = (col + 1) * GameMain.CELL_SIZE - GameMain.CELL_PADDING;
			int y2 = (row + 1) * GameMain.CELL_SIZE - GameMain.CELL_PADDING;
			graphic2D.drawLine(x1, y1, x2, y2);
			graphic2D.drawLine(x2, y1, x1, y2);
			
		// draws content for O player
		}else if (content == Player.NOUGHT) {
			graphic2D.setColor(Color.BLUE);
			graphic2D.drawOval(x1, y1, GameMain.SYMBOL_SIZE, GameMain.SYMBOL_SIZE);
		}
	} // end of paint method
	
	/** Set this cell's content to EMPTY */
	public void clear() { // start of clear method
		
	//Set the value of content to Empty enum in Player
		content = Player.EMPTY;
		
	} // end of clear method
		
} // end of Cell Class
