SELECT CONCAT("/home/grep/src/", F.BOARD_ID, "/", FILE_ID, FILE_NAME, FILE_EXT) AS FILE_PATH

FROM USED_GOODS_FILE F
JOIN USED_GOODS_BOARD B
ON F.BOARD_ID = B.BOARD_ID

WHERE B.VIEWS IN (
                        SELECT MAX(VIEWS) AS VIEWS
                        FROM USED_GOODS_BOARD
                    )
                    
ORDER BY FILE_ID DESC;

