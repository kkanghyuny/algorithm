import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int w;
		
		public Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return o.w - this.w;
		}
	}
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int ans1 = 0;
		int ans2 = 0;		
		PriorityQueue<Edge> pq1 = new PriorityQueue<>();
		PriorityQueue<Edge> pq2 = new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i = 0; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			pq1.offer(new Edge(a, b, c));
			pq2.offer(new Edge(a, b, c));
		}
		
		int pick = 0;
		parent = new int[n + 1];
		
		for(int i = 0; i <= n; i++) {
			parent[i] = i;
		}
		
		while(!pq1.isEmpty() && pick < n) {
			Edge curr = pq1.poll();
			int a = find(curr.from);
			int b = find(curr.to);
			
			if(a != b) {
				union(a, b);
				pick++;
				if(curr.w == 0) ans1++;
			}
		}
		
		pick = 0;
		
		for(int i = 0; i <= n; i++) {
			parent[i] = i;
		}
		
		while(!pq2.isEmpty() && pick < n) {
			Edge curr = pq2.poll();
			int a = find(curr.from);
			int b = find(curr.to);
			
			if(a != b) {
				union(a, b);
				pick++;
				if(curr.w == 0) ans2++;
			}
		}
		
		ans1 *= ans1;
		ans2 *= ans2;
		
		System.out.println(Math.abs(ans1 - ans2));
		
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
