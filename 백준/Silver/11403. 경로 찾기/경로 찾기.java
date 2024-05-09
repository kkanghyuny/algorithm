import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] arr, answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());

		arr = new int[n][n];
		answer = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < n; i++) {
			dfs(i, i);
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				sb.append(answer[i][j]).append(" ");
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int start, int to) {
		for(int i = 0; i < n; i++) {
			if(arr[to][i] == 1) {
				if(answer[start][i] != 1) {
					answer[start][i] = 1;
					dfs(start, i);
				}
			}
		}
	}
}
