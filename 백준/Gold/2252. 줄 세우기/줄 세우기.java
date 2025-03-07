import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;	// 학생의 수 -> 정점의 개수
	static int M;	// 키 비교 회수 -> 간선의 개수
	
	static int A, B;	// 학생의 번호, A가 B의 앞에 서야 한다는 의미
	static List<Integer>[] adjList;
	static int[] inDegree;	// 정점 진입 차수 저장 배열
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 첫째 줄에 N(1 ≤ N ≤ 32,000), M(1 ≤ M ≤ 100,000)이 주어진다. 
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 다음 M개의 줄에는 키를 비교한 두 학생의 번호 A, B가 주어진다.
		adjList = new ArrayList[N + 1];
		inDegree = new int[N + 1];
		for (int i = 0; i < N  + 1; i++) 
			adjList[i] = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			adjList[A].add(B);
			
			// 인접 차수 카운트
			inDegree[B]++;
		}
		
		// 위상정렬 실행
		Queue<Integer> dq = new ArrayDeque<>();
		
		// 진입차수가 0인 정점들 큐에 삽입
		for (int i = 1; i < N + 1; i++) 
			 if (inDegree[i] == 0) {
				 dq.offer(i);
				 sb.append(i + " ");
			 }
		
		// 큐가 비어 있을 때까지 실행
		while (!dq.isEmpty()) {
			// 큐의 처음 값 꺼내기
			int cur = dq.poll();
			
			// 해당 정점과 인접한 노드들의 진입 차수 1 감소 시키기
			for (int i = 0; i < adjList[cur].size(); i++) {
				inDegree[adjList[cur].get(i)]--;
				
				// 진입 차수가 0이 되었다면 큐에 삽입
				if (inDegree[adjList[cur].get(i)] == 0) {
					dq.offer(adjList[cur].get(i));
					sb.append(adjList[cur].get(i) + " ");
				}
			}
		}
		
		// 결과 출력
		System.out.println(sb);
		
		// close
		br.close();
		
	}
}
