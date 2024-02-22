-- 코드를 입력하세요
SELECT f.FLAVOR
FROM FIRST_HALF f, ICECREAM_INFO i
WHERE f.FLAVOR = i.FLAVOR and i.INGREDIENT_TYPE = 'fruit_based'
and f.TOTAL_ORDER > 3000
ORDER BY f.TOTAL_ORDER DESC
# 총 주문량 3000 초과
# 주성분이 과일
# 주문량이 큰 순서대로 조회