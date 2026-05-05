import java.util.*;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        // 인접 리스트
        ArrayList<HashSet<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new HashSet<>());
        }

        // 각 노드의 차수
        int[] degree = new int[n];

        // 인접 리스트 및 차수 계산
        for (int[] edge : lighthouse) {
            int u = edge[0] - 1, v = edge[1] - 1;
            adjList.get(u).add(v);
            adjList.get(v).add(u);
            degree[u]++;
            degree[v]++;
        }

        // 리프 노드 찾기
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                queue.offer(i);
            }
        }

        int answer = 0;
        boolean[] visited = new boolean[n];

        while (!queue.isEmpty()) {
            int leaf = queue.poll();

            if (visited[leaf]) continue;

            visited[leaf] = true;

            for (int parent : adjList.get(leaf)) {
                if (visited[parent]) continue;

                // 부모 선택
                answer++;
                visited[parent] = true;

                for (int next : adjList.get(parent)) {
                    degree[next]--;
                    if (!visited[next] && degree[next] == 1) {
                        queue.offer(next);
                    }
                }
            }


        }

        return answer;
    }
}