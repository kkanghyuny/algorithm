import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int d = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());

		int[] dp = new int[d+1];

		dp[0] = Integer.MAX_VALUE;

		while(p-- > 0){
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			for(int i = d; i >= l; i--){
				int min = Math.min(dp[i - l], c);
				dp[i] = Math.max(dp[i], min);
			}
		}

		System.out.println(dp[d]);

	}
}
