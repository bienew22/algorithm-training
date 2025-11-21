/**
 * 단어 만들기
 * <p>
 * 문자열 처리 문제.
 *
 * -- 해결 로그 --
 * 메모리 초과 1차 : 모든 데이터를 HashMap을 저장한 걸 그 때 그 때 만들어서 사용으로 수정.
 * 메모리 초과 2차 : board 정보가 1회용이므로 저장 X.
 * 메모리 초과 3차 : 출력을 모아서 출력이 아닌 한개 계산 끝날 때 마다 출력.
 * 메모리 초과 4차 : wordCount를 새로 만드는 것이 아닌 재사용으로 수정.
 * 메모리 초과 5차 : 귀찮지만 HashMap에서 bit mask으로 수정.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        List<byte[]> wordCharCounts = new ArrayList<>();

        while (true) {
            String word =  br.readLine();
            if (word.equals("-"))
                break;

            byte[] count = new byte[26];
            for (char c: word.toCharArray()) {
                int idx = c - 'A';

                count[idx]++;
            }
            wordCharCounts.add(count);
        }


        StringBuilder sb = new StringBuilder();
        //== 로직 처리.
        while (true) {
            String board =  br.readLine();

            if (board.equals("#"))
                break;

            byte[] boardCharCount = new byte[26];
            int boardMask = 0;

            for (char c: board.toCharArray()) {
                int idx = c - 'A';

                boardMask |= (1 << idx);
                boardCharCount[idx]++;
            }

            // 각 문자를 중아으로 했을 때 만들수 있는 단어의 개수를 의미.
            int[] counter = new int[26];

            // 각 단어에 대하여 해당 보드로 만들 수 있는지 계산.
            for (byte[] wordCharCount: wordCharCounts) {
                boolean isOk = true;
                for (int i = 0; i < 26; i++) {
                    if (boardCharCount[i] < wordCharCount[i]) {
                        isOk = false;
                        break;
                    }
                }
                if (!isOk) {
                    continue;
                }

                for (int i = 0; i < 26; i++) {
                    if (wordCharCount[i] > 0) {
                        counter[i]++;
                    }
                }
            }

            // 최저와 최고 난이도 문자 구하기.
            int easy = Integer.MAX_VALUE;
            List<Character> easyChar = new ArrayList<>();
            int hard = Integer.MIN_VALUE;
            List<Character> hardChar = new ArrayList<>();

            for (int i = 0; i < 26; i++) {
                if (((1 << i) & boardMask) == 0) {
                    continue;
                }

                int count = counter[i];
                char ch = (char) ('A' + i);

                if (count < easy) {
                    easy = count;
                    easyChar.clear();
                    easyChar.add(ch);
                } else if (count == easy) {
                    easyChar.add(ch);
                }

                if (count > hard) {
                    hard = count;
                    hardChar.clear();
                    hardChar.add(ch);
                } else if (count == hard) {
                    hardChar.add(ch);
                }
            }

            easyChar.stream().sorted().forEach(sb::append);
            sb.append(" ").append(easy).append(" ");
            hardChar.stream().sorted().forEach(sb::append);
            sb.append(" ").append(hard);

            System.out.println(sb);
            sb.setLength(0);
        }
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}