-- 코드를 입력하세요
SELECT APNT_NO, PT_NAME, p.PT_NO, a.MCDP_CD, DR_NAME, APNT_YMD
FROM APPOINTMENT a INNER JOIN DOCTOR d INNER JOIN PATIENT p
ON a.MDDR_ID = d.DR_ID AND a.PT_NO = p.PT_NO
WHERE APNT_CNCL_YN = 'N' AND APNT_YMD LIKE '2022-04-13%' AND a.MCDP_CD = 'CS'
ORDER BY APNT_YMD

# 2022년 4월 13일 취소되지않은 CS 진료내역 조회
# PATIENT: PT_NAME,PT_NO
# DOCTOR: MCDP_CD. DR_NAME, 
# APPOINTMENT : APNT_NO, APNT_YMD