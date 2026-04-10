def solution(arr, l, r):
    ans_value, ans_cnt = 0, 1

    arr_size = len(arr)

    # part1 : arr[i]가 brr에서 끝나는 index 구하기
    s_idx_list = [0, ]
    e_idx_list = [arr[0] - 1]

    for idx in range(1, arr_size):
        s_idx_list.append(e_idx_list[-1] + 1)
        e_idx_list.append(e_idx_list[-1] + arr[idx])

    # part2 : l과 r에 해당하는 arr의 인덱스 찾기
    base_s, base_e = -1, -1

    for idx, value in enumerate(e_idx_list):
        if base_s == -1 and l - 1 <= value:
            base_s = idx

        if base_e == -1 and r - 1 <= value:
            base_e = idx

    if base_e == -1:
        base_e = len(e_idx_list) - 1

    # part3 : 현재 구간이 되는 값 구하기.
    s, length, idx_s = l - 1, r - l + 1, base_s

    while length > 0:
        move = e_idx_list[idx_s] - s + 1

        if move > length:
            move = length

        ans_value += arr[idx_s] * move
        idx_s += 1
        s += move
        length -= move

    # part4 : 현재 구간에 오른쪽으로 슬라이딩
    s, e, offset = l - 1, r - 1, 0
    idx_s, idx_e = base_s, base_e

    while e < e_idx_list[-1]:
        # e에서 다음 base_e 까지 현재 남은 개수
        # = 한 번에 이동할 횟수
        move_cnt = e_idx_list[idx_e] - e

        # 이동 횟수가 0인 경우
        if move_cnt == 0:
            idx_e += 1
            move_cnt = 1

            # 끝에 도달한 경우.
            if idx_e == arr_size:
                break

        e += move_cnt

        # 이동 하기.
        while move_cnt > 0:
            # s에서 다음 base_e 까지 현재 남은 개수
            # = 변동 폭이 같은 구간
            now_move = e_idx_list[idx_s]  - s

            if now_move > move_cnt:
                now_move = move_cnt

            diff = arr[idx_e] - arr[idx_s]

            if now_move == 0:
                idx_s += 1
                now_move = 1

            new_offset = offset + diff * now_move

            if offset == 0 and new_offset == 0:
                ans_cnt += now_move
            elif offset * new_offset < 0 and offset % diff == 0:
                ans_cnt += 1
            elif new_offset == 0:
                ans_cnt += 1

            # 값 갱신
            offset = new_offset
            s += now_move
            move_cnt -= now_move


    # part 5 : 현재 구간에 왼쪽으로 슬라이딩
    # 로직은 오른쪽으로 이동의 반대로 수행.
    s, e, offset = l - 1, r - 1, 0
    idx_s, idx_e = base_s, base_e

    while s > 0:
        # 총 이동 횟수
        move_cnt = s - s_idx_list[idx_s]

        # 이동 횟수가 0인 경우
        if move_cnt == 0:
            idx_s -= 1
            move_cnt = 1

            # 끝에 도달한 경우.
            if idx_s < 0:
                break

        s -= move_cnt

        # 이동 하기.
        while move_cnt > 0:
            # s에서 다음 base_e 까지 현재 남은 개수
            # = 변동 폭이 같은 구간
            now_move = e - s_idx_list[idx_e]

            if now_move > move_cnt:
                now_move = move_cnt

            diff = arr[idx_s] - arr[idx_e]

            if now_move == 0:
                idx_e -= 1
                now_move = 1

            new_offset = offset + diff * now_move

            if offset == 0 and new_offset == 0:
                ans_cnt += now_move
            elif offset * new_offset < 0 and offset % diff == 0:
                ans_cnt += 1
            elif new_offset == 0:
                ans_cnt += 1

            # 값 갱신
            offset = new_offset
            e -= now_move
            move_cnt -= now_move

    return [ans_value, ans_cnt]