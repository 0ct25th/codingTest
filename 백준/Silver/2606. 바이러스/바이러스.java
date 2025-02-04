import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static List<Integer>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// 양방향 그래프
			adjList[a].add(b);
			adjList[b].add(a);
		}

		System.out.println(bfs(1));
	}

	static int bfs(int start) {
		Queue<Integer> dq = new ArrayDeque<>();
		int answer = 0;
		boolean[] isVisited = new boolean[N + 1];

		// 1번 컴퓨터 = 시작점
		isVisited[1] = true;
		dq.offer(1);

		while (!dq.isEmpty()) {
			int curNode = dq.poll();

			for (int nextNode : adjList[curNode]) {
				if (isVisited[nextNode])
					continue;

				isVisited[nextNode] = true; // 방문 체크
				dq.offer(nextNode); // 덱 삽입
				answer++; // 바이러스 걸린 컴퓨터 수 증가
			}
		}

		return answer;
	}
}
