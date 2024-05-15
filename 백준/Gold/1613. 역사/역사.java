import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] arr, ans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new int[n + 1][n + 1];
		ans = new int[n + 1][n + 1];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = 1;
			arr[b][a] = -1;
		}
		
		for(int i = 1; i <= n; i++) {
			beforeDfs(i, i);
			afterDfs(i, i);
		}
		
		int k = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(ans[b][a]).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void beforeDfs(int start, int to) {
		for(int i = 1; i <= n; i++) {
			if(arr[to][i] == -1) {
				if(ans[start][i] != -1) {
					ans[start][i] = -1;
					beforeDfs(start, i);
				}
			}
		}
	}
	
	static void afterDfs(int start, int to) {
		for(int i = 1; i <= n; i++) {
			if(arr[to][i] == 1) {
				if(ans[start][i] != 1) {
					ans[start][i] = 1;
					afterDfs(start, i);
				}
			}
		}
	}
}
