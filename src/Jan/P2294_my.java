package Jan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2294_my {

    private static int n;
    private static int k;
    private static int[] coins;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new int[n];
        arr = new int[k + 1];

        for(int i=0; i<=k; i++)
            arr[i] = -1;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int val = Integer.parseInt(st.nextToken());
            coins[i] = val;
            arr[val] = 1;
        }

        System.out.println(dp(k));
    }

    private static int dp(int val){
        if(val <= 0)
            return -1;

        if(arr[val] != -1)
            return arr[val];

        // ArrayIndexOutOfBound???

        int minVal = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            int count = dp(val - coins[i]) + 1;
            if(count != 0 && minVal > count)
                minVal = count;
        }

        if(minVal == Integer.MAX_VALUE)
            minVal = -1;

        return arr[val] = minVal;
    }
}
