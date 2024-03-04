import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//java8에서는 case 2, 3, 4: dir--; break;
//이렇게 case를 여러개 모아서가 안되네요
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			String s = br.readLine();
			
			int x = 0;
			int y = 0;
			int maxX = 0;
			int maxY = 0;
			int minX = 0;
			int minY = 0;
			
			// 1위 2오른쪽 3아래 4왼쪽
			int dir = 1;
			
			for(int j = 0; j < s.length(); j++) {
				char cmd = s.charAt(j);
				
				switch(cmd) {
				case 'F': {
					switch(dir) {
					case 1: maxY = Math.max(maxY, ++y); break;
					case 2: maxX = Math.max(maxX, ++x); break;
					case 3: minY = Math.min(minY, --y); break;
					case 4: minX = Math.min(minX, --x); break;
					}
					break;
				}
				case 'B': {
					switch(dir) {
					case 1: minY = Math.min(minY, --y); break;
					case 2: minX = Math.min(minX, --x); break;
					case 3: maxY = Math.max(maxY, ++y); break;
					case 4: maxX = Math.max(maxX, ++x); break;
					}
					break;
				}
				case 'L': {
					switch(dir) {
					case 1: dir = 4; break;
					case 2:
					case 3:
					case 4: dir--; break;
					}
					break;
				}
				case 'R': {
					switch(dir) {
					case 1:
					case 2:
					case 3: dir++; break;
					case 4: dir = 1; break;
					}
					break;
				}
				}
			}
			int size = (maxX - minX) * (maxY - minY);
			sb.append(size).append('\n');
		}
		System.out.println(sb);		
	}
}
