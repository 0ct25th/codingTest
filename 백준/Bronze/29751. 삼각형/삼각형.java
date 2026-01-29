import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        System.out.println(String.format("%.1f", W * H * 0.5));
    }
}