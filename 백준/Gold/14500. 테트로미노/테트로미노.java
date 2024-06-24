import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] arr;
	static boolean[][] vis;
	static int n, m;
	static int ans;

	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ans = 0;
		
		arr = new int[n][m];
		vis = new boolean[n][m];

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				vis[i][j] = true;
				find(i,j,arr[i][j],1);
				vis[i][j] = false;
			}
		}

		System.out.println(ans);
	}

	static void find(int r, int c, int sum, int cnt) {

		if(cnt == 4) {
			ans = Math.max(ans, sum);
			return;
		}

		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

			if(!vis[nr][nc]) {
				if(cnt == 2) {
					vis[nr][nc] = true;
					find(r, c, sum + arr[nr][nc], cnt + 1);
					vis[nr][nc] = false;
				}

				vis[nr][nc] = true;
				find(nr, nc, sum + arr[nr][nc], cnt + 1);
				vis[nr][nc] = false;
			}
		}
	}
}