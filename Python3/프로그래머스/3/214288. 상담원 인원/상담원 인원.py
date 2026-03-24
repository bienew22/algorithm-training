from heapq import heappush, heappop

def solution(k, n, reqs):

    max_n = n - k
    wait_k = []
    map_k_reqs = {}
    wait_init = 0
    wait_diff = []

    for k_ in range(k):
        map_k_reqs[k_] = []

    for r in reqs:
        map_k_reqs[r[2] - 1].append(r[:2])

    for k_ in range(k):
        wait_k_ = 0

        for n_ in range(max_n + 1):
            heap_n = []
            wait_time = 0

            for _ in range(n_ + 1):
                heappush(heap_n, 0)

            for req in map_k_reqs[k_]:
                min_time = heappop(heap_n)

                if min_time <= req[0]:
                    heappush(heap_n, req[0] + req[1])
                else:
                    wait_time += min_time - req[0]
                    heappush(heap_n, min_time + req[1])

            if n_ == 0:
                wait_init += wait_time
            else:
                heappush(wait_diff, wait_time - wait_k_)

            wait_k_ = wait_time

    wait_min = wait_init

    for _ in range(max_n):
        wait_min += heappop(wait_diff)

    return wait_min