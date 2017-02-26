package solitair;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class ChessPiece implements Comparable<ChessPiece> {

	protected Tuple locale;

	protected String piecename;

	protected int id;

	/*
	 * Prints the Chess Piece's board name;
	 * 
	 * @return String of the chess piece board name.
	 */
	public String printBoardName() {

		return piecename.substring( 0, 1 );
	}

	/*
	 * Returns the Chess Piece's id
	 * 
	 * @return int ID of Chess Piece
	 */
	public int getID() {

		return id;
	}

	/*
	 * Creates the toString representation of the Chess Piece.
	 * 
	 * @return String String representation of the chess piece.
	 */
	public String toString() {

		return "ID : " + id + " " + piecename + " : " + locale.toString()
				+ " hashcode: " + hashCode();

	}

	/*
	 * Move the piece to the location of another chess piece.
	 * 
	 * @param ChessPiece c - chess piece to move to and remove
	 * HashSet<ChessPiece> config - set of all chess pieces in the game.
	 */
	public HashSet<ChessPiece> movePiece(ChessPiece c,
			HashSet<ChessPiece> config) {

		this.locale = c.getLocation();
		config.remove( c );
		return config;
	}

	/*
	 * Generates all possible neighbors for a chesspiece and returns an array of
	 * the pieces.
	 * 
	 * @param ArrayList<Tuple> moves - list of possible move locations
	 * HashSet<ChessPiece> config - set of all chess pieces in the game.
	 * 
	 * @return ArrayList<HashSet<ChessPiece>> with all possible neighbor
	 * configs.
	 */
	public ArrayList<HashSet<ChessPiece>> getNeighbors(ArrayList<Tuple> moves,
			HashSet<ChessPiece> config) {

		ArrayList<HashSet<ChessPiece>> configs = new ArrayList<HashSet<ChessPiece>>();

		for ( Tuple move : moves ) {

			ChessPiece taken = null;
			ChessPiece taker = null;
			for ( ChessPiece c : config ) {
				if ( c.getLocation().compareTo( move ) == 0 ) {
					taken = c;
				}
				if ( c.getLocation().compareTo( this.locale ) == 0 ) {
					taker = c;
				}
			}
			if ( taker != null && taken != null ) {
				config = taker.movePiece( taken, config );
				configs.add( config );
			}

		}

		return configs;

	}

	/*
	 * Generates all possible move locations for the chess piece on the given
	 * board.
	 * 
	 * @param HashSet<ChessPiece> config - set of all chess pieces in the game.
	 * 
	 * @return ArrayList<Tuple> list of all move locations.
	 */
	public abstract ArrayList<Tuple> getMoves(HashSet<ChessPiece> config);

	/*
	 * Updates the ChessPiece's location to given location
	 * 
	 * @param Tuple location new location to update chess piece to.
	 */
	public void setLocation(Tuple location) {

		this.locale = location;
	}

	/*
	 * Returns the locatin of the Chess piece
	 * 
	 * @param - none.
	 * 
	 * @return Tuple location of chess piece.
	 */
	public Tuple getLocation() {

		return locale;
	}

	/*
	 * Creates a unique hashcode for the object based off the variables.
	 * 
	 * @return int generated hashcode value.
	 */
	@Override
	public int hashCode() {

		final int prime = 89;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		result = prime * result
				+ ((piecename == null) ? 0 : piecename.hashCode());
		return result;
	}

	/*
	 * Determines if a chess piece and a given object are equal or not.
	 * 
	 * @param Object obj - any object to compare to.
	 * 
	 * @return boolean if they are equal or not.
	 */
	@Override
	public boolean equals(Object obj) {

		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		ChessPiece other = (ChessPiece) obj;
		if ( id != other.id )
			return false;
		if ( locale == null ) {
			if ( other.locale != null )
				return false;
		} else if ( !locale.equals( other.locale ) )
			return false;
		if ( piecename == null ) {
			if ( other.piecename != null )
				return false;
		} else if ( !piecename.equals( other.piecename ) )
			return false;
		return true;
	}

}