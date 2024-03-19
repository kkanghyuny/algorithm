import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] perm, arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n + 1];
		perm = new int[m];
		
		for(int i = 1; i < n + 1; i++) {
			arr[i] = i;
		}
		
		for(int i = 1; i < n + 1; i++) {
			perm[0] = arr[i];
			
			perm(1, i);
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
		
		for(int i = idx; i < n + 1; i++) {
			
			perm[depth] = arr[i];
			perm(depth + 1, i);
		}
	}
}
