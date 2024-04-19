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
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int minHuman = 0;
		int minBacon = Integer.MAX_VALUE;
		Queue<Node> queue = new ArrayDeque<>();
		
		Node[] nodes = new Node[n + 1];
		boolean[] vis = new boolean[n + 1];
		
		for(int i = 0; i < n + 1; i++) {
			nodes[i] = new Node(i);
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			nodes[a].link.add(nodes[b]);
			nodes[b].link.add(nodes[a]);
		}
		
		for(int i = 1; i < n + 1; i++) {
			vis = new boolean[n + 1];
			vis[i] = true;
			int count = 1;
			int bacon = 0;
			queue.offer(nodes[i]);
			
			while(true) {
				int size = queue.size();
				if(size == 0) break;
				
				while(size-- > 0) {
					Node curr = queue.poll();
					
					for(int t = 0; t < curr.link.size(); t++) {
						if(!vis[curr.link.get(t).id]) {
							vis[curr.link.get(t).id] = true;
							bacon += count;
							queue.offer(curr.link.get(t));
						}
					}
				}
				count++;
			}
			if(minBacon > bacon) {
				minBacon = bacon;
				minHuman = i;
			}
		}
		
		System.out.println(minHuman);
	}
}
