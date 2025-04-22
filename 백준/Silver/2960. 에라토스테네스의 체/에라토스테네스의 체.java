import java.io.*;
import java.util.*;

public class Main {

	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N + 1];
		for (int i = 2; i <= N; i++)
			arr[i] = i;

		for (int i = 2; i <= N; i++) {
			if (arr[i] == 0)
				continue; // 이미 지운 수는 건너뛰기

			// 배수들 지우기
			for (int j = i; j <= N; j += i) {
				if (arr[j] != 0) {
					arr[j] = 0;
					K--;

					if (K == 0) {
						System.out.print(j);
						break;
					}
				}
			}
		}
	}
}
