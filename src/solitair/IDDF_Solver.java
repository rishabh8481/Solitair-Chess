package solitair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author Sharma
 *
 * @param <T>
 * Perform Iterative Deepening Search algorithm for solving the problem.
 * Iterative Deepening Search ALGO AS FOLLOW:- 
 * 	1. Start from the root node.
 *  2. place root node at the top of the stack.
 *  3. Mark node at the top of the stack as visited and calculate the possible neighbor of the root node.
 *  4. Insert the neighbor to the top of the queue.
 *  5. Repeat the step 3 and 4 till the goal state is achieved.
 *  
 */
public class IDDF_Solver<T> extends Solver<T>{

	private Puzzle<T> myPuzzle;

	public IDDF_Solver(Puzzle<T> thePuzzle){
		myPuzzle = thePuzzle;
	}
	/**
	 * solve() utilizes a DLS along with an Stack to return the steps needed
	 * to solve a puzzle 	 
	 * @param curConf is a general puzzle with it's starting config
	 * @return an Stack with the steps needed to solve the puzzle
	 */ 
	public ArrayList<T> solve() {
		HashMap<T,T> visited = new HashMap<T,T>();
		Stack<T> stack  = new Stack<T>();
		ArrayList<T> queue = new ArrayList<T>();
		boolean found = false;
		T curStep = myPuzzle.getStart();
		stack.push(curStep);
		visited.put(curStep, null);
		while(!stack.isEmpty()){
			curStep = stack.pop();
			queue.add(curStep);
			if(myPuzzle.isGoal(curStep))
			{
				found = true;
				break;
			}
			if(stack.size() == 0){
				for(T queueTop : queue){
					for(T neighbor : myPuzzle.getNeighbors(queueTop))
					{
						if(!visited.containsKey(neighbor))
						{
							visited.put(neighbor, queueTop);
							stack.push(neighbor);
						}
					}
				}
				queue.removeAll(queue);
			}
			
		}
		// Perform the BackTracking operation once the solution is found 
		// if No solution is found then return No solution.
		// display steps required to achieve the goal state
		if(found)
		{
			ArrayList<T> reverseSteps = new ArrayList<T>();
			ArrayList<T> steps = new ArrayList<T>();
			reverseSteps.add(curStep);
			while(visited.get(curStep) != null)
			{
				reverseSteps.add(visited.get(curStep));
				curStep = visited.get(curStep);
			}
			for(int i = reverseSteps.size()-1; i >= 0; i--)
			{
				steps.add(reverseSteps.get(i));
			}
			return steps;
		}
		return null;
	}
}
