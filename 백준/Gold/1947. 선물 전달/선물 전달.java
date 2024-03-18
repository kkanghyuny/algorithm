import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int div = 1000000000;
		
		long[] dp = new long[n + 1];
		
		dp[1] = 0;
		if(n >= 2) dp[2] = 1;
		
		for(int i = 2; i < n; i++) {
			dp[i + 1] = (dp[i] * i + dp[i - 1] * i) % div;
		}
		
		System.out.println(dp[n]);
	}
}
