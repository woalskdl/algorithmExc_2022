package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

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
        dp = new int[N][(int) Math.pow(2, N)];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], -1);
        }

        dp[0][0] = S - V[0] >= 0 ? S - V[0] : -1;
        dp[0][1] = S + V[0] <= M ? S + V[0] : -1;

        for (int i = 1; i < N; i++) {
            for (int j = 0, length = dp[i].length; j < length; j++) {
                if (dp[i - 1][j] == -1)
                    continue;

                dp[i][j * 2] = dp[i - 1][j] - V[i] >= 0 ? dp[i - 1][j] - V[i] : -1;
                dp[i][j * 2 + 1] = dp[i - 1][j] + V[i] <= M ? dp[i - 1][j] + V[i] : -1;
            }
        }

//        System.out.println(memo(0, S));
        System.out.println(Arrays.stream(dp[N - 1]).max().getAsInt());

    }

    private static int memo(int i, int P) {
        if (P < 0 || P > M)
            return -1;

        if (i == N)
            return P;

        return Math.max(memo(i + 1, P - V[i]), memo(i + 1, P + V[i]));
    }
}
