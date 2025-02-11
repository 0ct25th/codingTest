import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] A, idxArr;
	static List<Integer> LIS;
	static Stack<Integer> stk;

	public static void main(String[] arts) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		A = new int[N]; // 입력된 수열을 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		/////////////////////// end of Input

		idxArr = new int[N]; // 입력된 각 수열의 위치를 저장
		Arrays.fill(idxArr, -1); // idxArr 초기화
		LIS = new ArrayList<>(); // 이분탐색을 통해 증가하는 수열을 저장할 객체

		for (int i = 0; i < N; i++) {
			int key = A[i];

			if (LIS.isEmpty() || LIS.get(LIS.size() - 1) < key) {
				LIS.add(key);
				idxArr[i] = LIS.size(); // 현재 LIS의 길이
			} else {
				int start = 0;
				int end = LIS.size() - 1;

				while (start < end) {
					int mid = (start + end) / 2;

					if (LIS.get(mid) < key)
						start = mid + 1;
					else
						end = mid;
				}

				LIS.set(end, key);
				idxArr[i] = end + 1; // end + 1로 수정
			}
		}

		stk = new Stack<>();
		int idx = LIS.size(); // 현재 찾길 원하는 증가수열의 인덱스 값

		for (int i = N - 1; i > -1; i--) {
			if (idxArr[i] == idx) {
				stk.push(A[i]); // stack에 경로를 추가
				idx--; // 다음 인덱스 값
			}
		}

		// 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
		System.out.println(LIS.size());

		// 둘째 줄에는 정답이 될 수 있는 가장 긴 증가하는 부분 수열을 출력한다.
		while (!stk.isEmpty())
			System.out.print(stk.pop() + " ");
	}
}
