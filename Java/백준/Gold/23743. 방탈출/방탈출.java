import java.util.*;
import java.lang.*;
import java.io.*;


class Main {

    int getParent(int child, int[] parent) {
        if (child == parent[child]) {
            return child;
        }

        parent[child] = getParent(parent[child], parent);
        return parent[child];
    }

    void solve() throws Exception {
        // 변수 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 방의 개수
        int M = Integer.parseInt(st.nextToken());   // 워프의 개수

        List<int[]> edges = new ArrayList<>();

        // 워프 정보 저장.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            edges.add(new int[] {node1, node2, value});
        }

        // 각 방에서 출구 까지 비용 저장.
        st = new StringTokenizer(br.readLine());

        for (int node = 1; node <= N; node++) {
            int value = Integer.parseInt(st.nextToken());

            edges.add(new int[] {0, node, value});
        }

        // 비용 기반 오름 차순으로 정렬 -> 크루스칼
        edges.sort(Comparator.comparingInt(a -> a[2]));

        // union & find -> 크루스칼.
        int[] parent = new int[N + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        int answer = 0;

        for (int[] edge: edges) {
            int node1Parent = getParent(edge[0], parent);
            int node2Parent = getParent(edge[1], parent);

            if (node1Parent != node2Parent) {
                answer += edge[2];

                parent[node2Parent] = node1Parent;
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        new Main().solve();
    }
}