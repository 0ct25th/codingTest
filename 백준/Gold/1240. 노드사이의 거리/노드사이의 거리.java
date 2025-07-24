import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static List<Node>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 노드의 개수
		M = Integer.parseInt(st.nextToken()); // 거리를 알고 싶은 노드 쌍의 개수

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			// 무방향 그래프
			adjList[a].add(new Node(b, d));
			adjList[b].add(new Node(a, d));
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			sb.append(bfs(a, b)).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static int bfs(int a, int b) {
		Queue<Node> dq = new ArrayDeque<>();
		boolean[] isVisited = new boolean[N + 1];

		isVisited[a] = true;
		dq.offer(new Node(a, 0));

		while (!dq.isEmpty()) {
			Node cur = dq.poll();
			int n = cur.n;
			int d = cur.d;

			if (n == b)
				return d;

			for (Node nxt : adjList[n]) {
				if (isVisited[nxt.n])
					continue;

				isVisited[nxt.n] = true;
				dq.offer(new Node(nxt.n, d + nxt.d));
			}
		}

		return -1;
	}

	static class Node {
		int n; // 번호
		int d; // 거리

		Node(int n, int d) {
			this.n = n;
			this.d = d;
		}
	}
}
