-- 코드를 입력하세요
# 이게 어딜봐서 JOIN 뭐 LEFT JOIN 이라도 시키라는건가
SELECT NAME, DATETIME
FROM ANIMAL_INS
WHERE ANIMAL_ID NOT IN
(
    SELECT ANIMAL_ID
    FROM ANIMAL_OUTS
)
ORDER BY DATETIME
LIMIT 3;

# ANIMAL_OUTS에 없는 동물중, 가장 오래 보호소에 있던 동물 3마리의
# 이름과 보호 시작일을 조회
# 보호 시작일 순으로 조회