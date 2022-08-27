import java.awt.*;			// import all java classes for creating GUI
import java.awt.event.*;	// import interfaces and classes for event
import javax.swing.*;		// import package to create window based application

public class GameMain extends JPanel implements MouseListener{ // start of GameMain class
	
	// Constants for game 
	// Number of ROWS by COLS cell constants 
	public static final int ROWS = 3;    
	public static final int COLS = 3;  
	public static final String TITLE = "Tic Tac Toe";

	/**constants for dimensions used for drawing*/
	//cell width and height
	public static final int CELL_SIZE = 100;
	//drawing canvas
	public static final int CANVAS_WIDTH = CELL_SIZE * COLS;
	public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;
	//Noughts and Crosses are displayed inside a cell, with padding from border
	public static final int CELL_PADDING = CELL_SIZE / 6;    
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;    
	public static final int SYMBOL_STROKE_WIDTH = 8;
	
	/**declare game object variables*/
	// the game board 
	private Board board;
	// the currentState
	private GameState currentState; 
	// the currentPlayer
	private Player currentPlayer; 
	// for displaying game status message
	private JLabel statusBar;   	

	/** Constructor to setup the UI and game components on the panel */
	public GameMain() {   // start of constructor
		
		// This JPanel fires a MouseEvent on MouseClicked, add event listener to addMoustListner          	
		addMouseListener(this);
	    
		// Setup the status bar (JLabel) to display status message       
		statusBar = new JLabel("         ");       
		statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 14));       
		statusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));       
		statusBar.setOpaque(true);       
		statusBar.setBackground(Color.LIGHT_GRAY);  
		
		//layout of the panel is in border layout
		setLayout(new BorderLayout());       
		add(statusBar, BorderLayout.SOUTH);
		// account for statusBar height in overall height
		setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT + 30));
		
		// Create a new instance of the game "Board" by calling the constructor. 
		board = new Board();
		
		// Call the initGame method to initialise the game board
		initGame(); 

	} // end of gameMain constructor
	
	public static void main(String[] args) { // start of main method
		
		// Run GUI code in Event Dispatch thread for thread safety.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	        	 
				//create a main window to contain the panel
				JFrame frame = new JFrame(TITLE);
				// Create the new GameMain panel and add it to the frame
				GameMain panel = new GameMain();
				frame.add(panel);
				// set the default close operation of the frame to exit_on_close
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				// Sizes the frame
				frame.pack();             
				// Set location of window
				frame.setLocationRelativeTo(null);
				// makes frame appear on screen
				frame.setVisible(true);
	         }
		 });
	} // end of main method
	
	/** Custom painting codes on this JPanel */
	public void paintComponent(Graphics g) { // start of paintComponent
		
		//fill background and set colour to white
		super.paintComponent(g);
		setBackground(Color.WHITE);
		//ask the game board to paint itself
		board.paint(g);
		
		//set status bar message
		if (currentState == GameState.PLAYING) {          
			statusBar.setForeground(Color.BLACK);          
			if (currentPlayer == Player.CROSS) {   
			
				// Use the status bar to display the message "X"'s Turn
				statusBar.setText("'X' - your turn");

			} else {    
				
				// Use the status bar to display the message "O"'s Turn
				statusBar.setText("'O' - your turn");
				
			}       
				// Use the status bar to display it's a draw
			} else if (currentState == GameState.DRAW) {          
				statusBar.setForeground(Color.RED);          
				statusBar.setText("It's a Draw! Click to play again.");   
				// Use the status bar to display the message that X won
			} else if (currentState == GameState.CROSS_WON) {          
				statusBar.setForeground(Color.RED);          
				statusBar.setText("'X' Won! Click to play again.");    
				// Use the status bar to display the message that O won
			} else if (currentState == GameState.NOUGHT_WON) {          
				statusBar.setForeground(Color.RED);          
				statusBar.setText("'O' Won! Click to play again.");       
			}
		} // end of paintComponent
		
	
	  /** Initialise the game-board contents and the current status of GameState and Player) */
		public void initGame() { // start of initGame method
			
			for (int row = 0; row < ROWS; ++row) {          
				for (int col = 0; col < COLS; ++col) {  
					// all cells empty
					board.cells[row][col].content = Player.EMPTY;           
				}
			}
			// set gameState and Player at initialisation
			 currentState = GameState.PLAYING;
			 currentPlayer = Player.CROSS;
		} // end of initGame method
		
		
		/**After each turn check to see if the current player hasWon by putting their symbol in that position, 
		 * If they have the GameState is set to won for that player
		 * If no winner then isDraw is called to see if deadlock, if not GameState stays as PLAYING	 */
		public void updateGame(Player thePlayer, int row, int col) { // start of updateGame method
			// check which player has won and update currentState 
			if(board.hasWon(thePlayer, row, col)) {
								
				// X has won
				if (currentPlayer.equals(Player.CROSS)) {
					currentState = GameState.CROSS_WON; 
				}
				// O has won
				else if (currentPlayer.equals(Player.NOUGHT)) {
					currentState = GameState.NOUGHT_WON; 
					}
			} 
			// draw
			else if (board.isDraw ()) {
				currentState = GameState.DRAW;	
			}
			//otherwise no change to current state of playing
		} // end of updateGame method
		
		/** Event handler for the mouse click on the JPanel. If selected cell is valid and Empty then current player is added to cell content.
		 *  UpdateGame is called which will call the methods to check for winner or Draw. if none then GameState remains playing.
		 *  If win or Draw then call is made to method that resets the game board.  Finally a call is made to refresh the canvas so that new symbol appears*/
	
	public void mouseClicked(MouseEvent e) {  // start of mouseClicked method
		
	    // get the coordinates of where the click event happened            
		int mouseX = e.getX();             
		int mouseY = e.getY();          
		
		// Get the row and column clicked             
		int rowSelected = mouseY / CELL_SIZE;             
		int colSelected = mouseX / CELL_SIZE;               			
		if (currentState == GameState.PLAYING) {
			
			if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0 && colSelected < COLS && board.cells[rowSelected][colSelected].content == Player.EMPTY) {
				// move  
				board.cells[rowSelected][colSelected].content = currentPlayer; 
				// update currentState                  
				updateGame(currentPlayer, rowSelected, colSelected); 
				// Switch player
				if (currentPlayer == Player.CROSS) {
					currentPlayer =  Player.NOUGHT;
				}
				else {
					currentPlayer = Player.CROSS;
				}
			}             
		} else {        
			// game over and restart              
			initGame();            
		}   
		
		// redraw the graphics on the UI using repaint method 
		repaint(); 
           
	} // end of mouseClicked method
		
	
	@Override
	public void mousePressed(MouseEvent e) {
		//  Auto-generated, event not used
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//  Auto-generated, event not used
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// Auto-generated,event not used
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// Auto-generated, event not used
		
	}

} // end of GameMain class
