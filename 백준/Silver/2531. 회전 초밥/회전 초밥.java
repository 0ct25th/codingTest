import java.io.*;
import java.util.*;

public class Main {

	static int N, d, k, c, result;
	static int[] order, eated;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 접시의 수
		d = Integer.parseInt(st.nextToken()); // 초밥 가짓 수
		k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		c = Integer.parseInt(st.nextToken()); // 쿠폰의 번호

		order = new int[N];
		for (int i = 0; i < N; i++)
			order[i] = Integer.parseInt(br.readLine());

		//////// end of Input

		slidingWindow();

		System.out.println(result);

	}

	static void slidingWindow() {
		eated = new int[d + 1];
		eated[c] = 1;
		int cnt = 1;

		// 0번부터 k개 스시 먹기 -> 처음 윈도우
		for (int i = 0; i < k; i++) {
			if (eated[order[i]] == 0)
				cnt++;

			eated[order[i]]++;
		}

		// 결과의 시작은 처음 윈도우에서 먹은 스시 개수
		result = cnt;

		// 이후 슬라이딩 윈도우
		for (int i = 1; i < N; i++) {
			// 왼쪽 빼기
			eated[order[i - 1]]--;
			if (eated[order[i - 1]] == 0) // 먹은 접시 없으면
				cnt--; // 개수 줄이기

			// 오른쪽 더하기
			if (eated[order[(i + k - 1) % N]] == 0) // 먹은 접시 없으면
				cnt++; // 개수 더하기
			eated[order[(i + k - 1) % N]]++;

			result = Math.max(result, cnt);
		}
	}
}
