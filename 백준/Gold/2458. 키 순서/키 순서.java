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
		List<Node> parent;
		List<Node> child;

		public Node(int id) {
			this.id = id;
			this.parent = new ArrayList<>();
			this.child = new ArrayList<>();
		}
	}

	static int parents, childs;
	static boolean[] vis;
	static Node[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int ans = 0;

		nodes = new Node[n + 1];

		for (int i = 1; i <= n; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			nodes[a].parent.add(nodes[b]);
			nodes[b].child.add(nodes[a]);
		}

		for (int i = 1; i <= n; i++) {
			parents = 0;
			vis = new boolean[n + 1];
			vis[i] = true;
			findParent(i);
			
			childs = 0;
			vis = new boolean[n + 1];
			vis[i] = true;
			findChild(i);

			if (parents + childs == n - 1) {
				ans++;
			}
		}
		
		System.out.println(ans);
	}

	static void findParent(int n) {
		Queue<Node> queue = new ArrayDeque<>();

		queue.offer(nodes[n]);

		while (true) {
			if(queue.isEmpty()) break;
			
			Node curr = queue.poll();
			
			for (int i = 0; i < curr.parent.size(); i++) {
				if (vis[curr.parent.get(i).id])
					continue;
				vis[curr.parent.get(i).id] = true;
				parents++;
				queue.offer(curr.parent.get(i));
			}
		}
	}

	static void findChild(int n) {
		Queue<Node> queue = new ArrayDeque<>();

		queue.offer(nodes[n]);

		while (true) {
			if(queue.isEmpty()) break;
			
			Node curr = queue.poll();
			
			for (int i = 0; i < curr.child.size(); i++) {
				if (vis[curr.child.get(i).id])
					continue;
				vis[curr.child.get(i).id] = true;
				childs++;
				queue.offer(curr.child.get(i));
			}
		}
	}
}
