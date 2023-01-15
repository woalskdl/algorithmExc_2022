package sol_2022.Book1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P159_sol {

    // 주어진 칸을 덮을 수 있는 네가지 방법.

    // 가장 왼쪽 위에 있는 칸을 무조건 먼저 채운다고 가정하고, 해당 칸을 기준으로 (dy, dx)
    private static final int[][][] coverType = {
            {{0, 0}, {1, 0}, {0, 1}},
            {{0, 0}, {0, 1}, {1, 1}},
            {{0, 0}, {1, 0}, {1, 1}},
            {{0, 0}, {1, 0}, {1, -1}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int c = Integer.parseInt(br.readLine());

        int H;
        int W;
        int[][] board;
        int emptyCnt;

        StringBuffer sf = new StringBuffer();

        for (int i = 0; i < c; i++) {
            int result;
            emptyCnt = 0;

            String line = br.readLine();
            H = Integer.parseInt(line.split(" ")[0]);
            W = Integer.parseInt(line.split(" ")[1]);

            board = new int[H][W];

            for (int j = 0; j < H; j++) {
                String l = br.readLine();

                for (int h = 0; h < W; h++) {
                    char sign = l.charAt(h);
                    board[j][h] = sign == '.' ? 0 : 1;
                    if (sign == '.')
                        emptyCnt += 1;
                }
            }

            // 비어있는 칸이 3으로 나누어 떨어지지 않는다면 전부 채울 수 없으므로 0 출력 후 종료
            if (emptyCnt % 3 != 0) {
                sf.append(0 + "\n");
                continue;
            }

            result = cover(board);

            sf.append(result).append("\n");
        }

        System.out.println(sf);
    }

    // board의 (y,x)를 type 방법으로 덮거나, 덮었던 블록을 없앤다.
    // delta = 1이면 덮고, delta = -1이면 덮었던 블록을 없앤다.
    // 만약 게임판 밖으로 나가거나, 겹치거나, 검은 칸을 덮으면 false를 반환한다.
    private static boolean set(int[][] board, int y, int x, int type, int delta) {
        boolean ok = true;
        for (int i = 0; i < 3; i++) {
            int ny = y + coverType[type][i][0];
            int nx = x + coverType[type][i][1];

            if (ny < 0 || ny >= board.length || nx < 0 || nx >= board[0].length)
                ok = false;
            else if ((board[ny][nx] += delta) > 1)
                ok = false;
        }

        return ok;
    }

    private static int cover(int[][] board) {
        // 아직 채우지 못한 칸 중 가장 윗줄 왼쪽에 있는 칸을 찾는다.
        int y = -1, x = -1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    y = i;
                    x = j;
                    break;
                }
            }

            if (y != -1)
                break;
        }

        // 기저 사례 : 모든 칸을 채웠으면 1을 반환한다.
        if (y == -1)
            return 1;

        int result = 0;
        for (int type = 0; type < 4; type++) {
            // 만약 board[y][x]를 type 형태로 덮을 수 있으면 재귀호출한다.
            if (set(board, y, x, type, 1))
                result += cover(board);

            // 덮었던 블록을 치운다.
            set(board, y, x, type, -1);
        }

        return result;
    }
}
