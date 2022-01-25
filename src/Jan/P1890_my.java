package Jan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1890_my {
    private static int N;
    private static int[][] map;
    private static int[][] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        count = new int[N][N];

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dp(0, 0));
    }

    private static int dp(int y, int x) {
        if (y == N - 1 && x == N - 1)
            return 1;

        if (count[y][x] != 0)
            return count[y][x];

        int jump = map[y][x];
        int result = 0;

        if (inArea(y + jump, x))
            result += dp(y + jump, x);

        if (inArea(y, x + jump))
            result += dp(y, x + jump);

        return count[y][x] = result;
    }

    private static boolean inArea(int y, int x) {
        return (y == N - 1 && x == N - 1) || (y < N && x < N && map[y][x] != 0);
    }
}
