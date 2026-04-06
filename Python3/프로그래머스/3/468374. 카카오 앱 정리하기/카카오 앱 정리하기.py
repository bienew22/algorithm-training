from collections import deque

# 1: 오른, 2: 아래, 3: 왼, 4: 위
dirs = [(1, 0), (0, 1), (-1, 0), (0, -1)]

def solution(board, commands):
    H, W = len(board), len(board[0])

    # key = app id
    # app_pos[key][0] = 왼쪽 아래 x 좌표
    # app_pos[key][1] = 왼쪽 아래 y 좌표
    # app_pos[key][2] = app 크기
    app_pos = parse_app(board, H, W)

    answer = [[0] * W for _ in range(H)]

    for cmd in commands:
        to_move = deque()
        #print(f'1={app_pos[1]}, 2={app_pos[2]}, 14={app_pos[14]}')

        move_app(app_pos, cmd, H, W, 1, to_move)

        #print(to_move)

        #print(f'1={app_pos[1]}, 2={app_pos[2]}, 14={app_pos[14]}')

        while to_move:
            now = to_move[0]
            now_app = app_pos[now]

            #print(f'now={now}, now_app={now_app}')


            # 밖으로 이동.
            if cmd[1] == 1:
                now_app[0] = -1 - now_app[2]
            elif cmd[1] == 2:
                now_app[1] = -1 - now_app[2]
            elif cmd[1] == 3:
                now_app[0] = W
            else:
                now_app[1] = H

            move_app(app_pos, [now, cmd[1]], H, W, app_pos[now][2] + 1, to_move)

            to_move.popleft()
            #print(to_move)
            # break

    for key in app_pos:
        app = app_pos[key]

        for y in range(app[1], app[1] + app[2] + 1):
            for x in range(app[0], app[0] + app[2] + 1):
                answer[y][x] = key

    return answer


# 앱을 cnt 만큼 cmd에 따라서 이동 시킴.
def move_app(app_pos, cmd, H, W, cnt, to_move):
    app_id, arrow = cmd
    app = app_pos[app_id]

    #print(f'move id={app_id}, app={app}, arrow={arrow}, cnt={cnt}')

    for i in range(cnt):
        # 1. 한 칸 이동.
        app[0] += dirs[arrow - 1][0]
        app[1] += dirs[arrow - 1][1]

        # 자체가 앱 밖으로 이동 -> 다른 것을 맵 밖으로 이동 시키지 않음.
        # 남은 cnt 만큼 이동은 무의미.
        # 이동 주체이므로 to_move에 중복 들어가지 않음.
        if cnt == 1 and is_overflow(app, H, W):
            to_move.append(app_id)
            break
        else:
            # 다른 것을 이동 시키는 경우.
            for key in app_pos:
                # 이동 주체 또는 이미 오버플로우 되어서 이동 예정인 겻은 패스
                if key == app_id or key in to_move:
                    continue

                if is_over_lapping(app, app_pos[key]):
                    # 겹치는 것들을 한 칸 이동.
                    move_app(app_pos, [key, arrow], H, W, 1, to_move)


# board[][] -> app_pos {app_id: [sx, sy, size]}
def parse_app(board, H, W):
    res = {}

    for y in range(H):
        for x in range(W):
            if board[y][x] == 0 or board[y][x] in res:
                continue

            app_size = 1

            while y + app_size < H and board[y + app_size][x] == board[y][x]:
                app_size += 1

            res[board[y][x]] = [x, y, app_size - 1]
    return res


# 두 앱의 겹침 여부
def is_over_lapping(s1, s2):
    # s1 (x1, y1), (x2, y2)
    # s2 (x3, y3), (x4, y4)
    x1, y1 = s1[0], s1[1]
    x2, y2 = s1[0] + s1[2], s1[1] + s1[2]
    x3, y3 = s2[0], s2[1]
    x4, y4 = s2[0] + s2[2], s2[1] + s2[2]

    # 겹침 조건 : not (x2 < x3 or x1 > x4 or y2 < y3 or y1 > y4)
    return not (x2 < x3 or x1 > x4 or y2 < y3 or y1 > y4)


# 앱을한 칸 이동
def move_one_pixel(app, direction):
    app[0] += direction[0]
    app[1] += direction[1]


# 앱이 밖으로 나갔는지 판단
def is_overflow(app, height, width):
    if app[0] < 0 or app[0] + app[2] >= width:
        return True

    if app[1] < 0 or app[1] + app[2] >= height:
        return True

    return False