package solitair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author Sharma
 *
 * @param <T>
 * Perform Iterative Deepening A star algorithm for solving the problem.
 * order of the heuristic is as follow
 *  In the priority order : - Queen, Rook, Bishop, Knight, King, Pawn
 *  where Queen has the highest Priority and Pawn has the Lowest Priority.
 *  
 * Note : - here we have consider g(n) = 0 for all the values.
 *			whereas the value of h(n) are in respective to the order in which the Priority Queue is mentioned above. 
 *			(i.e., Queen with the lowest value and Pawn with the highest value).
 *
 */
public class AstarIterative_Solver<T> extends Solver<T> {

	Puzzle<T> myPuzzle;
	
	public AstarIterative_Solver(Puzzle<T> thePuzzle){
		myPuzzle = thePuzzle;
	}
	/* (non-Javadoc)
	 * @see solitair.Solver#solve()
	 * Check the Piece with the lowest heuristic value present in the puzzle.
	 * Select that piece for solving the puzzle.
	 * 
	 */
	@Override
	public ArrayList<T> solve() {
		HashMap<T,T> visited = new HashMap<T,T>();
		Stack<T> stack  = new Stack<T>();
		ArrayList<T> queue = new ArrayList<T>();
		ArrayList<String> stack1 = new ArrayList<String>();
		boolean found = false;
		T curStep = myPuzzle.getStart();
		stack.push(curStep);
		String[] str = curStep.toString().split("hashcode: [0-9]+");
		str[0] = str[0].substring(1);
		stack1.add(str[0]);
		for(int i=1;i<str.length-1;i++){
			str[i] = str[i].substring(2);
			stack1.add(str[i]);
		}
		//decider will select which piece to take for the first iteration.
		String decider;
		if(stack1.toString().contains("Queen")){
			decider = "Queen";
		}else if(stack1.toString().contains("Rook")){
			decider = "Rook";
		}else if(stack1.toString().contains("Bishop")){
			decider = "Bishop";
		}else if(stack1.toString().contains("Knight")){
			decider = "Knight";
		}else if(stack1.toString().contains("King")){
			decider = "King";
		}else {
			decider = "Pawn";
		}
		visited.put(curStep, null);
		// Execution of the A star algorithm.
		while(!stack.isEmpty()  && stack1.toString().contains(decider)){
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
			// Creation of Heuristic for the algorithm
			if(!stack.toString().contains(decider)){
				if(decider.equals("Queen")){
					decider.replaceAll("[A-Z a-z]+", "Rook");
				}else if(decider.equals("Rook")){
					decider.replaceAll("[A-Z a-z]+", "Bishop");
				}else if(decider.equals("Bishop")){
					decider.replaceAll("[A-Z a-z]+", "Knight");
				}else if(decider.equals("Knight")){
					decider.replaceAll("[A-Z a-z]+", "King");
				}else{
					decider.replaceAll("[A-Z a-z]+", "Pawn");
				}
			}
		}
		// Perform the BackTracking operation once the solution is found 
		// if No solution is found then return No solution.
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
		return null; //No solution found
	}
}