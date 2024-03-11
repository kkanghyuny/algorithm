import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, g, r;
	static int maxFlower = Integer.MIN_VALUE;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static boolean[][] vis;
	static boolean[] visArr;
	static char[][] liquid;
	static List<Point> arr;
	static Queue<Point> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		vis = new boolean[n][m];
		liquid = new char[n][m];
		arr = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				liquid[i][j] = ' ';
				int val = Integer.parseInt(st.nextToken());
				if (val == 0) {
					vis[i][j] = true;
				} else if (val == 2) {
					arr.add(new Point(i,j));
				}
			}
		}
		
		visArr = new boolean[arr.size()];
		
		for(int i = 0; i < arr.size(); i++) {
			Point now = arr.get(i);
			int row = now.x;
			int col = now.y;
			visArr[i] = true;
			vis[row][col] = true;
			liquid[row][col] = 'g';
//			System.out.println(row + " " + col);
			green(i, 1);
			visArr[i] = false;
			vis[row][col] = false;
			liquid[row][col] = ' ';
		}
		
		System.out.println(maxFlower);

	}

	public static void green(int idx, int greenCnt) {
		if(greenCnt == g) {
			for(int i = 0; i < arr.size(); i++) {
				if(visArr[i]) continue;
				Point now = arr.get(i);
				int row = now.x;
				int col = now.y;
				visArr[i] = true;
				vis[row][col] = true;
				liquid[row][col] = 'r';
				red(i, 1);
				visArr[i] = false;
				vis[row][col] = false;
				liquid[row][col] = ' ';
			}
		}
		
		for(int i = idx + 1; i < arr.size(); i++) {
			if(visArr[i]) continue;
			Point now = arr.get(i);
			int row = now.x;
			int col = now.y;
			visArr[i] = true;
			vis[row][col] = true;
			liquid[row][col] = 'g';
			green(i, greenCnt + 1);
			visArr[i] = false;
			vis[row][col] = false;
			liquid[row][col] = ' ';
		}
	}
	
	public static void red(int idx, int redCnt) {
		if(redCnt == r) {
//			System.out.println("bfs 돌아유");
			bfs();
		}
		
		for(int i = idx + 1; i < arr.size(); i++) {
			if(visArr[i]) continue;
			Point now = arr.get(i);
			int row = now.x;
			int col = now.y;
			visArr[i] = true;
			vis[row][col] = true;
			liquid[row][col] = 'r';
			red(i, redCnt + 1);
			visArr[i] = false;
			vis[row][col] = false;
			liquid[row][col] = ' ';
		}
	}
	
	public static void bfs() {
		int flowerCnt = 0;
		char[][] queueLiquid = new char[n][m];
		boolean[][] queueVis = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				queueLiquid[i][j] = liquid[i][j];
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				queueVis[i][j] = vis[i][j];
			}
		}
		
		
		for(int i = 0; i < arr.size(); i++) {
			Point now = arr.get(i);
			if(queueLiquid[now.x][now.y] == 'g') {
				queue.add(now);
			}
		}
		
		for(int i = 0; i < arr.size(); i++) {
			Point now = arr.get(i);
			if(queueLiquid[now.x][now.y] == 'r') {
				queue.add(now);
			}
		}
		
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			int row = now.x;
			int col = now.y;
			
			if(queueLiquid[row][col] == 'F') continue;
			
			
			queueVis[row][col] = true;
			
			for(int d = 0; d < 4; d++) {
				int nr = row + dr[d];
				int nc = col + dc[d];
				
				if(nr >= 0 && nr < n && nc >= 0 && nc < m) {
					if(queueLiquid[row][col] == 'g' && (!queueVis[nr][nc])) {
//						System.out.println(row+ " " + col + " 일 때 " + nr + " " + nc + " 로 그린 영역확장");
						queueVis[nr][nc] = true;
						queueLiquid[nr][nc] = 'g';
						queue.add(new Point(nr, nc));
					} else if(queueLiquid[row][col] == 'r') {
						if(queueLiquid[nr][nc] == 'g') {
//							System.out.println(nr+ " " + nc + " 꽃 완성");
							queueLiquid[nr][nc] = 'F';
						} else if (!queueVis[nr][nc]) {
//							System.out.println(row+ " " + col + " 일 때 " + nr + " " + nc + " 로 레드 영역확장");
							queueVis[nr][nc] = true;
							queueLiquid[nr][nc] = 'r';
							queue.add(new Point(nr, nc));
						}
					}
				}
			}
			
			queueLiquid[row][col] = ' ';
			
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(queueLiquid[i][j] == 'F') {
					flowerCnt++;
				}
			}
		}
		
		maxFlower = Math.max(maxFlower, flowerCnt);
	}
}
