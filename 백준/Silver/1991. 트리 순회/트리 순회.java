import java.io.*;
import java.util.*;

public class Main {

	static int N, order[];
	static Node head;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		head = new Node('A', null, null);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			char root = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);

			insertNode(head, root, left, right);
		}

		order = new int[N];
		pre(head);
		sb.append("\n");
		in(head);
		sb.append("\n");
		post(head);

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static void post(Node node) {
		if (node == null)
			return;

		post(node.left);
		post(node.right);
		sb.append(node.root);
	}

	static void in(Node node) {
		if (node == null)
			return;

		in(node.left);
		sb.append(node.root);
		in(node.right);
	}

	static void pre(Node node) {
		if (node == null)
			return;

		sb.append(node.root);
		pre(node.left);
		pre(node.right);
	}

	static void insertNode(Node cur, char root, char left, char right) {
		if (cur.root == root) {
			cur.left = (left == '.' ? null : new Node(left, null, null));
			cur.right = (right == '.' ? null : new Node(right, null, null));
		} else {
			if (cur.left != null)
				insertNode(cur.left, root, left, right);

			if (cur.right != null)
				insertNode(cur.right, root, left, right);
		}
	}

	static class Node {
		char root;
		Node left, right;

		Node(char root, Node left, Node right) {
			this.root = root;
			this.left = left;
			this.right = right;
		}
	}
}
