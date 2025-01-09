import java.io.*;

public class Main {
	
	static int N;
	static int[] numbers;
	static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine().strip());
		
		numbers = new int[N];
		isSelected = new boolean[N];
		
		permutation(0);
	}
	
	static void permutation(int depth) {
		// 기저조건: 모든 수를 다 탐색한 경우
		if (depth == N) {
			for(int number: numbers)
				System.out.print(number + " ");
			System.out.println();
			
			return;
		}
		
		// 모든 수를 탐색
		for(int i = 0; i < N; i++) {
			// 해당 숫자가 이미 선택된 경우
			if (isSelected[i])
				continue; // 넘김
			
			numbers[depth] = i + 1;	// 해당 숫자 순열에 넣기
			isSelected[i] = true;	// 선택
			permutation(depth + 1);	// 재귀 함수 호출
			isSelected[i] = false;	// 선택 원복
		}
	}
}
