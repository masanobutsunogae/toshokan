-- 図書管理システムのテーブル作成SQL
-- LibraryDAO.java の実装（BOOKテーブル、大文字カラム名）に合わせています

DROP TABLE IF EXISTS BOOK;

CREATE TABLE BOOK (
    ID INT PRIMARY KEY,
    TITLE VARCHAR(255) NOT NULL,
    AUTHOR VARCHAR(255) NOT NULL,
    STATUS INT NOT NULL -- 1: 貸出可能 (Available), 0: 貸出中 (Loaned)
);
