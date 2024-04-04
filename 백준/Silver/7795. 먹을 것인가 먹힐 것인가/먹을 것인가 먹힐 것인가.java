import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			int[] arr1 = new int[n];
			int[] arr2 = new int[m];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				arr1[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m; i++) {
				arr2[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr1);
			Arrays.sort(arr2);
			
			int i = 0;
			int j = 0;
			
			int count = 0;
			
			while(i < n) {
				if(j == m) {
					count += j * (n - i - 1);
					break;
				}
				if(arr1[i] > arr2[j]) {
					count++;
					j++;
				} else if(i + 1 < n){
					count += j;
					i++;
				} else {
					i++;
				}
			}
			sb.append(count).append('\n');
		}
		System.out.println(sb);
	}
}
