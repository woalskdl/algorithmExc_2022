package Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

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

        Map<Integer, Integer> map = new HashMap<>();

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

            if(map.containsKey(check))
                map.put(check, map.get(check) + 1);
            else
                map.put(check, 1);
        }

        for(Integer key : map.keySet())
            result = Math.max(result, map.get(key));

        System.out.println(result);
    }
}
