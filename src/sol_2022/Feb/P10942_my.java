package sol_2022.Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10942_my {
    private static int N;
    private static int M;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1][N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 1; i <= N; i++){
            arr[0][i] = Integer.parseInt(st.nextToken());
            arr[i][i] = 1;
        }

        for(int i = 1; i <= N - 1; i++){
            if(arr[0][i] == arr[0][i + 1])
                arr[i][i + 1] = 1;
        }

        for(int i = 2; i <= N - 1; i++){
            for(int j = 1; j <= N - i; j++){
                if(arr[0][j] == arr[0][j + i] && arr[j + 1][j + i - 1] == 1)
                    arr[j][j + i] = 1;
            }
        }

        M = Integer.parseInt(br.readLine());

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            System.out.println(arr[S][E]);
        }
    }
}
