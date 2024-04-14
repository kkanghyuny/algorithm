import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		HashSet<String> set = new HashSet<>();
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String who = st.nextToken();
			String ent = st.nextToken();
			
			switch(ent) {
			case "enter": set.add(who); break;
			case "leave": if(set.contains(who)) set.remove(who); break;
			}
		}
		
		String[] arr = set.toArray(new String[0]);
		
		Arrays.sort(arr);
		
		for(int i = arr.length - 1; i >= 0; i--) {
			sb.append(arr[i]).append('\n');
		}
		
		System.out.println(sb);
	}
}
