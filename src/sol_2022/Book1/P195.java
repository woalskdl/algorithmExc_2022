package sol_2022.Book1;

/*
울타리 잘라내기 (ID : FENCE)
너비가 같은 N개의 나무 판자를 붙여 세운 울타리가 있는데
이 판자들이 망가져 울타리를 통째로 교체하려 한다.
이때, 버리는 울타리의 일부를 직사각형으로 잘라내어 재활용하고자 하는데
잘라낼 수 있는 직사각형의 최대 너비는?

첫 줄에 테스트 케이스 c(c<=50)
각 테스트의 첫 줄에는 판자의 수 N(1<=N<=20000)
그 다음 줄에는 N개의 정수로 왼쪽부터 각 판자의 높이가 순서대로 주어지며 높이는 모두 10000 이하의 자연수

각 테스트 케이스당 최대 넓이 정수 하나를 한 줄에 출력한다.

예시 입력
3
7
7 1 5 9 6 7 3
7
1 4 4 4 4 1 1
4
1 8 2 2

예시 출력
20
16
8

 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class P195 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int c = Integer.parseInt(br.readLine());

        StringBuffer sf = new StringBuffer();

        for (int i = 0; i < c; i++) {
            int N = Integer.parseInt(br.readLine());

            String heights = br.readLine();

            List<Integer> heightList = Arrays.stream(heights.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            sf.append(solve(heightList, 0, heightList.size() - 1)).append("\n");
        }

        System.out.println(sf);
    }

    private static int solve(List<Integer> heightList, int left, int right) {
        if (left == right)
            return heightList.get(left);

        int mid = (left + right) / 2;

        int result = Math.max(solve(heightList, left, mid), solve(heightList, mid + 1, right));

        int lo = mid;
        int hi = mid + 1;

        int height = Math.min(heightList.get(mid), heightList.get(hi));

        result = Math.max(result, height * 2);

        while (lo > left || hi < right) {
            if (hi < right && (lo == left || heightList.get(lo - 1) < heightList.get(hi + 1))) {
                hi += 1;
                height = Math.min(height, heightList.get(hi));
            } else {
                lo -= 1;
                height = Math.min(height, heightList.get(lo));
            }

            result = Math.max(result, height * (hi - lo + 1));
        }

        return result;
    }
}
