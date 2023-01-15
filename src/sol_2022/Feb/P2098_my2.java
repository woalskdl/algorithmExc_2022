package sol_2022.Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2098_my2 {
    private static int N;
    private static int[][] W;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        W = new int[N][N];
        dp = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++)
                W[i][j] = Integer.parseInt(st.nextToken());
        }

        // dp : j 만큼 움직여서 i로 완료하는 최소 비용 / dp[i][0] : 마지막 출발지점
        for(int i=0; i<N; i++){
            int min = Integer.MAX_VALUE;
            int minIdx = -1;

            for(int j=0; j<N; i++) {
                if(min > W[j][i] && W[j][i] != 0) {
                    min = W[j][i];
                    minIdx = j;
                }
            }

            dp[i][1] = min;
            dp[i][0] = minIdx;
        }

        for(int i=2; i<N; i++){
            for(int j=0; j<N; j++){
                int min = Integer.MAX_VALUE;
                int minIdx = -1;

                for(int k=0; k<N; k++){
                    int start = dp[k][0];

                    if(start != j) {
                        if(min > dp[k][i - 1] + W[start][j]) {
                            min = dp[k][i - 1] + W[start][j];
                            minIdx = k;
                        }
                    }
                }

                dp[j][i] = dp[j][j - 1] + min;
                dp[j][0] = minIdx;
            }
        }
    }
}
