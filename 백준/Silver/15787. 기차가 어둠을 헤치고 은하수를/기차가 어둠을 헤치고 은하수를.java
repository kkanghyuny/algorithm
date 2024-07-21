import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n + 1][21];
		Set<String> set = new HashSet<>();

		for(int i = 0; i < m; i++){
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			switch(cmd){
				case 1: {
					int idx = Integer.parseInt(st.nextToken());
					arr[num][idx] = 1;
					break;
				}
				case 2: {
					int idx = Integer.parseInt(st.nextToken());
					arr[num][idx] = 0;
					break;
				}
				case 3: {
					for(int j = 20; j >= 1; j--) {
						arr[num][j] = arr[num][j - 1];
					}
					break;
				}
				case 4: {
					for(int j = 1; j < 20; j++){
						arr[num][j] = arr[num][j + 1];
					}
					arr[num][20] = 0;
					break;
				}
			}
		}

		int answer = 0;
		for(int i = 1; i <= n; i++) {
			String s = "";
			for(int j = 1; j <= 20; j++) {
				s += arr[i][j];
			}
			if(set.contains(s)) continue;
			set.add(s);
			answer++;
		}

		System.out.println(answer);
	}
}
