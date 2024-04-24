import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, count;
	static int[] arr;
	static boolean[] vis, fin;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			count = 0;
			arr = new int[n + 1];
			vis = new boolean[n + 1];
			fin = new boolean[n + 1];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 1; i <= n; i++) {
				dfs(i);
			}
			
			sb.append(n - count).append('\n');
		}
		System.out.println(sb);
	}
	
	static void dfs(int k) {
		if(fin[k]) return;
		
		vis[k] = true;
		int next = arr[k];
		
		if(!vis[next]) {
			dfs(next);
		} else if(!fin[next]) {
			count++;
			for(int i = next; i != k; i = arr[i]) {
				count++;
			}
		}		
		fin[k] = true;
	}
}
