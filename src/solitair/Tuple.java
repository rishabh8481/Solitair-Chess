package solitair;

public class Tuple implements Comparable<Tuple> { 
	public final int x; 
	public final int y; 

	public Tuple(int x, int y) { 
		this.x = x; 
		this.y = y; 
	}
/*
 * (non-Javadoc)
 * @see java.lang.Comparable#compareTo(java.lang.Object)
 */
	@Override
	public int compareTo(Tuple o) {
		// TODO Auto-generated method stub
		if (this.x == o.x && this.y == o.y) {

			return 0;

		}else{
			return -1;
		}
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Tuple other = (Tuple) obj;
		if ( x != other.x )
			return false;
		if ( y != other.y )
			return false;
		return true;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "( " + Integer.toString( x ) + " , " + Integer.toString( y ) + " )";
	} 
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	 
}
