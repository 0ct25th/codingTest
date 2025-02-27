import java.io.*;
import java.util.*;

public class Main {

	static int N, K, result;
	static int[] S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		S = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			S[i] = Integer.parseInt(st.nextToken());

		////////////////////////// end of Input

		twoPointer();

		System.out.println(result);
	}

	static void twoPointer() {
		int end = 0, len = 0, remove = 0;
		for (int start = 0; start < N; start++) {
			while (end < N) {
				// 짝수인 경우
				if (S[end] % 2 == 0) {
					len++; // 길이 증가
					end++; // 인덱스 이동
				}
				// 홀수인 경우
				else if (S[end] % 2 != 0) {
					// 삭제 가능한 경우
					if (remove < K) {
						remove++; // 삭제 횟수 증가
						end++; // 인덱스 이동
					}
					// 삭제를 K번 이미 한 경우
					else {
						break; // 멈추기
					}
				}
			}

			// 연속한 부분 수열 중 가장 긴 길이 갱신
			result = Math.max(result, len);

			// 시작이 짝수인 경우
			if (S[start] % 2 == 0)
				len--; // 길이 감소
			// 시작이 홀수인 경우
			else
				remove--; // 삭제 횟수 감소
		}
	}
}
