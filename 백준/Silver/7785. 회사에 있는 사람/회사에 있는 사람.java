import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		TreeSet<String> set = new TreeSet<>();
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String order = st.nextToken();
			
			if(order.equals("enter")) {
				set.add(name);
			} else if(order.equals("leave")) {
				set.remove(name);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!set.isEmpty()) {
			sb.append(set.pollLast()).append('\n');
		}
		
		System.out.println(sb);
		
		
		
	}

}
