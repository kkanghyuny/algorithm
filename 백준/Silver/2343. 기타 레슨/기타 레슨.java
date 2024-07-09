import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int left = 0;
        int right = 0;

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left, arr[i]);
            right += arr[i];
        }

        while(left <= right){
            int mid = (left + right) / 2;

            int cnt = 0;
            int sum = 0;
            for(int i = 0; i < n; i++){
                if(sum + arr[i] > mid){
                    cnt++;
                    sum = 0;
                }

                sum += arr[i];
            }

            if(sum != 0) cnt++;

            if(cnt > m){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(left);
    }
}
