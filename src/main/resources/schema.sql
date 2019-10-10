create table if not exists books (
  book_id    varchar(36) primary key,
  book_name  varchar(255),
  description varchar(512),
  price int,
  image varchar(200),
  del_flag int
);

