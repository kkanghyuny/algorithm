import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<String> queue = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		boolean answer = false;

		// S
		String first = br.readLine();
		
		// T
		String second = br.readLine();
		
		while(first.length() != second.length()) {
			if(second.charAt(second.length() - 1) == 'A') {
				second = second.substring(0, second.length() - 1);
			} else {
				second = second.substring(0, second.length() - 1);
				sb.setLength(0);
				sb.append(second);
				sb.reverse();
				second = sb.toString();
			}
		}
		
		if(first.equals(second)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
		
	}
}
