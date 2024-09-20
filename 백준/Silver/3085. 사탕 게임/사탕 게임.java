import java.io.*;

public class Main {
	static int n, answer;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static char[][] arr;
	static boolean[][] vis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		answer = 0;

		arr = new char[n][n];
		vis = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		mixCandy();
		System.out.println(answer);
	}

	static void swap(int r1, int c1, int r2, int c2) {
		char tmp = arr[r1][c1];
		arr[r1][c1] = arr[r2][c2];
		arr[r2][c2] = tmp;
	}

	static void mixCandy() {
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(!checkBoundary(nr, nc)) continue;
					if(vis[nr][nc] || arr[r][c] == arr[nr][nc]) continue;
					swap(r, c, nr, nc);
					checkCount();
					swap(r, c, nr, nc);
				}
				vis[r][c] = true;
			}
		}
	}

	static void checkCount() {
		int maxCnt = 0;
		for(int i = 0; i < n; i++) {
			int cnt = 1;
			for(int j = 1; j < n; j++) {
				if(arr[i][j] == arr[i][j - 1]) cnt++;
				else {
					maxCnt = Math.max(maxCnt, cnt);
					cnt = 1;
				}
			}
			maxCnt = Math.max(maxCnt, cnt);
		}

		for(int j = 0; j < n; j++) {
			int cnt = 1;
			for(int i = 1; i < n; i++) {
				if(arr[i][j] == arr[i - 1][j]) cnt++;
				else {
					maxCnt = Math.max(maxCnt, cnt);
					cnt = 1;
				}
			}
			maxCnt = Math.max(maxCnt, cnt);
		}
		answer = Math.max(answer, maxCnt);
	}

	static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}
}
