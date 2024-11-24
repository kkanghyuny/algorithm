import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if(n == 1) {
			System.out.println(0);
			return;
		}
		long[][] dp = new long[3][n + 1];
		dp[0][2] = 1;
		dp[1][2] = 1;
		
		for(int i = 3; i <= n; i++) {
			dp[0][i] = (dp[1][i - 1] + dp[2][i - 1]) % 1_000_000_007;
			dp[1][i] = (dp[0][i - 1] + dp[2][i - 1]) % 1_000_000_007;
			dp[2][i] = (dp[0][i - 1] + dp[1][i - 1]) % 1_000_000_007;
		}
		System.out.println(dp[0][n]);
	}
}
