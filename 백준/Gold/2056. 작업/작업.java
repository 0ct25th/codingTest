import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] time, inDgree, endTime;
	static List<Integer>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		time = new int[N + 1];
		inDgree = new int[N + 1];
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());

			int cnt = Integer.parseInt(st.nextToken());

			for (int j = 0; j < cnt; j++) {
				int num = Integer.parseInt(st.nextToken());
				adjList[num].add(i);
				inDgree[i]++;
			}

		}

		topologySort();

		int answer = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++)
			answer = Math.max(answer, endTime[i]);

		System.out.println(answer);
	}

	static void topologySort() {
		Queue<Integer> dq = new ArrayDeque<>();
		endTime = new int[N + 1];

		// 인접 차수 0
		for (int i = 1; i <= N; i++) {
			if (inDgree[i] == 0) {
				dq.offer(i);
				endTime[i] = time[i];
			}
		}

		while (!dq.isEmpty()) {
			int cur = dq.poll();

			// 인접한 노드들 차수 감소
			for (int i = 0; i < adjList[cur].size(); i++) {
				int nxt = adjList[cur].get(i);

				inDgree[nxt]--; // 차수 감소
				endTime[nxt] = Math.max(endTime[nxt], endTime[cur] + time[nxt]);

				if (inDgree[nxt] == 0)
					dq.offer(nxt); // 덱 삽입
			}
		}
	}

}
