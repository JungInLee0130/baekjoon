-- 코드를 입력하세요
SELECT count(*) USERS
FROM USER_INFO
WHERE AGE >= 20 and AGE <= 29 
and JOINED >= '2021-01-01' and JOINED < '2022-01-01';