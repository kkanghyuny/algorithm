import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[k];
		long left = 0;
		long right = 0;
		
		for(int i = 0; i < k; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			right = Math.max(right, arr[i]);
		}
		
		right++;
		
		while(left < right) {
			long cnt = 0;
			long mid = (left + right) / 2;
			
			for(int i = 0; i < k; i++) {
				cnt += arr[i] / mid;
			}
			
			if(cnt < n) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		System.out.println(left - 1);
	}
}
