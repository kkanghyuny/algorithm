import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static class Node{
        int id;
        Node left;
        Node right;
        
        public Node(int id) {
            super();
            this.id = id;
        }
    }
    
    static StringBuilder sb = new StringBuilder();
    static Map<Integer, Node> map;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        map = new HashMap<>();
        Deque<Integer> deque = new ArrayDeque<>();
        
        String s = br.readLine();
        int start = Integer.parseInt(s);
        map.put(start, new Node(start));
        deque.offer(start);
        int prev = start;
        
        while((s = br.readLine()) != null) {
            int n = Integer.parseInt(s);
            map.put(n, new Node(n));
            
            if((prev = deque.peekLast()) > n) {
                Node curr = map.get(prev);
                curr.left = map.get(n);
                map.put(prev, curr);
            } else {
            	while(!deque.isEmpty() && deque.peekLast() < n) {
            		prev = deque.pollLast();
            	}
            	
            	Node curr = map.get(prev);
            	curr.right = map.get(n);
            	map.put(prev, curr);
            }
            deque.offer(n);
        }
        
        postfix(start);
        
        System.out.println(sb);
    }
    
    static void postfix(int n) {
        Node curr = map.get(n);
        if(curr.left != null) {
            postfix(curr.left.id);
        }
        if(curr.right != null) {
            postfix(curr.right.id);
        }
        sb.append(n).append('\n');
    }
}