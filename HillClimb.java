import java.util.PriorityQueue;
import java.util.Random;

public class HillClimb {
	public static Board hillClimb(int[] toSol){
		Board current, neighbor;
		
		//create the board object which we want to solve
		current = new Board(toSol);
		System.out.println("Looking for a solution to \n" + current + "\nwith " + current.fitness + " conflicts");
		System.out.println();
		
		//continue searching until no better move can be found
		do{
			//generate all possible moves, then select the most fit one
			neighbor = getBestNeighbor(current);
			
			//if the most fit of all best possible moves is worse, or we found the solution then exit
			if(value(current) <= value(neighbor) || value(current) == 0){
				System.out.println("Solution found with " + current.fitness + " conflicts");
				return current;
			}
			
			//otherwise execute best move and repeate
			current = neighbor;
		}while(true);
	}
	
	private static int value(Board b) {
		return b.fitness;
	}
	
	//generates all possible moves and returns the most fit of those moves
	private static Board getBestNeighbor(Board current){
		//use priority queue for allowing me to select the best move
		PriorityQueue<Board> children = new PriorityQueue<Board>();
		
		//generate all moves and add them to the priority queue
		for(int x = 0; x < current.state.length; x++){
			for(int y = 0; y < current.state.length; y++){
				int[] copy = new int[current.state.length];
				copy(copy, current.state);
				
				copy[x] = y;
				
				children.add(new Board(copy));
			}
		}
		
		//return the best of all moves
		return children.peek();
	}

	//copy the board (state) into the destination (copy)
	private static void copy(int[] copy, int[] state) {
		for(int x = 0; x < state.length; x++){
			copy[x] = state[x];
		}
	}
	
	//generate random board to be solved
	static int[] randomProblem(int queens) {
		int[] random = new int[queens];
		Random ran = new Random();
		
		for(int x = 0; x < queens; x++){
			random[x] = ran.nextInt(queens);
		}
		
		return random;
	}
}
