drop table if exists query_frequency_info;
create table query_frequency_info (ID int auto_increment primary key, userName varchar(20), fieldName varchar(20), count int);