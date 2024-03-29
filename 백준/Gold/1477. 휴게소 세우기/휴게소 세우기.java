import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int group = n + m + 1;

        int left = 0;
        int right = l;
        int answer = Integer.MAX_VALUE;

        int[] arr = new int[n + 1];

        if (n != 0) {
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
        }
        arr[n] = l;

        Arrays.sort(arr);

        while (left < right) {
            int mid = (left + right) / 2;
            int idx = 0;
            int nowVal = 0;
            int cnt = 0;
            
            if(mid == 0) break;
            
            while (idx <= n) {
                if (arr[idx] <= nowVal + mid) {
                    cnt++;
                    nowVal = arr[idx++];
                } else {
                	cnt++;
                    nowVal += mid;
                }
            }

            if (cnt <= group) {
                right = mid;
                answer = Math.min(answer, mid);
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);

    }
}