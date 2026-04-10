def solution(arr, l, r):
    ans_value, ans_cnt = 0, 1

    arr_size = len(arr)

    # arr[i]가 brr에서 끝나는 index 구하기
    s_idx_list = [0, ]
    e_idx_list = [arr[0] - 1]

    for idx in range(1, arr_size):
        s_idx_list.append(e_idx_list[-1] + 1)
        e_idx_list.append(e_idx_list[-1] + arr[idx])

    # l과 r에 해당하는 arr의 인덱스 찾기
    base_s, base_e = -1, -1

    for idx, value in enumerate(e_idx_list):
        if base_s == -1 and l - 1 <= value:
            base_s = idx

        if base_e == -1 and r - 1 <= value:
            base_e = idx

    if base_e == -1:
        base_e = len(e_idx_list) - 1

    # print(f's_idx_list={s_idx_list}')
    # print(f'e_idx_list={e_idx_list}')
    # print(f'base_s={base_s}, base_e={base_e}')

    # 현재 구간이 되는 값 구하기.
    s, length, idx_s = l - 1, r - l + 1, base_s

    while length > 0:
        move = e_idx_list[idx_s] - s + 1

        if move > length:
            move = length

        ans_value += arr[idx_s] * move
        idx_s += 1
        s += move
        length -= move

    # 현재 구간에 오른쪽으로 슬라이딩
    s, e, offset = l - 1, r - 1, 0
    idx_s, idx_e = base_s, base_e

    while e < e_idx_list[-1]:
        # e에서 다음 base_e 까지 현재 남은 개수
        left_e = e_idx_list[idx_e] - e

        # s에서 다음 base_e 까지 현재 남은 개수
        left_s = e_idx_list[idx_s] - s

        move_cnt = min(left_e, left_s)

        # print(f'move_cnt={move_cnt}')

        # 이동횟수 0인 경우
        # case1 : 끝에 도착한경우 e == e_idx_list[-1]
        # case2 : idx_s, idx_e를 증가 시켜야하는 경우.
        if move_cnt == 0 and left_e == 0:
            # 끝에 도착한 경우
            if idx_e + 1 == arr_size:
                break

            # idx_e 증가 시키야 하는 경우
            idx_e += 1

        # 한 번 이동 시 증감량.
        diff = arr[idx_e] - arr[idx_s]

        if move_cnt == 0 and left_s == 0:
            idx_s += 1

        if move_cnt == 0:
            move_cnt = 1

        new_offset = offset + diff * move_cnt

        # move_cnt 만큼 이동했지만 offset이 0을 유지하는 경우.
        if offset == 0 and new_offset == 0:
            ans_cnt += move_cnt
        # offset -> new_offset으로 이동할 때 0을 거치는 경우.
        # 0을 완전히 도착하는 경우는 offset이 diff의 배수인 경우.
        elif offset * new_offset < 0 and offset % diff == 0:
            ans_cnt += 1
        elif new_offset == 0:
            ans_cnt += 1

        offset = new_offset

        # 좌표 이동
        s += move_cnt
        e += move_cnt

    # 현재 구간에서 왼쪽으로 슬라이딩
    # 로직은 오른쪽으로 슬라이딩 반대로
    s, e, offset = l - 1, r - 1, 0
    idx_s, idx_e = base_s, base_e

    while s > 0:
        # s에서 이전 base_s 까지 현재 남은 개수
        left_e = e - s_idx_list[idx_e]

        # s에서 다음 base_s 까지 현재 남은 개수
        left_s = s - s_idx_list[idx_s]

        move_cnt = min(left_e, left_s)

        # print(f'move_cnt={move_cnt}')

        # 이동횟수 0인 경우
        # case1 : 끝에 도착한경우 e == e_idx_list[-1]
        # case2 : idx_s, idx_e를 증가 시켜야하는 경우.
        if move_cnt == 0 and left_s == 0:
            # 끝에 도착한 경우
            if idx_s - 1 < 0:
                break

            # idx_e 증가 시키야 하는 경우
            idx_s -= 1

        # 한 번 이동 시 증감량.
        diff = arr[idx_s] - arr[idx_e]

        if move_cnt == 0 and left_e == 0:
            idx_e -= 1

        if move_cnt == 0:
            move_cnt = 1

        new_offset = offset + diff * move_cnt

        # move_cnt 만큼 이동했지만 offset이 0을 유지하는 경우.
        if offset == 0 and new_offset == 0:
            ans_cnt += move_cnt
        # offset -> new_offset으로 이동할 때 0을 거치는 경우.
        # 0을 완전히 도착하는 경우는 offset이 diff의 배수인 경우.
        elif offset * new_offset < 0 and offset % diff == 0:
            ans_cnt += 1
        # 0에 정확히 도착한 경우.
        elif new_offset == 0:
            ans_cnt += 1

        offset = new_offset

        # 좌표 이동
        s -= move_cnt
        e -= move_cnt

        # print(f's={s}, e={e}, offset={offset}')

    return [ans_value, ans_cnt]