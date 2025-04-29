import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		Stack<Integer> stk = new Stack<Integer>();
		for (int i = 0; i < N; i++) {
			while (!stk.isEmpty() && A[stk.peek()] < A[i])
				A[stk.pop()] = A[i];

			stk.push(i); // 인덱스를 스택에 추가
		}

		while (!stk.isEmpty())
			A[stk.pop()] = -1;

		for (int a : A)
			sb.append(a).append(" ");

		System.out.println(sb);
	}
}
