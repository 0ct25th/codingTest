import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static Queue<Integer> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());

		q = new LinkedList<>();
		for (int i = 1; i <= N; i++)
			q.offer(i);

		while (!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(" ");

			if (q.isEmpty())
				break;

			q.offer(q.poll());
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
