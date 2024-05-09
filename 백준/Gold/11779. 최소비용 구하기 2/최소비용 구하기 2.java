import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node>{
		int id;
		int w;
		int cnt;
		
		public Node(int id, int w, int cnt) {
			this.id = id;
			this.w = w;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
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
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		List<Link>[] link = new List[n + 1];
		int[] answer = new int[n + 1];
		int[] vis = new int[n + 1];
		boolean[] visited = new boolean[n + 1];
		
		for(int i = 0; i <= n; i++) {
			vis[i] = Integer.MAX_VALUE;
			answer[i] = i;
			link[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			link[b].add(new Link(a, w));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.offer(new Node(end, 0, 0));
		vis[end] = 0;
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			int id = curr.id;
			int dist = curr.w;
			
			if(visited[id]) continue;
			
			visited[id] = true;
			
			if(id == start) {
				sb.append(curr.w).append('\n').append(curr.cnt + 1).append('\n');
				break;
			}
			
			for(Link next : link[id]) {
                int distance = dist + next.w;
                int to = next.to;
                
                if(vis[to] <= distance) continue;
                vis[to] = distance;
                answer[to] = id;
                pq.offer(new Node(to, distance, curr.cnt + 1));
            }
		}
		
		for(int i = start; i != end; i = answer[i]) {
			sb.append(i).append(" ");
		}
		sb.append(end);
		
		System.out.println(sb);
	}
}
