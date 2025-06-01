import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static List<Integer>[] list;
	static int[] degree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생 수
		M = Integer.parseInt(st.nextToken()); // 비교 수

		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			list[i] = new ArrayList<>();
		degree = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			list[A].add(B);
			degree[B]++;
		}

		Queue<Integer> dq = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0) {
				sb.append(i).append(" ");
				dq.offer(i);
			}
		}

		while (!dq.isEmpty()) {
			int cur = dq.poll();

			for (int i : list[cur]) {
				degree[i]--;

				if (degree[i] == 0) {
					sb.append(i).append(" ");
					dq.offer(i);
				}
			}
		}
		
		System.out.println(sb);
	}
}
