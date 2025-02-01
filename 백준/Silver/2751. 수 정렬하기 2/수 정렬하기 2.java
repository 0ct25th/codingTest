import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static List<Integer> lst;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		lst = new ArrayList<>();

		for (int i = 0; i < N; i++)
			lst.add(Integer.parseInt(br.readLine()));

		Collections.sort(lst);

		for (int i : lst)
			sb.append(i).append("\n");

		System.out.println(sb);
	}
}
