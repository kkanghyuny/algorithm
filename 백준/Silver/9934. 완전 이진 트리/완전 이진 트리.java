import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int k = Integer.parseInt(br.readLine());
		int size = (int)Math.pow(2, k);
		int[] arr = new int[size];
		boolean[] vis = new boolean[size];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i < size; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		queue.offer(size / 2);
		int size2 = size / 2;
		
		while(true) {
			int n = queue.size();
			if(n == 0) break;
			
			while(n-- > 0) {
				int a = queue.poll();
				sb.append(arr[a]).append(" ");

				int left = a - (size2 / 2);
				int right = a + (size2 / 2);
				vis[a] = true;
				
				if(left > 0 && !vis[left]) queue.offer(left);
				if(right < size && !vis[right]) queue.offer(right);
			}
			sb.append('\n');
			size2 /= 2;
		}
		
		System.out.println(sb);
	}
}
