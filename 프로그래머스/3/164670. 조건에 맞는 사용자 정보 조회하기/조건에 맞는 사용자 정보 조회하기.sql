-- 코드를 입력하세요
# 중고 거래 게시물 3건이상 등록한
# 사용자의 ID, 닉네임, 전체주소, 전화번호 조회
#SELECT WRITER_ID, STATUS
#FROM USED_GOODS_BOARD;

WITH P1 AS (
SELECT 
USER_ID
, NICKNAME
, CONCAT(CITY, " ", STREET_ADDRESS1, " ", STREET_ADDRESS2) AS TOTAL_ADDRESS
, CONCAT(SUBSTR(TLNO, 1, 3),'-',SUBSTR(TLNO, 4, 4),'-',SUBSTR(TLNO, 8, 4)) AS TLNO
FROM USED_GOODS_USER
)

SELECT USER_ID, NICKNAME, TOTAL_ADDRESS AS '전체주소', TLNO '전화번호'
FROM USED_GOODS_BOARD ub INNER JOIN P1
ON USER_ID = WRITER_ID
GROUP BY USER_ID
HAVING COUNT(*) >= 3
ORDER BY WRITER_ID DESC





#SELECT board_id, writer_id, title, contents, price, created_date, status, views
#from used_goods_board;

#select user_id, nickname, city, street_address1, street_address2, tlno
#from used_goods_user;

# 전체주소: 시, 도로명 주소, 상세주소, 전화번호 : xxx-xxxx-xxxx
# 결과는 회원 ID를 기준으로 내림차순 정렬해주세요.
# concat, substr : 1부터 시작
#select  ugu.user_id, ugu.nickname, concat(ugu.city, ugu.street_address1, ugu.street_address2) 전체주소,
#        concat(substr(ugu.tlno, 1, 3),'-',substr(ugu.tlno, 4, 4),'-',substr(ugu.tlno, 8, 4)) 전화번호
#from    used_goods_user ugu join used_goods_board ugb
#on      ugu.user_id = ugb.writer_id
#where writer_id in (select writer_id, count(*)
#from used_goods_board ugb1
#group by ugb1.writer_id);