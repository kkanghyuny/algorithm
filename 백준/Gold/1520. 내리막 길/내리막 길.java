import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] arr, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		dp = new int[n][m];

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		System.out.println(dfs(0, 0));
	}

	static int dfs(int r, int c) {
		if(r == n - 1 && c == m - 1) return 1;
		if(dp[r][c] != -1) return dp[r][c];

		dp[r][c] = 0;
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if(nr < 0 || nc < 0 || nr >= n || nc >= m) continue;

			if(arr[nr][nc] < arr[r][c]) {
				dp[r][c] += dfs(nr, nc);
			}
		}
		return dp[r][c];
	}

}
