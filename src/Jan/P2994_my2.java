package Jan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2994_my2 {
    private static int n;
    private static int k;
    private static int[][] coins;

    private static final int S = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new int[S + 1][S + 1];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            coins[Integer.parseInt(st.nextToken())][1] = 1;
        }

    }
}
