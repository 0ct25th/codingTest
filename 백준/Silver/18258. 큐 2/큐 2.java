import java.io.*;
import java.util.*;

public class Main {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		Deque<Integer> dq = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String op = st.nextToken();

			if (op.equals("push")) {
				int num = Integer.parseInt(st.nextToken());
				dq.offer(num);
			} else if (op.equals("pop"))
				sb.append(dq.isEmpty() ? -1 : dq.pollFirst()).append("\n");
			else if (op.equals("size"))
				sb.append(dq.size()).append("\n");
			else if (op.equals("empty"))
				sb.append(dq.isEmpty() ? 1 : 0).append("\n");
			else if (op.equals("front"))
				sb.append(dq.isEmpty() ? -1 : dq.peekFirst()).append("\n");
			else if (op.equals("back"))
				sb.append(dq.isEmpty() ? -1 : dq.peekLast()).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
