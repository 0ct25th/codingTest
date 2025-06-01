import java.io.*;
import java.util.*;

public class Main {

	static int N, result;
	static List<Integer> A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		A = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A.add(Integer.parseInt(st.nextToken()));

		B = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			B.add(Integer.parseInt(st.nextToken()));

		Collections.sort(A);
		Collections.sort(B, (o1, o2) -> -(o1 - o2));

		for (int i = 0; i < N; i++) {
			result += A.get(i) * B.get(i);
		}

		System.out.println(result);
	}
}
