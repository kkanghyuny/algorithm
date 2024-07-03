import java.util.*;
import java.io.*;

public class Main {
    static class Node{
        int parent;
        int childCnt;
        List<Integer> childs;

        public Node(int childCnt){
            this.childCnt = childCnt;
            this.parent = 0;
            this.childs = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[n];
        int start = 0;

        for(int i = 0; i < n; i++){
            nodes[i] = new Node(0);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int parent = Integer.parseInt(st.nextToken());
            if(parent == -1) {
                start = i;
                continue;
            }
            nodes[i].parent = parent;
            nodes[parent].childs.add(i);
            nodes[parent].childCnt++;
        }

        int vis = Integer.parseInt(br.readLine());
        nodes[nodes[vis].parent].childCnt--;

        if(vis == start){
            System.out.println(0);
            return;
        }
        
        Queue<Node> queue = new ArrayDeque<>();
        int answer = 0;
        queue.offer(nodes[start]);

        while(!queue.isEmpty()){
            Node curr = queue.poll();
            if(curr.childCnt == 0){
                answer++;
                continue;
            }

            for(int i = 0; i < curr.childs.size(); i++){
                if(curr.childs.get(i) == vis) continue;
                queue.offer(nodes[curr.childs.get(i)]);
            }
        }

        System.out.println(answer);
    }
}
