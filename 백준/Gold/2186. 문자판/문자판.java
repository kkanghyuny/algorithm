import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n, m, k;
	static String ans;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static char[][] arr;
	static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String s;

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		int cnt = 0;

		arr = new char[n][m];

		for (int i = 0; i < n; i++) {
			s = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j);
			}
		}

		ans = br.readLine();
		dp = new int[n][m][ans.length()];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == ans.charAt(0)) {
					cnt += dfs(i, j, 0);
				}
			}
		}

		System.out.println(cnt);
	}

	static int dfs(int r, int c, int depth) {
		
		if(dp[r][c][depth] != -1) {
			return dp[r][c][depth];
		}
		
		if (depth + 1 == ans.length()) {
			return 1;
		}
		
		int cnt = 0;
		
		for (int i = 1; i <= k; i++) {
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d] * i;
				int nc = c + dc[d] * i;

				if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
				
				if (arr[nr][nc] == ans.charAt(depth + 1)) {
					cnt += dfs(nr, nc, depth + 1);
				}
			}
		}
		
		return dp[r][c][depth] = cnt;
	}
}
