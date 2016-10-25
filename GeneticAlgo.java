import java.util.Arrays;
import java.util.Random;

public class GeneticAlgo {
	//variables to allow for manipulating the genetic algorithm.
	//default to 17 queens
	private static int queens = 17;
	//rate of mutation in the population
	private double mutation = 0.33;
	//size of the population
	private int pop = 50;
	
	Random rng = new Random();
	Board[] popVec = new Board[pop];

//Constructors
	public GeneticAlgo(int q){
		queens = q;
	}
	
	public GeneticAlgo(int q, int m){
		queens = q;
		mutation = m;
	}
	
	public GeneticAlgo(int q, int m, int p){
		queens = q;
		mutation = m;
		pop = p;
	}
//End constructors
	
	//generate initial population of random boards.=
	public Board generatePop(){
		//keep generating until population size is reached
		for(int i = 0; i < pop; i++){
			int[] tempArray = new int[queens];
			
			//generate 1 random individual
			for(int j = 0; j < queens; j++){
				tempArray[j] = rng.nextInt(queens);
			}
			
			Board temp = new Board(tempArray);
			//if the board is a solution the exit and return that board
			if(temp.fitness == 0)
				return temp;		
			
			popVec[i] = temp;
		}

		//sort the population based on fitness
		Arrays.sort(popVec);
		
		//no solution found during creation of population
		return null;
	}
	
	public Board generateNextGen(){
		//cross over the top half of the population, and kill off the lower half (fastest)
		int count1 = 0;
		int count2 = 1;
		for(int i = pop/2; i < pop; i++){
			Board child = new Board(crossOver(popVec[count1], popVec[count2]));
			
			popVec[i] = child;
			count1++;
			count2++;
		}
		
		//cross over the most fit individual with the rest of the population (slower)
//		Board parent1 = popVec[0];
//		for(int i = 1; i < pop - 1; i++){
//			Board parent2 = popVec[i];
//			
//			Board child = new Board(crossOver(parent1, parent2));
//		
//			popVec[i] = child;
//		}		
		
		//chance to mutate a part of the population
		for(int i = 1; i < pop; i++){
			int toMutate = rng.nextInt((int)(1 / mutation));
			
			if(toMutate == 0){
				popVec[i].mutate();
			}
			
			if(popVec[i].fitness == 0){
				return popVec[i];
			}
		}
		
		//sort the population based on fitness
		Arrays.sort(popVec);
		
		//no solution is found so continue
		return null;
	}
	
	//perform cross over on 2 boards.
	public int[] crossOver(Board parent1, Board parent2){
		int[] child = new int[queens];
		
		//put first half of first array into a new individual
		for(int i = 0; i < queens/2; i++){
			child[i] = parent1.state[i];
		}
		//put seonds half of second array into a new individual
		for(int i = queens/2; i < queens; i++){
			child[i] = parent2.state[i];
		}
		
		//return newly created individual
		return child;
	}
}
