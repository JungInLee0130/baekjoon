-- 코드를 입력하세요
SELECT ai.ANIMAL_ID, ai.ANIMAL_TYPE, ai.NAME
FROM ANIMAL_INS ai JOIN ANIMAL_OUTS ao
ON ai.ANIMAL_ID = ao.ANIMAL_ID
WHERE ai.SEX_UPON_INTAKE LIKE 'Intact%'
AND ao.SEX_UPON_OUTCOME NOT LIKE 'Intact%'
ORDER BY ai.ANIMAL_ID


# INS 중성화 X, OUTS 중성화된 동물의 아이디, 생물 종, 이름
# 아이디 순으로 조회
# Intact Male or Intact Female : 중성화 X
# Spayed Female or Neutered Male -> 중성화