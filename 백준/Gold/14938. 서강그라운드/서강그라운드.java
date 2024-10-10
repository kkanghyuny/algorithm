import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	static class Node {
		int id;
		List<Integer> link;
		List<Integer> distList;
		public Node(int id) {
			this.id = id;
			link = new ArrayList<>();
			distList = new ArrayList<>();
		}
	}

	static class Human implements Comparable<Human> {
		int nowAt;
		int dist;
		public Human(int nowAt, int dist) {
			this.nowAt = nowAt;
			this.dist = dist;
		}

		@Override
		public int compareTo(Human o) {
			return this.dist - o.dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int answer = 0;

		Node[] nodes = new Node[n + 1];
		int[] items = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			nodes[i] = new Node(i);
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}

		while(r-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			nodes[a].link.add(b);
			nodes[b].link.add(a);
			nodes[a].distList.add(dist);
			nodes[b].distList.add(dist);
		}

		PriorityQueue<Human> q = new PriorityQueue<>();
		for(int i = 1; i <= n; i++) {
			q.offer(new Human(i, 0));
			int cnt = 0;
			boolean[] vis = new boolean[n + 1];

			while(!q.isEmpty()) {
				Human curr = q.poll();
				int now = curr.nowAt;
				int dist = curr.dist;
				if(vis[now]) continue;
				vis[now] = true;
				cnt += items[now];
				for(int j = 0; j < nodes[now].link.size(); j++) {
					int next = nodes[now].link.get(j);
					int sumDist = dist + nodes[now].distList.get(j);
					if(sumDist <= m && !vis[next]) {
						q.offer(new Human(next, sumDist));
					}
				}
			}
			answer = Math.max(answer, cnt);
		}
		System.out.println(answer);
	}
}
