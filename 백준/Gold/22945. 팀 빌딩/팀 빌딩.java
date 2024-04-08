import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int i = 0;
		int j = n - 1;
		int maxAbil = 0;
		
		while(i < j) {
			int size = j - i - 1;
			maxAbil = Math.max(maxAbil, size * (Math.min(arr[i], arr[j])));
			
			if(arr[i] > arr[j]) {
				j--;
			} else {
				i++;
			}
		}
		
		System.out.println(maxAbil);
	}
}
