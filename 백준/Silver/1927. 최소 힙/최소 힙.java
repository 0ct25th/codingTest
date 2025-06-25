import java.io.*;
import java.util.*;

public class Main {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		Queue<Long> pq = new PriorityQueue<>();

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			long n = Long.parseLong(br.readLine());

			if (n == 0) {
				if (pq.isEmpty())
					sb.append("0\n");
				else
					sb.append(pq.poll()).append("\n");
			} else
				pq.offer(n);
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}
}
