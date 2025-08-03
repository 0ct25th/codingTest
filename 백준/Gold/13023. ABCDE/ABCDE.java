import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] adj;
    static boolean[] isVisited;
    static boolean result = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        for (int i = 0; i < N; i++) {
            isVisited = new boolean[N];
            isVisited[i] = true;
            dfs(1, i);
            if (result) break;
        }
        System.out.println(result ? 1 : 0);
    }

    static void dfs(int depth, int cur) {
        if (result) return;

        if (depth == 5) {
            result = true;
            return;
        }

        for (int nxt : adj[cur]) {
            if (isVisited[nxt]) continue;
            isVisited[nxt] = true;
            dfs(depth + 1, nxt);
            isVisited[nxt] = false;
        }
    }
}
