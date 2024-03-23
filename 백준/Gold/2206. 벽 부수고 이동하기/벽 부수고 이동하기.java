import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int answer = 0;

        // 큐 생성
        Queue<int[]> queue = new LinkedList<>();

        // 델타 배열 생성
        int[] dr = { 1, 0, -1, 0 };
        int[] dc = { 0, 1, 0, -1 };

        // nxm 사이즈 행렬 생성
        int[][] arr = new int[n][m];
        boolean[][] noBreakVis = new boolean[n][m];
        boolean[][] breakVis = new boolean[n][m];
        
        noBreakVis[0][0] = true;
        breakVis[0][0] = true;
        // 값 받아서 하나하나 떼서 넣어주기
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        // 큐에 시작 위치인 0,0 과 세번째 0은 현재 내가 부순적이 있는지 여부(0: 없음, 1: 있음), 네번째는 현재까지 온 거리
        queue.add(new int[]{0, 0, 0, 1});

        // 큐가 빌때까지
        while (!queue.isEmpty()) {
            // 현재 위치를 cur에 빼주고
            int[] cur = queue.poll();
            // 빈칸을 기준으로 행, 열의 인덱스를 구분해서 정수로 저장
            int r = cur[0];
            int c = cur[1];
            int isBreak = cur[2];
            int path = cur[3] + 1;
            if (r == n - 1 && c == m - 1) {
            	answer = path - 1;
            	break;
            }

            // 4방향 확인 시작
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                // 범위를 넘어가면 패스해주고
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }

                // 첫번째 if문 부순적이 없고 나아가려는 방향이 1로 막혀 있을 경우 부순적이 있다고 표시하고 거리+1
                // 두번째 if문 부순적이 있고 나아가려는 방향이 0이 아닐 경우 continue (높은 숫자라면 이미 더 빠른 경로가 있을 것이고 1이라면 막혀있을것)
                if (isBreak == 0 && arr[nr][nc] == 1) {
                    queue.add(new int[] {nr, nc, 1, path});
                    breakVis[nr][nc] = true;
//                    continue;
                } else if (isBreak == 0 && arr[nr][nc] == 0 && !noBreakVis[nr][nc]) {
                	queue.add(new int[] {nr, nc, 0, path});
                	noBreakVis[nr][nc] = true;
//                	continue;
                } else if (isBreak == 1 && arr[nr][nc] == 0 && !breakVis[nr][nc]) {
                	queue.add(new int[] {nr, nc, 1, path});
                	breakVis[nr][nc] = true;
//                	continue;
                } else if (isBreak == 1 && arr[nr][nc] == 1) {
                    continue;
                }
             // 현재 위치의 값보다 +1 해주기 (간 거리 계산)
//                arr[nr][nc] = arr[r][c] + 1;
                // 큐에 넣고 큐에 값이 생겼으니 다시 while 반복
//                queue.add(new int[] {nr, nc, isBreak});
            }
            
        }
//        // 테스트용 arr 출력
//        for(int i =0;i<n;i++) {
//            for(int j=0;j<m;j++) {
//                System.out.print(arr[i][j]+ " ");
//            }
//            System.out.println();
//        }
        
        // 마지막 위치가 0일아무 경로도 도달하지 못한 것이니 -1출력
        // 혹시 모를 1x1 배열 [0]을 위해 1 출력하게... 해두기...
        // 아닐 경우 도달한 것이니 n,m 위치의 값 출력
        if (answer == 0) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
