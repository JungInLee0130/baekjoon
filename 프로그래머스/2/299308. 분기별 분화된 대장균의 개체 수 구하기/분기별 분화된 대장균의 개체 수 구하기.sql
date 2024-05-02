-- 코드를 작성해주세요
WITH QUARTER_TABLE AS (SELECT ID,
(CASE WHEN MONTH(DIFFERENTIATION_DATE) >= 1 AND MONTH(DIFFERENTIATION_DATE) <= 3 THEN '1Q'
WHEN MONTH(DIFFERENTIATION_DATE) >= 4 AND MONTH(DIFFERENTIATION_DATE) <= 6 THEN '2Q'
WHEN MONTH(DIFFERENTIATION_DATE) >= 7 AND MONTH(DIFFERENTIATION_DATE) <= 9 THEN '3Q'
WHEN MONTH(DIFFERENTIATION_DATE) >= 10 AND MONTH(DIFFERENTIATION_DATE) <= 12 THEN '4Q'
END) AS QUARTER
FROM ECOLI_DATA)

SELECT QUARTER, COUNT(*) AS ECOLI_COUNT
FROM QUARTER_TABLE
GROUP BY QUARTER
ORDER BY QUARTER;
