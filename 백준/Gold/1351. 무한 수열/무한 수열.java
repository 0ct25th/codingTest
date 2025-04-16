import java.io.*;
import java.util.*;

public class Main {

	static long N, P, Q;
	static Map<Long, Long> A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Long.parseLong(st.nextToken());
		P = Long.parseLong(st.nextToken());
		Q = Long.parseLong(st.nextToken());

		A = new HashMap<>();
		System.out.println(recursion(N));
	}

	static long recursion(long i) {
		if (i == 0L)
			return 1;

		if (A.containsKey(i))
			return A.get(i);

		A.put(i, recursion(i / P) + recursion(i / Q));

		return A.get(i);
	}
}
