-- 코드를 작성해주세요
# item_A(parent) -> item_B (child)
# X -> item_A(root)

select i.ITEM_ID, ITEM_NAME
from ITEM_INFO i, ITEM_TREE t
WHERE i.ITEM_ID = t.ITEM_ID
and PARENT_ITEM_ID IS NULL
ORDER BY i.ITEM_ID;
