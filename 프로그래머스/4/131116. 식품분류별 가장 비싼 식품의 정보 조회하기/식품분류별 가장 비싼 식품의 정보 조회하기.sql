-- 코드를 입력하세요
SELECT f1.CATEGORY, MAX_PRICE, PRODUCT_NAME
FROM FOOD_PRODUCT f1, (SELECT CATEGORY, MAX(PRICE) AS MAX_PRICE
FROM FOOD_PRODUCT
GROUP BY CATEGORY) f2
WHERE f1.CATEGORY = f2.CATEGORY and PRICE = MAX_PRICE
AND f1.CATEGORY IN ('과자', '국', '김치', '식용유')
ORDER BY MAX_PRICE DESC;


# 식품분류별로 가격이 제일비싼 식품의
# 분류, 가격, 이름을 조회
# 식품 분류가 과자, 국, 김치, 식용유인 경우만 출력
# 식품 가격 기준으로 내림차순