package horsegame;

import java.util.*;


public class ProblemSet {
	ArrayList<Expression> problemList = new ArrayList<Expression>();
	
	/**
	 * Randomly populates list with 10 problems
	 */
	public ProblemSet() {
		for(int i = 0; i < 10; i++) {
			Expression newExpression = new Expression();
			problemList.add(newExpression);
		}
	}
	
	/**
	 * Randomly populates list with problems of either addition [exclusive] or subtraction
	 * @param operator either + or - specifying what kind of expression to populate the list
	 */
	public ProblemSet(char operator) {
		if (operator == '-') {
			for(int i = 0; i < 10; i++) {
				Expression newExpression = new Expression('-');
				problemList.add(newExpression);
			}
		} else {
			for(int i = 0; i < 10; i++) {
				Expression newExpression = new Expression('+');
				problemList.add(newExpression);
			}
		}
	}
	
	/**
	 * Randomly populates list with inputed num of problems
	 * @param numProblems number of problems to include in list
	 */
	public ProblemSet(int numProblems) {
		for(int i = 0; i < numProblems; i++) {
			Expression newExpression = new Expression();
			problemList.add(newExpression);
		}
	}
	
	public void addExpression(Expression e) {
		if(!(problemList.contains(e))) {
			problemList.add(e);
		}
	}
	
	public Expression getFirst() {
		Expression e = problemList.get(0);
		problemList.remove(0);
		return e;
	}
	
	public Expression getRandom() {
		Random rng = new Random(); 
		int randomIndex = rng.nextInt(problemList.size());
		Expression e = problemList.get(randomIndex);
		problemList.remove(randomIndex);
		return e;
	}
	
}
