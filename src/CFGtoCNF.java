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
				l.add("<"+split+">");
				rhs=rhs+"<"+split+">";
				map.put("<"+split+">", cnf(split));
				
			}
			r.add(rhs);
			return rhs;
			
		}else{
			if(chkCase(split)){
				return split;
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
		String cfg = "A -> ABceDfS";
		
		String split[] = cfg.split("->");
		l.add(split[0]);
		for(int i=0;i<split.length;i++){
			split[i]=split[i].trim();
		}
		map.put(split[0], cnf(split[1]));
		
		
		System.out.println(split[0]+"->"+map.get(split[0]));
		for(int i=1;i<l.size();i++){
			System.out.println(l.get(i)+"->"+map.get(l.get(i)));
		}

	}

}
