package solitair;

import java.util.ArrayList;
/*
 * An Abstract class used for allowing polymorphism for all solver class to use solve function
 *  
 */
public abstract class Solver<T> {

	public abstract ArrayList<T> solve();

}
