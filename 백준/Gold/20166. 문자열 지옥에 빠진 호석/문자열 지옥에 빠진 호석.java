import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] dr = {1, -1, 0, 0, 1, 1, -1, -1};
		int[] dc = {0, 0, 1, -1, 1, -1, 1, -1};
		Queue<int[]> queue = new ArrayDeque<>();
		Map<String, Integer> map = new HashMap<>();
		
		char[][] arr = new char[n][m];
		
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		
		for(int t = 0; t < k; t++) {
			String s = br.readLine();
			
			if(map.containsKey(s)) {
				sb.append(map.get(s)).append('\n');
				continue;
			}
			
			int length = s.length();
			int count = 0;
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(arr[i][j] == s.charAt(0)) {
						queue.offer(new int[] {i, j, 1});
					}
				}
			}
			
			while(!queue.isEmpty()) {
				int[] curr = queue.poll();
				int r = curr[0];
				int c = curr[1];
				int depth = curr[2];
				
				if(depth == length) {
					count++;
					continue;
				}
				
				for(int d = 0; d < 8; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr == -1) nr = n - 1;
					if(nr == n) nr = 0;
					if(nc == -1) nc = m - 1;
					if(nc == m) nc = 0;
					
					if(arr[nr][nc] == s.charAt(depth)) {
						queue.offer(new int[] {nr, nc, depth + 1});
					}
				}
			}
			map.put(s, count);
			sb.append(count).append('\n');
		}
		System.out.println(sb);
	}
}
