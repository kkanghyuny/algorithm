import java.io.*;
import java.util.*;

public class Main {
	static int n, m, min, max;
	static int[] minTree, maxTree, arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int size = (int)Math.pow(2, (int)Math.ceil(Math.log(n) / Math.log(2)) + 1);
		minTree = new int[size];
		maxTree = new int[size];
		makeTree(0, minTree, 1, n, 1);
		makeTree(1, maxTree, 1, n, 1);

		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			min = 1_000_000_001;
			max = 0;
			find(0, minTree, 1, n, 1, a, b);
			find(1, maxTree, 1, n, 1, a, b);
			sb.append(min).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

	static void makeTree(int type, int[] tree, int st, int ed, int node) {
		if(st == ed) tree[node] = arr[st];
		else {
			int mid = (st + ed) / 2;
			makeTree(type, tree, st, mid, node * 2);
			makeTree(type, tree, mid + 1, ed, node * 2 + 1);

			if(type == 0) {
				tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
			} else {
				tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
			}
		}
	}

	static void find(int type, int[] tree, int st, int ed, int node, int l, int r) {
		if(l > ed || r < st) return;
		if(l <= st && ed <= r) {
			if(type == 0) {
				min = Math.min(min, tree[node]);
			} else {
				max = Math.max(max, tree[node]);
			}
			return;
		}

		int mid = (st + ed) / 2;
		find(type, tree, st, mid, node * 2, l, r);
		find(type, tree, mid + 1, ed, node * 2 + 1, l, r);
	}
}
