import java.io.*;
import java.util.*;

public class Main {

	static int N, w[], result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		w = new int[N];
		for (int i = 0; i < N; i++)
			w[i] = Integer.parseInt(br.readLine());

		Arrays.sort(w);

		for (int i = N - 1; i > -1; i--) {
			result = Math.max(result, w[i] * (N - i));
		}

		System.out.println(result);
	}
}
