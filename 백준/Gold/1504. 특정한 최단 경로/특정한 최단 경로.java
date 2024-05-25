import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int end;
		int dist;

		public Node(int end, int dist) {
			this.end = end;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}
	
	static int n, e;
	static int INF = 200000001;
	static List<Node>[] arr;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[n + 1];
		for(int i = 0; i <= n; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			arr[a].add(new Node(b, c));
			arr[b].add(new Node(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		
		int one = Integer.parseInt(st.nextToken());
		int two = Integer.parseInt(st.nextToken());
		
		int dist1 = 0;
		int dist2 = 0;
		
		dist1 = dij(1, one) + dij(one, two) + dij(two, n);
		dist2 = dij(1, two) + dij(two, one) + dij(one, n);
		
		int ans = (dist1 >= INF && dist2 >= INF) ? -1 : Math.min(dist1, dist2);
		
		System.out.println(ans);
	}
	
	static int dij(int st, int ed) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[n + 1];
		for(int i = 0; i <= n; i++) {
			dist[i] = INF;
		}
		
		pq.offer(new Node(st, 0));
		dist[st] = 0;
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			int now = curr.dist;
			int id = curr.end;
			
			if(dist[id] < now) continue;
			
			for(Node next: arr[id]) {
				int nextId = next.end;
				int nextDist = next.dist;
				
				int cost = now + nextDist;
				if(cost < dist[nextId]) {
					dist[nextId] = cost;
					pq.offer(new Node(nextId, cost));
				}
			}
		}
		
		return dist[ed];
	}

}
