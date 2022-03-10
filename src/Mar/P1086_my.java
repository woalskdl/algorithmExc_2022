package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1086_my {
    private static int N, K;
    private static String[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        num = new String[N];

        for(int i=0; i<N; i++)
            num[i] = br.readLine();

        K = Integer.parseInt(br.readLine());


    }
}
