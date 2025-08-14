import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static List<Member> members;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		members = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();

			members.add(new Member(i, age, name));
		}

		Collections.sort(members);

		StringBuilder sb = new StringBuilder();
		for (Member member : members)
			sb.append(member.age).append(" ").append(member.name).append("\n");

		System.out.println(sb);
	}

	static class Member implements Comparable<Member> {
		int n, age;
		String name;

		Member(int n, int age, String name) {
			this.n = n;
			this.age = age;
			this.name = name;
		}

		@Override
		public int compareTo(Member o) {
			if (this.age == o.age)
				return Integer.compare(this.n, o.n);

			return Integer.compare(this.age, o.age);
		}
	}
}
