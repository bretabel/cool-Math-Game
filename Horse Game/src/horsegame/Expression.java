package horsegame;


import java.util.*;

public class Expression {
	int op1, op2;
	char operator;
	
	public Expression() {
		Random rng = new Random();
		
		op1 = rng.nextInt(11);
		int temp = rng.nextInt(2);
		
		if (temp == 0) {
			operator = '-';
		} else {
			operator = '+'; 
		}
		
		if (operator == '-') {
			op2 = rng.nextInt(op1 + 1);
		} else {
			op2 = rng.nextInt(11);
		}
	}
	
	public Expression(char operator) {
		Random rng = new Random();
		if (operator == '-') {
			op1 = rng.nextInt(11);
			op2 = rng.nextInt(op1 + 1);
		} else {
			op1 = rng.nextInt(11);
			op2 = rng.nextInt(11);
		}
		this.operator = operator; 
	}
	
	public Expression(String expression) {
		String[] split = expression.split(" ");
		
		try {
			op1 = Integer.parseInt(split[0]);
			op2 = Integer.parseInt(split[2]);
			operator = split[1].charAt(0);
		} catch (Exception e) {
			System.out.println("Invalid expression entered.");
		}
	}
	
	public Expression(int op1, int op2, char operator) {
		this.op1 = op1;
		this.op2 = op2;
		this.operator = operator;
	}
	
	public int getSolution() {
		if (operator == '+') {
			return op1 + op2;
		} else {
			return op1 - op2;
		}
	} 
	
	public Boolean isSolution(int solution) {
		if (solution == getSolution()) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		return op1 + " " + operator + " " + op2;
	}
	
	public String solutionString() {
		return toString() + " = " + getSolution();
	}
	
	public Boolean equals(Expression other) {
		if(other.op1 == this.op1 && other.op2 == this.op2 && other.operator == this.operator){
			return true;
		} else {
			return false;
		}
	}
}
