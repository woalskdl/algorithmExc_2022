package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1495_my {
    private static int N;
    private static int S;
    private static int M;
    private static int[] V;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        V = new int[N];
        List<Integer> dp = new ArrayList<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++)
            V[i] = Integer.parseInt(st.nextToken());

        // V[i] 정렬한 다음에 싹 더해보고 크면 작은거부터 빼다가 M보다 작거나 같아지면 출력해보자
        // M보다 작거나 같아지는 케이스가 여러 경우가 있을 경우, M보다 작아지려면 여러개의 수가 필요할 경우
        Arrays.sort(V);
        int result = S + Arrays.stream(V).sum();

        for(int i=0; i<N; i++) {
            if(result <= M)
                break;

            result -= V[i] * 2;
        }

        System.out.println(result);

        /*
        dp.add(S);
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            list.clear();

            for (int num : dp) {
                if (num - V[i] >= 0)
                    list.add(num - V[i]);

                if (num + V[i] <= M)
                    list.add(num + V[i]);
            }

            dp.clear();
            dp.addAll(list);

//            int length = dp[i - 1].length * 2;
//            dp[i] = new int[length];
//
//            for (int j = 0; j < length; j++) {
//                if (dp[i - 1][j] == -1)
//                    continue;
//
//                dp[i][j * 2] = dp[i - 1][j] - V[i] >= 0 ? dp[i - 1][j] - V[i] : -1;
//                dp[i][j * 2 + 1] = dp[i - 1][j] + V[i] <= M ? dp[i - 1][j] + V[i] : -1;
//            }
        }
         */

//        System.out.println(memo(0, S));
//        System.out.println(Arrays.stream(dp[N - 1]).max().getAsInt());

        /*
        if (dp.isEmpty())
            System.out.println(-1);
        else
            System.out.println(Collections.max(dp));
         */

    }

    private static int memo(int i, int P) {
        if (P < 0 || P > M)
            return -1;

        if (i == N)
            return P;

        return Math.max(memo(i + 1, P - V[i]), memo(i + 1, P + V[i]));
    }
}
