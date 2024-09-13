import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s = br.readLine();
		String bomb = br.readLine();
		int l = bomb.length();

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) {
			stack.push(s.charAt(i));

			if (stack.size() >= l) {
				boolean isBomb = true;
				for (int j = 0; j < l; j++) {
					if (stack.get(stack.size() - l + j) != bomb.charAt(j)) {
						isBomb = false;
						break;
					}
				}
				if (isBomb) {
					for (int j = 0; j < l; j++) {
						stack.pop();
					}
				}
			}
		}

		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		System.out.println(sb.length() == 0 ? "FRULA" : sb.reverse().toString());
	}
}
