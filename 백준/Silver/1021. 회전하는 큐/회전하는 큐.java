import java.io.*;
import java.util.*;

public class Main {

	static int N, M, result;
	static LinkedList<Integer> dq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 큐의 크기
		M = Integer.parseInt(st.nextToken()); // 뽑아내려고 하는 수의 개수

		int[] pick = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			pick[i] = Integer.parseInt(st.nextToken());

		dq = new LinkedList<>();
		for (int i = 1; i <= N; i++)
			dq.add(i);

		for (int i = 0; i < M; i++) {
			int target = pick[i];

			if (check(target)) {
				// 2번 연산
				while (target != dq.peek()) {
					dq.offerLast(dq.pollFirst());
					result++;
				}
			} else {
				// 3번 연산
				while (target != dq.peek()) {
					dq.offerFirst(dq.pollLast());
					result++;
				}
			}

			// 1번 연산
			dq.pollFirst();
		}

		System.out.println(result);
	}

	static boolean check(int x) {
		for (int i = 0; i <= dq.size() / 2; i++) {
			if (x == dq.get(i))
				return true;
		}

		return false;
	}
}
