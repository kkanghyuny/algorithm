import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		TreeSet<Integer> set = new TreeSet<>();
		
		while(n-- > 0) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		while(!set.isEmpty()) {
			sb.append(set.pollFirst()).append(' ');
		}
		System.out.println(sb);
	}
}
