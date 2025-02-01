import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static Queue<Member> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();

			pq.offer(new Member(age, i, name));
		}

		while (!pq.isEmpty()) {
			Member member = pq.poll();

			sb.append(member.age).append(" ").append(member.name).append("\n");
		}

		System.out.println(sb);
	}

	static class Member implements Comparable<Member> {
		int age;
		int order;
		String name;

		Member(int age, int order, String name) {
			this.age = age;
			this.order = order;
			this.name = name;
		}

		@Override
		public int compareTo(Member o) {
			// 나이가 같으면 가입한 순
			return this.age == o.age ? (this.order - o.order) : (this.age - o.age);
		}
	}
}
