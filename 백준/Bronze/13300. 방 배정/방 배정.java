import java.io.*;
import java.util.*;

public class Main {

	static int N, K, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학생 수
		K = Integer.parseInt(st.nextToken()); // 최대 인원

		Map<Integer, Integer> female = new HashMap<>();
		Map<Integer, Integer> male = new HashMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken()); // 성별 0: 여, 1: 남
			int Y = Integer.parseInt(st.nextToken()); // 학년

			// 여학생인 경우
			if (S == 0) {
				if (female.containsKey(Y)) {
					if (female.get(Y) < K)
						female.replace(Y, female.get(Y) + 1);
					else {
						result++;
						female.replace(Y, 1);
					}
				} else {
					result++;
					female.put(Y, 1);
				}
			}

			// 남학생인 경우
			else {
				if (male.containsKey(Y)) {
					if (male.get(Y) < K)
						male.replace(Y, male.get(Y) + 1);
					else {
						result++;
						male.replace(Y, 1);
					}
				} else {
					result++;
					male.put(Y, 1);
				}
			}
		}

		System.out.println(result);
	}
}
