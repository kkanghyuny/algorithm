import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());
			String[] arr = new String[n];
			
			for(int i = 0; i < n; i++) {
				arr[i] = br.readLine();
			}
			
			Arrays.sort(arr);
			
			String check = arr[0];
			
			boolean isYes = true;
			
			for(int i = 1; i < n; i++) {
				int length = check.length() <= arr[i].length() ? check.length() : arr[i].length();
				if(Integer.parseInt(check.substring(0, length)) < Integer.parseInt(arr[i].substring(0, length))) {
					check = arr[i];
				} else if(Integer.parseInt(check.substring(0,  length)) == Integer.parseInt(arr[i].substring(0, length))) {
					isYes = false;
					break;
				}
			}
			
			if(isYes) {
				sb.append("YES").append('\n');
			} else {
				sb.append("NO").append('\n');
			}
		}
		
		System.out.println(sb);
	}
}
