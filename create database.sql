/*

drop database QLCHLaptop;

create database QLCHLaptop;
use QLCHLaptop;

*/

create table NhaCungCap (
	maNCC char(7) not null,
    tenNCC nvarchar(100) not null,
    diaChi nvarchar(200),
    soDienThoai varchar(20),
    email varchar(200),
    trangThai bool,
    constraint PK_NCC primary key (maNCC)
);

create table NhanVien (
	maNV char(6) not null,
    tenNV nvarchar(50) not null,
    password nvarchar(200) not null,
    chucVu nvarchar(20),
    ngayVaoLam date,
    ngaySinh date,
    quanLy char(6),
    phanQuyen binary(1),
    trangThai bool,
    constraint PK_NV primary key (maNV),
    constraint PK_QL_NV foreign key (quanLy) references NhanVien(maNV)
);

create table PhieuNhap (
	maPN char(8) not null,
    ngayNhap date not null,
    maNCC char(7) not null,
    tongTien decimal(15),
    maNV char(6) not null,
    constraint PK_PN primary key (maPN),
    constraint FK_PN_NCC foreign key (maNCC) references NhaCungCap(maNCC),
    constraint FK_PN_NV foreign key (maNV) references NhanVien(maNV)
);

create table SanPham(
	maSP varchar(15) not null,
    tenSP nvarchar(200) not null,
    loaiSP nvarchar(20) not null,
    anhSP nvarchar(200),
    donGia decimal(15),
    soLuong int,
    trangThai char(1),
    constraint PK_SP primary key (maSP)
);

create table ChiTietPhieuNhap(
	maPN char(8) not null,
    maSP varchar(15) not null,
    donGia decimal(15, 0),
    soLuong smallint,
    constraint PK_CTPN primary key (maPN, maSP),
    constraint FK_CTPN_PN foreign key (maPN) references PhieuNhap(maPN),
    constraint FK_CTPN_SP foreign key (maSP) references SanPham(maSP)
);

create table Laptop(
	maSP varchar(15) not null,
    thuongHieu varchar(20),
    namRaMat smallint,
    trongLuong float,
    mauSac nvarchar(50),
    chatLieu nvarchar(50),
    CPU varchar(150),
    RAM tinyint,
    ncRAM nvarchar(20),
    ktManHinh float,
    doPhanGiai varchar(10),
    tanSo smallint,
    congNghe varchar(10),
    tamNen varchar(10),
    cardDoHoa varchar(100),
    oCung varchar(50),
    baoMat nvarchar(50),
    bluetooth float,
    dungLuongPin nvarchar(10),
    heDieuHanh varchar(50),
    constraint PK_Laptop primary key (maSP),
    constraint FK_Laptop_SP foreign key (maSP) references SanPham(maSP)
);

create table KhachHang(
    maKH int auto_increment not null,
    tenKH nvarchar(50) not null,
    ngaySinh date,
    gioiTinh nvarchar(3) not null,
    soDienThoai varchar(11),
    cmnd varchar(12) unique,
    muaHo int,
    constraint PK_KH primary key (maKH),
    constraint PK_MH_KH foreign key (muaHo) references KhachHang(maKH)
);

create table DonHang(
	maDH char(10) not null,
    ngayDH date,
    khMua int not null,
    khNhan int not null,
    maNV char(6) not null,
    thangTraGop tinyint,
    gopHangThang decimal(15,0),
    constraint PK_DH primary key (maDH),
    constraint FK_DH_KHMua foreign key (khMua) references KhachHang(maKH),
    constraint FK_DH_KHNhan foreign key (khMua) references KhachHang(maKH),
    constraint FK_DH_NV foreign key (maNV) references NhanVien(maNV)
);

create table ThanhToan(
	maDH char(10) not null,
    ngayTT date,
    maNV char(6) not null,
    soTien decimal(15),
    soThang tinyint,
    constraint PK_TT primary key (maDH, ngayTT),
    constraint FK_TT_DH foreign key (maDH) references DonHang(maDH),
    constraint FK_TT_NV foreign key (maNV) references NhanVien(maNV)
);

