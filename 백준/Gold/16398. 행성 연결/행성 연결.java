import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge>{
		int u;
		int v;
		int w;
		
		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	static int[] group;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		group = new int[n];
		
		for(int i = 0; i < n; i++) {
			group[i] = i;
		}
		int[][] arr = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for(int i = 0; i < n; i++) {
			for(int j = i + 1; j < n; j++) {
				pq.offer(new Edge(i, j, arr[i][j]));
			}
		}
		
		int pick = 0;
		long ans = 0;
		
		while(!pq.isEmpty() && pick < n - 1) {
			Edge curr = pq.poll();
			
			int find1 = find(curr.u);
			int find2 = find(curr.v);
			
			if(find1 != find2) {
				union(find1, find2);
				pick++;
				ans += curr.w;
			}
		}
		
		System.out.println(ans);
		
	}
	
	static int find(int n) {
		if(group[n] == n) return n;
		return group[n] = find(group[n]);
	}
	
	static void union(int a, int b) {
		if(a > b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		group[b] = a; 
	}
}
