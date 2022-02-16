package Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1094_my {
    private static final int INIT = 64;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int result = 0;

        for(int i=6; i>=0; i--) {
            int num = (int) Math.pow(2, i);

            if(num <= n) {
                n -= num;
                result += 1;

                if(n == 0)
                    break;
            }
        }

        System.out.println(result);
    }
}
