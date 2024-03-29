import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int x = Integer.parseInt(st.nextToken());
		long y = Integer.parseInt(st.nextToken());
		long z = ((long)y * 100 / x);
		
		int game = (int)Math.ceil((double)(100 * y - x * (z + 1)) / (z - 99));

		if (z == 100 || z == 99) {
			System.out.println(-1);
		} else {
			System.out.println(game);
		}
	}
}
