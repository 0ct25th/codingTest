import java.io.*;
import java.util.*;

public class Main {

	static int N, K, result;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new int[N];
		for (int i = N - 1; i > -1; i--)
			A[i] = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			if (K == 0)
				break;

			result += K / A[i];
			K %= A[i];
		}
		System.out.println(result);
	}

}
