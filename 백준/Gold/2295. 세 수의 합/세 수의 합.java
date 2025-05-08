import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] U;
	static List<Integer> lst;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		U = new int[N];
		for (int i = 0; i < N; i++)
			U[i] = Integer.parseInt(br.readLine());

		lst = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				lst.add(U[i] + U[j]);
		}

		Arrays.sort(U);
		Collections.sort(lst);

		for (int i = N - 1; i >= 0; i--) {
			for (int j = N - 1; j >= 0; j--) {
				int minus = U[i] - U[j];

				if (Collections.binarySearch(lst, minus) >= 0) {
					System.out.println(U[i]);
					return;
				}
			}
		}
	}
}
