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
		int w;
		
		public Node(int id, int w) {
			this.id = id;
			this.w = w;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	
	static class Link{
		int to;
		int dist;
		
		public Link(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		PriorityQueue<Node> pq;
		List<Link>[] link;
		boolean[] vis;
		
		for(int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int ans = 0;
			int cnt = 0;
			
			link = new List[n + 1];
			vis = new boolean[n + 1];
			
			for(int i = 0; i <= n; i++) {
				link[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				// b가 감염되면 w 이후 a가 감염 ( 방햠을 가짐 )
				link[b].add(new Link(a, w));
			}
			
			pq = new PriorityQueue<>();
			
			pq.offer(new Node(c, 0));
			
			while(!pq.isEmpty()) {
				Node curr = pq.poll();
				int now = curr.id;
				int nowDist = curr.w;
				
				if(vis[now]) continue;
				
				vis[now] = true;
				cnt++;
				ans = nowDist;
				
				for(int i = 0; i < link[now].size(); i++) {
					int next = link[now].get(i).to;
					int nextDist = link[now].get(i).dist;
					
					if(vis[next]) continue;
					pq.offer(new Node(next, nowDist + nextDist));
				}
			}
			sb.append(cnt).append(" ").append(ans).append('\n');
		}
		System.out.println(sb);
	}
}
