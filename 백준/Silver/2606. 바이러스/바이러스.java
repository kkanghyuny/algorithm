import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
	int data;
	List<Node> link;
	
	public Node(int data) {
		this.data = data;
		this.link = new ArrayList<>();
	}
	
	
}

public class Main {
	static int count = 0;
	static boolean[] vis;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[n + 1];
		vis = new boolean[n + 1];
		
		for(int i = 1; i < n + 1; i++) {
			nodes[i] = new Node(i);
		}
		
		int k = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			nodes[first].link.add(nodes[second]);
			nodes[second].link.add(nodes[first]);
		}
		vis[1] = true;
		bfs(nodes[1]);
		
		System.out.println(count - 1);
	}
	
	static void bfs(Node node) {
		Queue<Node> q = new LinkedList<>();
		q.add(node);
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			count++;
			
			for(int i = 0; i < now.link.size(); i++) {
				if(!vis[now.link.get(i).data]) {
					vis[now.link.get(i).data] = true;
					q.add(now.link.get(i));
				}
			}
		}
	}
}
