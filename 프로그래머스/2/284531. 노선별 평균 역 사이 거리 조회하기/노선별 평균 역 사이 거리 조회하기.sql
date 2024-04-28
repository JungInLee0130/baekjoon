-- 코드를 작성해주세요
SELECT ROUTE, CONCAT(ROUND(SUM(D_BETWEEN_DIST), 1),'km') AS TOTAL_DISTANCE,
CONCAT(ROUND(AVG(D_BETWEEN_DIST), 2), 'km') AS AVERAGE_DISTANCE
FROM SUBWAY_DISTANCE
GROUP BY ROUTE
ORDER BY ROUND(SUM(D_BETWEEN_DIST), 1) DESC

# 문자열로 order by 가 안됨.

# 총 누계거리 : 역 사이 거리 총 합
# 평균 역 사이 거리