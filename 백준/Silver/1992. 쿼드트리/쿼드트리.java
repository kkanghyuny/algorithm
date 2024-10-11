import java.io.*;

public class Main {
	static int n;
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];

		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < n; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}

		find(0, 0, n);
		System.out.println(sb);
	}

	static void find(int startR, int startC, int size) {
		if(size  == 1) {
			sb.append(arr[startR][startC]);
			return;
		}

		boolean isCanQuad = true;
		int start = arr[startR][startC];

		a: for(int i = startR; i < startR + size; i++) {
			for(int j = startC; j < startC + size; j++) {
				if(start != arr[i][j]) {
					isCanQuad = false;
					break a;
				}
			}
		}

		if(isCanQuad) sb.append(start);
		else {
			int nextSize = size / 2;
			sb.append("(");
			find(startR, startC, nextSize);
			find(startR, startC + nextSize,nextSize);
			find(startR + nextSize, startC, nextSize);
			find(startR + nextSize, startC + nextSize, nextSize);
			sb.append(")");
		}
	}
}
