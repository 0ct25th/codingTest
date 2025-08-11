import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static Queue<Student> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int k = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			pq.offer(new Student(name, k, e, m));
		}

		while (!pq.isEmpty()) {
			Student cur = pq.poll();
			sb.append(cur.name).append("\n");
		}

		System.out.println(sb);
	}

	static class Student implements Comparable<Student> {
		String name;
		int k, e, m;

		Student(String name, int k, int e, int m) {
			this.name = name;
			this.k = k;
			this.e = e;
			this.m = m;
		}

		@Override
		public int compareTo(Student o) {
			// 국어 내림차순
			if (k != o.k)
				return Integer.compare(o.k, this.k);
			// 영어 오름차순
			if (e != o.e)
				return Integer.compare(this.e, o.e);
			// 수학 내림차순
			if (m != o.m)
				return Integer.compare(o.m, this.m);
			// 이름 사전순 오름차순
			return this.name.compareTo(o.name);
		}
	}
}
