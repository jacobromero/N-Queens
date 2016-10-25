public class Driver {
	
	public static void main(String[] args) {
		//number of queens that will attempted to be solved
		int queens = 17;
		
		//create a new genetic algorithm object
		GeneticAlgo ga = new GeneticAlgo(queens);
		
		//generate initial population
		Board sol = ga.generatePop();
		//limit maximum generations before restarting the search
		int maxGen = 6500;
		int gen = 0;
		//continue search while we haven't found a solution
		while(sol == null){
			if(gen < maxGen){
				//select, crossover, and mutate current population
				sol = ga.generateNextGen();
			}
			//current generation is the max we allow, so nuke it and start from scratch
			else{
				sol = ga.generatePop();
				gen = 0;
			}
		}
		
		//print solution
		System.out.println("Genetic Algorithm - \nSolution Found! - " + sol + " With fitness: " + sol.fitness + "\n");		
		
		//perform steepest hill climb
		System.out.println("Steepest Hill Climb - ");
		
		//comment the following statement if you want to input your own board
		int[] state = HillClimb.randomProblem(queens);
		
		//uncomment the following line and enter your board in terms of 1d array
//		int[] state = {};
		Board a = HillClimb.hillClimb(state);
		
		//print best found solution
		System.out.println(a);
	}
}
