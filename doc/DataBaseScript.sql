CREATE TABLE VOCABULARY (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    WORD CHAR(100) NOT NULL,
    PRONOUNCE CHAR(100),
    TRANSLATION CHAR(100) NOT NULL,
    EXAMPLE CHAR(300),
    LEARN_TIMES INTEGER DEFAULT 0 NOT NULL,
    DNK_TIMES INTEGER DEFAULT 0 NOT NULL,
    HM_TIMES INTEGER DEFAULT 0 NOT NULL,
    KIM_TIMES INTEGER DEFAULT 0 NOT NULL,
    LEARN_SCORE REAL DEFAULT 0 NOT NULL,
    UNIQUE (WORD)
);



INSERT INTO VOCABULARY (WORD, PRONOUNCE, TRANSLATION) VALUES
('Croatia','kro''eʃjɚ','n. 克罗地亚'),
('Juventus','ju''vɛntus','n. 尤文图斯'),
('Belgium','''bɛldʒəm','n. 比利时');



INSERT INTO VOCABULARY (WORD, PRONOUNCE, TRANSLATION, EXAMPLE) VALUES
('Portugal','''pɔtjuɡəl','n. 葡萄牙', 'Use absolute positioning if your form layout will not change based on user input. 如果表单布局不随用户输入改变，则应使用绝对定位。'),
('Uruguay','''juərəɡwei','n. 乌拉圭', 'Use absolute positioning if your form layout will not change based on user input. 如果表单布局不随用户输入改变，则应使用绝对定位。'),
('Switzerland','''switsələnd','n. 瑞士', 'Use absolute positioning if your form layout will not change based on user input. 如果表单布局不随用户输入改变，则应使用绝对定位。'),
('Denmark','''denma:k','n. 丹麦', 'Use absolute positioning if your form layout will not change based on user input. 如果表单布局不随用户输入改变，则应使用绝对定位。');



