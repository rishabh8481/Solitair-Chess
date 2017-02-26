package solitair;

import java.util.ArrayList;
import java.util.HashSet;

public class King extends ChessPiece {

	public King(int row, int col, int id) {

		super.locale = new Tuple(row, col);
		super.piecename = "King";
		super.id = id;
	}

	/*
	 * Compares current chess piece to given chess piece object and compares all
	 * variables.
	 * 
	 * @param ChessPiece o - chess piece to compare to0
	 * 
	 * @return int -1 if not equal 0 if equal.
	 */
	@Override
	public int compareTo(ChessPiece o) {

		if ( this.id != o.id ) {
			return -1;
		} else if ( locale.x != o.getLocation().x
				|| locale.y != o.getLocation().y ) {
			return -1;
		} else if ( piecename != o.piecename ) {
			return -1;
		} else {
			return 0;
		}
	}
/*
 * 	(non-Javadoc)
 * @see solitair.ChessPiece#printBoardName()
 * Use K for King in Puzzle
 */
	@Override
	public String printBoardName() {

		return "K";
	}
/*
 * (non-Javadoc)
 * @see solitair.ChessPiece#getMoves(java.util.HashSet)
 * 
 * Legal Moves for King on Chess Board
 * 
 */
	@Override
	public ArrayList<Tuple> getMoves(HashSet<ChessPiece> config) {

		@SuppressWarnings("unchecked")
		HashSet<ChessPiece> clone = (HashSet<ChessPiece>) config.clone();
		ArrayList<Tuple> moves = new ArrayList<Tuple>();
		for ( ChessPiece c : clone ) {
			// south
			if ( c.getLocation().x == this.locale.x + 1
					&& c.getLocation().y == this.locale.y ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}
			// north
			if ( c.getLocation().x == this.locale.x - 1
					&& c.getLocation().y == this.locale.y ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}

			// east
			if ( c.getLocation().x == this.locale.x
					&& c.getLocation().y == this.locale.y + 1 ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}
			// west
			if ( c.getLocation().x == this.locale.x
					&& c.getLocation().y == this.locale.y - 1 ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}

			// southeast
			if ( c.getLocation().x == this.locale.x + 1
					&& c.getLocation().y == this.locale.y + 1 ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}

			// northeast
			if ( c.getLocation().x == this.locale.x - 1
					&& c.getLocation().y == this.locale.y + 1 ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}

			// northwest
			if ( c.getLocation().x == this.locale.x - 1
					&& c.getLocation().y == this.locale.y - 1 ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}
			// southwest
			if ( c.getLocation().x == this.locale.x + 1
					&& c.getLocation().y == this.locale.y - 1 ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}

		}
		// TODO Auto-generated method stub
		return moves;
	}

}
