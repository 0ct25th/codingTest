import java.io.*;
import java.util.*;

public class Main {

	static int N, L, result;
	static int[] spot;
	static boolean[] pipe;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 물이 새는 곳의 개수
		L = Integer.parseInt(st.nextToken()); // 테이프의 길이

		st = new StringTokenizer(br.readLine());
		pipe = new boolean[1001];
		for (int i = 0; i < N; i++)
			pipe[Integer.parseInt(st.nextToken())] = true;

		int index = 0;
		while (index <= 1000) {
			if (pipe[index]) {
				result++;
				index += L;
			} else
				index += 1;
		}

		System.out.println(result);
	}
}
