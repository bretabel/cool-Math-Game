package horsegame;
import java.util.*;


public class Expression {
    int op1, op2;
    char operator;

    public Expression() {
        Random rng = new Random();

        op1 = rng.nextInt(100);
        int temp = rng.nextInt(3);

        if (temp == 0) {
            operator = '-';
        } else if (temp == 1){
            operator = '+'; 
        } else {
            operator = '.';
        }

        if (operator == '-') {
            op2 = rng.nextInt(op1 + 1);
        } else if (operator == '.'){
            String strOP = Integer.toString(op1);
            String[] nums = strOP.split("");
            op2 = Integer.parseInt(nums[rng.nextInt(nums.length)]);
        } else {
            op2 = rng.nextInt(100);
        }
    }

    public Expression(char operator) {
        Random rng = new Random();
        if (operator == '-') {
            op1 = rng.nextInt(100);
            op2 = rng.nextInt(op1 + 1);
        } else if (operator == '+'){
            op1 = rng.nextInt(100);
            op2 = rng.nextInt(100);
        } else {
            op1 = rng.nextInt(100);
            String strOP = Integer.toString(op1);
            String[] nums = strOP.split("");
            op2 = Integer.parseInt(nums[rng.nextInt(nums.length)]);
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
        int arrayPlace = -1;

        if (operator == '+') {
            return op1 + op2;
        } else if (operator == '-'){
            return op1 - op2;
        } else {
            String strOP = Integer.toString(op1);

            int[] nums = new int[strOP.length()];
            
            for(int i = 0; i < strOP.length(); i++){
                nums[i] = strOP.charAt(i) - '0';
            }
            

            for(int i = 0; i < nums.length; i++) {
                if(op2 == nums[i]) {
                    arrayPlace = i;
                }
            }
            int temp = strOP.length() - arrayPlace -1;

            if(temp == 0) {
                return 1;
            } else if (temp == 1) {
                return 10;
            } else {
                return 100;
            }

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
        if (operator == '.') {
            return "What place is " + op2 + " in " + op1 + "?";
        } else {
            return op1 + " " + operator + " " + op2;
        }
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