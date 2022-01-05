package Jan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1520_my {
    private static int M;
    private static int N;
    private static int[][] map;
    private static boolean[][] visited;

    private static int[] dy = {0, 0, -1, 1};
    private static int[] dx = {1, -1, 0, 0};
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        dp(0, 0);

        System.out.println(result);
    }

    private static void dp(int y, int x) {
        visited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny == M - 1 && nx == N - 1 && map[ny][nx] < map[y][x]) {
                visited = new boolean[M][N];
                result += 1;
                return;
            }

            if (inArea(ny, nx) && map[ny][nx] < map[y][x]) {
                dp(ny, nx);
            }
        }
    }

    private static boolean inArea(int y, int x) {
        return y >= 0 && x >= 0 && y < M && x < N && !visited[y][x];
    }
}
