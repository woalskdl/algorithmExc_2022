package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1086_answer {

    private static int N, K;
    private static char[][] num;

    private static long[] fibo;
    private static long[][] dp;
    private static int[][] dpMod;

    private static long p;
    private static long q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        num = new char[N][];

        for (int i = 0; i < N; i++)
            num[i] = br.readLine().toCharArray();

        K = Integer.parseInt(br.readLine());

        fibo = new long[N + 1];
        fibo[1] = 1L;
        for (int i = 2; i <= N; i++)
            fibo[i] = (long) i * fibo[i - 1];

        dp = new long[K][1 << N];
        dpMod = new int[K][N];

        for (int i = 0; i < K; i++) {
            Arrays.fill(dp[i], -1);
            Arrays.fill(dpMod[i], -1);
        }

        p = memo(0, 0);
        q = fibo[N];

        if (p == 0) {
            q = 1;
        } else {
            long gcd = GCD(p, q);
            p /= gcd;
            q /= gcd;
        }

        System.out.println(p + "/" + q);

    }

    private static long memo(int mod, int flag) {
        if (dp[mod][flag] != -1)
            return dp[mod][flag];

        if (flag == (1 << N) - 1)
            return dp[mod][flag] = mod == 0 ? 1L : 0;

        long sum = 0;
        for (int i = 0; i < N; i++) {
            if ((flag & (1 << i)) > 0)
                continue;

            sum += memo(getMod(mod, i), flag | (1 << i));
        }

        return dp[mod][flag] = sum;
    }

    private static int getMod(int mod, int n) {
        if (dpMod[mod][n] != -1)
            return dpMod[mod][n];

        int cur = mod;
        for (int i = 0, length = num[n].length; i < length; i++) {
            cur *= 10;
            cur = (cur + num[n][i] - '0') % K;
        }

        return dpMod[mod][n] = cur;
    }

    private static long GCD(long p, long q) {

        while (p % q != 0) {
            long tmp = p % q;
            p = q;
            q = tmp;
        }

        return q;
    }
}
