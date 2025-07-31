import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static List<Integer>[] adjList;
	static int[] inner, order;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken()); // 과목의 수
		M = Integer.parseInt(st.nextToken()); // 조건의 수

		inner = new int[N + 1];
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adjList[a].add(b);
			inner[b]++;
		}

		topologySort();

		for (int i = 1; i <= N; i++)
			sb.append(order[i]).append(" ");

		System.out.println(sb);
	}

	static void topologySort() {
		Queue<Integer> dq = new ArrayDeque<>();
		order = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			if (inner[i] == 0) {
				order[i] = 1;
				dq.offer(i);
			}
		}

		int idx = 2;
		while (!dq.isEmpty()) {
			int size = dq.size();
			for (int i = 0; i < size; i++) {
				int cur = dq.poll();

				for (int nxt : adjList[cur]) {
					inner[nxt]--;

					if (inner[nxt] == 0) {
						order[nxt] = idx;
						dq.offer(nxt);
					}
				}
			}
			idx++;
		}
	}
}
