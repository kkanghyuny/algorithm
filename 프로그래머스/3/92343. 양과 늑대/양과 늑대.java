import java.io.*;
import java.util.*;

class Node {
    int isWolf;
    List<Integer> linked;
    
    public Node(int isWolf) {
        this.isWolf = isWolf;
        this.linked = new ArrayList<>();
    }
}

class Solution {
    
    static Node[] nodes;
    static int n, maxSheepCnt;
    
    public int solution(int[] info, int[][] edges) {
        maxSheepCnt = 0;
        n = info.length;
        nodes = new Node[n];
        
        for(int i = 0; i < n; i++) {
            nodes[i] = new Node(info[i]);
        }
        
        for(int i = 0; i < edges.length; i++) {
            int[] curr = edges[i];
            int a = curr[0];
            int b = curr[1];
            // 양방향 연결
            nodes[a].linked.add(b);
            nodes[b].linked.add(a);
        }
        
        boolean[] vis = new boolean[n];
        dfs(0, 0, 0, vis);
        
        return maxSheepCnt;
    }
    
    void dfs(int idx, int sheepCnt, int wolfCnt, boolean[] vis) {
        vis[idx] = true; // 현재 위치는 방문 처리
        
        if(nodes[idx].isWolf == 0) {
            sheepCnt++;
        } else {
            wolfCnt++;
        }
        
        if(wolfCnt >= sheepCnt) return; // 늑대가 넘어가면 return;
        
        if(maxSheepCnt < sheepCnt) maxSheepCnt = sheepCnt; // 양의 최대값 처리
        
        for(int i = 0; i < n; i++) {
            if(!vis[i]) continue; // 아직 가지 않은 곳이라면 continue;
            
            for(int j = 0; j < nodes[i].linked.size(); j++) {
                int nextIdx = nodes[i].linked.get(j); // 현재 방문된 곳에서 갈 수 있는 곳들
                
                if(vis[nextIdx]) continue; // 현재 방문이 되어있는 곳이라면 continue;
                
                boolean[] newVis = new boolean[n];
                for(int k = 0; k < n; k++) {
                    newVis[k] = vis[k]; // 다음 위치로 가도 vis은 그대로여야함
                }
                dfs(nextIdx, sheepCnt, wolfCnt, newVis);
            }
        }
    }
}