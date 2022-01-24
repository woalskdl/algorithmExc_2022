package Jan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P2294_my {

    private static int n;
    private static int k;
    private static List<Integer> coins;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        HashSet<Integer> temp = new HashSet<>();
        arr = new int[k + 1];

        for(int i=0; i<=k; i++)
            arr[i] = -1;

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int val = Integer.parseInt(st.nextToken());
            temp.add(val);

            if(val <= k)
                arr[val] = 1;
        }

        coins = new ArrayList<>(temp);
        Collections.sort(coins, Collections.reverseOrder());

        int result = dp(k);

        System.out.println(result > 10000 ? -1 : result);
    }

    private static int dp(int val){
        if(arr[val] != -1)
            return arr[val];

        int minVal = 10001;
        for(Integer c : coins){
            if(c >= val)
                continue;

            int count = dp(val - c) + 1;
            if(count != 0 && minVal > count)
                minVal = count;
        }

        return arr[val] = minVal;
    }
}
