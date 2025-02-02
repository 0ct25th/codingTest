import java.io.*;
import java.util.*;

public class Main {

	static int K, N;
	static long end;
	static int[] cable;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken()); // 오영식이 이미 가지고 있는 랜선의 개수
		N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수

		// K줄에 걸쳐 이미 가지고 있는 각 랜선의 길이가 센티미터 단위의 정수로 입력
		cable = new int[K];
		for (int i = 0; i < K; i++) {
			cable[i] = Integer.parseInt(br.readLine());

			if (end < cable[i])
				end = cable[i];
		}
		end++; // 가장 큰 값 + 1

		System.out.println(binarySearch() - 1);
	}

	static long binarySearch() {
		long start = 1;
		long mid = 0;

		while (start < end) {
			mid = (start + end) / 2; // 범위 내 중간 길이
			long count = 0;

			// 구해진 중간 길이로 잘라 총 몇개가 만들어지는지 구함
			for (int i = 0; i < K; i++)
				count += (cable[i] / mid);

			if (count < N)
				end = mid;
			else
				start = mid + 1;
		}
		
		return start;
	}

}
