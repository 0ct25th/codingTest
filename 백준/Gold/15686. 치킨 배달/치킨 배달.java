import java.io.*;
import java.util.*;

public class Main {

	static int N, M, result;
	static int[][] map, dist;
	static List<Coord> houses, chickens;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시 크기
		M = Integer.parseInt(st.nextToken()); // 치킨 집 최대 개수

		map = new int[N + 1][N + 1];
		houses = new ArrayList<>();
		chickens = new ArrayList<>();
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				int n = Integer.parseInt(st.nextToken());
				map[r][c] = n;

				switch (n) {
				case 0:
					continue;
				case 1:
					houses.add(new Coord(r, c));
					break;
				case 2:
					chickens.add(new Coord(r, c));
					break;
				}
			}
		}

		result = Integer.MAX_VALUE;
		dfs(0, 0, new ArrayList<>());
		System.out.println(result);

	}

	static void dfs(int depth, int cnt, List<Coord> choices) {
		// 기저 조건: 모든 치킨집 고려를 완료한 경우
		if (depth == chickens.size()) {
			if (0 < cnt && cnt <= M)
				result = Math.min(result, calc(choices));

			return;
		}

		// 선택한 경우
		choices.add(chickens.get(depth));
		dfs(depth + 1, cnt + 1, choices);
		choices.remove(choices.size() - 1);

		// 선택하지 않은 경우
		dfs(depth + 1, cnt, choices);
	}

	static int calc(List<Coord> choices) {
		int answer = 0;

		for (Coord house : houses) {
			int min = Integer.MAX_VALUE;
			for (Coord choice : choices)
				min = Math.min(min, Math.abs(house.r - choice.r) + Math.abs(house.c - choice.c));

			answer += min;
		}

		return answer;
	}

	static class Coord {
		int r, c; // 좌표

		Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
