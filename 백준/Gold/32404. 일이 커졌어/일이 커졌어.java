import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		for(int i = 1; i <= n / 2; i++) {
			sb.append(i + n / 2).append(' ').append(n / 2 - i + 1).append(' ');
		}
		if(n % 2 != 0) sb.append(n);

		System.out.println(sb);
	}
}
