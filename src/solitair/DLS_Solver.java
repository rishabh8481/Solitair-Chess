package solitair;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
/**
 * @author Sharma
 *
 * @param <T>
 * Perform DLS algorithm for solving the problem.
 * Depth Limit Search ALGO AS FOLLOW:- 
 * 	1. Ask user in how many steps does he wants the solution.
 * 	2. Start from the root node.
 *  3. place root node at the top of the stack.
 *  4. Mark node at the top of the queue as visited and calculate the possible neighbor of the root node.
 *  5. Insert the neighbor to the top of the stack.
 *  6. Repeat the step 3 and 4 till the goal state is achieved.
 *  7. Count the number of steps it requires to get to the goal state from start state.
 *  8. If the count is more than what user desire return error message.
 */
public class DLS_Solver<T> extends Solver<T> {

	private Puzzle<T> myPuzzle;
	public DLS_Solver(Puzzle<T> thePuzzle){
		myPuzzle = thePuzzle;
	}
	/**
	 * solve() utilizes a DLS along with an Stack to return the steps needed
	 * to solve a puzzle 	 
	 * @param curConf is a general puzzle with it's starting config
	 * @return an Stack with the steps needed to solve the puzzle
	 * 
	 * ask for the depth from the user. 
	 */
	public ArrayList<T> solve() {
		int depth;
		System.out.println("Please Enter the depth value:");
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		depth = s.nextInt();
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
		// count the number of states requires to get the goal state.
		// if the count is less than what is required  
		//     then display steps required to achieve the goal state
		// else 
		//     Display the error message.
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
			if(depth >= steps.size()) {
				return steps;
			}else{
				System.out.println("Sorry There is no solution available at depth: "+depth);
				return null;
			}
		}	
		return null;
	}
}
