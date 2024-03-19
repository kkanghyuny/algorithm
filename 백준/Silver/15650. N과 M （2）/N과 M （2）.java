import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] perm, arr;
	static boolean[] vis;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n + 1];
		perm = new int[m];
		vis = new boolean[n + 1];
		
		for(int i = 1; i < n + 1; i++) {
			arr[i] = i;
		}
		
		for(int i = 1; i < n + 1; i++) {
			perm[0] = arr[i];
			
			vis[i] = true;
			perm(1, i);
			vis[i] = false;
		}
		
		System.out.println(sb);
	}
	
	static void perm(int depth, int idx) {
		if(depth == m) {
			for(int i = 0; i < m; i++) {
				sb.append(perm[i]).append(" ");
			}
			sb.append('\n');
			return;
		}
		
		for(int i = idx + 1; i < n + 1; i++) {
			if(vis[i]) continue;
			
			vis[i] = true;
			perm[depth] = arr[i];
			perm(depth + 1, i);
			vis[i] = false;
		}
	}
}
