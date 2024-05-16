import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int [][] scoreArr;
	static int max = 0;
	static int turn =0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int size = Integer.parseInt(br.readLine());
		int[][] arr = new int[size][9];

		for (int i = 0; i < arr.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		scoreArr = arr;
		int [] player = {1,2,3,4,5,6,7,8};
		perm(player, 0);
		System.out.println(max);
	}

	public static void perm(int[] arr, int pivot) {
		if (pivot == arr.length) {
			play(arr);
			return;
		}
		for (int i = pivot; i < arr.length; i++) {
			swap(arr, i, pivot);
			perm(arr, pivot + 1);
			swap(arr, i, pivot);
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	public static void play(int[] temp) {
		int [] arr = new int[9];
		int j=0;
		for(int i=0 ; i< 9 ; i++) {
			if(i!=3) {
				arr[i] = temp[j++];
			}
		}
		
		turn = 0;
		int score=0;
		for (int i = 0; i < scoreArr.length; i++) {
			int out =0;
			int [] scores = scoreArr[i];
			List<Integer> list = new ArrayList<>();
			while (out <3) {
				int player = arr[turn++ %9 ] ;
				int ruta = scores[player];
				if(ruta == 0) out++;
				else list.add(ruta);
			}
			int sum =0;
			for (int k = list.size() -1; k >= 0; k--) {
				sum += list.get(k);
				if(sum >= 4) {
					score += k+1;
					break;
				}
			}
		}
		max = Math.max(max, score);
	}
	
	
}