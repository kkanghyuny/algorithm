import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[d];
		arr[d - 1] = k;
		int prev = arr[d - 1];
		int curr = arr[d - 2];
		int idx = d - 3;
		for(int i = 1; i < k; i++) {
			arr[d - 2] = i;
			prev = arr[d - 1];
			curr = arr[d - 2];
			idx = d - 3;
			while(prev - curr > 0 && idx >= 0) {
				arr[idx--] = prev - curr;
				prev = arr[idx + 2];
				curr = arr[idx + 1];
			}
			if(arr[0] != 0 && arr[0] <= arr[1]) break;
		}
		System.out.println(arr[0] + "\n" + arr[1]);
	}
}
