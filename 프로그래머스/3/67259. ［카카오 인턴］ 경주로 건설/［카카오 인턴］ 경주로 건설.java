import java.io.*;
import java.util.*;
import java.lang.*;

class Solution {
    
    static int row, col;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    
    static class Node implements Comparable<Node> {
        int r, c, cost, dir;
        
        public Node(int r, int c, int cost, int dir) {
            this.r = r;
            this.c = c;
            this.cost = cost;
            this.dir = dir;
        }
        
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }
    
    public int solution(int[][] board) {
        int answer = 0;
        row = board.length;
        col = board[0].length;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 동
        if(board[0][1] != 1) pq.offer(new Node(0, 1, 100, 0));
        // 남
        if(board[1][0] != 1) pq.offer(new Node(1, 0, 100, 1));
        
        boolean[][][] vis = new boolean[row][col][4];
        for(int d = 0; d < 4; d++) {
            vis[0][0][d] = true;
        }
        
        while(!pq.isEmpty()) {
            Node curr = pq.poll();
            int r = curr.r;
            int c = curr.c;
            int cost = curr.cost;
            int dir = curr.dir;
            
            if(r == row - 1 && c == col - 1) {
                answer = cost;
                break;
            }
            
            if(vis[r][c][dir]) continue;
            vis[r][c][dir] = true;
            
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            
            if(checkBoundary(nr, nc) && !vis[nr][nc][dir] && board[nr][nc] != 1) {
                pq.offer(new Node(nr, nc, cost + 100, dir));
            }
            
            int nr2 = r + dr[(dir + 1) % 4];
            int nc2 = c + dc[(dir + 1) % 4];
            int nr3 = r + dr[(dir + 3) % 4];
            int nc3 = c + dc[(dir + 3) % 4];
            
            if(checkBoundary(nr2, nc2) && !vis[nr2][nc2][(dir + 1) % 4] && board[nr2][nc2] != 1) {
                pq.offer(new Node(nr2, nc2, cost + 600, (dir + 1) % 4));
            }
            
            if(checkBoundary(nr3, nc3) && !vis[nr3][nc3][(dir + 3) % 4] && board[nr3][nc3] != 1) {
                pq.offer(new Node(nr3, nc3, cost + 600, (dir + 3) % 4));
            }
            
        }
        
        return answer;
    }
    
    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < row && c >= 0 && c < col;
    }
}