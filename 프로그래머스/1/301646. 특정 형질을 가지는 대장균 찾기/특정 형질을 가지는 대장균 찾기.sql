-- 코드를 작성해주세요
SELECT COUNT(*) AS COUNT
FROM ECOLI_DATA
WHERE !(GENOTYPE & 2) && ((GENOTYPE & 1) || (GENOTYPE & 4))

# COUNT(*) AS COUNT
# GENOTYPE != 2 && 1,3
# COUNT(*)
# 이진수로 나타내었을때 오른쪽부터 1,2,3,4....
# 2가없고 1 3이 있어야하니까
# !(GENOTYPE & 2) && (GENOTYPE & 1) && (GENOTYPE & 3)

# 1
# 13 = 1101 1 2 4 8