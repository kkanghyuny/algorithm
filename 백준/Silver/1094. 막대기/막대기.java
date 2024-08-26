import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(br.readLine());
		int cnt = 0;
		int size = (int)(Math.log(x) / Math.log(2));
		for(int i = size; i >= 0; i--) {
			if((1 << i & x) != 0) cnt++;
		}
		System.out.println(cnt);
	}
}
