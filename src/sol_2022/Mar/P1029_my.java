package sol_2022.Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1029_my {
    private static int N;
    private static int[][] price;
    private static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        price = new int[N][N];
        dp = new int[N][10][1 << N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                price[i][j] = line.charAt(j) - '0';
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<10; j++)
                Arrays.fill(dp[i][j], -1);
        }

        System.out.println(memo(0, 0, 1, 1));
    }

    private static int memo(int n, int pay, int cnt, int flag) {
        if (flag == (1 << N) - 1)
            return N;

        if(dp[n][pay][flag] != -1)
            return dp[n][pay][flag];

        int result = cnt;
        for (int i = 0; i < N; i++) {
            if ((flag & (1 << i)) > 0 || i == n || price[n][i] < pay)
                continue;

            result = Math.max(result, memo(i, price[n][i], cnt + 1,flag | (1 << i)));
        }

        return dp[n][pay][flag] = result;
    }
}
