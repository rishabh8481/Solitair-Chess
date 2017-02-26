package solitair;

import java.util.ArrayList;
import java.util.HashSet;

public class Rook extends ChessPiece{

	public Rook(int row, int col, int id) {


		super.locale = new Tuple(row, col);
		super.piecename = "Rook";
		super.id = id;
	}
/*
 * (non-Javadoc)
 * @see java.lang.Comparable#compareTo(java.lang.Object)
 */
	@Override
	public int compareTo(ChessPiece o) {
		if ( this.id != o.id ) {
			return -1;
		} else if(locale.x != o.getLocation().x || locale.y != o.getLocation().y ) {
			return -1;
		} else if(piecename != o.piecename){
			return -1;
		}else{
			return 0;
		}
	}
/*
 * (non-Javadoc)
 * @see solitair.ChessPiece#printBoardName()
 * Use R as Rook on chess board
 */

	@Override
	public String printBoardName() {

		return "R";
	}

/*
 * (non-Javadoc)
 * @see solitair.ChessPiece#getMoves(java.util.HashSet)
 * Set of Legal moves for Rook on chess board
 * 
 */
	@Override
	public ArrayList<Tuple> getMoves(HashSet<ChessPiece> config) {
		ArrayList<Tuple> moves = new ArrayList<Tuple>();
		HashSet<ChessPiece> moveable = new HashSet<ChessPiece>();

		for ( ChessPiece c : config ) {
			if ( (c.getLocation().x == this.locale.x
					|| c.getLocation().y == this.locale.y) && c.compareTo( this ) != 0 ) {
				moveable.add( c );
			}

		}

		ChessPiece north = null;
		ChessPiece south = null;
		ChessPiece east = null;
		ChessPiece west = null;

		for ( ChessPiece c : moveable ) {
			// horizontal movement
			if ( c.getLocation().x == this.locale.x ) {
				int relative = c.getLocation().y - this.locale.y;
				if ( relative > 0 ) {
					// east
					if ( east == null
							|| east.getLocation().y > c.getLocation().y ) {
						east = c;
					}

				} else {
					// west
					if ( west == null
							|| west.getLocation().y < c.getLocation().y ) {
						west = c;
					}
				}
			}
			// vertical movement
			if ( c.getLocation().y == this.locale.y ) {
				int relative = c.getLocation().x - this.locale.x;
				if ( relative < 0 ) {
					// north
					if ( north == null
							|| north.getLocation().x < c.getLocation().x ) {
						north = c;
					}

				} else {
					// west
					if ( south == null
							|| south.getLocation().x > c.getLocation().x ) {
						south = c;
					}
				}
			}
		}

		if ( north != null ) {
			moves.add( north.getLocation() );
		}
		if ( south != null ) {
			moves.add( south.getLocation() );
		}
		if ( east != null ) {
			moves.add( east.getLocation() );
		}
		if ( west != null ) {
			moves.add( west.getLocation() );
		}

		return moves;
	}
}

