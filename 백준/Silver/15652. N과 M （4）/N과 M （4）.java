import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N;			// 1부터 N까지
	static int M;			// 길이가 M인 수열 저장
	static int[] numbers;	// 완성된 수열 저장
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String string = br.readLine();
		String[] strArr = string.split(" ");
		
		N = Integer.parseInt(strArr[0]);
		M = Integer.parseInt(strArr[1]);
		
		numbers = new int[M];
		
		comb(0, 1);
	}
	
	private static void comb(int cnt, int start) {
		// 기저 조건
		if (cnt == M) {
			for (int i: numbers) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		}
		
		// 모든 수 탐색하기
		for (int i = start; i <= N; i++) {
			numbers[cnt] = i;
			comb(cnt + 1, i);
		}
	}

}
