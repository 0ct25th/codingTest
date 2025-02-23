import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String message = br.readLine();
		int N = message.length();
		
		int R = 0, C = 0;
		
		for(int i = 1; i < N; i++) {
			if(N % i == 0) {
				int q = N / i;
				
				if (i > q)
					break;
				
				R = i;
			}
		}
		
		C = N / R;
		char[][] arr = new char[R][C];
		int cnt = 0;
		for(int c = 0; c < C; c++) {
			for(int r = 0; r < R; r++)
				arr[r][c] = message.charAt(cnt++);
		}
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++)
				System.out.print(arr[r][c]);
		}
		
		System.out.println();
	}
}
