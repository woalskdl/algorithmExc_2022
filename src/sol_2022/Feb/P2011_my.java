package sol_2022.Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2011_my {

    private static final int MAX = 26;
    private static final int REM = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String codeLine = br.readLine();
        if(codeLine == null || codeLine.length() == 0) {
            System.out.println(0);
            return;
        }

        int length = codeLine.length();

        int[] code = new int[length];
        int[] dp = new int[length];

        for (int i = 0; i < length; i++) {
            code[i] = codeLine.charAt(i) - '0';

            if(code[i] < 0 || code[i] > 9) {
                System.out.println(0);
                return;
            }
        }

        if (code[0] == 0) {
            System.out.println(0);
            return;
        }

        dp[0] = 1;

        StringBuilder sb;

        for (int i = 1; i < length; i++) {
            sb = new StringBuilder();

            sb.append(code[i - 1]);
            sb.append(code[i]);

            int num = Integer.parseInt(String.valueOf(sb));

            if (num <= 0)
                break;

            if (num <= MAX) {
                if(num % 10 == 0) {
                    if (i == 1) {
                        dp[i] = dp[i - 1];
                        continue;
                    }

                    dp[i] = dp[i - 1] == dp[i - 2] ? dp[i - 1] : dp[i - 2];
                } else if (num < 10) {
                    dp[i] = dp[i - 1];
                } else {
                    if(i == 1) {
                        dp[i] = (dp[i - 1] + 1) % REM;
                        continue;
                    }

                    dp[i] = (dp[i - 1] % REM + dp[i - 2] % REM) % REM;
                }
            } else {
                if (num % 10 == 0)
                    break;

                dp[i] = dp[i - 1];
            }
        }

        Arrays.stream(dp).forEach(System.out::print);
        System.out.println();
        System.out.println(dp[length - 1]);
    }
}
