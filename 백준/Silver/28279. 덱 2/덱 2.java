import java.io.*;
import java.util.*;

public class Main {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		Deque<Integer> dq = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int op = Integer.parseInt(st.nextToken());
			int num = 0;
			switch (op) {
			case 1:
				num = Integer.parseInt(st.nextToken());

				dq.offerFirst(num);
				break;
			case 2:
				num = Integer.parseInt(st.nextToken());

				dq.offer(num);
				break;
			case 3:
				if (dq.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(dq.poll()).append("\n");
				break;

			case 4:
				if (dq.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(dq.pollLast()).append("\n");
				break;

			case 5:
				sb.append(dq.size()).append("\n");
				break;

			case 6:
				if (dq.isEmpty())
					sb.append(1).append("\n");
				else
					sb.append(0).append("\n");
				break;

			case 7:
				if (dq.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(dq.peek()).append("\n");
				break;

			case 8:
				if (dq.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(dq.peekLast()).append("\n");
				break;
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
}
