import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[n][n];
		int[][] rgArr = new int[n][n];
		boolean[][] vis = new boolean[n][n];
		boolean[][] rgVis = new boolean[n][n];
		int count = 0;
		int rgCount = 0;
		
		int[] dr = {1, 0, -1, 0};
		int[] dc = {0, 1, 0, -1};
		
		// {row, column, 색깔(red:0 green:1 blue:2)}
		Queue<int[]> queue = new LinkedList<>();
		Queue<int[]> rgQueue = new LinkedList<>();
		
		for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
            	char val = s.charAt(j);
            	if(val == 'R') {
            		arr[i][j] = 0;
            		rgArr[i][j] = 1;
            	} else if(val == 'G') {
            		arr[i][j] = 1;
            		rgArr[i][j] = 1;
            	} else if(val == 'B'){
            		arr[i][j] = 2;
            		rgArr[i][j] = 2;
            	}
            }
        }
		
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				if(vis[r][c]) {
					continue;
				}
				
				count++;
				
				vis[r][c] = true;
				
				queue.add(new int[] {r, c, arr[r][c]});
				
				while(!queue.isEmpty()) {
					int[] cur = queue.poll();
					
					int row = cur[0];
					int col = cur[1];
					int color = cur[2];
					
					for(int d = 0; d < 4; d++) {
						int nr = row + dr[d];
						int nc = col + dc[d];
						
						if(nr < 0 || nr >= n || nc < 0 || nc >= n) {
							continue;
						}
						
						if(vis[nr][nc] || arr[nr][nc] != color) {
							continue;
						}
						
						vis[nr][nc] = true;
						queue.add(new int[] {nr, nc, color});
					}
				}
			}
		}
		
		for(int r = 0; r < n; r++) {
			for(int c = 0; c < n; c++) {
				if(rgVis[r][c]) {
					continue;
				}
				
				rgCount++;
				
				rgVis[r][c] = true;
				
				rgQueue.add(new int[] {r, c, rgArr[r][c]});
				
				while(!rgQueue.isEmpty()) {
					int[] cur = rgQueue.poll();
					
					int row = cur[0];
					int col = cur[1];
					int color = cur[2];
					
					for(int d = 0; d < 4; d++) {
						int nr = row + dr[d];
						int nc = col + dc[d];
						
						if(nr < 0 || nr >= n || nc < 0 || nc >= n) {
							continue;
						}
						
						if(rgVis[nr][nc] || rgArr[nr][nc] != color) {
							continue;
						}
						
						rgVis[nr][nc] = true;
						rgQueue.add(new int[] {nr, nc, color});
					}
				}
			}
		}
		System.out.println(count + " " + rgCount);
	}
}
