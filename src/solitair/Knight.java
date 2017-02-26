package solitair;

import java.util.ArrayList;
import java.util.HashSet;

public class Knight extends ChessPiece{

	public Knight(int row, int col, int id) {

		super.locale = new Tuple(row, col);
		super.piecename = "Knight";
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
	 * Use N as Knight in Chess Board
	 */
	@Override
	public String printBoardName() {

		return "N";
	}
/*
 * (non-Javadoc)
 * @see solitair.ChessPiece#getMoves(java.util.HashSet)
 * Set of Legal moves for Knight
 */
	@Override
	public ArrayList<Tuple> getMoves(HashSet<ChessPiece> config) {

		ArrayList<Tuple> moves = new ArrayList<Tuple>();
		for ( ChessPiece c : config ) {
			int abs1 = Math.abs( c.getLocation().x - this.locale.x );
			int abs2 = Math.abs( c.getLocation().y - this.locale.y );

			if ( (abs1 == 1 && abs2 == 2) || (abs1 == 2 && abs2 == 1) ) {
				moves.add( new Tuple( c.getLocation().x, c.getLocation().y ) );
			}

		}

		return moves;
	}

}
