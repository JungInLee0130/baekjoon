-- 코드를 입력하세요
WITH p1 AS (SELECT 
            c.car_id
            ,c.car_type
            ,daily_fee
            ,ROUND(daily_fee * (1-discount_rate * 0.01) * 30, 0) AS FEE
            
            FROM CAR_RENTAL_COMPANY_CAR c
            INNER JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY ch
            INNER JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN cd
            ON c.CAR_ID = ch.CAR_ID and c.CAR_TYPE = cd.CAR_TYPE
            
            WHERE c.CAR_ID NOT IN 
            (
                SELECT CAR_ID
                FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
                WHERE START_DATE <= '2022-11-30' and END_DATE >= '2022-11-01'
            )
            and DURATION_TYPE = '30일 이상'
           )

SELECT CAR_ID, CAR_TYPE, FEE
FROM p1
GROUP BY CAR_ID
HAVING (CAR_TYPE = '세단' or CAR_TYPE = 'SUV')
AND FEE >= 500000 and FEE < 2000000
ORDER BY FEE DESC, p1.CAR_TYPE, CAR_ID DESC



#WHERE CAR_TYPE='세단' or CAR_TYPE='SUV'
#and END_DATE < '2022-11-01' and START_DATE > '2022-11-30'
# 30일간의 대여 금액이 50만원 이상 200만원 미만인
# daily_fee,p1.duration_type, period
# 대여기록이 없거나
# 자동차 종류가 '세단' 또는 'SUV' 인 자동차
# 2022년 11월 1일부터 2022년 11월 30일까지 대여 가능