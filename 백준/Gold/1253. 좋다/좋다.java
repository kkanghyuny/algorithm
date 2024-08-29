import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		Arrays.sort(arr);

		for(int i = 0; i < n; i++) {
			int left = 0;
			int right = n - 1;
			while(true) {
				if(left == i) left++;
				else if(right == i) right--;

				if(left >= right) break;

				if(arr[left] + arr[right] > arr[i]) right--;
				else if(arr[left] + arr[right] < arr[i]) left++;
				else {
					answer++;
					break;
				}
			}
		}
		System.out.println(answer);
	}
}
