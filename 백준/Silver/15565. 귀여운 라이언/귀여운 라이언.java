import java.io.*;
import java.util.*;

public class Main {

	static final int INF = Integer.MAX_VALUE;
	static int N, K, result;
	static List<Integer> coordList;
	static int[] dolls;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 인형의 개수
		K = Integer.parseInt(st.nextToken()); // 라이언 개수

		dolls = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			dolls[i] = Integer.parseInt(st.nextToken());

		coordList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			if (dolls[i] == 1)
				coordList.add(i);
		}

		result = INF;
		if (coordList.size() < K)
			System.out.println(-1);
		else {
			for (int i = 0; i <= coordList.size() - K; i++) {
				int start = coordList.get(i);
				int end = coordList.get(i + K - 1);

				result = Math.min(result, end - start + 1);
			}

			System.out.println(result);
		}
	}

}
