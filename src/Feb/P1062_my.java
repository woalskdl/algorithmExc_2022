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

        // a,n,t,i,c 는 필수
        if(K < 5) {
            System.out.println(0);
            return;
        }

        int fix;
        fix = (1 << LETTERS.indexOf('a'));
        fix = fix | (1 << LETTERS.indexOf('n'));
        fix = fix | (1 << LETTERS.indexOf('t'));
        fix = fix | (1 << LETTERS.indexOf('i'));
        fix = fix | (1 << LETTERS.indexOf('c'));

        int[][] arr = new int[27][1 << LETTERS.length()];

        for(int i=0; i<N; i++) {
            String word = br.readLine();
            String ckWord = word.substring(4, word.length() - 4);

            int check = fix;
            int wordCnt = 5;
            int length = ckWord.length();

            for(int j=0; j<length; j++) {
                int pos = LETTERS.indexOf(ckWord.charAt(j));
                
                if((check & (1 << pos)) > 0)
                    continue;

                check = check | (1 << pos);
                wordCnt += 1;
            }

            if(wordCnt > K)
                continue;

            arr[wordCnt][check] += 1;
        }

        int[] result = arr[K];
        Arrays.sort(result);
        int idx = result.length - 1;
        if(result[idx] != 0) {
            System.out.println(result[idx]);
        } else {
            for(int i=idx; i >= 0; i--) {
                int[] temp = arr[i];
                Arrays.sort(temp);
                if(temp[temp.length  - 1] != 0) {
                    int cnt = idx - i;
                    while (cnt >= 0) {

                    }
                }
            }
        }
    }
}
