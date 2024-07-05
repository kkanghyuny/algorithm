import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    static class Node implements Comparable<Node>{
        int id;
        int time;

        public Node(int id, int time){
            this.id = id;
            this.time = time;
        }

        public int compareTo(Node n){
            return this.time - n.time;
        }
    }

    static int t, n, k;
    static int[] time, cnt, maxTime;
    static List<Integer>[] list;
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= t; tc++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            pq = new PriorityQueue<>();
            time = new int[n + 1];
            cnt = new int[n + 1];
            maxTime = new int[n + 1];
            list = new List[n + 1];


            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                time[i] = Integer.parseInt(st.nextToken());
                list[i] = new ArrayList<>();
            }

            for(int i = 0; i < k; i++){
                st = new StringTokenizer(br.readLine());
                int prev = Integer.parseInt(st.nextToken());
                int next = Integer.parseInt(st.nextToken());
                cnt[next]++;
                list[prev].add(next);
            }

            for(int i = 1; i <= n; i++){
                if(cnt[i] == 0) pq.offer(new Node(i, 0));
            }

            int goal = Integer.parseInt(br.readLine());
            int answer = 0;

            while(!pq.isEmpty()){
                Node curr = pq.poll();
                int id = curr.id;

                if(id == goal) {
                    answer = curr.time + time[id];
                    break;
                }

                for(int i = 0; i < list[id].size(); i++){
                    cnt[list[id].get(i)]--;
                    maxTime[list[id].get(i)] = Math.max(maxTime[list[id].get(i)], curr.time + time[id]);
                    if(cnt[list[id].get(i)] == 0) pq.offer(new Node(list[id].get(i), maxTime[list[id].get(i)]));
                }
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
