import java.io.*;
import java.util.*;

public class Main {

	static int N, result;
	static List<Meeting> meetings, picks;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		meetings = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			meetings.add(new Meeting(start, end));
		}

		Collections.sort(meetings);

		result = 0;
		picks = new ArrayList<>();
		set();

		System.out.println(picks.size());
	}

	static void set() {
		// greedy하게 첫 번째 회의 무조건 저장
		picks.add(meetings.get(0));

		// 마지막 회의 종료 시간보다 늦거나 같아야 저장
		for (int i = 1; i < N; i++) {
			Meeting last = picks.get(picks.size() - 1);

			if (last.end <= meetings.get(i).start)
				picks.add(meetings.get(i));
		}
	}

	static class Meeting implements Comparable<Meeting> {
		int start;
		int end;

		Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			return this.end == o.end ? (this.start - o.start) : (this.end - o.end);
		}
	}
}
