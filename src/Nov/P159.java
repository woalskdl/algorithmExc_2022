package Nov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P159 {
    /*
     * H X W 크기의 게임판이 있고 게임판은 검은 칸과 흰 칸으로 구성된 격자 모양을 하고 있다.
     * 모든 흰 칸을 세 칸짜리 L자 모양의 블록으로 덮고 싶다. 블록은 자유롭게 회전해서 놓을 수 있지만 겹치거나, 검은 칸을 덮거나, 게임판 밖으로 나가서는 안된다.
     * 게임판이 주어질 때 이를 덮는 방법의 수를 계산하는 프로그램을 작성하라.
     *
     * 첫 줄에는 테스트 케이스의 수 C (C <= 30)
     * 각 테스트 케이스의 첫 줄에는 두 개의 정수 H, W (1 <= H, W <= 20)가 주어진다.
     * 다음 H 줄에는 각 W글자로 게임판의 모양이 주어진다. #는 검은칸, .는 흰칸
     * 입력에 주어지는 게임판에 있는 흰 칸의 수는 50을 넘지 않는다.
     *
     * 예제 입력
3
3 7
#.....#
#.....#
##...##
3 7
#.....#
#.....#
##..###
8 9
#########
#.......#
#.......#
#.......#
#.......#
#.......#
#.......#
#########
     * 예제 출력
     * 0
     * 2
     * 1514
     */

    private static int C;
    private static int result;

    private final static int[] dx = {0, 0, -1, -1};
    private final static int[] dy = {1, -1, 0, 0};
    private final static int[] dxx = {1, 1, 0, 0};
    private final static int[] dyy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        C = Integer.parseInt(br.readLine());

        int H;
        int W;
        char[][] board;
        boolean[][] covered;
        int emptyCnt;

        StringBuffer sf = new StringBuffer();

        for (int i = 0; i < C; i++) {
            result = 0;
            emptyCnt = 0;

            String line = br.readLine();
            H = Integer.parseInt(line.split(" ")[0]);
            W = Integer.parseInt(line.split(" ")[1]);

            board = new char[H][W];
            covered = new boolean[H][W];

            for (int j = 0; j < H; j++) {
                String l = br.readLine();

                for (int h = 0; h < W; h++) {
                    char sign = l.charAt(h);
                    board[j][h] = sign;
                    if (sign == '.')
                        emptyCnt += 1;
                }
            }

            // 비어있는 칸이 3으로 나누어 떨어지지 않는다면 전부 채울 수 없으므로 0 출력 후 종료
            if (emptyCnt % 3 != 0) {
                sf.append(0 + "\n");
                continue;
            }

//            for(int j=0; j<H; j++) {
//                for(int h=0; h<W; h++) {
//                    if(board[j][h] == '.')
//                        cover(board, covered, H, W, emptyCnt, j, h);
//                }
//            }

            cover(board, covered, H, W, emptyCnt, 0, 0);
            sf.append(result).append("\n");
        }

        System.out.println(sf);
    }

    private static void cover(char[][] board, boolean[][] covered, int H, int W, int emptyCnt, int y, int x) {
        // 비어있는 칸이 0이라면 결과 + 1 후 종료
        if (emptyCnt == 0) {
            result += 1;
            return;
        }

        // 검사 구역을 벗어났다면 종료
        if (!inArea(y, x, H, W))
            return;

        // 4가지 케이스에 대해 검사
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            int nyy = y + dyy[i];
            int nxx = x + dxx[i];

            // 해당 모양으로 덮을 수 있다면 덮고 매개변수 변경 후 재귀함수 호출
            if (coverable(board, covered, y, x, H, W) &&
                    coverable(board, covered, ny, nx, H, W) &&
                    coverable(board, covered, nyy, nxx, H, W))
            {
                covered[y][x] = true;
                covered[ny][nx] = true;
                covered[nyy][nxx] = true;
                emptyCnt -= 3;

            }

            cover(board, covered, H, W, emptyCnt, ny, nx);
            cover(board, covered, H, W, emptyCnt, nyy, nxx);
        }

    }

    private static boolean inArea(int y, int x, int H, int W) {
        return y >= 0 && x >= 0 && y < H && x < W;
    }

    private static boolean coverable(char[][] board, boolean[][] covered, int y, int x, int H, int W) {
        return inArea(y, x, H, W) && !covered[y][x] && board[y][x] == '.';
    }
}
