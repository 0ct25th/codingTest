import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K, result;
	static List<FireBall> fireBalls;
	static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 격자 크기
		M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
		K = Integer.parseInt(st.nextToken()); // 명령 개수

		fireBalls = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			result += m; // 파이어볼 질량의 합
			fireBalls.add(new FireBall(r, c, m, s, d));
		}

		for (int i = 0; i < K; i++)
			move();

		System.out.println(result);
	}

	static void move() {
		Map<String, List<FireBall>> hash = new HashMap<>();

		// 모든 파이어볼 자신의 방향 d로 속력 s만큼 이동
		for (FireBall ball : fireBalls) {
			int nr = (ball.r - 1 + dr[ball.d] * ball.s) % N;
			nr = (nr < 0) ? nr + N + 1 : nr + 1;

			int nc = (ball.c - 1 + dc[ball.d] * ball.s) % N;
			nc = (nc < 0) ? nc + N + 1 : nc + 1;

			String key = nr + " " + nc;
			if (!hash.containsKey(key))
				hash.put(key, new ArrayList<>());
			hash.get(key).add(new FireBall(nr, nc, ball.m, ball.s, ball.d));
		}

		// 이동 끝난 뒤, 2개 이상 파이어볼이 있는 칸
		List<FireBall> newBalls = new ArrayList<>();
		for (String key : hash.keySet()) {
			List<FireBall> list = hash.get(key);

			// 해당 좌표에 한개인 경우
			if (list.size() == 1) {
				newBalls.addAll(list); // 그대로 넣기
				continue;
			}

			int nm = 0; // 합쳐진 질량
			int ns = 0; // 합쳐진 속력
			boolean even = true; // 짝수 체크
			boolean odd = true; // 홀수 체크
			for (FireBall ball : list) {
				nm += ball.m;
				ns += ball.s;

				if (ball.d % 2 == 0) {
					odd = false; // 짝수가 존재 → 홀수 전부 아님
				} else {
					even = false; // 홀수가 존재 → 짝수 전부 아님
				}
			}
			// 기존 질량 제거
			result -= nm;

			// 파이어볼이 4개로 나눠짐
			// 질량은 (합쳐진 파이어볼 질량 합) / 5
			nm /= 5;
			// 질량이 0인 파이어볼은 소멸되어 없어짐
			if (nm == 0)
				continue;
			// 속력은 (합쳐진 파이어볼 속력의 합) / (합쳐진 파이어볼의 개수)
			ns /= list.size();
			// 합쳐지는 파이어볼 방향이 모두 홀수이거나, 짝수이면 방향은 0, 2, 4, 6이 되고, 아니면 1, 3, 5, 7이 됨
			int[] nd = (even || odd) ? new int[] { 0, 2, 4, 6 } : new int[] { 1, 3, 5, 7 };
			for (int d : nd) {
				newBalls.add(new FireBall(list.get(0).r, list.get(0).c, nm, ns, d));
			}

			// 새 질량 추가
			result += nm * 4;
		}

		// 새로운 파이어볼로 교체
		fireBalls = newBalls;
	}

	static class FireBall {
		int r, c; // 좌표
		int m; // 질량
		int s; // 속력
		int d; // 방향

		FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
}
