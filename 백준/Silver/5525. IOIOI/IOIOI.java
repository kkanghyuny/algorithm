import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		String s = br.readLine();

		int count = 0;
		int answer = 0;

		for (int i = 1; i < m; i++) {
			if (s.charAt(i - 1) == 'I' && s.charAt(i) == 'O') {
				count++;
			} else if (s.charAt(i - 1) == 'I' && s.charAt(i) == 'I') {
				if (count >= n) {
					answer += count - n + 1;
				}
				count = 0;
			} else if (s.charAt(i - 1) == 'O' && s.charAt(i) == 'O') {
				if (count > n) {
					count--;
					answer += count - n + 1;
				}
				count = 0;
			}
		}

		if(s.charAt(m - 1) == 'O') count--;
		
		if (count >= n) {
			answer += count - n + 1;
		}

		System.out.println(answer);

	}
}
