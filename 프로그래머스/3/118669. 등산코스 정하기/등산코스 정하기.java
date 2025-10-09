import java.io.*;
import java.util.*;

class Solution {
    
    class Node {
        int id;
        List<Integer> linked = new ArrayList<>();
        List<Integer> costs = new ArrayList<>();
        public Node(int id) { this.id = id; }
    }
    
    class Path implements Comparable<Path> {
        int now, maxTime;
        public Path(int now, int maxTime) {
            this.now = now;
            this.maxTime = maxTime;
        }
        @Override
        public int compareTo(Path o) {
            return Integer.compare(this.maxTime, o.maxTime); // 오름차순
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Set<Integer> gateSet = new HashSet<>();
        Set<Integer> summitSet = new HashSet<>();
        for (int g : gates) gateSet.add(g);
        for (int s : summits) summitSet.add(s);
        
        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) nodes[i] = new Node(i);
        for (int[] p : paths) {
            nodes[p[0]].linked.add(p[1]);
            nodes[p[0]].costs.add(p[2]);
            nodes[p[1]].linked.add(p[0]);
            nodes[p[1]].costs.add(p[2]);
        }
        
        int INF = 987_654_321;
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, INF);
        
        PriorityQueue<Path> pq = new PriorityQueue<>();
        for (int g : gates) {
            intensity[g] = 0;
            pq.offer(new Path(g, 0));
        }
        
        while (!pq.isEmpty()) {
            Path curr = pq.poll();
            int now = curr.now;
            int maxTime = curr.maxTime;
            
            if (summitSet.contains(now)) continue; // 산봉우리는 도착점
            if (maxTime > intensity[now]) continue;
            
            for (int i = 0; i < nodes[now].linked.size(); i++) {
                int next = nodes[now].linked.get(i);
                int cost = nodes[now].costs.get(i);
                int newIntensity = Math.max(maxTime, cost);
                
                if (newIntensity < intensity[next] && !gateSet.contains(next)) {
                    intensity[next] = newIntensity;
                    pq.offer(new Path(next, newIntensity));
                }
            }
        }
        
        int bestSummit = INF;
        int bestIntensity = INF;
        Arrays.sort(summits); // 낮은 번호 우선
        
        for (int s : summits) {
            if (intensity[s] < bestIntensity) {
                bestIntensity = intensity[s];
                bestSummit = s;
            }
        }
        
        return new int[]{bestSummit, bestIntensity};
    }
}
