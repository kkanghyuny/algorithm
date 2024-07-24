import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();

		if(s.charAt(0) == '0') {
			System.out.println(0);
			return;
		}

		int[] dp = new int[s.length() + 1];
		dp[0] = 1;

		for(int i = 1; i <= s.length(); i++) {
			int curr = s.charAt(i - 1) - '0';

			if(curr != 0) {
				dp[i] += dp[i - 1];
				dp[i] %= 1000000;
			}

			if(i == 1) continue;

			int prev = s.charAt(i - 2) - '0';

			if(curr == 0 && prev == 0) {
				System.out.println(0);
				return;
			}

			if(prev == 0) continue;

			if((prev * 10 + curr) <= 26) {
				dp[i] += dp[i - 2];
				dp[i] %= 1000000;
			}
		}

		System.out.println(dp[s.length()]);
	}
}
