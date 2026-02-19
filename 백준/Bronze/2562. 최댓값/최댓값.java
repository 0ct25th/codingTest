import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int max = Integer.parseInt(br.readLine());
		int maxIdx = 1;
		for(int i = 2; i < 10; i++) {
			int cur = Integer.parseInt(br.readLine());
			
			if(max < cur) {
				max = cur;
				maxIdx = i;
			}
		}
		
		System.out.printf("%d\n%d", max, maxIdx);
		
		br.close();
	}
}
