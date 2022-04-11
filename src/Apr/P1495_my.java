package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1495_my {
    private static int N;
    private static int S;
    private static int M;
    private static int[] V;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        V = new int[N];
        dp = new int[N + 1][M + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++)
            V[i] = Integer.parseInt(st.nextToken());

        for(int i=0; i<=N; i++)
            Arrays.fill(dp[i], -1);

        dp[0][S] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                if (dp[i][j] == -1)
                    continue;

                int pls = j + V[i];
                int min = j - V[i];

                if (pls <= M)
                    dp[i + 1][pls] = 1;
                if (min >= 0)
                    dp[i + 1][min] = 1;
            }
        }

        for (int i = M; i >= 0; i--){
            if(dp[N][i] != -1) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }
}
