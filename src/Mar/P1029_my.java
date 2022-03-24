package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1029_my {
    private static int N;
    private static int[][] price;
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        price = new int[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++)
                price[i][j] = line.charAt(j) - '0';
        }

        System.out.println(dp(0, 0, 1, 1));
    }

    private static int dp(int n, int pay, int cnt, int flag) {
        if (flag == (1 << N) - 1)
            return N;

        for (int i = 0; i < N && i != n; i++) {
            if ((flag & (1 << i)) > 0 || price[n][i] < pay)
                continue;

            cnt = dp(i, price[n][i], cnt + 1, flag | (1 << i));
        }

        return cnt;
    }
}
