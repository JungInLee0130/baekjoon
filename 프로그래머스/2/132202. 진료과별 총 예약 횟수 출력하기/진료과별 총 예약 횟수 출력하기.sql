-- 코드를 입력하세요
SELECT MCDP_CD AS '진료과코드', count(DISTINCT(PT_NO)) AS '5월예약건수'
FROM APPOINTMENT
WHERE APNT_YMD >= '2022-05-01' AND APNT_YMD < '2022-06-01' # 2022년 5월에 예약한
#AND (APNT_CNCL_YN = 'N' or APNT_CNCL_YN IS NULL) # 취소 내역이 없는
GROUP BY MCDP_CD # 진료과 코드 별로 묶음
ORDER BY `5월예약건수`, `진료과코드`; # 예약 환자수 기준으로 오름차순, 환자수 같다면 진료과코드별로 오름차순
#ORDER BY '5월예약건수', '진료과코드'
#GROUP BY '진료과코드'
#ORDER BY '5월예약건수', '진료과코드'
# 
# , count(*)