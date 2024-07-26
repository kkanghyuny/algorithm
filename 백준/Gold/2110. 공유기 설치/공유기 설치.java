import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		int left = 1;
		int right = arr[n - 1] - arr[0] + 1;
		
		while(left < right) {
			int mid = (left + right) / 2;
			int cnt = 1;
			int install = arr[0];
			
			for(int i = 1; i < n; i++) {
				if(arr[i] - install >= mid) {
					cnt++;
					install = arr[i];
				}
			}
			
			if(cnt < c) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		System.out.println(left - 1);
		
	}
}
