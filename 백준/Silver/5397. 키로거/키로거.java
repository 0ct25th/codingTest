import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().strip());
		for(int t = 0; t < T; t++) {
			String L = br.readLine().strip();
			List<Character> str = new LinkedList<>();
			
			int index = 0;
			for(int i = 0; i < L.length(); i++) {
				char ch = L.charAt(i);
				
				switch(ch) {
					case '<':
						index = Math.max(0, index - 1);
						break;
					
					case '>':
						index = Math.min(str.size(), index + 1);
						break;
						
					case '-':
						if(index != 0) {
							index--;
							str.remove(index);
						}
						break;
					
					default:
						str.add(index, ch);
						index++;
						
				}
			}
			
			for(char ch: str)
				sb.append(ch);
			
			sb.append("\n");
		} // end of TestCase
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
}
