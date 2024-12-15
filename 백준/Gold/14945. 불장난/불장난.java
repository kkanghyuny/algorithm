import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[][] dp = new long[102][102];
		dp[2][1] = 2;
		for(int i = 3; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				dp[i][j] = (dp[i - 1][j] * 2 + dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 10007;
			}
		}
		long ans = 0;
		for(int i = 1; i <= n; i++) {
			ans += dp[n][i];
		}
		System.out.println(ans % 10007);
	}
}