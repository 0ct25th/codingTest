import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		Set<Integer> A = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < a; i++)
			A.add(Integer.parseInt(st.nextToken()));

		Set<Integer> B = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < b; i++)
			B.add(Integer.parseInt(st.nextToken()));

		int result = 0;
		for (int i : A) {
			if (B.contains(i))
				continue;

			result++;
		}

		for (int i : B) {
			if (A.contains(i))
				continue;

			result++;
		}

		System.out.println(result);
	}
}
