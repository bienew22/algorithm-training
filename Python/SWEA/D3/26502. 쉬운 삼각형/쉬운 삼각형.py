
T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    N = int(input())

    points = [list(map(int, input().split())) for _ in range(N)]

    value = 0
    for i in range(N):
        for j in range(N):
            if i == j:
                continue

            if points[i][0] != points[j][0]:
                continue

            for k in range(N):
                if i == k or j == k:
                    continue

                if points[i][1] == points[k][1]:
                    value = max(value, abs(points[i][1] - points[j][1]) * abs(points[i][0] - points[k][0]))

                if points[j][1] == points[k][1]:
                    value = max(value, abs(points[i][1] - points[j][1]) * abs(points[j][0] - points[k][0]))

    print(value)
