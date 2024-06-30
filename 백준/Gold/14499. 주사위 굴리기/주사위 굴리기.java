import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		// 1~4번 인덱스 동서북남
		int[] dr = {0, 0, 0, -1, 1};
		int[] dc = {0, 1, -1, 0, 0};

		int[][] map = new int[n][m];
		int[][] dice = new int[4][3];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		while(k-- > 0) {
			int cmd = Integer.parseInt(st.nextToken());
			int nr = x + dr[cmd];
			int nc = y + dc[cmd];
			
			if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
			
			switch(cmd) {
			// 동
			case 1:{
				int tmp = dice[1][0];
				dice[1][0] = dice[1][1];
				dice[1][1] = dice[1][2];
				dice[1][2] = dice[3][1];
				dice[3][1] = tmp;
				break;
			}
			// 서
			case 2:{
				int tmp = dice[1][0];
				dice[1][0] = dice[3][1];
				dice[3][1] = dice[1][2];
				dice[1][2] = dice[1][1];
				dice[1][1] = tmp;
				break;
			}
			// 북
			case 3:{
				int tmp = dice[0][1];
				dice[0][1] = dice[1][1];
				dice[1][1] = dice[2][1];
				dice[2][1] = dice[3][1];
				dice[3][1] = tmp;
				break;
			}
			// 남
			case 4:{
				int tmp = dice[0][1];
				dice[0][1] = dice[3][1];
				dice[3][1] = dice[2][1];
				dice[2][1] = dice[1][1];
				dice[1][1] = tmp;
			}
			}
			x = nr;
			y = nc;
			if(map[x][y] != 0) {
				dice[3][1] = map[x][y];
				map[x][y] = 0;
			} else {
				map[x][y] = dice[3][1];
			}
			
			sb.append(dice[1][1]).append('\n');
		}
		
		System.out.println(sb);
	}
}
