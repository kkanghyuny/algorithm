import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int count = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text = br.readLine();
		String pattern = br.readLine();

		KMP(text, pattern);
		
		System.out.println(count);
		System.out.println(sb);
	}

	static void KMP(String T, String P) {
		int[] pi = getPi(P);

		int j = 0;

		for (int i = 0; i < T.length(); i++) {
			while (j > 0 && T.charAt(i) != P.charAt(j)) {
				j = pi[j - 1];
			}

			if (T.charAt(i) == P.charAt(j)) {
				if (j == P.length() - 1) {
					sb.append(i - P.length() + 2).append(" ");
					count++;
					j = pi[j];
				} else {
					j++;
				}
			}
		}

	}

	static int[] getPi(String P) {
		int[] pi = new int[P.length()];

		int j = 0;

		for (int i = 1; i < P.length(); i++) {
			while (j > 0 && P.charAt(i) != P.charAt(j)) {
				j = pi[j - 1];
			}

			if (P.charAt(i) == P.charAt(j)) {
				pi[i] = ++j;
			}
		}

		return pi;
	}
}
