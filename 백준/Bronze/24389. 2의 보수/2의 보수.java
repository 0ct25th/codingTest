import java.io.*;

public class Main {

	static int N, M, xor;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = ~N + 1; // 비트 반전 후 1 더하기
		xor = N ^ M; // XOR 연산

		int answer = 0;
		for (char ch : Integer.toBinaryString(xor).toCharArray())
			if (ch == '1')
				answer++;

		System.out.println(answer);
	}
}
