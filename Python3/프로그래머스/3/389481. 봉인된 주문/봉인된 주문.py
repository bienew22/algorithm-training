def solution(n, bans):
    answer = ''
    
    # 주문을 원래 위치 번호로 변환.
    bans_num = sorted([spell_to_num(b) for b in bans])
    
    # ban에 따라서 최종 번호 결졍.
    for ban in bans_num:
        if ban <= n:
            n += 1
    
    return num_to_spell(n)
        
# 위치 번호로 주문 문자를 찾기
def num_to_spell(num):
    res =  ''
    
    while num > 0:
        num -= 1
        
        res = chr(ord('a') + num % 26) + res
        num //= 26
    
    return  res
    

# 주문을 원래 위치 번호로 변환    
def spell_to_num(spell):
    res = 0
    for s in spell:
        res = res * 26 + ord(s) - ord('a') + 1
    
    return  res
    