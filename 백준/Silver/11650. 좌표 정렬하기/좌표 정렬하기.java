import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static List<Coord> list;

	public static void main(String[] arg) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			list.add(new Coord(x, y));
		}

		Collections.sort(list);
		for (Coord coord : list)
			sb.append(coord.x).append(" ").append(coord.y).append("\n");

		bw.write(sb.toString());
		bw.flush();

		br.close();
		bw.close();
	}

	static class Coord implements Comparable<Coord> {
		int x, y;

		Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Coord o) {
			if (this.x == o.x)
				return Integer.compare(this.y, o.y);

			return Integer.compare(this.x, o.x);
		}
	}
}
