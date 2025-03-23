import java.io.*;
import java.util.*;

public class Main {

	static int n, result;
	static boolean isVisited[];
	static List<Node>[] nodeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		nodeList = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++)
			nodeList[i] = new ArrayList<>();

		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			// 무방향 그래프
			nodeList[a].add(new Node(b, w));
			nodeList[b].add(new Node(a, w));
		}

		/////////// end of Input

		for (int i = 1; i <= n; i++) {
			isVisited = new boolean[n + 1];
			dfs(i, 0);
		}

		System.out.println(result);
	}

	static void dfs(int num, int sum) {
		isVisited[num] = true;
		result = Math.max(result, sum);

		for (Node nxt : nodeList[num]) {
			if (isVisited[nxt.num])
				continue;

			dfs(nxt.num, sum + nxt.weight);
		}
	}

	static class Node {
		int num;
		int weight;

		Node(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}
	}
}
