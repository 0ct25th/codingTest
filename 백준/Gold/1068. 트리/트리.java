import java.io.*;
import java.util.*;

public class Main {

	static int N, parent[], M, start, result;
	static boolean isVisited[];
	static List<Integer>[] nodeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		nodeList = new ArrayList[N];
		for (int i = 0; i < N; i++)
			nodeList[i] = new ArrayList<>();

		parent = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int p = Integer.parseInt(st.nextToken());
			parent[i] = p;

			// 부모가 없는 루트인 경우
			if (p == -1) {
				start = i;
				continue;
			}

			nodeList[p].add(i);
		}

		// 지울 노드 번호
		M = Integer.parseInt(br.readLine());

		///////////////////// end of Input
		
		// 지울 노드의 부모 자식 리스트에서 지우기
		if(parent[M] == -1)
			nodeList[M].clear();
		else {
			int p = parent[M];
			for(int i = 0; i < nodeList[p].size(); i++) {
				if(nodeList[p].get(i) != M)
					continue;
				
				nodeList[p].remove(i);
				break;
			}
			
			isVisited = new boolean[N];
			dfs(start);
		}

		System.out.println(result);
	}

	static void dfs(int cur) {
		isVisited[cur] = true;
		
		// 현재 노드가 리프 노드인 경우
		if(nodeList[cur].size() == 0)
			result++;

		// 현재 노드의 자식이 있는 경우
		for (int nxt : nodeList[cur]) {
			if (nxt == M || isVisited[nxt])
				continue;

			// 연결된 노드(nxt)가 리프 노드인 경우
			if (nodeList[nxt].size() == 0)
				result++;
			else
				dfs(nxt);
		}
	}
}
