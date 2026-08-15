def solution(m, n, sx, sy, balls):
    answer = []
    
    for bx, by in balls:
        able = []
        # 상: y = n 맞추는 경우.
        # sx == bx and sy < by 경우 불가능.
        if sx != bx or sy >= by:
            able.append(get_dist(sx, sy, bx, 2 * n - by))
        
        # 좌: x = 0 맞추는 경우.
        # sy == by and sx > bx 경우 불가능.
        if sy != by or sx <= bx:
            able.append(get_dist(sx, sy, -bx, by))
        
        # 하: y = 0 맞추는 경우.
        # sx == bx and sy > by 경우 불가능.
        if sx != bx or sy <= by:
            able.append(get_dist(sx, sy, bx, -by))
        
        # 우: x = m 맞추는 경우.
        # sy == by and sx < bx 경우 불가능.
        if sy != by or sx >= bx:
            able.append(get_dist(sx, sy, 2 * m - bx, by))
            
        # 모서리에 맞는 경우.
        # case1: (0, 0)에 맞추는 경우.
        if sx < bx and sy < by and sy * bx == sx * by:
            able.append(get_dist(sx, sy, -bx, -by))
        
        # case2: (m, 0)에 맞추는 경우.
        if sx > bx and sy < by and (m - sx) * by == (m - bx) * sy:
            able.append(get_dist(sx, sy, 2 * m - bx, -by))
        
        # case3: (0, n)에 맞추는 경우.
        if sx < bx and sy > by and sx * (n - by) == bx * (n - sy):
            able.append(get_dist(sx, sy, -bx, 2 * n - by))
        
        # case4: (m, n)에 맞추는 경우.
        if sx > bx and sy > by and (m - sx) * (n - by) == (m - bx) * (n - sy):
            able.append(get_dist(sx, sy, 2 * m - bx, 2 * n - by))
        
        
        answer.append(min(able))
        
    return answer


def get_dist(sx, sy, ex, ey):
    return (sx - ex) ** 2 + (sy - ey) ** 2