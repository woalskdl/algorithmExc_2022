package sol_2022.Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 비트마스킹 문제 풀어보려고 이 문제 풀었기 때문에, 비트마스킹 접근 풀이
public class P1094_answer {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int result = 0;

        for(int i=0; i<7; i++) {
            if((n & (1 << i)) > 0)
                result += 1;
        }

        System.out.println(result);
    }
}
