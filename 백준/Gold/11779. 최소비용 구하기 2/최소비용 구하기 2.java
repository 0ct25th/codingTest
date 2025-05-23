import java.io.*;
import java.util.*;

public class Main {

	static final int INF = Integer.MAX_VALUE;
	static int n, m, start, end;
	static List<Node>[] adjList;
	static int[] minDist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine()); // 도시의 개수

		m = Integer.parseInt(br.readLine()); // 버스의 개수

		adjList = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adjList[s].add(new Node(e, w));
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		dijkstra();
	}

	static void dijkstra() {
		Queue<Node> pq = new PriorityQueue<>();
		boolean[] isVisited = new boolean[n + 1];
		minDist = new int[n + 1];
		Arrays.fill(minDist, INF);
		int[] order = new int[n + 1];
		Arrays.fill(order, INF);

		minDist[start] = 0;
		order[start] = 0;
		pq.offer(new Node(start, minDist[start]));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (isVisited[cur.n])
				continue;
			isVisited[cur.n] = true;

			for (Node nxt : adjList[cur.n]) {
				if (minDist[nxt.n] <= minDist[cur.n] + nxt.w)
					continue;

				minDist[nxt.n] = minDist[cur.n] + nxt.w;
				order[nxt.n] = cur.n;
				pq.offer(new Node(nxt.n, minDist[nxt.n]));
			}
		}

		System.out.println(minDist[end]);

		Stack<Integer> stk = new Stack<>();
		int idx = end;
		while (idx != 0) {
			stk.push(idx);
			idx = order[idx];
		}
		
		System.out.println(stk.size());

		while (!stk.isEmpty())
			System.out.printf("%d ", stk.pop());
	}

	static class Node implements Comparable<Node> {
		int n; // 번호
		int w; // 비용

		Node(int n, int w) {
			this.n = n;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
}
