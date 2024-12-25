import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Deque<Integer> deque = new ArrayDeque<>();
		for(int i = 0; i < n; i++) {
			deque.offer(i);
		}
		int curr = n;
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			switch(Integer.parseInt(st.nextToken())) {
			case 1: {
				arr[deque.pollFirst()] = curr--;
				break;
			}
			case 2: {
				int prev = deque.pollFirst();
				arr[deque.pollFirst()] = curr--;
				deque.offerFirst(prev);
				break;
			}
			case 3: {
				arr[deque.pollLast()] = curr--;
			}
			}
		}
		for(int i = 0; i < n; i++) {
			sb.append(arr[i]).append(' ');
		}
		System.out.println(sb);
	}
}
