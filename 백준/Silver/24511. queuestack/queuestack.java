import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Deque<Integer> deque = new ArrayDeque<>();
		
		int n = Integer.parseInt(br.readLine());
		
		boolean[] isQueue = new boolean[n];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			if(Integer.parseInt(st.nextToken()) == 0) {
				isQueue[i] = true;
			}
		}

		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			int val = Integer.parseInt(st.nextToken());
			if(isQueue[i]) deque.offer(val);
		}
		
		int k = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < k; i++) {
			deque.offerFirst(Integer.parseInt(st.nextToken()));
			sb.append(deque.pollLast()).append(" ");
		}
		
		System.out.println(sb);
	}
}
