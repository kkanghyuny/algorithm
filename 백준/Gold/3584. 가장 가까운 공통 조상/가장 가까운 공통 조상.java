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
	static class Node{
		int id;
		Node parent;
		List<Node> childs;
		int depth;
		
		public Node(int id) {
			this.id = id;
			this.childs = new ArrayList<>();
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			Node[] nodes = new Node[n + 1];
			
			for(int i = 1; i <= n; i++) {
				nodes[i] = new Node(i);
			}
			
			for(int i = 1; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				nodes[a].childs.add(nodes[b]);
				nodes[b].parent = nodes[a];
			}
			
			st = new StringTokenizer(br.readLine());
			int findA = Integer.parseInt(st.nextToken());
			int findB = Integer.parseInt(st.nextToken());
			
			int zero = 0;
			for(int i = 1; i <= n; i++) {
				if(nodes[i].parent == null) {
					zero = i;
					break;
				}
			}
			int depth = 1;
			nodes[zero].depth = depth++;
			
			Queue<Node> queue = new ArrayDeque<>();
			
			queue.offer(nodes[zero]);
			
			while(true) {
				int size = queue.size();
				if(size == 0) break;
				
				while(size-- > 0) {
					Node curr = queue.poll();
					
					for(int i = 0; i < curr.childs.size(); i++) {
						nodes[curr.childs.get(i).id].depth = depth;
						queue.offer(curr.childs.get(i));
					}
				}
				depth++;
			}
			
			Queue<Node> queue1 = new ArrayDeque<>();
			Queue<Node> queue2 = new ArrayDeque<>();
			queue1.offer(nodes[findA]);
			queue2.offer(nodes[findB]);
			
			while(queue1.peek().id != queue2.peek().id) {
				if(queue1.peek().depth > queue2.peek().depth) {
					queue1.offer(queue1.poll().parent);
				} else {
					queue2.offer(queue2.poll().parent);
				}
			}
			
			sb.append(queue1.peek().id).append('\n');
		}
		System.out.println(sb);
	}
}
