-- 코드를 입력하세요
SELECT DISTINCT(c.CAR_ID)
FROM CAR_RENTAL_COMPANY_CAR c INNER JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY ch
ON c.CAR_ID = ch.CAR_ID
WHERE CAR_TYPE = '세단'
AND START_DATE >= '2022-10-01'
ORDER BY c.CAR_ID DESC



# '세단'인 자동차들 중에
# 10월에 대여 시작한 기록이 있는 자동차 ID 리스트
# 자동차 ID 리스트 중복 없음
# 자동차 ID 기준으로 내림차순