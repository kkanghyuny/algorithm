import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());

		for (int t = 0; t < n; t++) {
			String s = br.readLine();
			boolean isPal = true;
			boolean isSemiPal = false;
			int isSkip = 0;
			int i = 0;
			int j = s.length() - 1;
			int skipI = 0;
			int skipJ = 0;

			while (i < j) {
				if (s.charAt(i) == s.charAt(j)) {
					i++;
					j--;
				} else {
					if(isSkip == 1) {
						i = skipI;
						j = skipJ;
						isSkip = 2;
						continue;
					}
					
					if (!isSemiPal && isSkip == 0) {
						if(s.charAt(i + 1) == s.charAt(j)) {
							skipI = i;
							skipJ = j - 1;
							isSkip = 1;
							i += 2;
							j--;
							isSemiPal = true;
							continue;
						} else if(s.charAt(i) == s.charAt(j - 1)){
							skipI = i + 1;
							skipJ = j;
							isSkip = 1;
							i++;
							j -= 2;
							isSemiPal = true;
							continue;
						}
					}
					isPal = false;
					break;
				}
			}
			
			if(isSemiPal && isPal) {
				sb.append(1).append('\n');
				continue;
			} else if(isPal) {
				sb.append(0).append('\n');
			} else {
				sb.append(2).append('\n');
			}
		}
		System.out.println(sb);
	}
}
