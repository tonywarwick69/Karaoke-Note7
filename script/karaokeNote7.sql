use master
create database [karaokeNote7]
 GO
--bit=0 "Nữ" || bit=1 "Nam"
use [karaokeNote7]
create table taikhoan
(
	tentk nvarchar(30) not null primary key,
	matkhau nvarchar(30) not null,
	 vaitro bit
)
--vaitro = 0 = false => "nhanvien" vaitro = 1 = true => "nhanvienquanly"
 
	



create table catruc
(
	maca int identity(1,1) not null primary key,
	loaica nvarchar(30),
	thoigianlam time
)
create table nhanvien
(
	manv int identity(1,1)  not null primary key,
	tentk nvarchar(30),
	maca int,
	tennv nvarchar(50) not null,
	cmnd int,
	tuoi int,
	ngaysinh date,
	gioitinh bit,
	diachi nvarchar(30),
	tienluong real,
	sdt int,
	foreign key (maca) references [catruc] (maca) ON UPDATE  NO ACTION  ON DELETE  CASCADE,
	foreign key (tentk) references [taikhoan] (tentk) ON UPDATE  NO ACTION  ON DELETE  CASCADE,

)
--bit=0 "Nữ" || bit=1 "Nam"
create table khachhang
( 
	makh int identity(1,1) not null primary key,
	tenkh nvarchar(50),
	cmnd int,
	tuoi int,
	gioitinh bit,
	ngaysinh date,
	sdt int,
)
--tinhtrang=0 -> " Phòng trống " tinhtrang=1 -> "Phòng đang sử dụng"
create table phong
(
	maphong int identity(1,1) not null primary key,
	loaiphong nvarchar(30),
	giathuephong real,
	tinhtrang bit,
)
--tinhtrang=0=false -> " Phòng trống " tinhtrang=1=true -> "Phòng đang sử dụng"
create table datphong(
	maphong int not null primary key,
	makh int NULL,
	loaiphong nvarchar(30) NULL, 
	giathuephong real NULL,
	tinhtrang bit NULL,
	thoigiandatphong time NULL,
	ngaydatphong date NULL,
	foreign key (maphong) references [phong] (maphong) ON UPDATE  NO ACTION  ON DELETE  CASCADE,
	foreign key (makh) references [khachhang] (makh) ON UPDATE  NO ACTION  ON DELETE  CASCADE,
)

create table hang
(
	mahang int identity(1,1) not null primary key,
	tenhang nvarchar(50),
	loaihang nvarchar(50),
	dongia real,
	soluong int,
	nhasanxuat nvarchar(50)
	)
--//tinhtrang=0=false -> "Chưa thanh toán" tinhtrang=1=true -> "Đã thanh toán"
create table hoadon_dichvu
(
	mahddv int  not null,
	mahang int not null,
	tenhang nvarchar(50),
	dongia real not null,
	soluong int not null,
	tinhtrang bit not null
	primary key(mahddv,mahang),
	foreign key (mahang) references [hang] (mahang) ON UPDATE  NO ACTION  ON DELETE  CASCADE,
)

create table hoadon
(
	mahd int identity(1,1) not null primary key,
	maphong int not null,
	manv int not null,
	ngaylaphd date,
	thanhtien real,
	foreign key (maphong) references [phong] (maphong) ON UPDATE  NO ACTION  ON DELETE  CASCADE,
	foreign key (manv) references [nhanvien] (manv) ON UPDATE  NO ACTION  ON DELETE  CASCADE,
	
)

create table chitiethoadon
(
	mahd int not null primary key,
	maphong int not null,
	makh int not null,
	manv int not null,
	mahddv int ,
	mahang int,
	ngaylaphd datetime,
	thanhtien real,
	thongtinhoadon nvarchar(200),
	foreign key (mahd) references [hoadon] (mahd) ,
	foreign key (manv) references [nhanvien] (manv) ,
	foreign key (makh) references [khachhang] (makh) ,
	foreign key (maphong) references [phong] (maphong) ,
	foreign key (mahddv,mahang) references hoadon_dichvu (mahddv,mahang) 
)

--Thêm taikhoan
insert into taikhoan values ('khang','123',1)
insert into taikhoan values ('long','123',0)
insert into taikhoan values ('huong','123',0)
insert into taikhoan values ('uyen','123',0)
--Thêm phong
insert into phong values('Thường 2',70000,1)
insert into phong values('VIP 3',110000,0)
-- Thêm khachhang
INSERT INTO khachhang values('Long',456,21,1,CAST(N'2000-07-20' AS Date),0964772094)
INSERT INTO khachhang values('Nguyễn Hoàng Minh',7834953,25,1,CAST(N'1996-07-20' AS Date),0933799522)
--Thêm ca
insert into catruc values('Sáng',CAST(N'08:00:00' AS Time))
insert into catruc values('Chiều',CAST(N'08:00:00' AS Time))
--Thêm hang
insert into hang values('Coca cola','Soda',15000,150,'Coca cola')
insert into hang values('Lẩu thái ','Lẩu',130000,100,'Karaoke Note7')
insert into hang values('Bia 333','Bia',16000,200,'Sabeco')
insert into hang values('Cá chép nướng','Cá',140000,50,'Vĩnh Thuận')
insert into hang values('Rượu Vodka','Rượu',75000,200,'Vodka Hà Nội')

--thêm nhân viên
select * from catruc
insert into nhanvien values('khang',2,'Đồng Đức Khang',13584385,22,CAST(N'1999-03-10' AS Date),1,'Tân Bình',8000000,0964772094)
insert into nhanvien values('uyen',3,'Lê Tú Uyên',53694592,24,CAST(N'1997-11-19' AS Date),0,'Gò Vấp',9000000,0933833960)

--thêm datphong
insert into datphong values(2,2,'VIP',65000,1,CAST(N'17:00:00' AS Time),CAST(N'2021-11-10' AS Date))
-- thêm hoadon_dichvu
insert into hoadon_dichvu values(1,1,'Coca cola',15000,16,0)
insert into hoadon_dichvu values(1,2,'lẩu Thái',130000,1,0)
--thêm hoadon
insert into hoadon values(1,2,CAST(N'2021-11-15' AS Date),500000)
--tinhtrang hoadon_dichvu:tinhtrang= 0 = false="Chưa thanh toán" ; tinhtrang=1=true="Đã thanh toán"













