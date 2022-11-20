package Nov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P148_sol {
    private static int n;
    private static int r;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        r = Integer.parseInt(br.readLine());

        StringBuffer sf = new StringBuffer();
        Stack<Integer> picked = new Stack<>();
        pick(picked, r, sf);
        System.out.println(sf);
    }

    private static void pick(Stack<Integer> picked, int toPick, StringBuffer sf) {
        if (toPick == 0) {
            sf.append(picked).append("\n");
            return;
        }

        int smallest = picked.isEmpty() ? 0 : picked.peek() + 1;

        for (int next = smallest; next < n; next++) {
            picked.push(next);
            pick(picked, toPick - 1, sf);
            picked.pop();
        }
    }
}
