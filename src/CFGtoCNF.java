/* CS 635 Programming Project - Abhinaya Dhandapani */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CFGtoCNF {
	//ArrayList to maintain the LHS and HashMap to record RHS
	public static ArrayList<String> l = new ArrayList<>();
	public static HashMap<String, String> map = new HashMap<>();

	//To check for terminals/non terminals in CFG/CNF
	public static boolean chkCase(String alph) {
		return alph.equals(alph.toLowerCase());
	}

	//Recursive function for CFG to CNF conversion
	public static String cnf(String split) {
		String rhs = "";
		//Condition to convert CNF from CFG of length greater than 2
		if (split.length() > 2) {
			//Condition for First letter split and conversion if lower case
			if (chkCase(split.substring(0, 1))) {
				rhs = rhs + "<" + split.substring(0, 1) + ">";
				l.add(rhs);
				map.put(rhs, split.substring(0, 1));
			} 
			//Condition for First letter split and conversion if upper case
			else {
				rhs = rhs + split.substring(0, 1);
			}
			//Condition for rest of the string split and conversion for strings greater than 2 letters
			if (split.substring(1).length() > 2) {
				rhs = rhs + "<" + split.substring(1) + ">";
				l.add("<" + split.substring(1) + ">");
				map.put("<" + split.substring(1) + ">", cnf(split.substring(1)));
			}
			//Condition for rest of the string split and conversion for strings lesser than 2 letters
			else {
				split = split.substring(1).substring(0);
				l.add("<" + split + ">");
				rhs = rhs + "<" + split + ">";
				map.put("<" + split + ">", cnf(split));
			}
			return rhs;
		}
		//Condition to convert CNF from CFG of length lesser than 2
		else {
			//Records the string as is if lower case
			if (chkCase(split)) {
				return split;
			}
			else {
				//Checks for lower case of first letter
				if (chkCase(split.substring(0, 1))) {
					rhs = rhs + "<" + split.substring(0, 1) + ">";
					l.add("<" + split.substring(0, 1) + ">");
					map.put("<" + split.substring(0, 1) + ">", cnf(split.substring(0, 1)));
				} else {
					rhs = rhs + split.substring(0, 1);
				}
				//Checks for lower case of second letter
				if (chkCase(split.substring(1))) {
					rhs = rhs + "<" + split.substring(1) + ">";
					l.add(split.substring(1));
					map.put(split.substring(1), cnf(split.substring(1)));
				} else {
					rhs = rhs + split.substring(1);
				}
			}
			//The appended CNF value
			return rhs;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Rule: ");
		//User input of the CFG
		String cfg = sc.nextLine();// "A -> aBcdE"
		//Split strings for processing
		String split[] = cfg.split(" ");
		l.add(split[0]);
		//Recursive function calls
		map.put(split[0], cnf(split[2]));
		//Final CNF Display 
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i) + " -> " + map.get(l.get(i)));
		}
	}
}