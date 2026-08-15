# https://school.programmers.co.kr/learn/courses/30/lessons/258711

from collections import defaultdict


def graph_navi(graph, visit, s):
    # 방문 한 모든 노드 기록
    res = {s}
    visit[s] = 1

    to_visit = [s]
    while to_visit:
        next_visit = []

        for now in to_visit:
            if now not in graph:
                continue

            for goal in graph[now]:
                visit[goal] += 1

                res.add(goal)
                # 최대 두 번의 방문 허용
                # 이유 : 8자 이랑 도넛 그래프를 보면 노드를 최대 2회 방문 할 수 있음.
                if visit[goal] < 2:
                    next_visit.append(goal)

        to_visit = next_visit

    return res


def solution(edges):
    # 데이터 전처리
    graph = defaultdict(list)
    in_count = defaultdict(int)
    for s, e in edges:
        graph[s].append(e)

        in_count[e] += 1

    # in count 가 0 인 노드 추가 - defaultdict 에 의하여 if 문 검사 하면서 자동 추가 됨.
    for node in in_count:
        if graph[node]:
            pass

    ans = [0, 0, 0, 0]

    # 추가 된 노드(s) 찾기
    # 문제 조건에 따라서 추가 된 노드의 in count 가 0 이 됨.
    # out count 는 2 이상이 됨.
    # 이 두가지를 만족하는 노드는 추가 된 노드만 만족 함.
    for node in graph:
        if in_count[node] == 0 and len(graph[node]) >= 2:
            ans[0] = node

            # 추가 된 간선 지우기
            for e in graph[node]:
                in_count[e] -= 1
            break

    # 각 노드의 방문 횟수를 저장 : 개별의 그래프이므로 하나의 visit 으로 관리
    visit = defaultdict(int)

    # 각 노드를 탐색
    for node in sorted(graph.keys(), key=lambda a: in_count[a]):
        # 이미 방문한 노드에 대하여 재방문 예방
        # 또는 추가한 노드에 대하여 탐색 X
        if node == ans[0] or visit[node] != 0:
            continue

        res = graph_navi(graph, visit, node)

        if in_count[node] == 0:
            # 무조건 막대 모양 그래프에서 시작 노드
            ans[2] += 1
        else:
            # 도넛, 8자, 막대 중간 노드 셋 중 하나 임.
            # 도넛 : 2번 방문한 노드의 수 1개
            # 8자 : 2번 방문한 노드의 수 2개 (중간 순환 지점 부터 방문하는 경우 문제 발생)
            #   - 노드 방문 우선순위에 따라서 중간 순환 지점 부터 탐색할 경우 존재 X
            # 막대 중간 노드 : 2번 방문한 노드의 수 0개
            count_two = 0

            for r in res:
                if visit[r] == 2:
                    count_two += 1

            if count_two == 1:
                ans[1] += 1
            elif count_two == 2:
                ans[3] += 1

    return ans
