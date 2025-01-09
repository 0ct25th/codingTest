import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] numbers;
	static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine().strip());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[M];
		isSelected = new boolean[N];
		permutation(0);
		
		br.close();
	}
	
	static void permutation(int depth) {
		// 기저조건: 모든 숫자를 탐방한 경우
		if(depth == M) {
			// 완성된 순열 출력
			for(int number: numbers) 
				System.out.print(number + " ");
			System.out.println();
			
			return;
		}
		
		for(int i = 0; i < N; i++) {
			// 이미 선택한 숫자인 경우
			if(isSelected[i])
				continue; // 넘김
			
			numbers[depth] = i + 1;		// 완성 순열에 넣기
			isSelected[i] = true; 	// 선택
			permutation(depth + 1);		// 재귀
			isSelected[i] = false; 	// 원복
		}
	}
}
