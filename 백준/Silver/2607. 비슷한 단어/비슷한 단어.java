import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()) - 1;
		String s = br.readLine();
		int answer = 0;
		int[] cntArr = new int[26];
		for(int i = 0; i < s.length(); i++){
			cntArr[s.charAt(i) - 'A']++;
		}

		while(n-- > 0) {
			String curr = br.readLine();
			if(Math.abs(curr.length() - s.length()) > 1) continue;
			int[] subCntArr = Arrays.copyOf(cntArr, 26);
			int cnt = 0;
			for(int i = 0; i < curr.length(); i++){
				int idx = curr.charAt(i) - 'A';
				if(subCntArr[idx] > 0) {
					cnt++;
					subCntArr[idx]--;
				}
			}
			if(s.length() - 1 == curr.length() && cnt == curr.length()) answer++;
			else if(s.length() + 1 == curr.length() && cnt == s.length()) answer++;
			else if(s.length() == curr.length() && (cnt == s.length() || cnt == s.length() - 1)) answer++;
		}
		System.out.println(answer);
	}
}