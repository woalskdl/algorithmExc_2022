package sol_2023.Book1.Jan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
어떤 수열에서 0개 이상의 숫자를 지운 결과를 원 수열의 부분 수열이라고 부르고, 오름차순으로 정렬되어 있는 부분 수열들을 가리켜 증가 부분 수열이라고 부른다.
어떤 두 개의 정수 수열 A, B 에서 증가 부분 수열을 얻은 뒤 이들을 크기 순서대로 합친 것을 합친 증가 부분 수열이라고 부르고, 이 중 가장 긴 수열을 합친 LIS 라고 부르자.
예를들어 '1 3 4 7 9'는 '1 9 4', '3 4 7'의 합친 증가 부분 수열이다.
A와 B가 주어질 때 합친 LIS의 길이를 계산하는 프로그램을 작성하시오.

첫 줄에는 테스트 케이스 C (1 <= C <= 50), 각 테스트 케이스의 첫 줄에는 A와 B의 길이 n과 m (1 <= n,m <= 100)이 주어진다.
다음 줄에는 n개의 정수로 A의 원소들이, 그 다음 줄에는 m개의 정수로 B의 원소들이 주어진다.

에제 입력
3
3 3
1 2 4
3 4 7
3 3
1 2 3
4 5 6
5 3
10 20 30 1 2
10 20 30

예제 출력
5
6
5

 */
public class P236 {

    static int n;
    static int m;
    static int[] arr1;
    static int[] arr2;
    static int[][] cache;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int c = Integer.parseInt(br.readLine());

        for (int i=0; i<c; i++) {
            String length = br.readLine();
            n = Integer.parseInt(length.split(" ")[0]);
            m = Integer.parseInt(length.split(" ")[1]);

            arr1 = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).mapToInt(e -> e).toArray();
            arr2 = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).mapToInt(e -> e).toArray();
            cache = new int[n + 1][m + 1];

            for (int j = 0; j < n + 1; j++)
                Arrays.fill(cache[j], -1);

            System.out.println(lis(-1, -1));
        }
    }

    private static int lis(int start1, int start2) {
        int ret = cache[start1 + 1][start2 + 1];

        if (ret != -1)
            return ret;

        ret = 2;
        int a = (start1 == -1 ? Integer.MIN_VALUE : arr1[start1]);
        int b = (start2 == -1 ? Integer.MIN_VALUE : arr2[start2]);
        int max = Math.max(a, b);

        for (int i = start1 + 1; i < n; i ++) {
            if (max < arr1[i])
                ret = Math.max(ret, lis(i, start2) + 1);
        }

        for (int i = start2 + 1; i < m; i++) {
            if (max < arr2[i])
                ret = Math.max(ret, lis(start1, i) + 1);
        }

        return cache[start1 + 1][start2 + 1] = ret;
    }
}

