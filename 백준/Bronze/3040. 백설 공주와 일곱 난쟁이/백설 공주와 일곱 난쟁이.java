import java.io.*;

public class Main {
	
	static int[] input;
	static boolean[] isSelected;
	static StringBuilder sb = new StringBuilder();		

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		input = new int[9];
		for(int i = 0; i < 9; i++) 
			input[i] = Integer.parseInt(br.readLine().strip());
		
		/////////////////////////// end of Input
		
		isSelected = new boolean[9];
		subSet(0, 0, 0); // 난쟁이 선택하는 집합메소드 실행
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void subSet(int depth, int count, int sum) {
		// 기저조건: 9난쟁이 모두 고려한 경우
		if(depth == 9) {
			// 7 난쟁이를 선택했고 합계가 100인 경우
			if(count == 7 && sum == 100) {
				for(int i = 0; i < 9; i++) 
					if(isSelected[i])
						sb.append(input[i]).append("\n");
				return;
			}
			
			return;
		}
		
		// depth번째 난쟁이를 선택한 경우
		isSelected[depth] = true;
		subSet(depth + 1, count + 1, sum + input[depth]);
		
		// depth번째 난쟁이를 선택하지 않은 경우
		isSelected[depth] = false;
		subSet(depth + 1, count, sum);
	}
	
}
