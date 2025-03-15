import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] a;
	static long[] minDist;
	static List<Node>[] nodeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		a = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			a[i] = Integer.parseInt(st.nextToken());
		a[N - 1] = 0;

		nodeList = new ArrayList[N];
		for (int i = 0; i < N; i++)
			nodeList[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // 분기점 번호
			int B = Integer.parseInt(st.nextToken()); // 분기점 번호
			int t = Integer.parseInt(st.nextToken()); // 지나는 시간

			if (a[A] == 1 || a[B] == 1)
				continue;

			// 양방향 그래프
			nodeList[A].add(new Node(B, t));
			nodeList[B].add(new Node(A, t));
		}
		///////////// end of Input

		System.out.print(dijkstra(0));
	}

	static long dijkstra(int start) {
		Queue<Node> pq = new PriorityQueue<>();
		minDist = new long[N];
		Arrays.fill(minDist, Long.MAX_VALUE);

		minDist[start] = 0;
		pq.offer(new Node(start, minDist[start]));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			// 도착한 경우
			if (cur.num == N - 1)
				return minDist[cur.num]; // 최소 시간 반환
			
			// 이미 갱신된 경우
			if (cur.time > minDist[cur.num])
				continue; // 넘기기

			for (Node nxt : nodeList[cur.num]) {
				if (minDist[nxt.num] <= minDist[cur.num] + nxt.time)
					continue;

				minDist[nxt.num] = minDist[cur.num] + nxt.time;
				pq.offer(new Node(nxt.num, minDist[nxt.num]));
			}
		}

		return -1; // 갈 수 없음
	}

	static class Node implements Comparable<Node> {
		int num;
		long time;

		Node(int num, long time) {
			this.num = num;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.time, o.time);
		}
	}
}
