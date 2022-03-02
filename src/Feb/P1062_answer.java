package Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1062_answer {
    private static int N;
    private static int K;
    private static int result;
    private static String[] words;

    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];
        result = 0;

        // a,n,t,i,c 는 필수
        if (K < 5) {
            System.out.println(result);
            return;
        }

        if (K == 26) {
            System.out.println(N);
            return;
        }

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            String ckWord = word.substring(4, word.length() - 4);

            words[i] = ckWord;
        }

        int flag;
        flag = (1 << LETTERS.indexOf('a'));
        flag |= (1 << LETTERS.indexOf('n'));
        flag |= (1 << LETTERS.indexOf('t'));
        flag |= (1 << LETTERS.indexOf('i'));
        flag |= (1 << LETTERS.indexOf('c'));

        comb(0, 0, flag);

        System.out.println(result);

    }

    private static void comb(int length, int start, int flag) {
        if (length == K - 5) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                boolean isValid = true;
                String word = words[i];

                for (int j = 0; j < word.length(); j++) {
                    int ch = word.charAt(j);

                    if((flag & (1 << LETTERS.indexOf(ch))) == 0) {
                        isValid = false;
                        break;
                    }
                }

                if(isValid)
                    cnt += 1;
            }

            result = Math.max(result, cnt);
            return;
        }

        for (int i = start; i < 26; i++) {
            if ((flag & (1 << i)) != 0) continue;

            comb(length + 1, i + 1, flag | (1 << i));
        }
    }
}
