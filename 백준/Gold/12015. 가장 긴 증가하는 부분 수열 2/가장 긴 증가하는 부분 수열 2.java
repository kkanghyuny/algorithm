import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());

		int[] lis = new int[n];
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		lis[0] = arr[0];
		int index = 0;
		
		for(int i = 1; i < n; i++) {
			int key = arr[i];
			
			if(lis[index] < key) {
				lis[++index] = key;
			} else {
				int left = 0;
				int right = index + 1;
				while(left < right) {
					int mid = (left + right) / 2;
					
					if(lis[mid] < key) {
						left = mid + 1;
					} else {
						right = mid;
					}
				}
				
				lis[left] = key;
			}
		}

		System.out.println(index + 1);
	}
}
