package sol_2022.Book1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
와일드카드는 다양한 운영체제에서 파일 이름의 일부만으로 파일 이름을 지정하는 방법이다.
이때 사용하는 문자열을 와일드카드 패턴이라고 하는데, 특수문자 *나 ?를 포함할 수 있는 문자열이다.
와일드카드 패턴을 앞에서 한 글자씩 파일명과 비교해서 모든 글자가 일치했을 때 해당 와일드카드 패턴이 파일명과 대응된다고 말한다.
단, 와일드카드 패턴에 포함된 ?는 어떤 글자와도 대응된다고 가정하며, *는 0글자 이상의 어떤 문자열에도 대응된다.
예를들어 he?p는 help, heap 과 대응되지만, helpp 에는 대응되지 않는다.
반면 *p*는 파일명 help, papa 에도 대응된다.
와일드카드 패턴과 함께 파일명의 집합이 주어질 때 그 중 패턴에 대응되는 파일명들을 찾아는 프로그램을 작성하라.

첫 줄에는 테스트 케이스의 수 C (1<=C<=10), 각 테스트 케이스의 첫 줄에는 와일드카드 패턴 W, 그 다음 줄에는 파일명의 수
n (1<=n<=50), 그 후 n줄에 하나씩 각 파일명이 주어진다. 와일드카드 패턴은 알파벳 대소문자,숫자와 *,?만으로 구성되며
파일명은 알파벳 대소문자와 숫자만으로 이루어진다. 모든 문자열의 길이는 1 이상 100이하이며, 공백을 포함하지 않는다.

[예제입력]
3
he?p
3
help
heap
helpp
*p*
3
help
papa
hello
*bb*
1
babbbc

[예제출력]
heap
help
help
papa
babbbc
 */
public class P218_sol2 {
    static int[][] cache;
    static String pattern, fileName;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int c = Integer.parseInt(br.readLine());
        StringBuffer sf = new StringBuffer();

        for (int i = 0; i < c; i++) {
            pattern = br.readLine();
            int n = Integer.parseInt(br.readLine());
            cache = new int[101][101];

            for (int j = 0; j < n; j++) {
                fileName = br.readLine();
                if (match(0, 0))
                    sf.append(fileName).append("\n");
            }
        }

        System.out.println(sf);

    }

    private static boolean match(int p, int f) {

        int ret = cache[p][f];

        if (ret != 0)
            return ret == 1;

        while (f < fileName.length() && p < pattern.length() &&
                (pattern.charAt(p) == '?' || pattern.charAt(p) == fileName.charAt(f))) {
            p += 1;
            f += 1;
        }

        if (p == pattern.length()) {
            ret = (f == fileName.length() ? 1 : -1);
            return ret == 1;
        }

        if (pattern.charAt(p) == '*')
            for (int skip = 0; f + skip <= fileName.length(); skip++)
                if (match(p + 1, f + skip))
                    return true;

        return false;
    }
}
