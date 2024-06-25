import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[] best;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int g = Integer.parseInt(br.readLine());
		int p = Integer.parseInt(br.readLine());
		
		best = new int[g + 1];
		
		for(int i = 0; i <= g; i++) {
			best[i] = i;
		}
		
		int cnt = 0;
		while(p-- > 0) {
			int bestGate = find(Integer.parseInt(br.readLine()));
			
			if(bestGate == 0) break;
			
			cnt++;
			union(bestGate, find(bestGate - 1));
		}
		
		System.out.println(cnt);
		
	}
	
	static int find(int n) {
		if(best[n] == n) return n;
		return best[n] = find(best[n]);
	}
	
	static void union(int a, int b) {
		if(a > b) {
			best[a] = b;
		} else {
			best[b] = a;
		}
	}
}
