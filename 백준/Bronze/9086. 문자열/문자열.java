import java.io.*;
import java.util.*;

public class Main {
	
	static int T;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			String str = br.readLine();
			char[] chr = str.toCharArray();
			
			sb.append(chr[0]).append(chr[chr.length - 1]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
}
