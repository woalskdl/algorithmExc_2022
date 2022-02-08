package Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1915_my {
    private static int n;
    private static int m;
    private static int[][] arr;
    private static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++)
                arr[i][j] = line.charAt(j) - '0';
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1 && visited[i][j] != 0)
                    dp(i, j);
            }
        }

    }

    private static int dp(int y, int x) {
        if (visited[y][x] != 0)
            return visited[y][x];

        if (arr[y][x + 1] == 1)
            return dp(y, x + 1);

        if(arr[y + 1][x] == 1)
            return dp(y + 1, x);

        return 0;
    }
}
