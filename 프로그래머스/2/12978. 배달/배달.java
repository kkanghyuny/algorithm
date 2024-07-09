import java.util.*;
import java.io.*;
import java.lang.*;

class Solution {
    static class Node implements Comparable<Node>{
        int id;
        int dist;
        
        public Node(int id, int dist){
            this.id = id;
            this.dist = dist;
        }
        
        public int compareTo(Node n){
            return this.dist - n.dist;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        int[][] list = new int[51][51];
        for(int i = 0; i < 51; i++){
            Arrays.fill(list[i], 987654321);
        }
        
        for(int i = 0; i < road.length; i++){
            int[] curr = road[i];
            list[curr[0]][curr[1]] = Math.min(curr[2], list[curr[0]][curr[1]]);
            list[curr[1]][curr[0]] = Math.min(curr[2], list[curr[1]][curr[0]]);
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] vis = new boolean[51];
        pq.offer(new Node(1, 0));
        
        while(!pq.isEmpty()){
            Node curr = pq.poll();
            int id = curr.id;
            int dist = curr.dist;
            
            if(vis[id]) continue;
            
            vis[id] = true;
            if(dist <= K) answer++;
            
            for(int i = 1; i < 51; i++){
                if(list[id][i] > 0){
                    pq.offer(new Node(i, dist + list[id][i]));
                }
            }
        }

        return answer;
    }
}