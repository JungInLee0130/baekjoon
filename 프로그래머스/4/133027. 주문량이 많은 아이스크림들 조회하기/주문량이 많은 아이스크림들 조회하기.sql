-- 코드를 입력하세요
WITH P1 AS (
    SELECT j.FLAVOR, (SUM(f.TOTAL_ORDER) + SUM(j.TOTAL_ORDER)) AS TOTAL_ORDER
    FROM FIRST_HALF f RIGHT JOIN JULY j
    ON f.SHIPMENT_ID = j.SHIPMENT_ID
    GROUP BY j.FLAVOR
    ORDER BY TOTAL_ORDER DESC
    LIMIT 3)

SELECT FLAVOR
FROM P1;



# 7월 아이스크림의 총 주문량과 상반기 아이스크림의 총 주문량을 더한값이
# 큰 순서대로 상위 3개의 맛을 조회