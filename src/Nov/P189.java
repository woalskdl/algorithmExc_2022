package Nov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

        for(int i=0; i<c; i++) {
            String quad = br.readLine();


        }

    }

    // 문자열을 앞부터 검사하며 x가 나오는 부분부터 해당 x가 완성될때 까지 해당 x객체에 담는다. > 재귀 호출로 반복
    // 완성된 쿼드 객체를 마지막 2로 나눠질때까지 앞2와 뒤2의 위치를 바꾼다.


}
