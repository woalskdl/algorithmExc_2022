package Nov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P148 {

    private static int n;
    private static int r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        r = Integer.parseInt(br.readLine());

        StringBuffer sf = new StringBuffer();
        Stack<Integer> target = new Stack<>();
        pick(0, target, sf);
        System.out.println(sf);
    }

    private static void pick(int idx, Stack<Integer> target, StringBuffer sf) {
        if (target.size() == r) {
            sf.append(target).append("\n");
            target.pop();
        }

        if (idx >= n) {
            if (target.empty())
                return;

            idx = target.peek();
            target.pop();
        } else {
            target.push(idx);
        }

        pick(idx + 1, target, sf);
    }
}
