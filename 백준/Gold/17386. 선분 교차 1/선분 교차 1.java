import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Point {
		long x;
		long y;

		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		Point p1 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
		Point p2 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
		st = new StringTokenizer(br.readLine());
		Point p3 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
		Point p4 = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));

		if((ccw(p1, p2, p3) * ccw(p1, p2, p4) < 0) && (ccw(p3, p4, p1) * ccw(p3, p4, p2) < 0)) {
			System.out.println(1);
			return;
		}
		System.out.println(0);
	}

	static int ccw(Point q1, Point q2, Point q3){
		long cp = (q2.x - q1.x)*(q3.y - q1.y) - (q3.x - q1.x)*(q2.y - q1.y);

		if (cp > 0){
			return 1;
		} else if (cp < 0){
			return -1;
		} else{
			return 0;
		}
	}
}
