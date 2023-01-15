package sol_2022.Feb;
// 내꺼랑 다를바가 없는데
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10942_answer {
    private static int N;
    private static int M;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            System.out.println(dp(S,E));
        }
    }

    private static int dp(int S, int E) {
        while (S <= E) {
            if(arr[S] != arr[E])
                return 0;

            S += 1;
            E -= 1;
        }

        return 1;
    }
}
