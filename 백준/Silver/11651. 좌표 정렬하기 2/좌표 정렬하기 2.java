import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static Queue<Coord> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			pq.offer(new Coord(x, y));
		}

		while (!pq.isEmpty()) {
			Coord coord = pq.poll();

			sb.append(coord.x).append(" ").append(coord.y).append("\n");
		}

		System.out.println(sb);
	}

	static class Coord implements Comparable<Coord> {
		int x, y;

		Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Coord o) {
			return this.y == o.y ? (this.x - o.x) : (this.y - o.y);
		}
	}
}
