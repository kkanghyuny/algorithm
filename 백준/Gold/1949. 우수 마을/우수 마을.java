import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static Node[] nodes;
	static int[][] dp;
	static boolean[] vis;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		nodes = new Node[n + 1];
		dp = new int[n + 1][2];
		vis = new boolean[n + 1];
		
		// 자식이 하나만 있는 node 즉 끝에 있는 node를 찾을 cnt배열
		int[] cnt = new int[n + 1];
		int start = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i < n + 1; i++) {
			nodes[i] = new Node(i, Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			nodes[a].link.add(nodes[b]);
			nodes[b].link.add(nodes[a]);
			cnt[a]++;
			cnt[b]++;
		}
		
		//어디든지 끝점 찾아서
		for(int i = 1; i < n + 1; i++) {
			if(cnt[i] == 1) {
				start = i;
				vis[i] = true;
				break;
			}
		}
//		System.out.println("출발 = " + start);
		dfs(start);
		
		System.out.println(Math.max(dp[start][0], dp[start][1]));
	}
	
	static void dfs(int start) {
		
		for(int i = 0; i < nodes[start].link.size(); i++) {
			if(!vis[nodes[start].link.get(i).id]) {
				vis[nodes[start].link.get(i).id] = true;
				
				dfs(nodes[start].link.get(i).id);
				
				dp[start][0] += Math.max(dp[nodes[start].link.get(i).id][0], dp[nodes[start].link.get(i).id][1]);
				dp[start][1] += dp[nodes[start].link.get(i).id][0];
			}
		}
		
		dp[start][1] += nodes[start].people;
		
//		System.out.println(start + " " + dp[start][0] + " " + dp[start][1]);
		
	}
	
	static class Node{
		int id;
		int people;
		List<Node> link;
		
		public Node() {
			link = new ArrayList<>();
		}
		
		public Node(int id, int people) {
			this.id = id;
			this.people = people;
			link = new ArrayList<>();
		}
	}
}
