-- 코드를 입력하세요
SELECT R.REST_ID, R.REST_NAME, R.FOOD_TYPE, R.FAVORITES, R.ADDRESS, ROUND(AVG(V.REVIEW_SCORE),2) AS SCORE
FROM REST_INFO R, REST_REVIEW V
WHERE R.REST_ID = V.REST_ID and R.ADDRESS LIKE '서울%'
GROUP BY R.REST_ID
ORDER BY SCORE DESC, R.FAVORITES DESC
