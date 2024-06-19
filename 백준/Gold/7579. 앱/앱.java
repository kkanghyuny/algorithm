import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int ans = Integer.MAX_VALUE;
		
		int[] active = new int[n];
		int[] disabled = new int[n];
		int[][] dp = new int[n][10001];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			active[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			disabled[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int j = 0; j <= 10000; j++) {
			int act = active[0];
			int dis = disabled[0];
			if(j >= dis) dp[0][j] = act;
			
			if(dp[0][j] >= m) ans = Math.min(ans, j);
		}
		
		for(int i = 1; i < n; i++) {
			int act = active[i];
			int dis = disabled[i];
			
			for(int j = 0; j <= 10000; j++) {
				if(j >= dis) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - dis] + act);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
				
				if(dp[i][j] >= m) ans = Math.min(ans, j);
			}
		}
		
		System.out.println(ans);
		
	}
}
