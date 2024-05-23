import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int[] count = new int[3];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		checkArr(0, n, 0, n, n);
		
		for(int i = 0; i < 3; i++) {
			System.out.println(count[i]);
		}

	}

	public static void checkArr(int rowStart, int rowEnd, int colStart, int colEnd, int n) {
		int first = arr[rowStart][colStart];
		boolean notFill = false;
		if(rowEnd - rowStart == 1) {
			count[first + 1]++;
			return;
		}
		
		out: for (int i = rowStart; i < rowEnd; i++) {
			for (int j = colStart; j < colEnd; j++) {
				if (arr[i][j] != first) {
					notFill = true;
					break out;
				}
			}
		}
		
		if(!notFill) {
			count[first + 1]++;
			return;
		} else {
			for(int i = rowStart; i < rowEnd; i += n / 3) {
				for(int j = colStart; j < colEnd; j += n / 3) {
					checkArr(i, i + n / 3, j, j + n / 3, n / 3);
				}
			}
		}
	}
}
