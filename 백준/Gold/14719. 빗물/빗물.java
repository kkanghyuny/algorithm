import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[w];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < w; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int i = 0;
		int j = w - 1;
		int leftMax = arr[i];
		int rightMax = arr[j];
		int plus = 0;
		int ans = 0;
		
		while(i < j) {
			plus = 0;
			
			if(leftMax <= rightMax) {
				i++;
				while(leftMax > arr[i]) {
					plus += leftMax - arr[i++];
				}
				leftMax = arr[i];
				ans += plus;
			} else {
				j--;
				while(rightMax > arr[j]) {
					plus += rightMax - arr[j--];
				}
				rightMax = arr[j];
				ans += plus;
			}
		}
		
		System.out.println(ans);
	}
}
