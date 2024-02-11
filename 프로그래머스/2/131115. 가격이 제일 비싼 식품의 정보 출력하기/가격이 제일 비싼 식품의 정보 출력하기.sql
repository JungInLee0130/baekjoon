-- 코드를 입력하세요
# 가격이 제일 비싼식품, 
SELECT *
from Food_product
where price = (select max(price) from Food_product)