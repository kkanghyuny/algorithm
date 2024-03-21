import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Node[] nodes;
	static int n;
	static boolean[] vis;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		
		nodes = new Node[n + 1];
		vis = new boolean[n + 1];
		
		for(int i = 1; i < n + 1; i++) {
			nodes[i] = new Node(i);
		}
		
		for(int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			nodes[a].link.add(nodes[b]);
			nodes[b].link.add(nodes[a]);
		}
		
		vis[1] = true;
		bfs(1);
		
		for(int i = 2; i < n + 1; i++) {
			sb.append(nodes[i].parent.id).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int start) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(nodes[start]);
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			
			for(int i = 0; i < now.link.size(); i++) {
				if(vis[now.link.get(i).id]) continue;
				vis[now.link.get(i).id] = true;
				nodes[now.link.get(i).id].parent = now;
				queue.add(now.link.get(i));
			}
		}
	}
	
	static class Node{
		int id;
		Node parent;
		List<Node> link;
		
		public Node() {}

		public Node(int id) {
			this.id = id;
			link = new ArrayList<>();
		}
		
	}
}
