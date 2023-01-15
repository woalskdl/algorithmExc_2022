package sol_2022.Jan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1520_my {
    private static int M;
    private static int N;
    private static int[][] map;
    private static int[][] visited;

    private static int[] dy = {0, 0, -1, 1};
    private static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j] = -1;
            }
        }

        System.out.println(dp(0, 0));
    }

    private static int dp(int y, int x) {
        if(y == M - 1 && x == N - 1)
            return 1;

        if(visited[y][x] != -1)
            return visited[y][x];

        visited[y][x] = 0;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(!inArea(ny, nx) || map[ny][nx] >= map[y][x])
                continue;

            visited[y][x] += dp(ny, nx);
        }

        return visited[y][x];
    }

    private static boolean inArea(int y, int x) {
        return y >= 0 && x >= 0 && y < M && x < N;
    }
}
