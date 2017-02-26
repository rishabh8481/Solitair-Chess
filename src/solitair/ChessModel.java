package solitair;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author Sharma
 * ChessModel class performs following operations: -
 *   1. Initialize the Solitaire Chess by Loading the puzzle from the text to the console.
 *   2. Allows user to select the algorithm which is to be used for solving the problem form the 4 uninformed search and 2 informed search algorithms
 *   3. Perform the storing of the pieces in the puzzle in the desired format required for solving the algorithms.
 *   4. Calculate the possible moves for the piece which could allow the kill the piece (Neighbor) in single move.
 *   5. Perform the Display function to give the view of the solution state.
 *   6. Check whether the current state is goal state or not.
 */

public class ChessModel extends Puzzle<HashSet<ChessPiece>>{


	private ArrayList<HashSet<ChessPiece>> moves = new ArrayList<HashSet<ChessPiece>>();

	private static int rows;

	private static int cols;

	private HashSet<ChessPiece> currentconfig = new HashSet<ChessPiece>();

	/**
	 * @param r
	 * @param c
	 * @param config
	 * Constructor initialize the rows and columns and the current puzzle configuration.
	 */
	@SuppressWarnings("static-access")
	public ChessModel(int r, int c, HashSet<ChessPiece> config) {

		super.start = config;
		super.goal = 1;
		this.rows = r;
		this.cols = c;
		this.currentconfig = config;
		this.moves.add( config );
	}

	/**
	 * @param args
	 *  Initialize the puzzle state (i.e., Load the puzzle from the text file).
	 *  Allow the user to select the algorithm type for solving the problem.
	 *  Begins the Execution of the solution. 
	 */
	public static void main(String[] args){

		String gameFile = "Input.txt";
		BufferedReader reader;
		try {
			reader = new BufferedReader( new FileReader(new File(gameFile)));
		} catch ( FileNotFoundException e ) {
			System.out.println( gameFile + " not found.");
			return;
		}
		// used for storing all the pieces form the puzzle.
		HashSet<ChessPiece> pieces = new HashSet<ChessPiece>();
		// Read the input file and generate the data structure required for solving the chess problem
		// from the given stages of input.
		// B stands for BISHOP.
		// Q stands for QUEEN.
		// K stands for KING.
		// N stands for KNIGHT.
		// P stands for PAWN.
		// R stands for ROOK.
		try {
			String line = reader.readLine();
			String[] split = line.split( "\\s+" );
			int rows = 4;
			int cols = 4;
			int row = 0;
			int id = 0;
			while (line != null) {
				split = line.split( "\\s+" );
				for ( int col = 0; col < split.length; col++ ) {
					if ( split[col].compareTo( "B" ) == 0 ) {   
						Bishop bishop = new Bishop( row, col, id );
						pieces.add( bishop );
						id++;
					}
					if ( split[col].compareTo( "Q" ) == 0 ) {
						Queen queen = new Queen( row, col, id );
						pieces.add( queen );
						id++;
					}
					if ( split[col].compareTo( "K" ) == 0 ) {
						King king = new King( row, col, id );
						pieces.add( king );
						id++;
					}
					if ( split[col].compareTo( "N" ) == 0 ) {
						Knight knight = new Knight( row, col, id );
						pieces.add( knight );
						id++;
					}
					if ( split[col].compareTo( "P" ) == 0 ) {
						Pawn pawn = new Pawn( row, col, id );
						pieces.add( pawn );
						id++;
					}
					if ( split[col].compareTo( "R" ) == 0 ) {
						Rook rook = new Rook( row, col, id );
						pieces.add( rook );
						id++;
					}
				}
				line = reader.readLine();
				row++;
			}

			reader.close();

			ChessModel game = new ChessModel( rows, cols, pieces );
			// Display the initial state of the board ("Problem statement for the solitaire chess.")
			printBoard( pieces );
			ArrayList<HashSet<ChessPiece>> steps = null;
			Solver<HashSet<ChessPiece>> chessSolver = null;
			// Allows the user to provide the input for solving the SOLITAIRE CHESS using one of the 6 algorithms.
			System.out.println("Select the type of algorithm to be used for solving the game: ");
			System.out.println("1. DFS Algo");
			System.out.println("2. BFS Algo");
			System.out.println("3. Iterative Deepening Algo");
			System.out.println("4. Depth Limit Search Algo");
			System.out.println("5. A Star Search Algo");
			System.out.println("6. A Star Iterative Search Algo");
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			int option = scan.nextInt();
			switch (option){
			case 1:
				chessSolver = new DFS_Solver<HashSet<ChessPiece>>(game);
				break;
			case 2:
				chessSolver = new BFS_Solver<HashSet<ChessPiece>>(game);
				break;
			case 3:
				chessSolver = new IDDF_Solver<HashSet<ChessPiece>>(game);
				break;
			case 4:
				chessSolver = new DLS_Solver<HashSet<ChessPiece>>(game);
				break;
			case 5:
				chessSolver = new Astar_Solver<HashSet<ChessPiece>>(game);
				break;
			case 6:
				chessSolver = new AstarIterative_Solver<HashSet<ChessPiece>>(game);
				break;
			}
			// execute the solving method used by the algorithm to get the solution of the PUZZLE.
			try {
				steps = chessSolver.solve();
				if ( steps != null ) {
					for ( int i = 0; i < steps.size(); i++ ) {
						System.out.println( "Depth: " + i  + ": ");
						printBoard( steps.get( i ) );
					}
				} else {
					System.out.println( "No solution." );
				}
			} catch (NullPointerException e) {
			}
		}catch ( IOException e ) {
			e.printStackTrace();
			return;
		}
	}
	/**
	 * Returns a copy of the current configuration and set of all chess pieces in the game.
	 * @return
	 */
	public HashSet<ChessPiece> getConfig() {

		return Copy( this.currentconfig );
	}

