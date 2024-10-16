import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		Node[] nodes = new Node[n + 1];
		for(int i = 1; i <= n; i++) {
			nodes[i] = new Node(i);
		}

		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			nodes[b].link.add(a);
			nodes[b].distList.add(dist);
		}

		int[][] arr = new int[n + 1][n + 1];
		// 0번 인덱스 = 현재 위치, 1번 인덱스 이전까지의 최대 허들 높이
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		for(int i = 1; i <= n; i++) {
			pq.clear();
			pq.offer(new int[] {i, 0});
			boolean[] vis = new boolean[n + 1];
			int cnt = 0;

			while(!pq.isEmpty() && cnt < n) {
				int[] curr = pq.poll();
				int now = curr[0];
				int dist = curr[1];

				if(vis[now]) continue;
				vis[now] = true;
				cnt++;
				arr[now][i] = dist;

				for(int j = 0; j < nodes[now].link.size(); j++) {
					int next = nodes[now].link.get(j);
					int nextDist = nodes[now].distList.get(j);
					if(vis[next]) continue;
					pq.offer(new int[] {next, Math.max(dist, nextDist)});
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(arr[start][end] != 0) sb.append(arr[start][end]);
			else sb.append(-1);
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
