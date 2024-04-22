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
		List<Node> link;
		int group;

		public Node(int id) {
			this.id = id;
			this.link = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int k = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < k; tc++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			Node[] nodes = new Node[v + 1];

			for (int i = 1; i < v + 1; i++) {
				nodes[i] = new Node(i);
			}

			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				nodes[a].link.add(nodes[b]);
				nodes[b].link.add(nodes[a]);
			}

			Queue<Node> queue = new ArrayDeque<>();
			boolean isRight = true;
			boolean[] vis = new boolean[v + 1];

			for (int i = 1; i < v + 1; i++) {
				if (!vis[i]) {
					vis[i] = true;
					nodes[i].group = 1;

					queue.offer(nodes[i]);

					a: while (!queue.isEmpty()) {
						Node curr = queue.poll();
						int id = curr.id;
						List<Node> list = curr.link;
						int group = curr.group;

						for (int j = 0; j < list.size(); j++) {
							if (!vis[list.get(j).id]) {
								vis[list.get(j).id] = true;
								nodes[list.get(j).id].group = 1 - group;
								queue.offer(nodes[list.get(j).id]);
							} else {
								if (list.get(j).group == group) {
									isRight = false;
									break a;
								}
							}
						}
					}

				}
			}

			if (isRight) {
				sb.append("YES").append('\n');
			} else {
				sb.append("NO").append('\n');
			}
		}
		System.out.println(sb);
	}
}
