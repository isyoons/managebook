drop database bookmanage;
create database bookmanage;
use bookmanage;
grant select,drop,insert,update,create,delete on bookmanage.* to book_user identified by 'rootroot';
create table book(
	book_code char(4)	primary key,
	book_name varchar(50),
	book_author varchar(40),
	book_made	varchar(40),
	book_price	int,
	book_lending_possible	bool,
	book_lending_count 	int
);

create table member(
	member_code char(4) primary key,
	member_name	varchar(40),
	member_tel	varchar(40),
	member_lending	int
);

create table receiptspayments(
	member_code char(4) not null,
	book_code	char(4) not null,
	lending_date	date,
	return_date 	date,
	constraint r_mc_fk foreign key (member_code) references member(member_code) on delete cascade,
	constraint r_bc_fk foreign key (book_code) references book(book_code) on delete cascade
);

-- "반납 관리" 프로그램 내 반납필요 도서목록 view
create view return_view as
select r.book_code, book_name, r.member_code, member_name, lending_date
from book b, member m, receiptspayments r
where book_lending_possible = false
and return_date is null
and r.book_code = b.book_code 
and r.member_code = m.member_code;


-- "반납 관리" 프로그램 내 반납관련 데이터 view
create view return_manage_view as
select r.book_code '도서코드', book_name '도서명', book_author '저자', book_made '출판사', book_price '가격', book_lending_count '총대여횟수', 
r.member_code '회원코드', member_name '성명', member_tel '전화번호', lending_date '대여일'
from book b, member m, receiptspayments r
where book_lending_possible = false
and return_date is null
and r.book_code = b.book_code
and r.member_code = m.member_code;


-- "회원 검색" 프로그램 내 대여정보 데이터 view
create view search_book_view as
select r.book_code '도서코드', b.book_name '도서명', r.lending_date '대여일', r.return_date '반납일',m.member_code '멤버코드'
from book b, receiptspayments r, member m
where  m.member_code = r.member_code
and r.book_code = b.book_code ;

select * from book;

select m.member_code, m.member_name, r.lending_date , r.return_date ,(case when DATEDIFF(r.return_date,r.lending_date ) >=3 then 'Y' else 'N' end ) from book b, receiptspayments r,  
member m where m.member_code = r.member_code and r.book_code = b.book_code  
and b.book_code = 'A001';

select * from bookmanage.receiptspayments;

insert into bookmanage.receiptspayments values('d123','a001','2015-12-14',null);
insert into bookmanage.receiptspayments values('d123','a001','2015-12-13',null);

select m.member_code, m.member_name, r.lending_date , r.return_date ,
(case 
when r.return_date is not null then 'N' 
when DATEDIFF(ifnull(r.return_date,curdate()),r.lending_date )>=3 
then 'Y' else 'N' end ) 
from book b, receiptspayments r, member m 
where m.member_code = r.member_code and r.book_code = b.book_code and b.book_code = 'A001'




select * from book;