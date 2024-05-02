import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node>{
		int id;
		int depth;
		int len;
		Node parent;
		List<Node> link;
		List<Integer> dist;
		
		public Node(int id) {
			this.id = id;
			this.link = new ArrayList<>();
			this.dist = new ArrayList<>();
		}
		
		@Override
		public int compareTo(Node o) {
			return o.depth - this.depth;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		Node[] nodes = new Node[n + 1];
		
		for(int i = 1; i <= n; i++) {
			nodes[i] = new Node(i);
		}
		
		for(int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			nodes[a].link.add(nodes[b]);
			nodes[a].dist.add(d);
			nodes[b].link.add(nodes[a]);
			nodes[b].dist.add(d);
		}
		
		Queue<Node> queue = new ArrayDeque<>();
		boolean[] vis = new boolean[n + 1];
		vis[1] = true;
		nodes[1].depth = 1;
		nodes[1].len = 0;
		queue.offer(nodes[1]);
		
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			int depth = curr.depth;
			int len = curr.len;
			
			for(int i = 0; i < curr.link.size(); i++) {
				if(!vis[curr.link.get(i).id]) {
					vis[curr.link.get(i).id] = true;
					curr.link.get(i).depth = depth + 1;
					curr.link.get(i).len = len + curr.dist.get(i);
					curr.link.get(i).parent = curr;
					queue.offer(curr.link.get(i));
				}
			}
		}
		
		int m = Integer.parseInt(br.readLine());
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for(int t = 0; t < m; t++) {
			pq.clear();
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int ans = nodes[a].len + nodes[b].len;
			
			pq.offer(nodes[a]);
			pq.offer(nodes[b]);
			
			Node curr = pq.poll();
			
			while(curr.id != pq.peek().id) {
				pq.offer(curr.parent);
				curr = pq.poll();
			}
			
			Node fin = pq.poll();
			
			ans = ans - (2 * fin.len);
			
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
		
	}
}
