import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n + 1];
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			
			int cnt = Integer.parseInt(st.nextToken());
			
			int prev = 0;
			
			for(int j = 0; j < cnt; j++) {
				prev = Math.max(prev, dp[Integer.parseInt(st.nextToken())]);
			}
			
			dp[i] = prev + time;
		}
		
		int ans = 0;
		
		for(int i = 1; i <= n; i++) {
			ans = Math.max(ans, dp[i]);
		}
		
		System.out.println(ans);
	}
}
