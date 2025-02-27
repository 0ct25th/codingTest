import java.io.*;
import java.util.*;

public class Main {

	static int N, d, k, c;
	static int[] order, eated;
	static long result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 접시의 수
		d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
		k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		order = new int[N];
		for (int i = 0; i < N; i++)
			order[i] = Integer.parseInt(br.readLine());

		slidingWindow();

		System.out.println(result);
	}

	static void slidingWindow() {
		eated = new int[d + 1]; // 먹은 인덱스 번호 스시 개수 저장할 배열
		eated[c] = 1; // 쿠폰으로 받은 스시 개수 1로 시작
		long cnt = 1l; // 쿠폰으로 받은 스시 접시 개수 1로 시작

		// 시작하는 윈도우
		for (int i = 0; i < k; i++) {
			// 이 접시를 처음 먹은 경우
			if (eated[order[i]] == 0)
				cnt++; // 개수 증가

			eated[order[i]]++; // 먹은 접시 증가
		}

		result = cnt; // 시작 윈도우 값으로 시작하기

		// 슬라이딩 윈도우 시작
		for (int i = 1; i < N; i++) {
			// 왼쪽 빼기
			eated[order[i - 1]]--; // 먹은 접시 감소
			if (eated[order[i - 1]] == 0) // 현재 윈도우에서 i번 스시 안먹은 경우
				cnt--; // 개수 감소

			// 오른쪽 더하기
			if (eated[order[(i + k - 1) % N]] == 0) // 현재 윈도우에서 i번 스시 처음 먹은 경우
				cnt++; // 개수 증가
			eated[order[(i + k - 1) % N]]++; // 먹은 접시 증가

			// 결과값 갱신
			result = Math.max(result, cnt);
		}
	}
}
