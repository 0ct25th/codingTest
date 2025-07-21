import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static Deque<Integer> dq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		dq = new ArrayDeque<>();
		dq.offer(N);
		for (int i = N - 1; i > 0; i--) {
			dq.offerFirst(i);

			for (int j = 0; j < i; j++)
				dq.offerFirst(dq.pollLast());
		}

		while (!dq.isEmpty())
			sb.append(dq.poll()).append(" ");

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
