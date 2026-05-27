def find_cycle(y, x, direction, grid):
    # direction = (y, x), order = 우 하 좌 상
    directions = [(0, 1), (-1, 0), (0, -1), (1, 0)]

    s_y, s_x, s_direction = y, x, direction
    y_max, x_max = len(grid), len(grid[0])

    cycle = 1

    while True:
        # move
        grid[y][x][direction + 1] = 1
        y = (y + directions[direction][0]) % y_max
        x = (x + directions[direction][1]) % x_max
        if grid[y][x][0] == "L":
            direction = (4 + direction - 1) % 4
        elif grid[y][x][0] == "R":
            direction = (4 + direction + 1) % 4

        if y == s_y and x == s_x and s_direction == direction:
            break
        cycle += 1

    return cycle


def solution(grid):
    # (y, x)에서 상하좌우으로 빛을 쏘는 것이 다른 사이클에 포함 여부 저장
    grid = [[[grid[i][j], 0, 0, 0, 0] for j in range(len(grid[i]))] for i in range(len(grid))]

    answer = []

    for y in range(len(grid)):
        for x in range(len(grid[y])):
            for direction in range(4):

                # 해당 방향으로 빛을 쏘는 것이 다른 사이클에 포함 됨 - 중복 사이클 의미
                if grid[y][x][direction + 1] == 1:
                    continue

                # 중복 아님 -> 사이클 찾기
                answer.append(find_cycle(y, x, direction, grid))

    return sorted(answer)