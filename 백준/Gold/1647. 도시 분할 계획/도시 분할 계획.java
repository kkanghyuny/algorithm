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
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n + 1];
		
		for(int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			pq.offer(new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		PriorityQueue<Integer> pq2 = new PriorityQueue<>((a, b) -> b - a);
		
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
				pq2.offer(curr.w);
			}
		}
		
		System.out.println(ans - pq2.poll());
	}
	
	static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}
	
	static void union(int a, int b) {
		if(a > b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		parent[b] = a;
	}
}
