import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node>{
		int r;
		int c;
		int brk;
		
		public Node(int r, int c, int brk) {
			this.r = r;
			this.c = c;
			this.brk = brk;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.brk - o.brk;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, 1, -1};
		
		int[][] arr = new int[n][m];
		boolean[][] vis = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.offer(new Node(0, 0, 0));
		vis[0][0] = true;
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			int r = curr.r;
			int c = curr.c;
			int brk = curr.brk;
			
			if(r == n - 1 && c == m - 1) {
				System.out.println(brk);
				break;
			}
			
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr >= n || nr < 0 || nc >= m || nc < 0 || vis[nr][nc]) continue;
				
				vis[nr][nc] = true;
				
				if(arr[nr][nc] == 1) {
					pq.offer(new Node(nr, nc, brk + 1));
				} else {
					pq.offer(new Node(nr, nc, brk));
				}
			}
		}
		
	}
}
