import java.io.*;
import java.util.*;

public class Main {
	static int n, answer;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] arr, dp;
	static boolean[][] vis;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		dp = new int[n][n];
		vis = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = 1;
			}
		}

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				dfs(i, j);
			}
		}
		System.out.println(answer);
	}

	static int dfs(int r, int c) {
		if(vis[r][c]) return dp[r][c];
		vis[r][c] = true;
		for(int d = 0; d < 4; d++) {
			int cnt = 1;
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(!checkBoundary(nr, nc)) continue;
			if(arr[nr][nc] > arr[r][c]) {
				cnt += dfs(nr, nc);
			}
			dp[r][c] = Math.max(cnt, dp[r][c]);
		}
		answer = Math.max(answer, dp[r][c]);
		return dp[r][c];
	}

	static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}
}
