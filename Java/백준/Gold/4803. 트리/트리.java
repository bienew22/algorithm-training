/**
 * 트리
 *
 * 문제 아이디어
 *  Union-find을 사용하여 트리및 그래프 구분.
 * 
 * 오류 해결 로그.
 * 8% 실패 해결 1 - 둘다 트리인 경우 처리 X
 * 8% 실패 해결 2 - parent 초기화 X
 * 8% 실패 해결 3 -
 *  4 6 / 1 2 / 2 3 / 3 4 / 1 1 / 2 2 / 33
 *  TC의 경우 treeCount가 음수로 떨어질 수 있는 것을 확인.
 *  8% 실패 해결 4 - 트리 아닌 그룹 내에서 연결이 발생한 경우 treeCount 도 감소 시킴.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    int findParent(int[] parent, int child) {
        if (parent[child] == child) {
            return child;
        }
        parent[child] = findParent(parent, parent[child]);
        return parent[child];
    }

    void solve() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //== 입력 받기 및 초기화
        StringBuffer sb = new StringBuffer();
        int tc = 1;
        int parse = 1200;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   // 정점의 개수
            int M = Integer.parseInt(st.nextToken());   // 간선의 개수

            if (N == 0 && M == 0) {
                break;
            }

            if (tc == parse) {
                System.out.println(N + "  " + M);
            }

            int treeCount = N;

            int[] parent = new int[N + 1]; // 각 정점의 소속을 의미
            for (int i  = 0;  i < parent.length; i++) {
                parent[i] = i;
            }

            boolean[] isTree = new boolean[N + 1]; // 각 정점이 트리 여부 의미
            Arrays.fill(isTree, true);

            for (int i = 0; i < M; i++) {
                int[] edge = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();

                if (tc == parse) {
                    System.out.println(edge[0] + "  " + edge[1]);
                }

                int sParent = findParent(parent, edge[0]);
                int eParent = findParent(parent, edge[1]);

                if (sParent == eParent) {
                    // 같은 그룹 내에서 사이클 발생을 의미.
                    // 해당 그룹이 트리인 경우에만 감소.
                    if (isTree[sParent]) {
                        treeCount--;
                        isTree[sParent] = false;
                    }
                } else {
                    // 12, 16, 17, 18, 22, 23
                    // 두개가 다른 그룹 -> 그룹 합치기.
                    parent[sParent] = eParent;

                    // 둘다 트리 아닌 경우 -> 같은 그룹 내 사이클 발생을로 이미 감소
                    // 하나 만 트리인 경우 -> 트리가 합쳐지면 그래프로 변경
                    // 둘 다 트리인 경우 -> 합쳐지면서 하나 트리가 됨.
                    if (isTree[sParent] ^ isTree[eParent]) {
                        // 하나가 트리가 아닌 경우에만 전체 트리수에 영향.
                        treeCount--;

                        isTree[sParent] = false;
                        isTree[eParent] = false;
                    } else if (isTree[sParent] && isTree[eParent]) {
                        treeCount--;
                    }
                }
            }

            if (treeCount <= 0) {
                sb.append("Case %d: No trees.\n".formatted(tc));
            } else if (treeCount == 1) {
                sb.append("Case %d: There is one tree.\n".formatted(tc));
            } else {
                sb.append("Case %d: A forest of %d trees.\n".formatted(tc, treeCount));
            }

            tc++;
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}