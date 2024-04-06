-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME
FROM ANIMAL_OUTS ao
WHERE ANIMAL_ID NOT IN
(
    SELECT ANIMAL_ID
    FROM ANIMAL_INS ai
)
ORDER BY ANIMAL_ID

# 입양 간 기록은 있는데 보호소에 들어온 기록이 없는 동물의 ID, 이름 출력