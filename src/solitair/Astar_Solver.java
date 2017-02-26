package solitair;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Sharma
 *
 * @param <T>
 * Perform A star algorithm for solving the problem.
 * order of the heuristic is as follow
 *  In the priority order : - Queen, Rook, Bishop, Knight, King, Pawn
 *  where Queen has the highest Priority and Pawn has the Lowest Priority.
 *  
 * Note : - here we have consider g(n) = 0 for all the values.
 *			whereas the value of h(n) are in respective to the order in which the Priority Queue is mentioned above. 
 *			(i.e., Queen with the lowest value and Pawn with the highest value).
 *
 */
public class Astar_Solver<T> extends Solver<T>{

	Puzzle<T> myPuzzle;

	Astar_Solver(Puzzle<T> thePuzzle){
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
		ArrayList<T> queue = new ArrayList<T>();
		ArrayList<String> queue1 = new ArrayList<String>();
		boolean found = false;
		T curStep = myPuzzle.getStart();
		queue.add(curStep);
		String[] str = curStep.toString().split("hashcode: [0-9]+");
		str[0] = str[0].substring(1);
		queue1.add(str[0]);
		for(int i=1;i<str.length-1;i++){
			str[i] = str[i].substring(2);
			queue1.add(str[i]);
		}
		//decider will select which piece to take for the first iteration.
		String decider;
		if(queue1.toString().contains("Queen")){
			decider = "Queen";
		}else if(queue1.toString().contains("Rook")){
			decider = "Rook";
		}else if(queue1.toString().contains("Bishop")){
			decider = "Bishop";
		}else if(queue1.toString().contains("Knight")){
			decider = "Knight";
		}else if(queue1.toString().contains("King")){
			decider = "King";
		}else {
			decider = "Pawn";
		}
		visited.put(curStep, null);
		// Execution of the A star algorithm.
		while(!queue.isEmpty() && queue1.toString().contains(decider))
		{
			curStep = queue.get(0);
			queue.remove(0);
			if(myPuzzle.isGoal(curStep))
			{
				found = true;
				break;
			}
			for(T neighbor : myPuzzle.getNeighbors(curStep))
			{
				if(!visited.containsKey(neighbor)) 
				{
					visited.put(neighbor, curStep);
					queue.add(neighbor);
				}
			}
			// Creation of Heuristic for the algorithm
			if(!queue.toString().contains(decider)){
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
		// Display steps required to achieve the goal state
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