	/**
	 * @param pieces
	 * @return
	 * 
	 * copies the configuration to be used again. Creates all new ChessPiece objects.
	 * config - set of all chess pieces in the game.
	 * copy - set of all chess pieces in the game
	 */
	public static HashSet<ChessPiece> Copy(HashSet<ChessPiece> pieces) {

		HashSet<ChessPiece> copy = new HashSet<ChessPiece>();
		for ( ChessPiece c : pieces ) {
			if ( c instanceof Bishop ) {
				Bishop piece = new Bishop( c.getLocation().x,
						c.getLocation().y, c.getID() );
				copy.add( piece );
			}
			if ( c instanceof Queen ) {
				Queen piece = new Queen( c.getLocation().x, c.getLocation().y,
						c.getID() );
				copy.add( piece );
			}
			if ( c instanceof King ) {
				King piece = new King( c.getLocation().x, c.getLocation().y,
						c.getID() );
				copy.add( piece );
			}
			if ( c instanceof Pawn ) {
				Pawn piece = new Pawn( c.getLocation().x, c.getLocation().y,
						c.getID() );
				copy.add( piece );
			}
			if ( c instanceof Knight ) {
				Knight piece = new Knight( c.getLocation().x,
						c.getLocation().y, c.getID() );
				copy.add( piece );
			}
			if ( c instanceof Rook ) {
				Rook piece = new Rook( c.getLocation().x, c.getLocation().y,
						c.getID() );
				copy.add( piece );
			}
		}

		return copy;

	}

	/**
	 * @return
	 * Returns the number of rows on the board
	 */
	public static int getRows() {

		return rows;
	}

	/**
	 * @return
	 * 
	 * Returns the number of columns on the board.
	 */
	public static int getCols() {

		return cols;
	}
	
	/**
	 * @return
	 * 
	 * Generate the visual view of the current state of the puzzle.
	 * 
	 */
	public static void printBoard(HashSet<ChessPiece> config) {

		String board[][] = new String[getRows()][getCols()];
		for ( int i = 0; i < getRows(); i++ ) {
			for ( int j = 0; j < getCols(); j++ ) {
				board[i][j] = ".";
			}
		}
		for ( ChessPiece c : config ) {
			board[c.getLocation().x][c.getLocation().y] = c.printBoardName();
		}
		System.out.println("=========================");
		System.out.println("=========================");
		for ( String[] row : board ) {
			for ( String piece : row ) {
				System.out.print( piece +"\t");
			}
			System.out.println();
		}
		System.out.println("=========================");
		System.out.println("=========================");
	}

	
	/* (non-Javadoc)
	 * @see solitair.Puzzle#getNeighbors(java.lang.Object)
	 * 
	 * Get the set of piece (Neighbor) which could be killed in just one move 
	 */
	@Override
	public ArrayList<HashSet<ChessPiece>> getNeighbors(HashSet<ChessPiece> config) {
		ArrayList<HashSet<ChessPiece>> neighbors = new ArrayList<HashSet<ChessPiece>>();

		for ( ChessPiece c : config ) {

			HashSet<ChessPiece> clone = Copy( config );

			ArrayList<HashSet<ChessPiece>> moves = c.getNeighbors(
					c.getMoves( clone ), clone );
			for ( HashSet<ChessPiece> neighbor : moves ) {
				neighbors.add( neighbor );
			}
		}

		return neighbors;
	}

	/* (non-Javadoc)
	 * @see solitair.Puzzle#isGoal(java.lang.Object)
	 * 
	 * Check whether the state is goal of the problem or not (i.e., the size of the problem is one or not.)
	 */
	@Override
	public boolean isGoal(HashSet<ChessPiece> config) {
		if ( config != null ) {
			if ( config.size() == 1 ) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
