-- 코드를 입력하세요

SELECT HISTORY_ID, ROUND(daily_fee * (1 - IFNULL(discount_rate, 0) * 0.01) * DURATION, 0) AS FEE

FROM

(SELECT CAR_TYPE, daily_fee, history_id, 

DATEDIFF(END_DATE, START_DATE) + 1 AS DURATION,

# p2 외래키
CASE
WHEN DATEDIFF(END_DATE, START_DATE) + 1 >= 90 THEN '90일 이상'
WHEN DATEDIFF(END_DATE, START_DATE) + 1 >= 30 THEN '30일 이상'
WHEN DATEDIFF(END_DATE, START_DATE) + 1 >= 7 THEN '7일 이상'
ELSE 'NONE'
END duration_type


FROM CAR_RENTAL_COMPANY_CAR c,

CAR_RENTAL_COMPANY_RENTAL_HISTORY ch

WHERE c.CAR_ID = ch.CAR_ID

and c.CAR_TYPE = '트럭') p1

LEFT JOIN

(SELECT CAR_TYPE, duration_type, discount_rate

FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN) p2

ON p1.duration_type = p2.duration_type

and p1.car_type = p2.car_type

ORDER BY FEE DESC, HISTORY_ID DESC





# CAR_RENTAL_COMPANY_DISCOUNT_PLAN cd
# DAILY_FEE * (1 - DISCOUNT_RATE * 0.01) * DATESUB
# 자동차 종류가 '트럭'인 자동차의 대여 기록에 대해서
# 대여 기록 별로 대여 금액(컬럼명: FEE)을 구하여
# 7일 이하인 부분은 discount_rate가 1로 고정