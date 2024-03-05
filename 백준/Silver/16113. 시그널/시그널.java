import java.util.Scanner;

public class Main {
	static int n;
	static char[][] arr;
	static boolean[][] vis;
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		String s = sc.next();
		arr = new char[5][n / 5];
		vis = new boolean[5][n / 5];
		sb = new StringBuilder();
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < n / 5; j++) {
				arr[i][j] = s.charAt(i * n / 5 + j);
			}
		}
		
		for(int i = 0; i < n / 5; i++) {
			if(arr[0][i] == '#' && !vis[0][i]) {
				checkNumber(i);
			}
		}
		
		System.out.println(sb);
	}
	
	static void checkNumber(int c) {		
		vis[0][c] = true;
		if(c + 1 >= n / 5) {
			sb.append(1);
			return;
		}
		switch(arr[0][c + 1]) {
		case '.': {
			switch(arr[2][c + 1]) {
			case '.': sb.append(1); break;
			case '#': sb.append(4); vis[0][c + 2] = true; break;
			}
			break;
		}
		case '#': {
			vis[0][c + 1] = true;
			vis[0][c + 2] = true;
			switch(arr[2][c + 1]) {
			case '.': {
				switch(arr[2][c]) {
				case '.': sb.append(7); break;
				case '#': sb.append(0); break;
				}
				break;
			}
			case '#': {
				switch(arr[1][c]) {
				case '.': {
					switch(arr[3][c]) {
					case '.': sb.append(3); break;
					case '#': sb.append(2); break;
					}
					break;
				}
				case '#': {
					switch(arr[3][c]) {
					case '.': {
						switch(arr[1][c + 2]) {
						case '.': sb.append(5); break;
						case '#': sb.append(9); break;
						}
						break;
					}
					case '#': {
						switch(arr[1][c + 2]) {
						case '.': sb.append(6); break;
						case '#': sb.append(8); break;
						}
						break;
					}
					}
				}
				}
			}
			break;
			}
			
		}
		}
	}
}
