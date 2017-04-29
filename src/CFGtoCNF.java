import java.util.ArrayList;

public class CFGtoCNF {
	
	public static boolean chkCase(String alph){
		return alph.equals(alph.toLowerCase());
	}

	public static void main(String[] args) {
		String cfg = "A -> aBcdE";
		ArrayList<String> l = new ArrayList<>();
		ArrayList<String> r = new ArrayList<>();
		String split[] = cfg.split("->");
		l.add(split[0]);
		for(int i=0;i<split.length;i++){
			split[i]=split[i].trim();
			System.out.println(split[i]);
		}
		String rhs="";
		if(split[1].length()>2){
			if(chkCase(split[1].substring(0, 1))){
				rhs=rhs+"<"+split[1].substring(0, 1)+">";
			}
			if(split[1].substring(1).length()>2){
				rhs=rhs+"<"+split[1].substring(1)+">";
			}
			r.add(rhs);
			rhs="";
			
		}
		System.out.println("LHS - "+l);
		System.out.println("RHS - "+r);

	}

}
