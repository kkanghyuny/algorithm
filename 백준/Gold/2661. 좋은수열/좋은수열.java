import java.io.*;

public class Main {
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		backtracking(0, "");
	}

	static void backtracking(int depth, String s) {
		if(depth == n) {
			System.out.println(s);
			System.exit(0);
		}
		for(int i = 1; i <= 3; i++) {
			boolean check = true;
			String curr = s + i;
			for(int j = 1; j <= (depth + 1) / 2; j++) {
				String left = curr.substring(depth + 1 - j * 2, depth + 1 - j);
				String right = curr.substring(depth + 1 - j, depth + 1);
				if(left.equals(right)) {
					check = false;
					break;
				}
			}
			if(check) backtracking(depth + 1, curr);
		}
	}
}
