import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long x = sc.nextInt();
		long y = sc.nextInt();		
		long z = ((long)y * 100 / x);
		
		int game = (int)Math.ceil((100 * y - x * (z + 1)) / (double)(z - 99));

		if (z == 100 || z == 99) {
			System.out.println(-1);
		} else {
			System.out.println(game);
		}
	}
}
