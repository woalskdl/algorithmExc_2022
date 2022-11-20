package Nov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P168_sol {

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
        int[] clock = new int[16];

        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < c; i++) {
            String line = br.readLine();

            for (int j = 0; j < 16; j++)
                clock[j] = Integer.parseInt(line.split(" ")[j]);

            int result = count(clock, 0);
            sf.append(result == Integer.MAX_VALUE ? -1 : result).append("\n");
        }

        System.out.println(sf);
    }

    private static int count(int[] clock, int buttonNum) {

        int cnt = Integer.MAX_VALUE;

        if(buttonNum == 10)
            return arrAllAligned(clock) ? 0 : cnt;

        for(int i=0; i<4; i++) {
            // 현재 버튼을 한번도 안눌렀을 케이스 부터 시작
            int ret = i + count(clock, buttonNum + 1);
            cnt = Math.min(cnt, ret < 0 ? Integer.MAX_VALUE : ret);

            // 다음 for문으로 넘어가면서 한번 누를거니까 눌러놓고 for문 넘기기
            set(clock, buttonNum);

        }

        return cnt;
    }

    // buttonNum 번의 스위치를 누르기
    private static void set(int[] clock, int buttonNum) {
        for (int i = 0; i < 16; i++)
            clock[i] = (clock[i] + button[buttonNum][i]) % 12 == 0 ? 12 : (clock[i] + button[buttonNum][i]) % 12;
    }

    private static boolean arrAllAligned(int[] clock) {
        boolean complete = true;

        for (int i = 0; i < 16; i++) {
            if (clock[i] != 12) {
                complete = false;
                break;
            }
        }

        return complete;
    }
}
