import java.util.Scanner;

public class Main {
	static int size, r, c;
	static int answer = -1;
	static int cnt = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();

		int k = (int) Math.pow(2, n);
		size = k * k;

		zigzag(k, k / 2, 0, 0);

		System.out.println(answer);
	}

	public static void zigzag(int k, int next, int row, int col) {
		if (answer != -1)
			return;

		if (next > 1) {
			for (int i = row; i < row + k; i += next) {
				if (r > i + next) {
					cnt += next * next * 2;
					continue;
				}
				for (int j = col; j < col + k; j += next) {
					if (c > j + next) {
						cnt += next * next;
						continue;
					} else {
						zigzag(k / 2, next / 2, i, j);
					}
				}
			}
		} else if (next == 1) {
			for (int i = row; i <= row + 1; i++) {
				for (int j = col; j <= col + 1; j++) {
					if (i == r && j == c) {
						answer = cnt;
						return;
					}
					cnt++;
				}
			}
		}
	}
}
