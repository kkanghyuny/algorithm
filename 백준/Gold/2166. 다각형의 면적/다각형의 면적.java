import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		long firstX = Long.parseLong(st.nextToken());
		long firstY = Long.parseLong(st.nextToken());
		long prevX = firstX;
		long prevY = firstY;
		long sum = 0;
		
		for(int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			long currX = Long.parseLong(st.nextToken());
			long currY = Long.parseLong(st.nextToken());
			sum += (prevX * currY) - (prevY * currX);
			prevX = currX;
			prevY = currY;
		}
		sum += (prevX * firstY) - (prevY * firstX);
		
		sum = Math.abs(sum);
        System.out.println(String.format("%.1f", sum / 2.0));
	}
}
