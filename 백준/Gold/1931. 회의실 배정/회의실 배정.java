import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static Queue<Meeting> meetings;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		meetings = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			meetings.offer(new Meeting(start, end));
		}

		System.out.println(greedy());
	}

	static int greedy() {
		int result = 1;
		Meeting cur = meetings.poll();

		while (!meetings.isEmpty()) {
			Meeting nxt = meetings.poll();

			if (cur.end > nxt.start)
				continue;

			cur = nxt;
			result++;
		}

		return result;
	}

	static class Meeting implements Comparable<Meeting> {
		int start, end;

		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			if (this.end == o.end)
				return Integer.compare(this.start, o.start);

			return Integer.compare(this.end, o.end);
		}
	}
}
