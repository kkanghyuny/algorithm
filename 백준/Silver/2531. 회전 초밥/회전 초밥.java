import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		
		int count = 0;
		
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n + k - 1];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = n; i < n + k - 1; i++) {
			arr[i] = arr[i % n];
		}
		
		for(int i = 0; i < n; i++) {
			set.clear();
			set.add(c);
			
			for(int j = i; j < i + k; j++) {
				set.add(arr[j]);
			}
			count = Math.max(count, set.size());
		}
		
		System.out.println(count);
	}
}
