create table if not exists books (
  book_id  int primary key auto_increment,
  book_name  varchar(255),
  description varchar(512),
  price int,
  image varchar(200),
  customer_code varchar(20), --現在cumtomer_code=cutomer_nameだが後々修正必要あり
  del_flag int
);

create table if not exists customers (
  customer_code  varchar(20) primary key,
  password varchar(100),
  del_flg int,
  role varchar(50)
);

