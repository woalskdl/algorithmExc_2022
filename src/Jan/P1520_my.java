package Jan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
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

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        // bfs 를 이용하여 갈라질때마다 +1, visited 체크를 안하면 탐색 종료를 어떻게 체크할까?
        bfs();

        System.out.println(visited[M - 1][N - 1]);
    }

    private static class Node{
        int y;
        int x;
        int route;

        public Node(int y, int x, int route) {
            this.y = y;
            this.x = x;
            this.route = route;
        }
    }

    private static void bfs(){
        Queue<Node> queue = new LinkedList<>();
        int route = 1;
        queue.add(new Node(0, 0, route));

        while (!queue.isEmpty()){
            Node node = queue.poll();
            int y = node.y;
            int x = node.x;
            int r = node.route;
            int cnt = 0;

            for(int i=0; i<4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];

                // route를 엎어치는 부분에서 작은수로 엎어쳐지는 경우
                if(inArea(ny, nx) && map[ny][nx] < map[y][x]){
                    if(visited[ny][nx] != r){
                        int nr = r;
                        if(cnt > 0){
                            route += 1;
                            nr = route;
                        }

                        queue.add(new Node(ny, nx, nr));
                        visited[ny][nx] = nr;

                        cnt += 1;
                    }
                }
            }
        }
    }

    private static boolean inArea(int y, int x){
        return y >= 0 && x >= 0 && y < M && x < N;
    }
}
