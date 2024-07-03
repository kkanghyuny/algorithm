import java.io.*;
import java.util.*;

public class Main {
    static class Item {
        int idx;
        int value;

        public Item(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        Deque<Item> deque = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int now = Integer.parseInt(st.nextToken());

            while(!deque.isEmpty() && deque.peekLast().value > now){
                deque.pollLast();
            }

            deque.offer(new Item(i, now));
            if(i - l + 1 > deque.peekFirst().idx){
                deque.pollFirst();
            }

            sb.append(deque.peekFirst().value).append(" ");

        }
        System.out.println(sb);
    }
}
