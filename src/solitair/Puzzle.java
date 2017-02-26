package solitair;
import java.util.ArrayList;

/**
 * 
 * Puzzle - interface for a general puzzle
 *         problem Has the ability to access the start and goal configurations,
 *         can also generate neighbor configs.
 *         
 * Anything that extends Puzzle must extend it as Puzzle<ConfigType>
 * More than likely, neighbor will be an ArrayList<ConfigType>
 */

public abstract class Puzzle<E> {

	protected Integer goal;

	protected E start;

	/**
	 * getGoal gets the end config for the puzzle
	 * @return an int which represents the goal config
	 */
	public Integer getGoal() {
		
		return goal;
	}

	/**
	 * Each kind of Puzzle will know a separate way to return its neighbors
	 */
	public abstract ArrayList<E> getNeighbors(E config);

	/**
	 * getStart gets the starting configuration for the puzzle
	 * @return an int which represents the start config
	 */
	public E getStart() {
		
		return start;
	}
	
	/**
	 * Compares a given config with the puzzle's personal goal config
	 * @param config - Type E, a puzzle's style of config
	 * @return boolean - true if the config is the same as the goal, or within.
	 */
	public abstract boolean isGoal(E config);
}
