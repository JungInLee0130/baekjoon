-- 코드를 입력하세요
SELECT CAR_TYPE, COUNT(CAR_ID) CARS
FROM CAR_RENTAL_COMPANY_CAR
WHERE (OPTIONS LIKE '%통풍시트%'
       or OPTIONS LIKE '%열선시트%'
       or OPTIONS LIKE '%가죽시트%')
GROUP BY CAR_TYPE
ORDER BY CAR_TYPE

# '통풍시트', '열선시트', '가죽시트' 중 하나 이상의 옵션이 포함된 자동차
# 자동차 종류 별로 몇 대
# 자동차 수 컬럼명은 CARS
# 자동차 종류를 기준으로 오름차순