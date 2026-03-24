from heapq import heappush, heappop
from collections import defaultdict

def solution(k, n, reqs):
    # k : 상담 유형 수
    # n : 멘토의 수 
    # reqs : 상담 요청, [a, b, c], a 기준으로 오름 차순으로 정리 됨.
    #   a - 상담 시작 시간
    #   b - 상담 시간, 종료 시간 = a + b
    #   c - 상담 유형
    answer = 10 ** 10
    
    # 상담 유형 별로 분리
    map_k_reqs = defaultdict(list)
    
    for req in reqs:
        map_k_reqs[req[2] - 1].append([req[0], req[1]])

    
    # DP[k][i] = k 유형 상담에 i 명의 상담사 있을 때 대기 시간
    DP = {_: [0] * (n - k + 2)  for _ in range(k)}
    
    for now_n in range(1, n - k + 2):
        
        for now_k in map_k_reqs:
            counselors = [0] * now_n
            wait_time = 0
            
            for req in map_k_reqs[now_k]:
                last_time = heappop(counselors)
                
                if req[0] >= last_time:
                    heappush(counselors, req[0] + req[1])
                else:
                    wait_time += last_time - req[0]
                    heappush(counselors, last_time + req[1])
            
            DP[now_k][now_n] = wait_time
                
    
    for counselors in get_counselors([1] * k, n - k, 0):
        
        wait = sum(DP[k_][c_] for k_, c_ in enumerate(counselors))
        
        answer = min(answer, wait)
        
    
    # print(DP)
    
    return answer

def get_counselors(arr, n, idx):
    if n == 0:
        yield arr
    else:
        for i in range(idx, len(arr)):
            arr[i] += 1
            yield from get_counselors(arr, n - 1, i)
            arr[i] -= 1
