import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		int[] dp = new int[n + 2];

		// dp[0] = 1 을 안해주면 1, 1, 1 과 같은 테케에서 맨 마지막 ans *= 부분에서 0을 곱해버림
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		int ans = 1;
		
		int prev = 0;
		
		for(int i = 0; i < m; i++) {
			int now = Integer.parseInt(br.readLine());
			ans *= dp[now - prev - 1];
			prev = now;
		}
		
		ans *= dp[n - prev];
		
		System.out.println(ans);
	}

}
