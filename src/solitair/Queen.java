package solitair;

import java.util.ArrayList;
import java.util.HashSet;

public class Queen extends ChessPiece{

	public Queen(int row, int col, int id) {
	

		super.locale = new Tuple(row, col);
		super.piecename = "Queen";
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
	 * Use Q in instead of Queen in chess board
	 */
	@Override
	public String printBoardName() {

		return "Q";
	}
	
	/*
	 * (non-Javadoc)
	 * @see solitair.ChessPiece#getMoves(java.util.HashSet)
	 * Set of Legal moves for Queen on chess board
	 */
	@Override
	public ArrayList<Tuple> getMoves(HashSet<ChessPiece> config) {
		ArrayList<Tuple> moves = new ArrayList<Tuple>();
		HashSet<ChessPiece> moveablese = new HashSet<ChessPiece>();
		HashSet<ChessPiece> moveablene = new HashSet<ChessPiece>();
		HashSet<ChessPiece> moveablenw = new HashSet<ChessPiece>();
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

	
	private double distance(double x1, double y1, double x2, double y2) {

		return Math.sqrt( Math.pow( (x1 - x2), 2 ) + Math.pow( (y1 - y2), 2 ) );
	}
}
