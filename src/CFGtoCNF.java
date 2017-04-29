import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CFGtoCNF {
	public static ArrayList<String> l = new ArrayList<>();
	public static ArrayList<String> r = new ArrayList<>();
	public static Map<String, String> map = new LinkedHashMap<>();
	
	public static boolean chkCase(String alph){
		return alph.equals(alph.toLowerCase());
	}
	
	public static String cnf(String split){
		String rhs="";
		if(split.length()>2){
			if(chkCase(split.substring(0, 1))){
				rhs=rhs+"<"+split.substring(0, 1)+">";
				l.add(rhs);
				map.put(rhs, split.substring(0, 1));
			}else{
				rhs=rhs+split.substring(0, 1);
			}
			if(split.substring(1).length()>2){
				rhs=rhs+"<"+split.substring(1)+">";
				l.add("<"+split.substring(1)+">");
				map.put("<"+split.substring(1)+">", cnf(split.substring(1)));
			}else{
				
				split=split.substring(1).substring(0);
				System.out.println("Split "+split);
				l.add("<"+split+">");
				rhs=rhs+"<"+split+">";
				map.put("<"+split+">", cnf(split));
				
			}
			r.add(rhs);
			return rhs;
			
		}else{
			if(chkCase(split)){
			r.add(split);
			}else{
				if(chkCase(split.substring(0,1))){
					rhs=rhs+"<"+split.substring(0,1)+">";
					l.add("<"+split.substring(0,1)+">");
					map.put("<"+split.substring(0,1)+">", cnf(split.substring(0,1)));
				}
				if(chkCase(split.substring(1))){
					rhs=rhs+"<"+split.substring(1)+">";
					l.add(split.substring(1));
					map.put(split.substring(1), cnf(split.substring(1)));
				}else{
					rhs=rhs+split.substring(1);
				}
			}
			return rhs;
		}
	}

	public static void main(String[] args) {
		String cfg = "A -> aBcdE";
		
		String split[] = cfg.split("->");
		l.add(split[0]);
		for(int i=0;i<split.length;i++){
			split[i]=split[i].trim();
			System.out.println(split[i]);
		}
		cnf(split[1]);
		
		System.out.println("LHS - "+l);
		System.out.println("RHS - "+r);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		}

	}

}
