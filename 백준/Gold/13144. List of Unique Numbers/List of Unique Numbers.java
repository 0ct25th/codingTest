import java.io.*;
import java.util.*;

public class Main {

	static int N, A[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		int start = 0;
        int end = 0;
        long result = 0;
        boolean[] isVisited = new boolean[100001];
        
        while (end < N) {
            if (isVisited[A[end]]) {
                isVisited[A[start]] = false;
                start++;
            } else {
                isVisited[A[end]] = true;
                end++;
                result += (end - start);
            }
        }
		System.out.println(result);
	}
}
