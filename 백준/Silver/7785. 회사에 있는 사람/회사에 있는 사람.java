import java.io.*;
import java.util.*;

public class Main {
	
	static public void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine().strip());
		Map<String, String> person = new HashMap<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().strip());
			String name = st.nextToken();
			String status = st.nextToken();
			
			person.put(name, status);
		}
		
		List<String> company = new LinkedList<>();
		for(String name: person.keySet()) {
			if (person.get(name).equals("enter"))
				company.add(name);
		}
		
		Collections.sort(company, Comparator.reverseOrder());
		for(String name: company)
			sb.append(name).append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}

}
