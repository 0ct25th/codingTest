import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static Map<String, String> hash;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken()); // 저장된 주소의 수
		M = Integer.parseInt(st.nextToken()); // 찾으려는 주소의 수
		
		hash = new HashMap<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String site = st.nextToken();
			String pwd = st.nextToken();
			
			hash.put(site, pwd);
		}
		
		for(int i = 0; i < M; i++) {
			String site = br.readLine();
			
			sb.append(hash.get(site)).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
}
