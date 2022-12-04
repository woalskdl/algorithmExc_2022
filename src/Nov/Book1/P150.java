package Nov.Book1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P150 {

    private static String[][] boggle;
    private static String start;
    private static String answer;

    private static final int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
    private static final int[] dx = {1, -1, 0, 0, 1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        start = br.readLine();
        answer = br.readLine();

        boggle = new String[5][5];
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            boggle[i] = line.split(" ");
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (start.equals(boggle[i][j]))
                    findWord(i, j, "");
            }
        }
    }

    private static void findWord(int y, int x, String result) {
        if (y >= 5 || x >= 5 || y < 0 || x < 0)
            return;

        if (result.equals(answer)) {
            System.out.println(result);
            return;
        }

        if (result.length() == answer.length())
            result = result.substring(0, result.length() - 1);

        result += boggle[y][x];

        for (int i = 0; i < 8; i++)
            findWord(y + dy[i], x + dx[i], result);
    }
}
