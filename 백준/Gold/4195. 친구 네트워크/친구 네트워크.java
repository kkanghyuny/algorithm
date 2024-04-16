import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static Map<String, String> union;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			int f = Integer.parseInt(br.readLine());
			Map<String, Integer> map = new HashMap<>();
			union = new HashMap<>();
			
			for(int j = 0; j < f; j++) {
				st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				if(!union.containsKey(a) && !union.containsKey(b)) {
					union.put(a, a);
					union.put(b, a);
					map.put(a, 2);
					sb.append(2).append('\n');
				} else if(!union.containsKey(a) && union.containsKey(b)) {
					String parent = find(b);
					union.put(a, parent);
					map.put(parent, map.get(parent) + 1);
					sb.append(map.get(parent)).append('\n');
				} else if(union.containsKey(a) && !union.containsKey(b)) {
					String parent = find(a);
					union.put(b, parent);
					map.put(parent, map.get(parent) + 1);
					sb.append(map.get(parent)).append('\n');
				} else {
					String parentA = find(a);
					String parentB = find(b);
					if(parentA.equals(parentB)) {
						sb.append(map.get(parentA)).append('\n');
					} else {
						union.put(parentA, parentB);
						map.put(parentB, map.get(parentB) + map.get(parentA));
						map.remove(parentA);
						sb.append(map.get(parentB)).append('\n');
					}
				}
			}
		}
		System.out.println(sb);
	}
	
	static String find(String val) {
		if(union.get(val).equals(val)) return val;
		return find(union.get(val));
	}
}
