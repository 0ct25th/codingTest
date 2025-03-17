import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] s; // 스위치 번호
	static Map<Integer, Integer> hash; // 전구 번호
	static List<Integer> LIS; // LIS
	static Pair[] track; // 스위치 번호와 LIS 인덱스 매핑
	static int[] org; // 원래 스위치 번호

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		s = new int[N + 1]; // 스위치 번호
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			s[i] = Integer.parseInt(st.nextToken());

		hash = new HashMap<>(); // 전구 번호
		org = new int[N + 1]; // 원래 스위치 번호
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int bulb = Integer.parseInt(st.nextToken());
			hash.put(bulb, i);
			org[i] = bulb;
		}

		binarySearch();

		// 첫 번째 줄에는 가장 많은 전구가 켜지게 하는 스위치의 수를 출력
		sb.append(LIS.size()).append("\n");

		// 두 번째 줄에는 눌러야 하는 스위치의 번호를 오름차순(번호가 커지는 순서)으로 빈칸을 사이에 두고 하나의 줄에 출력
		List<Integer> result = new ArrayList<>();
		int idx = LIS.size() - 1;
		for (int i = N; i >= 1; i--) {
			if (track[i].idx == idx) {
				result.add(s[i]);
				idx--;
			}
		}
		Collections.sort(result);
		for (int i : result)
			sb.append(i).append(" ");

		System.out.println(sb);
	}

	static void binarySearch() {
		track = new Pair[N + 1];
		LIS = new ArrayList<>();

		// 첫 번째
		track[1] = new Pair(hash.get(s[1]), 0);
		LIS.add(hash.get(s[1]));

		for (int i = 2; i <= N; i++) {
			int bulbIndex = hash.get(s[i]);

			if (LIS.get(LIS.size() - 1) < bulbIndex) {
				LIS.add(bulbIndex);
				track[i] = new Pair(bulbIndex, LIS.size() - 1);
			} else {
				int left = 0;
				int right = LIS.size() - 1;

				while (left < right) {
					int mid = (left + right) / 2;

					if (LIS.get(mid) < bulbIndex)
						left = mid + 1;
					else
						right = mid;
				}

				LIS.set(left, bulbIndex);
				track[i] = new Pair(bulbIndex, left);
			}
		}

		int idx = LIS.size() - 1;
		for (int i = N; i >= 1; i--) {
			if (track[i].idx == idx) {
				LIS.set(idx, org[track[i].n]);
				idx--;
			}
		}
	}

	static class Pair {
		int n, idx;

		Pair(int n, int idx) {
			this.n = n;
			this.idx = idx;
		}
	}
}
