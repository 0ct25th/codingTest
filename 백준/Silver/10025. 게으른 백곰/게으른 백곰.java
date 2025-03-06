import java.io.*;
import java.util.*;

public class Main {

	static int N, K, coord[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		coord = new int[1000001];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken()); // 얼음의 양
			int x = Integer.parseInt(st.nextToken()); // x 좌표

			coord[x] = g;
		}
		//////////// end of Input

		System.out.println(slidingWindow());
	}

	static int slidingWindow() {
		int result = 0;
		int windowSize = 2 * K + 1;
		int windowSum = 0;

		// 초기 윈도우 계산
		for (int i = 0; i <= Math.min(windowSize - 1, 1000000); i++)
			windowSum += coord[i];
		result = windowSum;

		// 슬라이딩 윈도우
		for (int i = 1; i <= 1000000 - windowSize + 1; i++) {
			// 왼쪽 제거
			windowSum -= coord[i - 1];
			// 오른쪽 추가 (윈도우의 오른쪽 끝 = i + windowSize - 1)
			if (i + windowSize - 1 <= 1000000)
				windowSum += coord[i + windowSize - 1];

			result = Math.max(result, windowSum);
		}

		return result;
	}
}
