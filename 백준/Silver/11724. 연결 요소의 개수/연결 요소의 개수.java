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
		int data;
		List<Node> link;
		
		public Node() {
			link = new ArrayList<>();
		}

		public Node(int data) {
			this.data = data;
			link = new ArrayList<>();
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Node[] nodes = new Node[n];
		boolean[] vis = new boolean[n];
		for(int i = 0; i < n; i++) {
			nodes[i] = new Node(i);
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			nodes[a].link.add(nodes[b]);
			nodes[b].link.add(nodes[a]);
		}
		
		Queue<Node> queue = new ArrayDeque<>();
		int count = 0;
		for(int i = 0; i < n; i++) {
			if(!vis[i]) {
				queue.offer(nodes[i]);
				count++;
				vis[i] = true;
				while(!queue.isEmpty()) {
					Node curr = queue.poll();
					
					for(int j = 0; j < curr.link.size(); j++) {
						if(vis[curr.link.get(j).data]) continue;
						vis[curr.link.get(j).data] = true;
						queue.offer(curr.link.get(j));
					}
				}
			}
			
		}
		
		System.out.println(count);
		
	}
}
