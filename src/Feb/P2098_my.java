package Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2098_my {
    private static int N;
    private static int[][] W;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        W = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++)
                W[i][j] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        StringBuilder sb = new StringBuilder();
        visited = new boolean[N];
        min = dp(0, 0, 0);

        sb.append(min + "\n");
        System.out.println(sb);

    }

    private static int dp(int c, int idx, int start) {
        visited[idx] = true;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            if (W[idx][i] != 0 && !visited[i]) {
                min = Math.min(min, dp(c + W[idx][i], i, start));
                visited[i] = false;
            }
        }

        if(min == Integer.MAX_VALUE)
            min = c + W[idx][start];

        return min;
    }
}
