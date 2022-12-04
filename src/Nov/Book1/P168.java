package Nov.Book1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    시계 맞추기 (ID : CLOCKSYNC)
    4 x 4 격자 형태로 배치된 열여섯개의 시침밖에 없는 시계가 있다. 이 시계들은 모드 3,6,9,12시를 가리키고 있다.
    이 시계들이 모두 12시를 가리키도록 바꾸고 싶은데 시계를 조작하려면 스위치를 조작해야한다.
    각 스위치는 모두 적개는 세 개에서 많게는 다섯 개의 시계와 연결되어 있다.
    한 스위치를 누를때마다 해당 스위치와 연결된 시계들의 시간은 3시간씩 앞으로 움직인다.
    스위치와 연결된 시계들의 목록은 다음과 같다.
    0 - 0,1,2
    1 - 3,7,9,11
    2 - 4,10,14,15
    3 - 0,4,5,6,7
    4 - 6,7,8,10,12
    5 - 0,2,14,15
    6 - 3,14,15
    7 - 4,5,7,14,15
    8 - 1,2,3,4,5
    9 - 3,4,5,9,13

    모든 시계를 12시로 돌리기 위해 최소한 스위치를 몇번이나 눌러야 하는가?

    첫 줄에 테스트 케이스의 수 C (C<=30)
    테스트 케이스는 한 줄에 16개의 정수로 주어지며 각 정수는 0번부터 15번까지 시계가 가리키는 시간을 3,6,9,12 중 하나로 표현한다.
    불가능할 경우 -1 을 출력한다.

    예제 입력
2
12 6 6 6 6 6 12 12 12 12 12 12 12 12 12 12
12 9 3 12 6 6 9 3 12 9 12 9 12 12 6 6
 */


/**
 * 나의 패착
 * 1. 순서가 중요하지 않다는 사실을 놓침
 * 2. 버튼별로 for문을 돌리면 버튼을 누르고 다음 버튼으로 넘어가는 경우, 안누르고 다음 버튼으로 누르는 경우를 로직에 담지 못함
 *
 * 내가 정한 범위가 모든 케이스를 반영할 수 없다고 판단되면 다르게 생각하는 것도 연습하자.
 */
public class P168 {

    private static final int[][] button = {
            {3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 3, 0, 0, 0, 3, 0, 3, 0, 3, 0, 0, 0, 0},
            {0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 3, 3},
            {3, 0, 0, 0, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 3, 3, 3, 0, 3, 0, 3, 0, 0, 0},
            {3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3},
            {0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3},
            {0, 0, 0, 0, 3, 3, 0, 3, 0, 0, 0, 0, 0, 0, 3, 3},
            {0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 3, 3, 3, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int c = Integer.parseInt(br.readLine());
        int[] time = new int[16];

        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < c; i++) {
            String line = br.readLine();
            boolean[] visited = new boolean[10];

            for (int j = 0; j < 16; j++)
                time[j] = Integer.parseInt(line.split(" ")[j]);

            int result = count(time, new int[10]);
            sf.append(result == Integer.MAX_VALUE ? -1 : result).append("\n");
        }

        System.out.println(sf);
    }

    // click : 버튼별로 누른 횟수
    private static int count(int[] time, int[] click) {
        boolean complete = true;

        for (int i = 0; i < 16; i++) {
            if (time[i] != 12) {
                complete = false;
                break;
            }
        }

        if (complete) {
            int ret = 0;
            for (int i = 0; i < 10; i++)
                ret += click[i];
            return ret;
        }

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            // 한 버튼을 4번 이상 누르는건 의미가 없으므로 continue
            if (click[i] == 3)
                continue;

            set(time, i, true);
            click[i] += 1;

            result = Math.min(result, count(time, click));

            set(time, i, false);
            click[i] -= 1;
        }

        return result;
    }

    // buttonNum 번의 스위치를 눌러 시계를 앞으로(flag = true) 또는 뒤로(flag = false) 감기
    private static void set(int[] time, int buttonNum, boolean flag) {
        for (int i = 0; i < 16; i++) {
            if (flag)
                time[i] = time[i] + button[buttonNum][i] > 12 ? 3 : time[i] + button[buttonNum][i];
            else
                time[i] = time[i] - button[buttonNum][i] == 0 ? 12 : time[i] - button[buttonNum][i];
        }
    }

}
