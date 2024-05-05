import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge> {
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
	static Set<Integer> set;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		parent = new int[n + 1];
		set = new HashSet<>();
		
		for(int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < k; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		int pick = 0;
		int ans = 0;
		
		while(!pq.isEmpty() && pick < n - k) {
			Edge curr = pq.poll();
			
			int find1 = find(curr.u);
			int find2 = find(curr.v);
			
			if(set.contains(find1) && set.contains(find2)) continue;
			
			if(find1 != find2) {
				union(find1, find2);
				pick++;
				ans += curr.w;
			}
		}
		
		System.out.println(ans);
	}
	
	static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}
	
	static void union(int a, int b) {
		if(set.contains(b)) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		parent[b] = a;
	}
}
