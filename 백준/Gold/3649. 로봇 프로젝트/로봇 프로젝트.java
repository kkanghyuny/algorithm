import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s;
		while((s = br.readLine()) != null) {
			int x = Integer.parseInt(s) * 10_000_000;
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(br.readLine());
			}
			Arrays.sort(arr);
			int left = 0;
			int right = n - 1;
			boolean check = false;
			while(left < right) {
				int sum = arr[left] + arr[right];
				if(sum == x) {
					sb.append("yes ").append(arr[left]).append(" ").append(arr[right]).append('\n');
					check = true;
					break;
				}
				else if(sum < x) left++;
				else right--;
			}
			if(!check) sb.append("danger").append('\n');
		}
		System.out.println(sb);
	}
}
