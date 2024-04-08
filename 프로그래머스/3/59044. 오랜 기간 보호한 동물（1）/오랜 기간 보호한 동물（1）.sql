-- 코드를 입력하세요
# 이게 어딜봐서 JOIN 뭐 LEFT JOIN 이라도 시키라는건가
SELECT ai.NAME, ai.DATETIME
FROM ANIMAL_INS ai LEFT JOIN ANIMAL_OUTS ao
ON ai.ANIMAL_ID = ao.ANIMAL_ID
WHERE ao.ANIMAL_ID IS NULL
ORDER BY ai.DATETIME
LIMIT 3;



# ANIMAL_OUTS에 없는 동물중, 가장 오래 보호소에 있던 동물 3마리의
# 이름과 보호 시작일을 조회
# 보호 시작일 순으로 조회