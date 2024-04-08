import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr1 = new int[n];
		int[] arr2 = new int[n];
		int[] arr3 = new int[n];
		int[] arr4 = new int[n];
		
		long count = 0;
		
		int[] list1 = new int[n * n];
		int[] list2 = new int[n * n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr1[i] = Integer.parseInt(st.nextToken());
			arr2[i] = Integer.parseInt(st.nextToken());
			arr3[i] = Integer.parseInt(st.nextToken());
			arr4[i] = Integer.parseInt(st.nextToken());
		}
		
		int idx = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int sum = arr1[i] + arr2[j];
				list1[idx++] = sum;
			}
		}
		
		idx = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int sum = arr3[i] + arr4[j];
				list2[idx++] = sum;
			}
		}
		
		Arrays.sort(list1);
		Arrays.sort(list2);
		
		int i = 0;
		int j = (n * n) - 1;
		int prevI = 0;
		int prevJ = 0;
		
		while(i < n * n && j >= 0) {
			int sum = list1[i] + list2[j];
			
			if(sum == 0) {
				long count1 = 0;
				long count2 = 0;
				
				prevI = list1[i];
				while(i < n * n && prevI == list1[i]) {
					count1++;
					i++;
				}
				
				prevJ = list2[j];
				while(j >= 0 && prevJ == list2[j]) {
					count2++;
					j--;
				}
				
				count += (count1 * count2);
			}
			
			if(sum > 0) {
				j--;
			}else if(sum < 0) {
				i++;
			}
		}
		System.out.println(count);
	}
}
