import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	static int n, m, max;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static char[][] arr;
	static HashSet<Character> set;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		max = 0;
		
		arr = new char[n][m];
		set = new HashSet<>();
		
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j);
			}
		}
		
		set.add(arr[0][0]);
		
		dfs(0, 0, 1);
		
		System.out.println(max);
	}
	
	static void dfs(int r, int c, int depth) {
		max = Math.max(depth, max);
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
			if(set.contains(arr[nr][nc])) continue;
			
			set.add(arr[nr][nc]);
			dfs(nr, nc, depth + 1);
			set.remove(arr[nr][nc]);
		}
	}
}
