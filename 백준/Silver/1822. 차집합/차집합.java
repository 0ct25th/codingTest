import java.io.*;
import java.util.*;

public class Main {

	static int A, B;
	static Set<Integer> set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		set = new TreeSet<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < A; i++)
			set.add(Integer.parseInt(st.nextToken()));

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < B; i++)
			set.remove(Integer.parseInt(st.nextToken()));

		sb.append(set.size()).append("\n");
		for (int i : set)
			sb.append(i).append(" ");

		System.out.println(sb);
	}
}
