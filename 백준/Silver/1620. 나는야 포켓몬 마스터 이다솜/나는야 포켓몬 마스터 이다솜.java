import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static Map<String, Integer> nameMap;
	static Map<String, String> numMap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nameMap = new HashMap<>();
		numMap = new HashMap<>();
		
		for(int i = 1; i <= N; i++) {
			String name = br.readLine();
			
			nameMap.put(name, i);
			numMap.put(String.valueOf(i), name);
		}
		
		for(int i = 0; i < M; i++) {
			String poketMon = br.readLine();
			
			// 입력이 문자열이 들어온 경우
			if (poketMon.charAt(0) - 'A' >= 0 && poketMon.charAt(0) - 'Z' <= 26) 
				System.out.println(nameMap.get(poketMon));
			// 입력이 숫자가 들어온 경우
			 else 
				System.out.println(numMap.get(poketMon));
			
		}
	}

}
