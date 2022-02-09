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

        int length = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                if (arr[i][j] == 1) {
                    if(visited[i][j] == 0)
                        length = Math.max(length, dp(i, j, 1, 1));
                    else
                        length = Math.max(length, visited[i][j]);
                }
            }
        }

        System.out.println(length * length);

    }

    private static int dp(int y, int x, int lengthY, int lengthX) {
        if (visited[y][x] != 0)
            return visited[y][x];

        if (y >= n - 1 || x >= m - 1)
            return Math.min(lengthY, lengthX);

        if (arr[y + 1][x] == 1)
            lengthY = dp(y + 1, x, lengthY + 1, lengthX);

        if (arr[y][x + 1] == 1)
            lengthX = dp(y, x + 1, lengthY, lengthX + 1);

        return visited[y][x] = Math.min(lengthY, lengthX);
    }
}
