package snippet;

public class Snippet {
	create table admin(
		admin_id int primary key auto_increment
		, id varchar(20)
		, pwd varchar(64)
		, name varchar(20)
		, email varchar(30)
	);
	
	-- 상품 테이블
	
	create table product(
		product_id int primary key auto_increment
		, product_name varchar(25)
		, brand varchar(15)
		, price int default 0
		, discount int default 0
		, introduce varchar(250)
		, detail text 
		, subcategory_id int 
		, constraint fk_subcategory_product foreign key(subcategory_id) references subcategory(subcategory_id)
	);
	
	
	-- size 테이블 
	
	create table size(
		size_id int primary key auto_increment
		, size_name varchar(15)
	);
	insert into size(size_name) values('S');
	insert into size(size_name) values('M');
	insert into size(size_name) values('L');
	insert into size(size_name) values('XL');
	
	-- 해당 상품의 사이즈 정보
	create table product_size(
		product_size_id int primary key auto_increment
		, product_id int
		, size_id int
		, constraint fk_product_product_size foreign key(product_id) references product(product_id)
		, constraint fk_size_product_size foreign key(size_id) references size(size_id)
	);
	
	-- color 테이블
	create table color(
		color_id int primary key auto_increment
		, color_name varchar(20)
	);
	insert into color(color_name) values('red');
	insert into color(color_name) values('yellow');
	insert into color(color_name) values('green');
	
	-- 해당 상품의 색상 정보 
	create table product_color(
		product_color_id int primary key auto_increment
		, product_id int
		, color_id int
		, constraint fk_product_product_color foreign key(product_id) references product(product_id)
		, constraint fk_color_product_color foreign key(color_id) references color(color_id)
	);
	
	select * from topcategory;
	select * from subcategory;
	
	select * 
	from topcategory 
	
	insert into topcategory(top_name) values('가방');
	
	-- 각 상위 카테고리별 소속된 하위 카테고리의 수에 대한 통계 
	-- 상의 4, 하의 4 이런식으로 단, 하위카테고리 레코드가 없어도 0건이라고 표시를 해주어야 함 -> left/right outer join
	 select t.top_name, count(s.subcategory_id )
	 from topcategory t
	 left outer join subcategory s 
	 on t.topcategory_id = s.topcategory_id
	 group by t.topcategory_id;
	 
	
	create table test(
		test_id int primary key auto_increment
		, name varchar(20)
	);
	
	insert into test(name) values('scott');
	insert into test(name) values('adams');
	insert into test(name) values('allen');
	
	-- 가장 최신에 들어간 pk 알아내는 방법 
	select max(test_id) from test;  -- 안좋은 방법 (A가 max값이 3인걸 알고 insert하려는 찰나에 B가 먼저 insert)  B가 4, A가 5가 됨 
	
	-- 동시접속
	-- A가 insert한 것만 보존해주는 방법, 그니까 insert하자마자 만들어진 pk를 얻어와서 다른 테이블에 저장해야 하는 경우 ex) Product 생성하고, color_product, size_product에 상품 pk를 저장하는 경우 
	select last_insert_id();   -- 내가 작업한 것만 
	
	select * from product;
	select * from product_color;
	select * from product_size;
	select * from product_img;
	
	
	create table product_img(
		product_img_id int primary key auto_increment
		, filename varchar(20)
		, product_id int
		, constraint fk_product_product_img foreign key(product_id) references product(product_id)
	);
	
	
	
	select *
	from product p, subcategory s, topcategory t
	where s.topcategory_id = t.topcategory_id and p.subcategory_id = s.subcategory_id;
	
	select t.topcategory_id, top_name, s.subcategory_id, sub_name, product_id, product_name, brand, price, discount, introduce, detail
	from topcategory t inner join subcategory s inner join product p
	on t.topcategory_id = s.topcategory_id and s.subcategory_id = p.subcategory_id;
	
	select * from member;
	
	create table member(
		member_id int primary key auto_increment
		, id varchar(20)
		, pwd varchar(64)
		, name varchar(20)
		, email varchar(25)
		, regdate timestamp default now()
	);
	
}

