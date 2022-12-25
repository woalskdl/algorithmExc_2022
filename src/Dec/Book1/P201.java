package Dec.Book1;

/*
아이돌 멤버가 팬미팅을 하는데 일렬로 줄을 서서 팬들이 한줄로 오면 한명씩 악수 또는 포옹을 한다.
멤버가 한 줄로 서있으면 팬들이 일렬로 서서 아이돌 멤버를 스쳐지나가며 포옹 또는 악수를 하는 형식
남성 멤버는 남성 팬과 악수, 여성은 포옹을 한다. 나머지는 전부 포옹
줄을 선 멤버들과 팬들의 성별이 주어질 때 모든 멤버가 동시에 포옹을 하는 일이 몇 번이나 있는지 계산

첫 줄에 테스트 케이스, 두 줄에 걸쳐 아이돌 멤버의 성별과 팬의 성별

예시 입력
4
FFFMMM
MMMFFF
FFFFF
FFFFFFFFFF
FFFFM
FFFFFMMMMF
MFMFMFFFMMMFMF
MMFFFFFMFFFMFFFFFFMFFFMFFFFMFMMFFFFFFF

예제 출력
1
6
2
2

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P201 {
    public static void main(String[] args) throws IOException {

        int c;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sf = new StringBuffer();
        c = Integer.parseInt(br.readLine());

        for (int i = 0; i < c; i++) {
            String membersLine = br.readLine();
            String fansLine = br.readLine();

            int membersCnt = membersLine.length();
            int fansCnt = fansLine.length();

            // 남성 : false / 여성 : true
            boolean[] members = new boolean[membersCnt];
            boolean[] fans = new boolean[fansCnt];

            for (int j = 0; j < membersCnt; j++)
                members[j] = membersLine.charAt(j) == 'F';

            for (int j = 0; j < fansCnt; j++)
                fans[j] = fansLine.charAt(j) == 'F';

            int result = 0;
            for (int j = membersCnt - 1; j < fansCnt; j++) {
                boolean hug = true;

                for (int k = j - membersCnt + 1, m = 0; k < j + 1; k++, m++)
                    if (!members[m] && !fans[k]) {
                        hug = false;
                        break;
                    }

                if (hug)
                    result += 1;
            }

            sf.append(result).append("\n");

        }

        System.out.println(sf);

    }
}
