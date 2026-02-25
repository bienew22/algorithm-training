from collections import defaultdict


def solution(points, routes):
    answer = 0
    
    paths = [get_path(route, points) for route in routes]
    # return answer
    max_path = max(len(path) for path in paths)
    
    for i in range(max_path):
        dup = defaultdict(int)
        
        for path in paths:
            if len(path) <= i:
                continue
            
            dup[path[i]] += 1
        
        for key in dup:
            if dup[key] > 1:
                answer += 1
                
    return answer

def get_path(route, points):
    route = [r - 1 for r in route[::-1]]
    
    
    sy, sx = points[route.pop()]
    
    res = [sy * 100 + sx]
    
    while route:
        gy, gx = points[route.pop()]
        
        while gy != sy:
            if gy > sy:
                sy += 1
            else:
                sy -= 1
            
            res.append(sy * 100 + sx)
        
        while gx != sx:
            if gx > sx:
                sx += 1
            else:
                sx -= 1
            
            res.append(sy * 100 + sx)
        
    return res
        
    
    