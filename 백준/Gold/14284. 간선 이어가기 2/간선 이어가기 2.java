import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node>{
		int id;
		int dist;
		
		public Node(int id, int dist) {
			this.id = id;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}
	
	static class Link {
		int to;
		int w;
		
		public Link(int to, int w) {
			this.to = to;
			this.w = w;
		}
	}
	
	static int n, m;
	static List<Link>[] link;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		link = new List[n + 1];
		
		for(int i = 0; i <= n; i++) {
			link[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			link[start].add(new Link(end, w));
			link[end].add(new Link(start, w));
		}
		
		boolean[] vis = new boolean[n + 1];
		
		st = new StringTokenizer(br.readLine());
		int point1 = Integer.parseInt(st.nextToken());
		int point2 = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(point1, 0));
		vis[point1] = true;
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			int now = curr.id;
			int dis = curr.dist;
			
			if(now == point2) {
				System.out.println(dis);
				break;
			}
			
			vis[now] = true;
			
			for(int i = 0; i < link[now].size(); i++) {
				int to = link[now].get(i).to;
				int w = link[now].get(i).w;
				if(vis[to]) continue;
				
				pq.offer(new Node(to, dis + w));
			}
		}
	}
}
