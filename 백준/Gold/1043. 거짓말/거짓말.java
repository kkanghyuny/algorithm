import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		Queue<int[]> queue = new ArrayDeque<>();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int k = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < k; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			int[] arr = new int[size];
			for(int j = 0; j < size; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			queue.offer(arr);
		}
		
		int size = queue.size();
		
		while(size-- > 0) {
			int[] arr = queue.poll();
			boolean isExist = false;
			for(int i = 0; i < arr.length; i++) {
				if(set.contains(arr[i])) {
					isExist = true;
				}
			}
			
			if(isExist) {
				for(int i = 0; i < arr.length; i++) {
					set.add(arr[i]);
					size = queue.size();
				}
			} else {
				queue.offer(arr);
			}
		}

		System.out.println(queue.size());
	}
}
