//calculator_modularize
import java.util.*;
public class calculator_modularize {

    static ArrayList<Token> tokens = new ArrayList<Token>();
    
    private static int readNumber(String line, int index) {
	double number = 0;
	while (index < line.length() && Character.isDigit(line.charAt(index))) {
	    number  = number * 10 + (int)(line.charAt(index)-48);
	    index++;
	}
	if(index < line.length() && line.charAt(index) == '.') {
	    index++;
	    double keta = 0.1;
	    while(index < line.length() && Character.isDigit(line.charAt(index))) {
		number += (int)(line.charAt(index)-48)* keta;
		keta /= 10;
		index++;
	    }
	}
	tokens.add(new Token("NUMBER", number));
	return index;
    }
    private static int readPlus(String line, int index) {
	if(! Character.isDigit(line.charAt(index - 1))) {
	    System.out.println("Double symbol is invail: " +line.charAt(index - 1) + ", " + line.charAt(index));
	    System.exit(1);//また入力をうけつけたい
	}
	tokens.add(new Token("PLUS"));
	return index + 1;
    }
    private static int readMinus(String line, int index) {
	if(! Character.isDigit(line.charAt(index - 1))) {
	    System.out.println("Double symbol is invail: " +line.charAt(index - 1) + ", " + line.charAt(index));
	    System.exit(1);//また入力をうけつけたい
	}
	tokens.add(new Token("MINUS"));
	return index + 1;
    }
    private static void tokenize(String line) {
	int index = 0;
	while (index < line.length()) {
	    if (Character.isDigit(line.charAt(index))) {
		index = readNumber(line, index);
	    } else if (line.charAt(index) == '+') {
		index = readPlus(line, index);
	    } else if (line.charAt(index) == '-') {
		index = readMinus(line, index);
	    } else {
		System.out.println("Invalid character found: " + line.charAt(index));
		System.exit(1);//また入力をうけつけたい
	    }
	}
			
    }
    private static double evaluate() {
	double answer = 0;	
	tokens.add(0, new Token("PLUS"));
	int index = 1;
	while (index < tokens.size()) {
	    //System.out.println(tokens.get(index).type + ":" +tokens.get(index).number);
	    if (tokens.get(index).type.equals("NUMBER")) {
		if (tokens.get(index - 1).type.equals("PLUS")) {
		    answer += tokens.get(index).number;
		} else if (tokens.get(index - 1).type.equals("MINUS")) {
		    answer -= tokens.get(index).number;
		} else {
		    System.out.println("Invalid syntax");
		    System.exit(1);//また入力をうけつけたい
		}
	    }
	    index++;
	}
	    return answer;
    }
			
    
    private void test(String s) {
	//eval関数をどう実装したらいいのかわからない。
    }
    private void runTest() {
	System.out.println("==== Test started! ====");
	test("1+2");
	test("1.0+2.1-3");
	System.out.println("==== Test finished! ====");
    }
    
    public static void main(String[] args) {
	//runTest();
	Scanner sc = new Scanner(System.in);
	while(true) {
	    tokens.clear();
	    System.out.print("> ");
	    String line = sc.next();
	    tokenize(line);
	    double answer = evaluate();
	    System.out.println("answer = " + answer);
	}    
    }
}
class Token {
    String type;
    double number = 0;

    public Token(String type, double number) {
	this.type = type;
	this.number = number;
    }

    public Token(String type) {
	this.type = type;
    }
}
    
    
