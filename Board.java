import java.util.Arrays;
import java.util.Random;

//object for a representing a board of queens
public class Board implements Comparable<Board>{
	Random rng = new Random();
	
	//variables for board and fitness score(lower is better)
	int[] state;
	int fitness;
	
	public Board(int[] board){
		state = board;
		fitness = fitnessCalc(); 
	}
	
	//calculate the fitness of the current board
	public int fitnessCalc(){
		int fitnessCost = 0;
		
		//calculate the number of queens a particular queen is attacking, for all queens
		for(int i = 0; i < state.length; i++){
			for(int j = 0; j < state.length; j++){
				//don't check the column that we are current in
				if(i != j){
					//if the queens are horizontal of queen we are checking for, then it is attacking
					if(state[j] == state[i]){
						fitnessCost++;
					}
					//if the queen is diagonal in either direction(up/down) on both sides then it is attacking
					if(Math.abs(state[j] - state[i]) == Math.abs(j - i)){
						fitnessCost++;
					}
				}
			}
		}
		
		//return number of queens attacking each other
		return fitnessCost;
	}
	
	public void mutate(){
		int randQueen = rng.nextInt(state.length);
		
		state[randQueen] = rng.nextInt(state.length);
		
		fitness = fitnessCalc();
	}

	@Override
	public int compareTo(Board o) {
		if(this.fitness > o.fitness)
			return 1;
		else if(this.fitness < o.fitness)
			return -1;
		else
			return 0;
	}
	
	public String toString(){
		return Arrays.toString(state);
	}
}
