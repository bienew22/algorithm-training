def solution(k, n, reqs):
    # k : 상담 유형 수
    # n : 멘토의 수 
    # reqs : 상담 요청, [a, b, c], a 기준으로 오름 차순으로 정리 됨.
    #   a - 상담 시작 시간
    #   b - 상담 시간, 종료 시간 = a + b
    #   c - 상담 유형
    answer = 10 ** 10
    
    for counselor in get_counselors([1] * k, n - k, 0):
        DP = {i: [0] * counselor[i] for i in range(k)}
        wait = 0
        
        for req in reqs:
            counsel_type = req[2] - 1
            counsel_state = DP[counsel_type]
            
            best_idx = max(range(counselor[counsel_type]),
                         key=lambda i: req[0] - counsel_state[i])
            
            # 상담 종료 후에 요청
            if req[0] >= counsel_state[best_idx]:
                DP[counsel_type][best_idx] = req[0] + req[1]
            else:
                wait += counsel_state[best_idx] - req[0]
                # print(DP, wait)
                DP[counsel_type][best_idx] += req[1]
                
                
        # print(DP, wait)
        answer = min(answer, wait)
        
    
    return answer

def get_counselors(arr, n, idx):
    if n == 0:
        yield arr
    else:
        for i in range(idx, len(arr)):
            arr[i] += 1
            yield from get_counselors(arr, n - 1, i)
            arr[i] -= 1