create DATABASE bootblog;
use bootblog;

create table category(
	id int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);

create table user (
	id int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) not null,
    email VARCHAR(50) UNIQUE not null,
    password VARCHAR(50) not null,
    phoneNumber VARCHAR(50) not null,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

create table board(
	id int PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(100) not null,
    user_id int not null,
    title VARCHAR(100) NOT NULL,
    description text not null,
    category_id int not null,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

create table reply(
	id int PRIMARY KEY AUTO_INCREMENT,
    user_id int not null,
    board_id int not null,
    userName VARCHAR(100) not null,
    content VARCHAR(200),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (board_id) REFERENCES board(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

insert into category(name)
VALUES
	("운동"),
	("공부"),
    ("게임"),
    ("패션뷰티"),
    ("과학");