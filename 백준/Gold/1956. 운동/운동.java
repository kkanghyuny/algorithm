import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        
        int[][] arr = new int[v + 1][v + 1];
        for (int i = 1; i <= v; i++) {
            Arrays.fill(arr[i], 987654321);
        }
        
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            arr[from][to] = dist;
        }
        
        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= v; i++) {
            answer = Math.min(answer, arr[i][i]);
        }
        System.out.println(answer == 987654321 ? -1 : answer);
    }
}
