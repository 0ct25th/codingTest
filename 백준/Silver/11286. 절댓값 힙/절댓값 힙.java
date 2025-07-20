import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static Queue<Num> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());

			if (x != 0)
				pq.offer(new Num(x, Math.abs(x)));
			else {
				if (pq.isEmpty())
					sb.append("0\n");
				else {
					Num cur = pq.poll();

					sb.append(cur.org).append("\n");
				}
			}
		}

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static class Num implements Comparable<Num> {
		int org, abs;

		Num(int org, int abs) {
			this.org = org;
			this.abs = abs;
		}

		@Override
		public int compareTo(Num o) {
			if (this.abs == o.abs)
				return Integer.compare(this.org, o.org);

			return Integer.compare(this.abs, o.abs);
		}
	}
}
