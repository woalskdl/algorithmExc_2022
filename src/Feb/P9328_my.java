package Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P9328_my {
    private static final String KEY = "abcdefghijklmnopqrstuvwxyz";
    private static final String DOOR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static int n;

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            char[][] map = new char[h + 2][w + 2];
            boolean[][] visited = new boolean[h + 2][w + 2];
            Arrays.fill(map, '.');

            for (int j = 1; j <= h; j++) {
                String line = br.readLine();
                for (int k = 1; k <= w; k++)
                    map[j][k] = line.charAt(k - 1);
            }

            int keys = 0;

            String owns = br.readLine();
            if (!"0".equals(owns)) {
                for (int j = 0; j < owns.length(); j++)
                    keys |= (1 << KEY.indexOf(owns.charAt(j)));
            }


        }
    }

    private static int bfs(char[][] map, int owns) {
        Queue<Node> queue = new LinkedList<>();
        int h = map.length;
        int w = map[0].length;

        int cnt = 0;

        boolean[][] visited = new boolean[h][w];

        queue.add(new Node(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];

                if (visitable(map, ny, nx) && !visited[ny][nx]) {
                    if('$' == map[ny][nx])
                        cnt += 1;

                    char pos = map[ny][nx];

                    if(KEY.contains(Character.toString(pos))) {
                        owns |= (1 << KEY.indexOf(pos));
                        map[ny][nx] = '.';
                    }

                    if(DOOR.contains(Character.toString(pos))) {
                        if((owns & (1 << DOOR.indexOf(pos))) > 0) {

                        }
                    }

                }
            }
        }

        return cnt;
    }

    private static boolean visitable(char[][] map, int y, int x) {
        return y >= 0 && y < map.length && x >= 0 && x < map[0].length && map[y][x] != '*';
    }

    private static class Node {
        private int y;
        private int x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