create table ChiTietDonHang(
	maDH char(10) not null,
    maSP varchar(15) not null,
    donGia decimal(15, 0),
    soLuong smallint,
    constraint PK_CTDH primary key (maDH, maSP),
    constraint FK_CTDH_DH foreign key (maDH) references DonHang(maDH),
    constraint FK_CTDH_SP foreign key (maSP) references SanPham(maSP)
);

insert into NhanVien values
("AD1", "Admin", "aA@!1", "Admin", null, null, null, 0x16, 1),
("NV0003", "Hồ Hạ Hương", "123456", "Quản lý", "2020-01-29", "1994-06-24", "NV0003", 0x8, 1),
("NV0005", "Lý Thái Sơn", "123456", "Quản lý", "2020-01-29", "1995-10-20", "NV0005", 0x8, 1);

insert into NhanVien values
("NV0001", "Nguyễn Văn Huy", "123456", "Nhân viên", "2023-10-10", "1994-12-20", "NV0003", 0x7, 1),
("NV0002", "Trương Thị Kiều", "123456", "Nhân viên", "2022-07-23", "1997-02-07", "NV0005", 0x7, 1),
("NV0004", "Phùng Thị Hương Liên", "123456", "Nhân viên", "2023-10-10", "2000-12-18", "NV0005", 0x7, 1),
("NV0006", "Dương Thanh Thiên", "123456", "Nhân viên", "2021-12-20", "1997-04-17", "NV0003", 0x7, 1),
("NV0007", "Nguyễn Phạm Thanh Thảo", "123456", "Nhân viên", "2022-03-04", "1992-06-24", "NV0005", 0x7, 1),
("NV0008", "Lý Quốc Sơn", "123456", "Nhân viên", "2022-03-04", "1998-03-06", "NV0005", 0x7, 1),
("NV0009", "Đào Thanh Hưng", "123456", "Nhân viên", "2022-03-04", "1996-05-11", "NV0003", 0x7, 1),
("NV0010", "Trần Bình An", "123456", "Nhân viên", "2022-07-23", "2001-05-11", "NV0003", 0x7, 1);

insert into NhaCungCap values
("NCC0001", "Công ty tin học ABC", "2 Nguyễn Du Q1", "0123456789", "tinhocabc@gmail.com", 1),
("NCC0002", "Công ty phần mềm Trung Nguyên", "16 Đinh Bộ Lĩnh Q. Bình Thạnh", "0903012034", "pmtrungnguyen@gmail.com", 1);

