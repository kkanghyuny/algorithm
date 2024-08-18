import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		long[] dp = new long[10001];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 3;
		for(int i = 4; i <= 10000; i++) {
			dp[i] = 1 + i / 2 + dp[i - 3];
		}
		
		for(int i = 0; i < n; i++) {
			sb.append(dp[Integer.parseInt(br.readLine())]).append('\n');
		}
		
		System.out.println(sb);
	}
}
