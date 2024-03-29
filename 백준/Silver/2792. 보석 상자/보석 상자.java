import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[m];
		long left = 1;
		long right = 0;

		for (int i = 0; i < m; i++) {
			int cnt = Integer.parseInt(br.readLine());
			arr[i] = cnt;
			right += cnt;
		}
		
		while(left < right) {
			long mid = (left + right) / 2;
			int cnt = 0;
			for(int i = 0; i < m; i++) {
				cnt += ((arr[i] - 1) / mid) + 1; 
			}
			
			if(cnt == n) {
				right = mid;
			} else if(cnt > n) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		
		System.out.println(left);
		
	}
}
