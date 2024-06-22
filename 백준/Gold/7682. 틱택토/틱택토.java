import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static boolean validX, validO;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[][] arr = new char[3][3];

		while (true) {
			String s = br.readLine();
			if (s.equals("end"))
				break;

			int cntX = 0;
			int cntO = 0;

			validX = false;
			validO = false;

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					arr[i][j] = s.charAt(i * 3 + j);
					if (arr[i][j] == 'X') {
						cntX++;
					} else if (arr[i][j] == 'O') {
						cntO++;
					}
				}
			}

			if (cntX < cntO || Math.abs(cntX - cntO) >= 2) {
				sb.append("invalid").append('\n');
				continue;
			}

			check(arr);

			if (validX && validO) {
				sb.append("invalid").append('\n');
			} else if(validX && (cntX + cntO) % 2 == 0) {
				sb.append("invalid").append('\n');
			} else if(validX) {
				sb.append("valid").append('\n');
			} else if(validO && (cntX + cntO) % 2 == 1) {
				sb.append("invalid").append('\n');
			} else if(validO) {
				sb.append("valid").append('\n');
			} else if(cntX + cntO != 9){
				sb.append("invalid").append('\n');
			} else {
				sb.append("valid").append('\n');
			}
		}

		System.out.println(sb);
	}

	static void check(char[][] arr) {
		if(arr[0][0] == arr[0][1] && arr[0][1] == arr[0][2]) {
			if(arr[0][0] == 'X') validX = true;
			if(arr[0][0] == 'O') validO = true;
		} 
		if(arr[0][0] == arr[1][0] && arr[1][0] == arr[2][0]) {
			if(arr[0][0] == 'X') validX = true;
			if(arr[0][0] == 'O') validO = true;
		} 
		if(arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2]) {
			if(arr[0][0] == 'X') validX = true;
			if(arr[0][0] == 'O') validO = true;
		} 
		if(arr[2][0] == arr[2][1] && arr[2][1] == arr[2][2]) {
			if(arr[2][0] == 'X') validX = true;
			if(arr[2][0] == 'O') validO = true;
		} 
		if(arr[0][2] == arr[1][2] && arr[1][2] == arr[2][2]) {
			if(arr[0][2] == 'X') validX = true;
			if(arr[0][2] == 'O') validO = true;
		} 
		if(arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0]) {
			if(arr[0][2] == 'X') validX = true;
			if(arr[0][2] == 'O') validO = true;
		}
		if(arr[1][0] == arr[1][1] && arr[1][1] == arr[1][2]) {
			if(arr[1][0] == 'X') validX = true;
			if(arr[1][0] == 'O') validO = true;
		}
		if(arr[0][1] == arr[1][1] && arr[1][1] == arr[2][1]) {
			if(arr[0][1] == 'X') validX = true;
			if(arr[0][1] == 'O') validO = true;
		}
	}
}
