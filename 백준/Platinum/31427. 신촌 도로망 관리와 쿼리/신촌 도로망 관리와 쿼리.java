import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int start;
		int end;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	static int n, m, q;
	static int[] parent;
	static boolean[] vis;
	static Queue<Node>[] queueList;
	static Map<String, String> map;
	static String s = "ABCDE";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		vis = new boolean[5];

		queueList = new Queue[5];
		map = new HashMap<>();

		for (int i = 0; i < 5; i++) {
			queueList[i] = new ArrayDeque<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int idx = st.nextToken().charAt(0) - 'A';

			queueList[idx].offer(new Node(start, end));
		}

		for (int i = 0; i < 5; i++) {
			String val = "" + s.charAt(i);
			vis[i] = true;
			perm(val);
			vis[i] = false;
		}

		for (int i = 0; i < q; i++) {
			int[] arr = new int[5];
			long ans = 0;

			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < 5; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}

			vis = new boolean[5];

			String key = "";

			for (int j = 0; j < 5; j++) {
				int min = Integer.MAX_VALUE;
				int minIdx = 0;
				for (int k = 0; k < 5; k++) {
					if (vis[k])
						continue;
					if (min > arr[k]) {
						min = Math.min(min, arr[k]);
						minIdx = k;
					}
				}
				vis[minIdx] = true;
				key = key + (char)('A' + minIdx);
			}
			
			st = new StringTokenizer(map.get(key));
			
			for(int j = 0; j < 5; j++) {
				ans += Long.parseLong(st.nextToken()) * (arr[j]);
			}
			
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}

	static void perm(String perm) {
		if (perm.length() == 5) {
			MST(perm);
			return;
		}

		for (int i = 0; i < 5; i++) {
			if (vis[i])
				continue;
			vis[i] = true;
			perm(perm + s.charAt(i));
			vis[i] = false;
		}
	}

	static int find(int n) {
		if (parent[n] == n)
			return n;
		return parent[n] = find(parent[n]);
	}

	static void union(int a, int b) {
		if (a > b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		parent[b] = a;
	}

	static void MST(String perm) {
		int pick = 0;
		int[] cnt = new int[5];
		parent = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < 5 && pick < n - 1; i++) {
			int idx = perm.charAt(i) - 'A';

			int size = queueList[idx].size();

			while (size-- > 0 && pick < n - 1) {
				Node curr = queueList[idx].poll();

				int find1 = find(curr.start);
				int find2 = find(curr.end);

				if (find1 != find2) {
					union(find1, find2);
					pick++;
					cnt[idx]++;
				}
				queueList[idx].offer(curr);
			}
		}

		String value = "";
		for (int i = 0; i < 5; i++) {
			value += cnt[i] + " ";
		}

		map.put(perm, value);
	}

}
