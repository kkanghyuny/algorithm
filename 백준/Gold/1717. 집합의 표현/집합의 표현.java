import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] group;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		group = new int[n + 1];
		for(int i = 0; i <= n; i++) {
			group[i] = i;
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			switch(cmd) {
			case 0: {
				union(find(a), find(b));
				break;
			}
			case 1: {
				if(find(a) == find(b)) sb.append("YES\n");
				else sb.append("NO\n");
			}
			}
		}
		System.out.println(sb);
	}
	
	static int find(int n) {
		if(group[n] == n) return n;
		return group[n] = find(group[n]);
	}
	
	static void union(int a, int b) {
		if(a > b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		group[b] = a;
	}
}
