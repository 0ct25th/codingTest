import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int K = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i1 = Integer.parseInt(st.nextToken());
            int j1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            int sum = 0;
            for (int r = i1 - 1; r < x1; r++) {
                for (int c = j1 - 1; c < y1; c++) {
                    sum += arr[r][c];
                }
            }
            sb.append(sum).append('\n');
        }
        System.out.print(sb.toString());
    }
}
