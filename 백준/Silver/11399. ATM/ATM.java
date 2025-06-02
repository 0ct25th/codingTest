import java.io.*;
import java.util.*;

public class Main {

	static int N, P[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 사람의 수
		P = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			P[i] = Integer.parseInt(st.nextToken());

		System.out.println(greedy());
	}

	static int greedy() {
		int sum = 0;
		int result = 0;
		Arrays.sort(P);

		result = P[0];
		sum = P[0];
		for (int i = 1; i < N; i++) {
			result += sum + P[i];
			sum += P[i];
		}

		return result;
	}
}
