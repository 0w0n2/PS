-- 코드를 입력하세요
# 아픈 동물의 '아이디'와 '이름'을 조회
SELECT ANIMAL_ID, NAME
from ANIMAL_INS
where INTAKE_CONDITION = 'Sick'
order by ANIMAL_ID;
