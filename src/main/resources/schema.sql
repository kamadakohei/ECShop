create table if not exists books (
  book_id  int primary key auto_increment,
  book_name  varchar(255),
  description varchar(512),
  price int,
  image varchar(200),
  del_flag int
);

