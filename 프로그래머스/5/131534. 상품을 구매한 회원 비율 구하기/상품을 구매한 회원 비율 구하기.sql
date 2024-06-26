-- 코드를 입력하세요
SELECT YEAR(o.SALES_DATE) AS YEAR
, MONTH(o.SALES_DATE) AS MONTH
,COUNT(DISTINCT(u.USER_ID)) AS PURCHASED_USERS
,ROUND(COUNT(DISTINCT(u.USER_ID)) / (SELECT COUNT(*)
FROM USER_INFO
WHERE JOINED LIKE '2021%'), 1) AS PURCHASED_RATIO
FROM USER_INFO u INNER JOIN ONLINE_SALE o
ON u.USER_ID = o.USER_ID
WHERE JOINED LIKE '2021%'
GROUP BY YEAR, MONTH
ORDER BY YEAR, MONTH;

# 상품을 구매한 회원수 / 가입한 전체 회원수
#GROUP BY YEAR(SALES_DATE), MONTH(SALES_DATE)
#ORDER BY YEAR(SALES_DATE), MONTH(SALES_DATE);


# JOINED, ONLINE_SALE 테이블에 존재
# 2021년에 가입한 회원
# 상품 구매한 회원수
# 상품을 구매한 회원의 비율
# 년 월 별로 출력
# 비율 : 소수점 둘째자리에서 반올림
# 년 기준으로 오름차순, 월 기준으로 오름차순