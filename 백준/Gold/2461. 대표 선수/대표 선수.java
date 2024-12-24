import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>((a ,b) -> a[0] - b[0]);
        
        int[][] arr = new int[n][m];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr[i]);
            pq.offer(new int[]{arr[i][0], i, 0});
            max = Math.max(max, arr[i][0]);
        }

        int answer = Integer.MAX_VALUE;
        while (true) {
            int[] curr = pq.poll();
            int min = curr[0];
            int groupIdx = curr[1];
            int elementIdx = curr[2];
            answer = Math.min(answer, max - min);

            if (elementIdx + 1 == m) break;

            int nextValue = arr[groupIdx][elementIdx + 1];
            pq.offer(new int[]{nextValue, groupIdx, elementIdx + 1});
            max = Math.max(max, nextValue);
        }
        System.out.println(answer);
    }
}
