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
		int good;
		List<Node> childs;
		
		public Node(int id) {
			this.id = id;
			this.good = 0;
			this.childs = new ArrayList<>();
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Node[] nodes = new Node[n + 1];
		for(int i = 1; i <= n; i++) {
			nodes[i] = new Node(i);
		}
		
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for(int i = 2; i <= n; i++) {
			int parent = Integer.parseInt(st.nextToken());
			nodes[parent].childs.add(nodes[i]);
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int plus = Integer.parseInt(st.nextToken());
			nodes[num].good += plus;
		}
		
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(nodes[1]);
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			int plus = curr.good;
			
			for(int i = 0; i < curr.childs.size(); i++) {
				int idx = curr.childs.get(i).id;
				nodes[idx].good += plus;
				queue.offer(nodes[idx]);
			}
		}
		
		for(int i = 1; i <= n; i++) {
			sb.append(nodes[i].good).append(' ');
		}
		System.out.println(sb);
	}
}
