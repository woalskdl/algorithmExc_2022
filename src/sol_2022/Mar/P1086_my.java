package sol_2022.Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1086_my {
    private static int N, K;
    private static double[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        num = new double[N];

        for (int i = 0; i < N; i++)
            num[i] = Double.parseDouble(br.readLine());

        K = Integer.parseInt(br.readLine());

        int cnt = 0;
        for (int i = 0; i < num.length; i++) {
            double n = num[i];

            if(n % K == 0)
                cnt += 1;
        }

        int c = fac(N - cnt) * fac(cnt);
        int total = fac(N);

        System.out.println(c + "/" + total);

    }

    private static int fac(int n) {
        if(n == 0)
            return 0;

        int result = 1;

        for (; n > 0; n--)
            result *= n;

        return result;
    }
}
