package sol_2022.Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class P11723_my {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 당연히 시간초과...? System.out.println ??
        HashSet<String> set = new HashSet<>(20);

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            String action = st.nextToken();

            if ("add".equals(action)) {
                set.add(st.nextToken());
            } else if ("remove".equals(action)) {
                set.remove(st.nextToken());
            } else if ("check".equals(action)) {
                if (set.contains(st.nextToken()))
                    sb.append(1  + "\n");
                else
                    sb.append(0 + "\n");
            } else if ("toggle".equals(action)) {
                String val = st.nextToken();
                if (!set.add(val))
                    set.remove(val);
            } else if ("all".equals(action)) {
                for (int j = 1; j <= 20; j++)
                    set.add(j + "");
            } else if ("empty".equals(action)) {
                set.clear();
            }
        }

        System.out.println(sb);
    }
}
