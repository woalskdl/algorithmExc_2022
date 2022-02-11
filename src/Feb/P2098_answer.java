package Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2098_answer {

    private static int N;
    private static int[][] W;
    private static int[][] dp;
    private static final int INF = 16000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        W = new int[N][N];
        dp = new int[N][(1 << N) - 1];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++)
                W[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++)
            Arrays.fill(dp[i], INF);

        System.out.println(dfs(0, 1));
    }

    private static int dfs(int start, int visit) {
        if(visit == (1 << N) - 1) {
            if(W[start][0] == 0)
                return INF;

            return W[start][0];
        }

        if(dp[start][visit] != INF)
            return dp[start][visit];

        for(int i=0; i<N; i++){
            if(W[start][i] != 0 && (visit & (1 << i)) == 0) {
                dp[start][visit] = Math.min(dp[start][visit], dfs(i, visit | (1 << i)) + W[start][i]);
            }
        }

        return dp[start][visit];
    }
}
