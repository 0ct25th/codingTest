import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] arr;
	static List<Integer> LIS;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		///////////////////////////////////// end of Input

		BinarySearch();
	}

	static void BinarySearch() {
		LIS = new ArrayList<>();
		LIS.add(arr[0]);

		for (int i = 1; i < N; i++) {
			int key = arr[i];

			// 현재 원소가 LIS의 마지막 원소보다 작은 경우
			if (key < LIS.get(LIS.size() - 1))
				LIS.add(key); // LIS에 추가

			// 현재 원소가 LIS의 마지막 원소보다 큰 경우
			else {
				// 이진 탐색을 통해 key가 들어갈 위치 찾기
				int start = 0;
				int end = LIS.size() - 1;

				while (start < end) {
					int mid = (start + end) / 2;

					if (LIS.get(mid) > key)
						start = mid + 1;
					else
						end = mid;
				}

				// LIS의 end번 원소랑 key값 자리 바꾸기
				LIS.set(end, key);
			}
		}

		System.out.println(N - LIS.size());
	}

}
