from heapq import heappush, heappop
from collections import defaultdict


def solution(k, n, reqs):
    
    answer = 0
    
    # 상담 유형 별로 분리
    map_k_reqs = defaultdict(list)
    
    for req in reqs:
        map_k_reqs[req[2] - 1].append([req[0], req[1]])

    # 멘토를 추가할 때 감소되는 대기 시간을 나타냄.
    # diff_heap[i] = [a, b, c, d]
    #   - a : 감소되는 대기 시간량
    #   - b : 상담 요청 타입,
    #   - c : 멘토 인원 수
    #   - d : 대기 시간.
    diff_heap = []
    
    for now_k in map_k_reqs:
        # 멘토 1명인 경우
        wait_time1 = get_wait_time(1, map_k_reqs[now_k])
        
        # 멘토 2명인 경우
        wait_time2 = get_wait_time(2, map_k_reqs[now_k])
        
        answer += wait_time1
        
        diff = [wait_time2 - wait_time1, now_k, 2, wait_time2]
        
        heappush(diff_heap, diff)
    
    for i in range(n - k):
        now = heappop(diff_heap)
        
        answer += now[0]
        
        wait_time = get_wait_time(now[2] + 1, map_k_reqs[now[1]])
        
        next_diff = [wait_time - now[3], now[1], now[2] + 1, wait_time]
        
        heappush(diff_heap, next_diff)
        
    return answer

def get_wait_time(mentor_cnt, reqs):
    mentors = [0] * mentor_cnt
    wait_time = 0
    
    for req in reqs:
        last_time = heappop(mentors)
        
        if req[0] >= last_time:
            heappush(mentors, req[0] + req[1])
        else:
            wait_time += last_time - req[0]
            heappush(mentors, last_time + req[1])
    
    return wait_time