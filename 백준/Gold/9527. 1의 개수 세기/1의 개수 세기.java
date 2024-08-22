import java.io.*;
import java.util.*;

public class Main {
	static long[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());

		dp = new long[55];
		// 2의 54승이 10의16승을 커버할 수 있음
		// dp[n] = dp[n - 1] * 2 + 2 ^ n;
		// => dp[n] = dp[n - 1] << 1 + (1L << n)
		dp[0] = 1;
		for(int i = 1; i < 55; i++) {
			dp[i] = (dp[i - 1] << 1) + (1L << i);
		}
		System.out.println(calc(b) - calc(a - 1));
	}

	static long calc(long n) {
		// n 이 홀인지 짝인지 확인
		long count = n & 1;
		// n보다 작은 2^n의 최대 n 값 찾기
		int size = (int) (Math.log(n) / Math.log(2));
		for(int i = size; i > 0; i--) {
			// 지금 위치의 비트가 1이라면
			if((n & (1L << i)) != 0L) {
				// 하나 아래쪽의 비트에 대한 모든 가능한 경우의 개수(dp[i - 1])
				// n - (1L << i) + 1 은 현재 비트의 위치에서 추가로 발생하는 1의 개수
				count += dp[i - 1] + (n - (1L << i) + 1);
				n -= (1L << i);
			}
		}
		return count;
	}
}
