package Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2098_my {
    private static int N;
    private static int[][] W;
    private static int[] cost;

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
        for (int i = 0; i < N; i++) {
            cost = new int[N];
            int total = dp(0, i, i);

            min = Math.min(min, total);
        }

        System.out.println(min);

    }

    private static int dp(int c, int idx, int start) {
        int min = Integer.MAX_VALUE;
        int minIdx = -1;

        for (int i = 0; i < N; i++) {
            if (cost[i] == 0 && W[idx][i] != 0 && min > W[idx][i]) {
                min = W[idx][i];
                minIdx = i;
            }
        }

        if (minIdx == -1) {
            c += W[idx][start];
            return c;
        }

        c += min;
        cost[idx] = c;

        return dp(c, minIdx, start);
    }
}
