import java.util.*;
import java.io.*;

public class Main {
    // static int[] base = new int[4]; // A, C, G, T 개수 저장
    static int[] required = new int[4]; // 사용할 최소 개수
    static int[] current = new int[4]; // 현재 윈도우 내 염기 개수
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()); // DNA 문자열 길이
        int P = Integer.parseInt(st.nextToken()); // 부분 문자열 길이

        HashMap<Character, Integer> numCode = new HashMap<>();
        numCode.put('A', 0);
        numCode.put('C', 1);
        numCode.put('G', 2);
        numCode.put('T', 3);

        String dna = br.readLine();

        // 사용할 염기 개수 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            required[i] = Integer.parseInt(st.nextToken());
        }

        // 처음 P개의 문자 카운트 (초기 윈도우)
        for (int i = 0; i < P; i++) {
            current[numCode.get(dna.charAt(i))]++;
        }

        // 초기 윈도우가 조건을 만족하는지 확인
        if (checkValid()) result++;

        // 슬라이딩 윈도우
        for (int i = P; i < S; i++) {
            int addIdx = numCode.get(dna.charAt(i));     // 새로 추가될 문자
            int removeIdx = numCode.get(dna.charAt(i - P)); // 제거될 문자

            current[addIdx]++;   // 새로운 문자 추가
            current[removeIdx]--; // 맨 앞 문자 제거

            if (checkValid()) result++;
        }

        System.out.print(result);
    }

    // 현재 윈도우가 조건을 만족하는지 체크
    public static boolean checkValid() {
        for (int i = 0; i < 4; i++) {
            if (current[i] < required[i]) return false; // 부족한 염기가 있으면 불가능
        }
        return true;
    }
}