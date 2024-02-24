-- 코드를 입력하세요
SELECT BOOK_ID, DATE_FORMAT(PUBLISHED_DATE, "%Y-%m-%d") AS PUBLISHED_DATE
FROM BOOK
WHERE Year(PUBLISHED_DATE) = '2021' and CATEGORY = '인문'
ORDER BY PUBLISHED_DATE
# 도서 ID, 출판일
# 2021년에 출판된
# 인문 카테고리에 속하는 도서
# 출판일 기준으로 오름차순