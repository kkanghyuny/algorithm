import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] map;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		String s = null;
		int tc = 1;
		
		while(!(s = br.readLine()).equals("0")) {
			n = Integer.parseInt(s);
			map = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cost = dij();
			sb.append("Problem ").append(tc++).append(": ").append(cost).append('\n');
		}
		
		System.out.println(sb);	}
	
	static int dij() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[][] cng = new int[n][n];
		for(int i = 0; i < n; i++) {
			Arrays.fill(cng[i], Integer.MAX_VALUE);
		}
		
		pq.add(new Node(0, 0, map[0][0]));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int r = now.r;
			int c = now.c;
			int cost = now.cost;
			
			if(r == n - 1 && c == n - 1) {
				return cost;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				
				if(cost + map[nr][nc] < cng[nr][nc]) {
					cng[nr][nc] = cost + map[nr][nc];
					pq.add(new Node(nr, nc, cost+map[nr][nc]));
				}
			}
		}
		
		return -1;
		
	}
	
	static class Node implements Comparable<Node> {
		int r;
		int c;
		int cost;
		
		public Node() {}

		public Node(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}
