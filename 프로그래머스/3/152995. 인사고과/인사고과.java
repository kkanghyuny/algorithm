import java.util.*;
import java.io.*;
import java.lang.*;

class Solution {
    
    static class Score implements Comparable<Score> {
        int a;
        int b;
        
        public Score(int a, int b){
            this.a = a;
            this.b = b;
        }
        
        public int compareTo(Score s){
            if(this.a == s.a){
                return this.b - s.b;
            }
            return s.a - this.a;
        }
    }
    public int solution(int[][] scores) {
        int answer = -1;
        boolean isWanho = false;
        int[] rank = new int[200_001];
        int wanhoA = scores[0][0];
        int wanhoB = scores[0][1];
        int maxB = 0;
        
        PriorityQueue<Score> pq = new PriorityQueue<>();
        
        for(int i = 0; i < scores.length; i++){
            pq.offer(new Score(scores[i][0], scores[i][1]));
        }
        
        while(!pq.isEmpty()){
            Score curr = pq.poll();
            
            if(curr.a == wanhoA && curr.b == wanhoB && curr.b >= maxB){
                maxB = curr.b;
                rank[curr.a + curr.b]++;
                isWanho = true;
                continue;
            }
            
            if(curr.b >= maxB){
                maxB = curr.b;
                rank[curr.a + curr.b]++;
            }
        }
        
        if(isWanho){
            answer = 1;
            for(int i = 200_000; i > wanhoA + wanhoB; i--){
                answer += rank[i];
            }
        }
        
        return answer;
    }
}