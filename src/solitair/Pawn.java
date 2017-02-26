package solitair;

import java.util.ArrayList;
import java.util.HashSet;

public class Pawn extends ChessPiece{

	public Pawn(int row, int col, int id) {

		super.locale = new Tuple(row, col);
		super.piecename = "Pawn";
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
 * Use P as Pawn in chessboard
 */
	
	@Override
	public String printBoardName() {

		return "P";
	}
	
	/*
	 * (non-Javadoc)
	 * @see solitair.ChessPiece#getMoves(java.util.HashSet)
	 * 
	 * Legal Moves for Pawn 
	 */
	@Override
	public ArrayList<Tuple> getMoves(HashSet<ChessPiece> config) {
		
		
		ArrayList<Tuple> moves = new ArrayList<Tuple>();
		for ( ChessPiece c : config ) {
			
			//move northeast
			if(c.getLocation().x == this.locale.x-1 && c.getLocation().y == this.locale.y+1 ){
				
				moves.add(new Tuple(c.getLocation().x,c.getLocation().y));
			}
			//move northwest
			if(c.getLocation().x == this.locale.x-1 && c.getLocation().y == this.locale.y-1 ){
				moves.add(new Tuple(c.getLocation().x,c.getLocation().y));
			}

		}
		
		return moves;
	}

}