insert into SanPham values
("HP0001", "HP 245 G10 R5 7520U", "Laptop", "..\\img\\HP0001_1.jpg", 10990000, 100, 1),
("HP0002", "HP 14s-em0086AU R5 7520U", "Laptop", "..\\img\\HP0002_1.jpg", 13490000, 100, 1),
("HP0003", "HP 15s-fq5163TU i5 1235U", "Laptop", "..\\img\\HP0003_1.jpg", 13490000, 100, 1),
("HP0004", "HP Pavilion 15 eg2084TU i5 1240P", "Laptop", "..\\img\\HP0004_1.jpg", 14690000, 100, 1),
("HP0005", "HP 15s-fq5146TU i7 1255U", "Laptop", "..\\img\\HP0005_1.jpg", 16490000, 100, 1),
("HP0006", "HP Gaming Victus 16-e1107AX R5", "Laptop", "..\\img\\HP0006_1.jpg", 17890000, 100, 1),
("HP0007", "HP Pavilion 14-dv2072TU i7 1255U", "Laptop", "..\\img\\HP0007_1.jpg", 19490000, 100, 1),
("HP0008", "HP Gaming Victus 16-e1104AX R7 6800H", "Laptop", "..\\img\\HP0008_1.jpg", 20990000, 100, 1),
("HP0009", "HP Gaming Victus 16-s0078AX R5-7640HS", "Laptop", "..\\img\\HP0009_1.jpg", 24490000, 100, 1),
("HP0010", "HP Envy x360 13-bf0092TU i7 1250U", "Laptop", "..\\img\\HP0010_1.jpg", 24990000, 100, 1),
("SP000001", "Key-online Microsoft® 365 Personal (01 năm; 01 tài khoản; 05 thiết bị)", "Phần mềm", "..\\img\\SP000001.jpg", 990000, 0, 3),
("SP000002", "Key-online Microsoft® 365 Family (01 năm; 06 tài khoản; 30 thiết bị)", "Phần mềm", "..\\img\\SP000002.jpg", 1490000, 0, 3),
("SP000003", "Key-online Windows 11 Pro 64bit_FPP (Vĩnh viễn; 1PC)", "Phần mềm", "..\\img\\SP000003.jpg", 4990000, 0, 3),
("SP000004", "Key-online Windows 11 Home 64bit_FPP (Vĩnh viễn; 1PC)", "Phần mềm", "..\\img\\SP000004.jpg", 3290000, 0, 3),
("SP000005", "Key-online Windows 10 Pro 64bit_FPP (Vĩnh viễn; 1PC)", "Phần mềm", "..\\img\\SP000005.jpg", 4590000, 0, 0),
("SP000006", "Key-online Windows 10 Home 64bit_FPP (Vĩnh viễn; 1PC)", "Phần mềm", "..\\img\\SP000006.jpg", 2890000, 0, 3);

insert into Laptop values
('HP0001', 'HP', 2023, 1.36, 'Bạc', 'Nhựa', 'AMD Ryzen 5 7520U 4.30 GHz', 8, 'Không', 14.0, '1920x1080', 60, 'LCD', 'IPS', 'AMD Radeon Graphics', '256GB SSD M.2 NVMe', 'Mật khẩu', 5.3, '3 Cell', 'Windows 11'),
('HP0002', 'HP', 2023, 1.4, 'Bạc', 'Nhựa', 'AMD Ryzen 5 7520U 4.30 GHz', 16, 'Không', 14.0, '1920x1080', 60, 'FHD', 'SVA', 'Onboard', '512GB SSD M.2 PCIe', 'Mật khẩu', 5.3, '3 Cell', 'Windows 11'),
('HP0003', 'HP', 2022, 1.69, 'Bạc', 'Nhựa', 'Intel Core i5-1235U 4.40 GHz', 8, '32', 15.6, '1920x1080', 60, 'LCD', 'WVA', 'Onboard', '512GB SSD M.2 PCIe', 'Mật khẩu', 5.3, '3 Cell', 'Windows 11'),
('HP0004', 'HP', 2022, 1.71, 'Vàng', 'Nhôm', 'Intel Core i5-1240P 4.40 GHz', 8, '16', 15.6, '1920x1080', 60, 'FHD', 'IPS', 'Intel Iris Xe', '256GB SSD M.2 PCIe', 'Mật khẩu', 5.2, '3 Cell', 'Windows 11'),
('HP0005', 'HP', 2022, 1.69, 'Xanh', 'Nhựa', 'Intel Core i7-1255U 4.40 GHz', 8, '32', 15.6, '1920x1080', 60, 'LCD', 'WVA', 'Onboard', '512GB SSD M.2 PCIe', 'Mật khẩu', 5.3, '41 Wh', 'Windows 11'),
('HP0006', 'HP', 2022, 2.4 , 'Đen', 'Nhựa', 'AMD Ryzen 5 6600H 4.50 GHz', 8, '32', 16.1, '1920x1080', 144, 'IPS', 'IPS', 'NVIDIA GeForce RTX 3050 4GB', '512GB SSD M.2 NVMe', 'Mật khẩu', 5.3, '70 Wh', 'Windows 11'),
('HP0007', 'HP', 2022, 1.41, 'Vàng', 'Kim loại', 'Intel Core i7-1255U 4.40 GHz', 8, '32', 14.0, '1920x1080', 60, 'Anti-Glare', 'IPS', 'Onboard', '512GB SSD M.2 NVMe', 'Mật khẩu', 5.3, '43 Wh', 'Windows 11'),
('HP0008', 'HP', 2022, 2.4, 'Đen', 'Nhựa', 'AMD Ryzen 7 6800H 4.70 GHz', 8, '32', 16.1, '1920x1080', 144, 'FHD', 'IPS', 'NVIDIA GeForce RTX 3050 4GB', '512GB SSD M.2 PCIe', 'Mật khẩu', 5.2, '4 Cell', 'Windows 11'),
('HP0009', 'HP', 2023, 2.31, 'Đen', 'Nhựa', 'AMD Ryzen 5 7640HS 5.00 GHz', 16, '32', 16.1, '1920x1080', 144, 'Anti-Glare', 'IPS', 'NVIDIA GeForce RTX 3060 6GB', '512GB SSD M.2 NVMe', 'Mật khẩu', 5.3, '4 Cell', 'Windows 11'),
('HP0010', 'HP', 2023, 1.34, 'Xanh dương', 'Kim loại', 'Intel Core i7-1250U 4.70 GHz', 8, 'Không', 13.3, '2880x1800', 60, 'OLED', 'AMOLED', 'Onboard', '512GB SSD M.2 NVMe', 'Mật khẩu', 5.3, '66 Wh', 'Windows 11');

