import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static Deque<Integer> dq = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String op = st.nextToken();

			if (op.equals("push_front"))
				dq.offerFirst(Integer.parseInt(st.nextToken()));
			else if (op.equals("push_back"))
				dq.offerLast(Integer.parseInt(st.nextToken()));
			else if (op.equals("pop_front"))
				sb.append(dq.isEmpty() ? -1 : dq.pollFirst()).append("\n");
			else if (op.equals("pop_back"))
				sb.append(dq.isEmpty() ? -1 : dq.pollLast()).append("\n");
			else if (op.equals("size"))
				sb.append(dq.size()).append("\n");
			else if (op.equals("empty"))
				sb.append(dq.isEmpty() ? 1 : 0).append("\n");
			else if (op.equals("front"))
				sb.append(dq.isEmpty() ? -1 : dq.peekFirst()).append("\n");
			else if (op.equals("back"))
				sb.append(dq.isEmpty() ? -1 : dq.peekLast()).append("\n");
		}

		System.out.println(sb);
	}
}
