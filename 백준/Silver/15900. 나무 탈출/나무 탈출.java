import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int id;
		List<Integer> link;
		int len;
		
		public Node(int id) {
			this.id = id;
			this.link = new ArrayList<>();
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[n + 1];
		for(int i = 1; i <= n; i++) {
			nodes[i] = new Node(i);
		}
		
		for(int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].link.add(b);
			nodes[b].link.add(a);
		}
		
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(nodes[1]);
		boolean[] vis = new boolean[n + 1];
		vis[1] = true;
		int answer = 0;
		
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			int id = curr.id;
			int len = curr.len;
			boolean isLeaf = true;
			
			for(int i = 0; i < curr.link.size(); i++) {
				int next = curr.link.get(i);
				if(vis[next]) continue;
				isLeaf = false;
				vis[next] = true;
				nodes[next].len += len + 1;
				queue.offer(nodes[next]);
			}
			if(isLeaf) answer += len;
		}
		
		System.out.println(answer % 2 == 0 ? "No" : "Yes");
	}
}
