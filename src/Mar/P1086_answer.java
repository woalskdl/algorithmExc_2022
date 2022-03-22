package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1086_answer {
    private static int N, K;
    private static char[][] num;

    private static int cases;
    private static int total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        num = new char[N][];

        for (int i = 0; i < N; i++)
            num[i] = br.readLine().toCharArray();

        K = Integer.parseInt(br.readLine());

        dp(0, 0);

        String result;

        if(cases == 0)
            result = "0/1";
        else if (cases == total)
            result = "1/1";
        else
            result = cases + "/" + total;

        System.out.println(result);

    }

    private static void dp(int flag, int r) {
        if(((1 << N) - 1) - flag == 0) {
            if(r == 0)
                cases += 1;

            total += 1;
            return;
        }

        String rt = Integer.toString(r);

        for(int i=0, length = num.length; i<length; i++) {
            if(((1 << i) & flag) == 0) {
                for(int j=0, size = num[i].length; j<size; j++) {
                    String temp = rt + num[i][j];
                    int n = Integer.parseInt(temp);

                    System.out.println("n >> " + n);
                    System.out.println("n % K >> " + (n % K));

                    rt = Integer.toString(n % K);

                    System.out.println("rt >> " + rt);
                }

                dp(flag | (1 << i), Integer.parseInt(rt));
            }
        }
    }
}