insert into KhachHang (tenKH, ngaySinh, gioiTinh, soDienThoai, cmnd, muaHo) values
('Lý Anh Tài', '2003-02-13', 'Nam', '0123456789', '079203000001', null),
('Trần Nhật Linh', '2003-05-23', 'Nữ', '0123456789', '079303000002', null),
('Dương Ái Như', '2004-02-24', 'Nữ', '0123456789', '079304000003', null);

insert into KhachHang (tenKH, ngaySinh, gioiTinh, soDienThoai, cmnd, muaHo) values
('Lê Thanh Phong', '2001-10-31', 'Nam', '0123456789', null, 3),
('Cao Minh', '1974-11-04', 'Nam', '0123456789', '079074000005', null),
('Trương Bảo Nhi', '1980-07-19', 'Nữ', '0123456789', '079303000006', null);

insert into KhachHang (tenKH, ngaySinh, gioiTinh, soDienThoai, cmnd, muaHo) values
('Trương Ái Ninh', '2002-12-04', 'Nữ', '0123456789', null, 6);

insert into DonHang values
('DH000001', '2023-09-28', 1, 1, 'NV0004', 12, 1150000),
('DH000002', '2023-12-16', 2, 2, 'NV0010', 9, 1868000),
('DH000003', '2024-01-20', 5, 5, 'NV0001', 18, 1040000),
('DH000004', '2024-02-03', 3, 4, 'NV0002', 0, 0);

insert into ChiTietDonHang values
('DH000001', 'HP0006', 17890000, 1),
('DH000001', 'SP000001', 990000, 1),
('DH000002', 'HP0003', 13490000, 2),
('DH000003', 'SP000003', 4990000, 5),
('DH000004', 'HP0004', 14690000, 1);

insert into ThanhToan values
('DH000001', '2023-09-28', 'NV0004', 5664000, 1),
('DH000001', '2023-10-28', 'NV0005', 1150000, 1),
('DH000001', '2023-11-28', 'NV0001', 1150000, 1),
('DH000001', '2023-12-28', 'NV0002', 1150000, 1),
('DH000001', '2024-01-28', 'NV0009', 1150000, 1),
('DH000002', '2023-12-16', 'NV0010', 10792000, 1),
('DH000002', '2024-01-16', 'NV0010', 1868000, 1),
('DH000003', '2024-01-20', 'NV0001', 7485000, 1),
('DH000004', '2024-02-03', 'NV0002', 14690000, 1);