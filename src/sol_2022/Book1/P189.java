package sol_2022.Book1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
쿼드 트리 뒤집기 (ID : QUADTREE)
대량의 조표 데이터를 메모리 안에 압축해 저장하기 위해 사용하는 기법으로,
주어진 공간을 항상 4개로 분할해 재귀적으로 표현한다.

그림의 모든 픽셀이 검은색일 경우 b, 흰색일 경우 w, 섞여있을 경우 x 이며 다시 재귀적으로 호출한다.
예시는 책 참고

첫 줄에 테스트 케이스 c(c<=50)
c줄에 하나씩 쿼드 트리로 압축한 그림이 주어짐.
모든 문자열의 길이는 1,000 이하이며, 원본 그림의 크기는 2^20 x 2^20 을 넘지 않는다.

그림을 상하로 뒤집은 쿼드 트리를 압축하여 출력하라.

예제 입력
4
w
xbwwb
xbwxwbbwb
xxwwwbxwxwbbbwwxxxwwbbbwwwwbb

예제 출력
w
xwbbw
xxbwwbbbw
xxwbxwwxbbwwbwbxwbwwxwwwxbbwb

 */
public class P189 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int c = Integer.parseInt(br.readLine());

        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < c; i++) {
            String quad = br.readLine();
            sf.append(flip(quad) + "\n");
        }

        System.out.println(sf);

    }

    // 쿼드 문자열을 뒤집는다.
    private static String flip(String quad) {
        if (quad.length() < 4)
            return quad;

        List<String> pixelList = new ArrayList<>();
        String newQuad = "x";

        for (int i = 1; i < quad.length(); i++) {
            char pixel = quad.charAt(i);

            if (pixel != 'x') {
                pixelList.add(String.valueOf(pixel));
                continue;
            }

            int lastXPixelIndex = getLastXPixelIndex(quad.substring(i + 1));
            pixelList.add(flip(quad.substring(i, i + lastXPixelIndex + 1)));
            i += lastXPixelIndex;
        }

        newQuad += pixelList.get(2);
        newQuad += pixelList.get(3);
        newQuad += pixelList.get(0);
        newQuad += pixelList.get(1);

        return newQuad;
    }

    // x로 시작하는 문자열의 마지막 픽셀 데이터의 인덱스를 구한다. 입력받는 문자열은 가장 앞의 x 이후의 문자열로 이루어진다.
    private static int getLastXPixelIndex(String xQuad) {

        int pixelCount = 0;
        int pixelIdx = 0;

        for (; pixelIdx < xQuad.length() && pixelCount < 4; pixelIdx++) {

            pixelCount += 1;

            if (xQuad.charAt(pixelIdx) != 'x')
                continue;

            pixelIdx += getLastXPixelIndex(xQuad.substring(pixelIdx + 1));
        }

        return pixelIdx;
    }

}
