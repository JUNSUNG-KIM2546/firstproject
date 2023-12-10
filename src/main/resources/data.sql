INSERT INTO article(title, content) VALUES ('토요일 점심은 쌀국수', '1');
INSERT INTO article(title, content) VALUES ('일요일 점심은 구름계란덮밥', '2');
INSERT INTO article(title, content) VALUES ('월요일 점심은 학식', '3');

INSERT INTO article(title, content) VALUES ('당신의 인생 영화는?', '댓글 고');
INSERT INTO article(title, content) VALUES ('당신의 소울 푸드는?', '댓글 고고');
INSERT INTO article(title, content) VALUES ('당신의 취미는?', '댓글 고고고');

INSERT INTO comment(article_id, nickname, body) VALUES ('4', 'Park', '오직 그대만');
INSERT INTO comment(article_id, nickname, body) VALUES ('4', 'Kim', '너의 이름은');
INSERT INTO comment(article_id, nickname, body) VALUES ('4', 'Choi', '뷰티 인사이드');

INSERT INTO comment(article_id, nickname, body) VALUES ('5', 'Park', '치킨');
INSERT INTO comment(article_id, nickname, body) VALUES ('5', 'Kim', '생선구이');
INSERT INTO comment(article_id, nickname, body) VALUES ('5', 'Choi', '빵');

INSERT INTO comment(article_id, nickname, body) VALUES ('6', 'Park', '탁구');
INSERT INTO comment(article_id, nickname, body) VALUES ('6', 'Kim', '노래방');
INSERT INTO comment(article_id, nickname, body) VALUES ('6', 'Choi', '볼링');

INSERT INTO mem(email, pass) VALUES ('user1', '1234');
INSERT INTO mem(email, pass) VALUES ('user2', '1234');
INSERT INTO mem(email, pass) VALUES ('user3', '1234');

INSERT INTO coffee(name, price) VALUES ('애플유자차', '3500');
INSERT INTO coffee(name, price) VALUES ('아메리카노', '3000');
INSERT INTO coffee(name, price) VALUES ('쑥라떼', '3000');
