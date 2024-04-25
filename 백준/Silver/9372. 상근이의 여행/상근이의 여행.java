import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int id;
		List<Node> link;
		
		public Node(int id) {
			this.id = id;
			this.link = new ArrayList<>();
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			Node[] nodes = new Node[n + 1];
			boolean[] vis = new boolean[n + 1];
			
			for(int i = 1; i <= n; i++) {
				nodes[i] = new Node(i);
			}
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				nodes[a].link.add(nodes[b]);
				nodes[b].link.add(nodes[a]);
			}
			
			Queue<Node> queue = new ArrayDeque<>();
			
			vis[1] = true;
			int count = 0;
			
			queue.offer(nodes[1]);
			
			while(!queue.isEmpty()) {
				Node curr = queue.poll();
				
				for(int i = 0; i < curr.link.size(); i++) {
					if(!vis[curr.link.get(i).id]) {
						vis[curr.link.get(i).id] = true;
						queue.offer(curr.link.get(i));
						count++;
					}
				}
			}
			sb.append(count).append('\n');
		}
		System.out.println(sb);
	}
}
