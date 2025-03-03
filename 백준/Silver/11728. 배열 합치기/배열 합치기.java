import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static List<Integer> lst;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		lst = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			lst.add(Integer.parseInt(st.nextToken()));

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			lst.add(Integer.parseInt(st.nextToken()));

		//////////////////// end of Input

		Collections.sort(lst);

		for (int i : lst)
			sb.append(i).append(" ");

		System.out.println(sb);
	}
}
