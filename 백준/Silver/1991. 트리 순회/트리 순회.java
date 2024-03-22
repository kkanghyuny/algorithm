import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static Node[] nodes;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		char a = 'A';
		
		nodes = new Node[n];
//		System.out.println(a - 65);
		
		for(int i = 0; i < n; i++) {
			nodes[i] = new Node(a++);
		}
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			char first = st.nextToken().charAt(0);
			char second = st.nextToken().charAt(0);
			char third = st.nextToken().charAt(0);
			
			if(second != '.') {
				nodes[first-65].left = nodes[second-65];
			}
			
			if(third != '.') {
				nodes[first-65].right = nodes[third-65];
			}
		}
		
		preorder(nodes[0]);
		sb.append('\n');
		inorder(nodes[0]);
		sb.append('\n');
		postorder(nodes[0]);
		
		System.out.println(sb);
	}
	
	static void preorder(Node start) {
		sb.append(start.data);
		
		if(start.left != null) {
			preorder(start.left);
		}
		
		if(start.right != null) {
			preorder(start.right);
		}
	}
	
	static void inorder(Node start) {
		if(start.left != null) {
			inorder(start.left);
		}
		
		sb.append(start.data);
		
		if(start.right != null) {
			inorder(start.right);
		}
	}
	
	static void postorder(Node start) {
		if(start.left != null) {
			postorder(start.left);
		}
		
		if(start.right != null) {
			postorder(start.right);
		}
		
		sb.append(start.data);
	}
	
	static class Node{
		char data;
		Node left;
		Node right;
		
		public Node() {
		}

		public Node(char data) {
			this.data = data;
		}
	}
}
