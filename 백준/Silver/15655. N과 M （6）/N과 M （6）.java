import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
		
		arr = new int[n];
		perm = new int[m];
		vis = new boolean[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for(int i = 0; i < n; i++) {
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
		
		for(int i = idx + 1; i < n; i++) {
			if(vis[i]) continue;
			
			perm[depth] = arr[i];
			vis[i] = true;
			perm(depth + 1, i);
			vis[i] = false;
			
		}
	}
}
