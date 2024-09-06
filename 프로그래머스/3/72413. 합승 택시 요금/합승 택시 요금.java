import java.io.*;
import java.util.*;
import java.lang.*;

class Solution {
    static class Node implements Comparable<Node>{
        int id, dist, type;
        List<Integer> link, distList;
        
        public Node(int id) {
            this.id = id;
            this.link = new ArrayList<>();
            this.distList = new ArrayList<>();
        }
        
        public Node(int id, int dist, int type) {
            this.id = id;
            this.dist = dist;
            this.type = type;
        }
        
        public int compareTo(Node n) {
            return this.dist - n.dist;
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        Node[] nodes = new Node[n + 1];
        
        for(int i = 0; i <= n; i++) {
            nodes[i] = new Node(i);
        }
        
        for(int i = 0; i < fares.length; i++){
            int st = fares[i][0];
            int ed = fares[i][1];
            int distance = fares[i][2];
            nodes[st].link.add(ed);
            nodes[st].distList.add(distance);
            nodes[ed].link.add(st);
            nodes[ed].distList.add(distance);
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] vis = new boolean[3][n + 1];
        int[][] distFromNode = new int[3][n + 1];
        // type 0: S에서부터의 거리, 1: A에서부터의 거리, 2: B에서부터의 거리
        pq.offer(new Node(s, 0, 0));
        pq.offer(new Node(a, 0, 1));
        pq.offer(new Node(b, 0, 2));
        
        while(!pq.isEmpty()) {
            Node curr = pq.poll();
            int now = curr.id;
            int dist = curr.dist;
            int type = curr.type;
            
            if(vis[type][now]) continue;
            vis[type][now] = true;
            distFromNode[type][now] = dist;
            for(int i = 0; i < nodes[now].link.size(); i++) {
                int next = nodes[now].link.get(i);
                if(vis[type][next]) continue;
                pq.offer(new Node(next, dist + nodes[now].distList.get(i), type));
            }
        }
        
        for(int i = 1; i <= n; i++) {
            // 조건문이라면 시작점부터 아예 연결X
            if(distFromNode[0][i] == 0 && i != s) continue;
            answer = Math.min(distFromNode[0][i] + distFromNode[1][i] + distFromNode[2][i], answer);
        }
        
        return answer;
    }
}

// 시작점에 아무곳도 방문하지 않은 상태(합승), A를 방문한 상태(따로 갈 경우), B를 방문한 상태(따로 갈 경우)
// 매 구간마다 세가지를 보내줘야하는듯 안터지나? 3의 n승인데

// A에서 출발해서 S, B에서 출발해서 S 를 구해서 겹치는 구간 가격 빼기
// A에서 올때 경로를 기억해두고 B에서 올때 겹치는 경로 만나면 바로 거기서부터 거리