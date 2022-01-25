package Jan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1005_my {
    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int i=0; i<T; i++){
            st = new StringTokenizer(br.readLine(), " ");

            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] time = new int[N + 1];
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=N; j++)
                time[j] = Integer.parseInt(st.nextToken());

            int[][] build = new int[N + 1][N + 1];
            for(int j=0; j<K; j++){
                st = new StringTokenizer(br.readLine(), " ");

                int fst = Integer.parseInt(st.nextToken());
                int scd = Integer.parseInt(st.nextToken());

                build[fst][scd] = time[fst];
            }

            int W = Integer.parseInt(br.readLine());

            System.out.println(dp(W, build, time));
        }
    }

    private static int dp(int target, int[][] build, int[] time){
        if(build[0][target] != 0)
            return build[0][target];

        int result = -1;
        int N = build.length;

        for(int i=1; i<N; i++) {
            if(build[i][target] == 0)
                continue;

            int temp = dp(i, build, time);
            if(temp > result)
                result = temp;
        }

        if(result == -1)
            result = time[target];
        else
            result += time[target];

        return build[0][target] = result;
    }
}
