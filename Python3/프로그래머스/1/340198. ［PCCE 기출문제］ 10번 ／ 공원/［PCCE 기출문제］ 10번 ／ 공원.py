def solution(mats, park):
    board = [[0] * len(park[0]) for _ in range(len(park))]
    
    for y in range(len(park)):
        for x in range(len(park[0])):
            if park[y][x] != "-1":
                continue
                
            if y - 1 < 0 or x - 1 <  0:
                board[y][x] = 1
            
            board[y][x] = min(board[y][x - 1], board[y - 1][x], board[y - 1][x - 1]) + 1
    
    max_size = max(max(b) for b in board)
    
    able = [m for m in mats if m <= max_size]
    
    return max(able) if able else -1