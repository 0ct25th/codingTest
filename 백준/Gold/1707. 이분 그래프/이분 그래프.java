import java.io.*;
import java.util.*;

public class Main {

	static int V, E, isVisited[];
	static List<Integer>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 첫째 줄에 테스트 케이스의 개수 K가 주어진다.
		int K = Integer.parseInt(br.readLine());
		for (int t = 0; t < K; t++) {
			// 각 테스트 케이스의 첫째 줄에는 그래프의 정점의 개수 V와 간선의 개수 E가 빈 칸을 사이에 두고 순서대로 주어진다.
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			adjList = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++)
				adjList[i] = new ArrayList<>();

			// 둘째 줄부터 E개의 줄에 걸쳐 간선에 대한 정보가 주어지는데,
			// 각 줄에 인접한 두 정점의 번호 u, v (u ≠ v)가 빈 칸을 사이에 두고 주어진다.
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());

				// 무방향 그래프
				adjList[u].add(v);
				adjList[v].add(u);
			}
			////////////////// end of Input

			boolean isBipartite = true; // 이분 그래프 판별 변수
			isVisited = new int[V + 1];

            // 모든 정점을 체크하여 BFS 실행
            for (int i = 1; i <= V; i++) {
                if (isVisited[i] == 0) { // 아직 방문하지 않았다면
                    if (!bfs(i)) { // BFS를 실행하고, 이분 그래프가 아닐 경우 false 반환
                        isBipartite = false;
                        break; // 더 이상 검사할 필요 없음
                    }
                }
            }
            
            sb.append(isBipartite ? "YES\n" : "NO\n");

		} // end of TestCase

		System.out.println(sb);
	}

	static boolean bfs(int start) {
		Queue<Integer> dq = new ArrayDeque<>();
		
		isVisited[start] = 1; // 시작 정점을 그룹 1로 칠함
		dq.offer(start);

		while (!dq.isEmpty()) {
			int cur = dq.poll();

			for (int next : adjList[cur]) {
				// 미방문한 경우
				if(isVisited[next] == 0) {
					// 현재 정점과 다른 그룹으로 칠함
					isVisited[next] = isVisited[cur] == 1 ? 2 : 1;
					dq.offer(next);
				}
				
				// 이미 방문한 접점이 현재 정점과 같은 그룹인 경우
				if(isVisited[next] == isVisited[cur])
					return false; // 이분 그래프가 아님
			}
		}
		
		return true;
	}

}
