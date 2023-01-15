package sol_2022.Book1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P155 {

    /*
     * 두 명씩 짝을 지어 행동하게 하려고 합니다. 그런데 서로 친구가 아닌 학생들끼리 짝을 지어 주면 서로 싸우거나 같이 돌아다니지 않기 때문에,
     * 항상 서로 친구인 학생들끼리만 짝을 지어야 합니다. 각 학생들의 쌍에 대해 이들이 서로 친구인지 여부가 주어질 때, 학생들을 짝지을 수 있는
     * 방법의 수를 계산하는 프로그램을 작성하세요. 짝이 되는 학생들이 일부만 다르더라도 다른 방법이라고 봅니다.
     *
     * 첫줄에는 테스트 케이스의 수 C (C <= 50)
     * 각 테스트 케이스의 첫 줄에는 학생의 수 n (2 <= n <= 10) 과 친구 쌍의 수 m(0<= m <= n(n-1)/2) 이 주어진다.
     * 그 다음 줄에 m 개의 정수 쌍으로 서로 친구인 두 학생의 번호가 주어진다.
     * 번호는 모두 0부터 n-1 사이의 정수이고, 같은 쌍은 입력에 두번 주어지지 않는다. 학생의 수는 짝수이다.
     *

     * 예제 입력
3
2 1
0 1
4 6
0 1 1 2 2 3 3 0 0 2 1 3
6 10
0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5

     * 예제 출력
     1
     3
     4

     */

    private static int C;
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        C = Integer.parseInt(br.readLine());

        int n;
        int m;
        boolean[][] pair;
        boolean[] picked;

        StringBuffer sf = new StringBuffer();

        for (int i = 0; i < C; i++) {
            result = 0;

            String line = br.readLine();
            n = Integer.parseInt(line.split(" ")[0]);
            m = Integer.parseInt(line.split(" ")[1]);

            pair = new boolean[n][n];
            picked = new boolean[n];

            String friendsLine = br.readLine();
            for (int j = 0; j < m; j++) {
                int first = Integer.parseInt(friendsLine.split(" ")[j * 2]);
                int second = Integer.parseInt(friendsLine.split(" ")[j * 2 + 1]);

                pair[first][second] = true;
            }

            pick(pair, picked, n, 0, n);
            sf.append(result).append("\n");
        }

        System.out.println(sf);

    }

    private static void pick(boolean[][] pair, boolean[] picked, int n, int start, int toPick) {
        if (toPick == 0) {
            result += 1;
            return;
        }

        for (int i = start; i < n; i++) {
            if (!picked[i]) {
                picked[i] = true;
                for (int j = 0; j < n; j++) {
                    if (pair[i][j] && !picked[j]) {
                        picked[j] = true;
                        pick(pair, picked, n, i + 1,toPick - 2);
                        picked[j] = false;
                    }
                }
                picked[i] = false;
            }
        }
    }
}
