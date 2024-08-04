import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		int[] imos = new int[h + 1];
		for(int i = 0; i < n; i++) {
			int k = Integer.parseInt(br.readLine());
			if(i % 2 == 0) {
				imos[0]++;
				imos[k]--;
			} else {
				imos[h - k]++;
			}
		}
		
		int curr = 0;
		int cnt = 0;
		int minimum = 200_001;
		for(int i = 0; i < h; i++) {
			curr += imos[i];
			if(minimum == curr) {
				cnt++;
			} else if(minimum > curr) {
				minimum = curr;
				cnt = 1;
			}
		}
		
		System.out.println(minimum + " " + cnt);
	}
}
