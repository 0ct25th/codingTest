import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static List<Integer>[] adjList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		adjList = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < m; i++) {
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
		Queue<Person> dq = new ArrayDeque<>();
		int answer = 0;
		boolean[] isVisited = new boolean[n + 1];

		isVisited[1] = true;
		dq.offer(new Person(1, 0));

		while (!dq.isEmpty()) {
			Person curPerson = dq.poll();
			int num = curPerson.num;
			int count = curPerson.count;

			for (int nextNode : adjList[num]) {
				if (isVisited[nextNode] || count + 1 > 2)
					continue;

				isVisited[nextNode] = true;
				dq.offer(new Person(nextNode, count + 1));
				answer++;
			}
		}

		return answer;
	}

	static class Person {
		int num;
		int count;

		Person(int num, int count) {
			this.num = num;
			this.count = count;
		}
	}
}
