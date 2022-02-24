package Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1062_my {
    private static int N;
    private static int K;

    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int result = 0;

        // a,n,t,i,c 는 필수
        if(K < 5) {
            System.out.println(result);
            return;
        }

        int fix;
        fix = (1 << LETTERS.indexOf('a'));
        fix = fix | (1 << LETTERS.indexOf('n'));
        fix = fix | (1 << LETTERS.indexOf('t'));
        fix = fix | (1 << LETTERS.indexOf('i'));
        fix = fix | (1 << LETTERS.indexOf('c'));

        int[] arr = new int[1 << LETTERS.length()];
        int max = (1 << K) - 1;

        int check = fix;
        for(int i=0; i<N; i++) {
            String word = br.readLine();
            String ckWord = word.substring(4, word.length() - 4);

            int length = ckWord.length();

            for(int j=0; j<length; j++) {
                int pos = LETTERS.indexOf(ckWord.charAt(j));
                
                if((check & (1 << pos)) > 0)
                    continue;

                check = check | (1 << pos);
            }

            if(check > max)
                continue;

            result += 1; // 기존에 있는 숫자 체크해서 있으면 + 1 뭐 이런식으로?

            arr[check] += 1;
        }

    }

    private static int cnt(int num) {
        int result = 0;

        for(int i=0; ; i++) {
            int ck = (1 << i);

            if((num & ck) > 0)
                result += 1;

            if((num | ck) > num)
                break;
        }

        return result;
    }
}
