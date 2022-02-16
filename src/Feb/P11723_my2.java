package Feb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11723_my2 {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int check = 0;

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            String action = st.nextToken();

            if ("add".equals(action)) {
                int num = Integer.parseInt(st.nextToken());
                if((check & (1 << num)) == 0)
                    check = check | (1 << num);
            } else if ("remove".equals(action)) {
                int num = Integer.parseInt(st.nextToken());
                if((check & (1 << num)) != 0)
                    check = check ^ (1 << num);
            } else if ("check".equals(action)) {
                int num = Integer.parseInt(st.nextToken());
                if((check & (1 << num)) == 0)
                    sb.append(0 + "\n");
                else
                    sb.append(1 + "\n");
            } else if ("toggle".equals(action)) {
                int num = Integer.parseInt(st.nextToken());
                if((check & (1 << num)) == 0)
                    check = check | (1 << num);
                else
                    check = check ^ (1 << num);
            } else if ("all".equals(action)) {
                check = (1 << 21) - 1;
            } else if ("empty".equals(action)) {
                check = 0;
            }
        }

        System.out.println(sb);
    }
}
