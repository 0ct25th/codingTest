import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static List<Person> lst;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());

			lst = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int order = Integer.parseInt(st.nextToken());

				lst.add(new Person(score, order));
			}

			Collections.sort(lst, (o1, o2) -> Integer.compare(o1.score, o2.score));
			
			int result = 0;
			int min = Integer.MAX_VALUE;
			for(Person p: lst) {
				if(p.order < min) {
					result++;
					min = p.order;
				}
			}
			
			System.out.println(result);
		}

	}

	static class Person {
		int score, order;

		Person(int score, int order) {
			this.score = score;
			this.order = order;
		}
	}
}
