import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
	static int n, m, answer;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] arr;
	static List<Point> cctvList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		answer = 65;
		cctvList = new ArrayList<>();

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] > 0 && arr[i][j] < 6) cctvList.add(new Point(i, j));
			}
		}
		createCctv(0);
		System.out.println(answer);
	}

	static void createCctv(int idx) {
		if(idx >= cctvList.size()) {
			count();
			return;
		}
		int currX = cctvList.get(idx).x;
		int currY = cctvList.get(idx).y;

		switch(arr[currX][currY]) {
			case 1: // 한 방향
				for(int d = 0; d < 4; d++) {
					watch(currX, currY, d);
					createCctv(idx + 1);
					unwatch(currX, currY, d);
				}
				break;
			case 2: // 양 방향
				for(int d = 0; d < 2; d++) {
					watch(currX, currY, d);
					watch(currX, currY, d + 2);
					createCctv(idx + 1);
					unwatch(currX, currY, d);
					unwatch(currX, currY, d + 2);
				}
				break;
			case 3: // 직각 방향
				for(int d = 0; d < 4; d++) {
					watch(currX, currY, d);
					watch(currX, currY, (d + 1) % 4);
					createCctv(idx + 1);
					unwatch(currX, currY, d);
					unwatch(currX, currY, (d + 1) % 4);
				}
				break;
			case 4: // 세 방향
				for(int d = 0; d < 4; d++) {
					watch(currX, currY, d);
					watch(currX, currY, (d + 1) % 4);
					watch(currX, currY, (d + 2) % 4);
					createCctv(idx + 1);
					unwatch(currX, currY, d);
					unwatch(currX, currY, (d + 1) % 4);
					unwatch(currX, currY, (d + 2) % 4);
				}
				break;
			case 5: // 네 방향
				for(int d = 0; d < 4; d++) {
					watch(currX, currY, d);
				}
				createCctv(idx + 1);
				for(int d = 0; d < 4; d++) {
					unwatch(currX, currY, d);
				}
				break;
		}
	}

	static void watch(int x, int y, int direction) {
		int nx = x + dr[direction];
		int ny = y + dc[direction];
		while(checkBoundary(nx, ny) && arr[nx][ny] != 6) {
			if(arr[nx][ny] <= 0) arr[nx][ny]--; // 감시 영역 표시
			nx += dr[direction];
			ny += dc[direction];
		}
	}

	static void unwatch(int x, int y, int direction) {
		int nx = x + dr[direction];
		int ny = y + dc[direction];
		while(checkBoundary(nx, ny) && arr[nx][ny] != 6) {
			if(arr[nx][ny] < 0) arr[nx][ny]++; // 감시 영역 해제
			nx += dr[direction];
			ny += dc[direction];
		}
	}

	static void count() {
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == 0) cnt++;
			}
		}
		answer = Math.min(answer, cnt);
	}

	static boolean checkBoundary(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}
}
