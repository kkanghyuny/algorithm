import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long[][] dp = new long[n + m + 1][n + m + 1];

        for(int i = 0; i <= n + m; i++){
            dp[i][0] = 1;
            dp[i][1] = i;
            dp[i][i] = 1;
        }

        for(int i = 1; i <= n + m; i++){
            for(int j = 1; j <= i; j++){
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];

                if(dp[i][j] > 1_000_000_000){
                    dp[i][j] = 1_000_000_001;
                }
            }
        }

        if(dp[n + m][m] < k) {
            System.out.println(-1);
            return;
        }

        while(!(n == 0 && m == 0)) {
            if(dp[n + m - 1][m] >= k){
                sb.append("a");
                n--;
            } else {
                sb.append("z");
                k = k - dp[n + m - 1][m];
                m--;
            }
        }
        System.out.println(sb);
    }
}
