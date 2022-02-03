package Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1937_my {
    private static int n;
    private static int[][] map;
    private static int[][] count;

    private final static int[] dy = {-1, 1, 0, 0};
    private final static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        count = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, dp(i, j));
            }
        }

        System.out.println(result);
    }

    private static int dp(int y, int x) {
        if (count[y][x] != 0)
            return count[y][x];

        int result = 1;

        for (int i = 0; i < 4; i++) {
            int temp = 1;

            int ny = y + dy[i];
            int nx = x + dx[i];

            if (!inArea(ny, nx) || map[y][x] >= map[ny][nx])
                continue;

            temp += dp(ny, nx);
            result = Math.max(result, temp);
        }

        return count[y][x] = result;
    }

    private static boolean inArea(int y, int x) {
        return y >= 0 && x >= 0 && y < n && x < n;
    }
}
