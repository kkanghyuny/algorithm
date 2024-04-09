import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static char[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int n = sc.nextInt();

		arr = new char[n][n];

		for (int i = 0; i < n; i++) {
			Arrays.fill(arr[i], ' ');
		}

		star(n, 0, 0);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

	public static void star(int n, int row, int col) {
		if (n == 1) {
			arr[row][col] = '*';
			return;
		}

		for (int i = row; i < row + n; i += n / 3) {
			for (int j = col; j < col + n; j += n / 3) {
				if (i != row + n / 3 || j != col + n / 3) {
					star(n / 3, i, j);
				}
			}
		}
	}
}
