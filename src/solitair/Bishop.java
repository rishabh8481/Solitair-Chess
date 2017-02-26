package solitair;

import java.util.ArrayList;
import java.util.HashSet;

public class Bishop extends ChessPiece{
	/*
	 * Creates a bishop object. 
	 * 
	 * @param int x - x row location
	 * int y - y col location
	 * int id - unique id for piece. 
	 */
	public Bishop(int row, int col, int id) {

		super.locale = new Tuple(row, col);
		super.piecename = "Bishop";
		super.id = id;
	}

	/*
	 * Compares current chess piece to given chess piece object and compares all variables. 
	 *
	 * @param ChessPiece o - chess piece to compare to0
	 * 
	 * @return int -1 if not equal 0 if equal. 
	 *
	 */
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

	
	@Override
	public String printBoardName() {

		return "B";
	}
/*
 * 	(non-Javadoc)
 * @see solitair.ChessPiece#getMoves(java.util.HashSet)
 * Get all the Legal moves for Bishop
 */
	@Override
	public ArrayList<Tuple> getMoves(HashSet<ChessPiece> config) {
	
		ArrayList<Tuple> moves = new ArrayList<Tuple>();
		//south east
		HashSet<ChessPiece> moveablese = new HashSet<ChessPiece>();
		//north east
		HashSet<ChessPiece> moveablene = new HashSet<ChessPiece>();
		//north west
		HashSet<ChessPiece> moveablenw = new HashSet<ChessPiece>();
		//south west
		HashSet<ChessPiece> moveablesw = new HashSet<ChessPiece>();
		
		for ( ChessPiece c : config ) {
			int tempx = this.locale.x + 1;
			int tempy = this.locale.y + 1;
			while ( tempx < ChessModel.getRows()
					&& tempy < ChessModel.getCols() ) {
				if ( c.getLocation().x == tempx && c.getLocation().y == tempy ) {
					moveablese.add(c);
				}
				tempx++;
				tempy++;
			}

			tempx = this.locale.x - 1;
			tempy = this.locale.y + 1;
			while ( tempx > -1 && tempy < ChessModel.getCols() ) {
				if ( c.getLocation().x == tempx && c.getLocation().y == tempy ) {
					moveablene.add(c);
				}
				tempx--;
				tempy++;
			}

			tempx = this.locale.x - 1;
			tempy = this.locale.y - 1;
			while ( tempx > -1 && tempy > -1 ) {
				if ( c.getLocation().x == tempx && c.getLocation().y == tempy ) {
					moveablenw.add(c);
				}
				tempx--;
				tempy--;
			}

			tempx = this.locale.x + 1;
			tempy = this.locale.y - 1;
			while ( tempx < ChessModel.getRows() && tempy > -1 ) {
				if ( c.getLocation().x == tempx && c.getLocation().y == tempy ) {
					moveablesw.add(c);
				}
				tempx++;
				tempy--;
			}

		}
		
		ChessPiece northeast = null;
		ChessPiece southeast = null;
		ChessPiece northwest = null;
		ChessPiece southwest = null;

		if ( moveablene.size() > 1 ) {
			int distance = 1000;
			for ( ChessPiece c : moveablene ) {
				int distanceform = (int) distance(this.locale.x,
						c.getLocation().x, this.locale.y, c.getLocation().y);
				if ( distanceform < distance || northeast == null ) {
					northeast = c;
					distance = distanceform;
				}
			}
			moves.add(northeast.getLocation());
		}else if(moveablene.size() == 1) {
			northeast = moveablene.iterator().next();
			moves.add(northeast.getLocation());
		}

		if ( moveablese.size() > 1 ) {
			int distance = 1000;
			for ( ChessPiece c : moveablese ) {
				int distanceform = (int) distance(this.locale.x,
						c.getLocation().x, this.locale.y, c.getLocation().y);
				if ( distanceform < distance || southeast == null ) {
					southeast = c;
					distance = distanceform;
				}
			}
			moves.add(southeast.getLocation());
		}else if(moveablese.size() == 1) {
			southeast = moveablese.iterator().next();
			moves.add(southeast.getLocation());
		}

		if ( moveablenw.size() > 1 ) {
			int distance = 1000;
			for ( ChessPiece c : moveablenw ) {
				
				int distanceform = this.locale.x - c.getLocation().x;
						
				
				if ( distanceform < distance || northwest == null ) {
					northwest = c;
					
					distance = distanceform;
				}
			}
			moves.add(northwest.getLocation());
		}else if(moveablenw.size() == 1) {
			northwest = moveablenw.iterator().next();
			moves.add(northwest.getLocation());
		}

		if ( moveablesw.size() > 1 ) {
			int distance = 1000;
			for ( ChessPiece c : moveablesw ) {
				int distanceform = c.getLocation().x - this.locale.x;
				if ( distanceform < distance || southwest == null ) {
					southwest = c;
					distance = distanceform;
				}
			}
			moves.add(southwest.getLocation());
		}else if(moveablesw.size() == 1) {
			southwest = moveablesw.iterator().next();
			moves.add(southwest.getLocation());
		}
		
		return moves;

		
	}

	private double distance(double x1, double x2, double y1, double y2) {
		
		return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}
}
