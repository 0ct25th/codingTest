import java.io.*;
import java.util.*;

public class Main {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		Stack<Integer> stk = new Stack<>();
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			String op = st.nextToken();

			if (op.equals("push"))
				stk.add(Integer.parseInt(st.nextToken()));
			else if (op.equals("pop"))
				sb.append(stk.isEmpty() ? -1 : stk.pop()).append("\n");
			else if (op.equals("size"))
				sb.append(stk.size()).append("\n");
			else if (op.equals("empty"))
				sb.append(stk.isEmpty() ? 1 : 0).append("\n");
			else if (op.equals("top"))
				sb.append(stk.isEmpty() ? -1 : stk.peek()).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
