-- 코드를 입력하세요
SELECT ROUND(AVG(DAILY_FEE)) AS AVERAGE_FEE
FROM CAR_RENTAL_COMPANY_CAR
WHERE CAR_TYPE = 'SUV'

# 자동차 종류 = 'SUV'
# 평균 일일 대여 요금
# 소수 첫번째 자리에서 반올림
# 칼럼명 AVERAGE_FEE