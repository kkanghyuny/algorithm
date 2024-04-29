import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node>{
		int id;
		int depth;
		int size = 1;
		List<Node> link;

		public Node(int id) {
			this.id = id;
			link = new ArrayList<>();
		}
		
		@Override
		public int compareTo(Node o) {
			return o.depth - this.depth;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		Node[] nodes = new Node[n + 1];

		for (int i = 1; i <= n; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			nodes[a].link.add(nodes[b]);
			nodes[b].link.add(nodes[a]);
		}

		boolean[] vis = new boolean[n + 1];

		Queue<Node> queue = new ArrayDeque<>();
		PriorityQueue<Node> queue2 = new PriorityQueue<>();
		nodes[r].depth = 1;
		vis[r] = true;
		queue.offer(nodes[r]);

		while (!queue.isEmpty()) {
			Node curr = queue.poll();
			int depth = curr.depth;
			boolean last = true;

			for (int i = 0; i < curr.link.size(); i++) {
				if (!vis[curr.link.get(i).id]) {
					last = false;
					Node next = curr.link.get(i);
					vis[next.id] = true;
					next.depth = depth + 1;
					queue.offer(next);
				}
			}
			
			if(last) {
				queue2.offer(curr);
			}
		}
		
		vis = new boolean[n + 1];
		
		while(!queue2.isEmpty()) {
			Node curr = queue2.poll();
			int depth = curr.depth;
			int plus = 0;
			
			for(int i = 0; i < curr.link.size(); i++) {
				if(curr.link.get(i).depth == depth - 1 && !vis[curr.link.get(i).id]) {
					vis[curr.link.get(i).id] = true;
					queue2.offer(curr.link.get(i));
				} else if(curr.link.get(i).depth == depth + 1){
					plus += curr.link.get(i).size;
				}
			}
			
			curr.size += plus;
		}

		for (int i = 0; i < q; i++) {
			int u = Integer.parseInt(br.readLine());

			sb.append(nodes[u].size).append('\n');
		}

		System.out.println(sb);

	}
}
