def solution(bandage, health, attacks):
    time = 0
    attacks = attacks[::-1]
    now_atk = attacks.pop()
    heal, cnt = health, 0
    while attacks or time <= now_atk[0]:
        
        if time == now_atk[0]:
            heal -= now_atk[1]
            cnt = 0
            
            if attacks:
                now_atk = attacks.pop()
        else:
            cnt += 1
            heal += bandage[1]

            if cnt % bandage[0] == 0:
                cnt = 0 
                heal += bandage[2]

            heal = min(heal, health)
        
        if heal <= 0:
            break
        
        time += 1
        
        # print(f'heal={heal}, cnt={cnt}')
    
    return heal if heal > 0 else -1