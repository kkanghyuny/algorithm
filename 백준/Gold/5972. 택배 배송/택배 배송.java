import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    static class Node implements Comparable<Node>{
        int id;
        int cnt;

        public Node(int id, int cnt){
            this.id = id;
            this.cnt = cnt;
        }

        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int answer = 0;

        Node[] nodes = new Node[n + 1];
        boolean[] vis = new boolean[n + 1];
        List<Integer>[] links = new List[n + 1];
        List<Integer>[] dists = new List[n + 1];

        for(int i = 1; i <= n; i++){
            nodes[i] = new Node(i, 0);
            links[i] = new ArrayList<>();
            dists[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            links[a].add(b);
            dists[a].add(dist);
            links[b].add(a);
            dists[b].add(dist);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(nodes[1]);

        while(!pq.isEmpty()){
            Node curr = pq.poll();
            int id = curr.id;
            int cnt = curr.cnt;

            if(id == n) {
                answer = cnt;
                break;
            }

            if(vis[id]) continue;

            vis[id] = true;

            for(int i = 0; i < links[id].size(); i++){
                int next = links[id].get(i);
                if(vis[next]) continue;
                pq.offer(new Node(next, cnt + dists[id].get(i)));
            }
        }

        System.out.println(answer);
    }
}
