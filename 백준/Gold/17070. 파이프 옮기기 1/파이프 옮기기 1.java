import java.io.*;
import java.util.*;

public class Main {
	static int n, answer;
	static int[] dr = {0, 1, 1};
	static int[] dc = {1, 1, 0};
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 1, 0);
		System.out.println(answer);
	}

	// type 0 = 현재 가로 상태, 1 = 현재 세로 상태, 2 = 현재 대각선 상태
	static void dfs(int r, int c, int type) {
		if(r == n - 1 && c == n - 1) {
			answer++;
			return;
		}

		switch(type) {
			case 0: {
				for(int d = 0; d < 2; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
					if(arr[nr][nc] == 1) continue;

					if(d == 0) {
						dfs(nr, nc, 0);
					} else {
						if(arr[r + 1][c] != 1 && arr[r][c + 1] != 1) dfs(nr, nc, 2);
					}
				}
				break;
			}
			case 1: {
				for(int d = 1; d < 3; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
					if(arr[nr][nc] == 1) continue;

					if(d == 1) {
						if(arr[r + 1][c] != 1 && arr[r][c + 1] != 1) dfs(nr, nc, 2);
					} else {
						dfs(nr, nc, 1);
					}
				}
				break;
			}
			case 2: {
				for(int d = 0; d < 3; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
					if(arr[nr][nc] == 1) continue;

					if(d == 0) {
						dfs(nr, nc, 0);
					} else if(d == 1) {
						if(arr[r + 1][c] != 1 && arr[r][c + 1] != 1) dfs(nr, nc, 2);
					} else {
						dfs(nr, nc, 1);
					}
				}
			}
		}
	}
}
