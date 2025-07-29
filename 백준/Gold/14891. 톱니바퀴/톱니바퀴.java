import java.io.*;
import java.util.*;

public class Main {

	static LinkedList<Integer>[] gears;
	static int K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		gears = new LinkedList[5];
		for (int i = 1; i <= 4; i++)
			gears[i] = new LinkedList<>();
		for (int r = 1; r <= 4; r++) {
			String str = br.readLine();
			for (int c = 0; c < 8; c++)
				gears[r].add(str.charAt(c) - '0');
		}

		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 톱니바퀴 번호
			int d = Integer.parseInt(st.nextToken()); // 방향, 1: 시계, -1: 반시계

			turn(n, d);
		}

		System.out.println(calc());
	}

	static int calc() {
		int answer = 0;
		// 1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
		if (gears[1].peek() == 1)
			answer += 1;

		// 2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
		if (gears[2].peek() == 1)
			answer += 2;

		// 3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
		if (gears[3].peek() == 1)
			answer += 4;

		// 4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
		if (gears[4].peek() == 1)
			answer += 8;

		return answer;
	}

	static void turn(int n, int d) {
		switch (n) {
		case 1:
			one(d);
			return;
		case 2:
			two(d);
			return;
		case 3:
			three(d);
			return;
		case 4:
			four(d);
			return;
		}
	}

	static void four(int d) {
		int one_two = gears[1].get(2);
		int two_two = gears[2].get(2);
		int two_six = gears[2].get(6);
		int three_two = gears[3].get(2);
		int three_six = gears[3].get(6);
		int four_six = gears[4].get(6);

		// 시계 방향
		if (d == 1) {
			// 4번 톱니바퀴 시계 방향 회전
			forward(4);

			boolean three = false;
			if (four_six != three_two) {
				// 3번 톱니바퀴 반시계 방향 회전
				three = true;
				reverse(3);
			}

			boolean two = false;
			if (three && three_six != two_two) {
				// 2번 톱니바퀴 시계 방향 회전
				two = true;
				forward(2);
			}

			if (two && two_six != one_two)
				// 1번 톱니바퀴 반시계 방향 회전
				reverse(1);
		}

		// 반시계 방향
		else {
			// 4번 톱니바퀴 반시계 방향 회전
			reverse(4);

			boolean three = false;
			if (four_six != three_two) {
				// 3번 톱니바퀴 시계 방향 회전
				three = true;
				forward(3);
			}

			boolean two = false;
			if (three && three_six != two_two) {
				// 2번 톱니바퀴 반시계 방향 회전
				two = true;
				reverse(2);
			}

			if (two && two_six != one_two)
				// 1번 톱니바퀴 시계 방향 회전
				forward(1);
		}
	}

	static void three(int d) {
		int one_two = gears[1].get(2);
		int two_two = gears[2].get(2);
		int two_six = gears[2].get(6);
		int three_two = gears[3].get(2);
		int three_six = gears[3].get(6);
		int four_six = gears[4].get(6);

		// 시계 방향
		if (d == 1) {
			// 3번 톱니바퀴 시계 방향 회전
			forward(3);

			if (three_two != four_six)
				// 4번 톱니바퀴 반시계 방향 회전
				reverse(4);

			boolean two = false;
			if (three_six != two_two) {
				two = true;
				// 2번 톱니 바퀴 반시계 방향 회전
				reverse(2);
			}

			if (two && two_six != one_two)
				// 1번 톱니바퀴 시계 방향 회전
				forward(1);
		}

		// 반시계 방향
		else {
			// 3번 톱니바퀴 반시계 방향 회전
			reverse(3);

			if (three_two != four_six)
				// 4번 톱니바퀴 시계 방향 회전
				forward(4);

			boolean two = false;
			if (three_six != two_two) {
				two = true;
				// 2번 톱니 바퀴 시계 방향 회전
				forward(2);
			}

			if (two && two_six != one_two)
				// 1번 톱니바퀴 반시계 방향 회전
				reverse(1);
		}
	}

	static void two(int d) {
		int one_two = gears[1].get(2);
		int two_two = gears[2].get(2);
		int two_six = gears[2].get(6);
		int three_two = gears[3].get(2);
		int three_six = gears[3].get(6);
		int four_six = gears[4].get(6);

		// 시계 방향
		if (d == 1) {
			// 2번 톱니바퀴 시계 방향 회전
			forward(2);

			if (two_six != one_two)
				// 1번 톱니바퀴 반시계 방향 회전
				reverse(1);

			boolean three = false;
			if (two_two != three_six) {
				three = true;
				// 3번 톱니바퀴 반시계 방향 회전
				reverse(3);
			}

			if (three && three_two != four_six)
				// 4번 톱니바퀴 시계 방향 회전
				forward(4);
		}

		// 반시계 방향
		else {
			// 2번 톱니바퀴 반시계 방향 회전
			reverse(2);

			if (two_six != one_two)
				// 1번 톱니바퀴 시계 방향 회전
				forward(1);

			boolean three = false;
			if (two_two != three_six) {
				three = true;
				// 3번 톱니바퀴 시계 방향 회전
				forward(3);
			}

			if (three && three_two != four_six)
				// 4번 톱니바퀴 반시계 방향 회전
				reverse(4);
		}
	}

	static void one(int d) {
		int one_two = gears[1].get(2);
		int two_two = gears[2].get(2);
		int two_six = gears[2].get(6);
		int three_two = gears[3].get(2);
		int three_six = gears[3].get(6);
		int four_six = gears[4].get(6);

		// 시계 방향
		if (d == 1) {
			// 1번 톱니바퀴 시계 방향 회전
			forward(1);

			boolean two = false;
			if (one_two != two_six) {
				// 2번 톱니바퀴 반시계 방향 회전
				two = true;
				reverse(2);
			}

			boolean three = false;
			if (two && two_two != three_six) {
				// 3번 톱니바퀴 시계 방향 회전
				three = true;
				forward(3);
			}

			if (three && three_two != four_six)
				// 4번 톱니바퀴 반시계 방향 회전
				reverse(4);
		}

		// 반시계 방향
		else {
			// 1번 톱니바퀴 반시계 방향 회전
			reverse(1);

			boolean two = false;
			if (one_two != two_six) {
				// 2번 톱니바퀴 시계 방향 회전
				two = true;
				forward(2);
			}

			boolean three = false;
			if (two && two_two != three_six) {
				// 3번 톱니바퀴 반시계 방향 회전
				three = true;
				reverse(3);
			}

			if (three && three_two != four_six)
				// 4번 톱니바퀴 시계 방향 회전
				forward(4);
		}
	}

	static void reverse(int n) {
		gears[n].add(gears[n].poll());
	}

	static void forward(int n) {
		gears[n].addFirst(gears[n].pollLast());
	}
}
