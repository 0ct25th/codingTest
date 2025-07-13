import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		for(int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		for(int i : arr)
			sb.append(i).append("\n");
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
}
