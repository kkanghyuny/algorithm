import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int ans = 0;
		
		parent = new int[n];
		
		for(int i = 0; i < n; i++) {
			parent[i] = i;
		}
		
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = find(Integer.parseInt(st.nextToken()));
			int b = find(Integer.parseInt(st.nextToken()));
			
			if(a != b) {
				union(a, b);
			} else {
				ans = i;
				break;
			}
		}
		
		System.out.println(ans);
	}
	
	static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}
	
	static void union(int a, int b) {
		if(a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}
	}
}
