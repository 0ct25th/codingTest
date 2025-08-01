import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] times, inDgree, result;
	static List<Integer>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		times = new int[N + 1]; // 걸리는 시간
		inDgree = new int[N + 1];
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			times[i] = Integer.parseInt(st.nextToken());

			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				int jobs = Integer.parseInt(st.nextToken());

				adjList[i].add(jobs);
				inDgree[jobs]++;
			}
		}

		result = new int[N + 1]; // 끝나는 시간
		topologySort();

		int answer = 0;
		for (int i = 1; i <= N; i++)
			answer = Math.max(answer, result[i]);

		System.out.println(answer);
	}

	static void topologySort() {
		Queue<Integer> dq = new ArrayDeque<>();

		for (int i = 1; i <= N; i++) {
			if (inDgree[i] != 0)
				continue;

			result[i] = times[i];
			dq.offer(i);
		}

		while (!dq.isEmpty()) {
			int size = dq.size();

			for (int i = 0; i < size; i++) {
				int cur = dq.poll();

				for (int nxt : adjList[cur]) {
					inDgree[nxt]--;
					result[nxt] = Math.max(result[nxt], result[cur] + times[nxt]);

					if (inDgree[nxt] == 0)
						dq.offer(nxt);
				}
			}
		}
	}
}
