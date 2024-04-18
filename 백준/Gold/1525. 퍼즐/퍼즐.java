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
		StringTokenizer st;
		HashSet<String> set = new HashSet<>();
		
		char[] arr = new char[9];
		
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				arr[i * 3 + j] = st.nextToken().charAt(0);
			}
		}
		
		String s = new String(arr);
		set.add(s);
		
		Queue<String> queue = new ArrayDeque<>();
		
		queue.offer(s);
		int count = 0;
		int[] dr = {1, -1, 0, 0};
		int[] dc = {0, 0, 1, -1};
		
		a: while(true) {
			int size = queue.size();
			if(size == 0) {
				count = -1;
				break;
			}
			
			while(size-- > 0) {
				String curr = queue.poll();
				int idx = 0;
				if(curr.equals("123456780")) break a;
				
				for(int i = 0; i < 9; i++) {
					if(curr.charAt(i) == '0') {
						idx = i;
						break;
					}
				}
				
				int r = idx / 3;
				int c = idx % 3;
				
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr < 0 || nc < 0 || nr >= 3 || nc >= 3) continue;
					
					String newStr = swap(curr, idx, nr * 3 + nc);
					
					if(set.contains(newStr)) continue;
					
					set.add(newStr);
					
					queue.add(newStr);
				}
			}
			
			count++;
		}

		System.out.println(count);
	}
	
	static String swap(String s, int idx1, int idx2) {
		char[] arr = new char[9];
		for(int i = 0; i < 9; i++) {
			arr[i] = s.charAt(i);
		}
		
		char tmp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = tmp;
		
		return new String(arr);
		
	}
}
