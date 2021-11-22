package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import entity.NhanVien;

import connectDB.ConnectDB;
public class NhanVien_DAO {
	public ArrayList<NhanVien> getTatCaNhanVien(){
		ArrayList<NhanVien> dsNV= new ArrayList<NhanVien>();
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery("select * from nhanvien");
			while(rs.next()) {
				int manv=rs.getInt("manv");
				String tenkh= rs.getString("tentk");
				int maca=rs.getInt("maca");
				String tennv=rs.getString("tennv");
				int cmnd=rs.getInt("cmnd");
				int tuoi=rs.getInt("tuoi");
				Date ngaysinh=rs.getDate("ngaysinh");
				boolean gioitinh= rs.getBoolean("gioitinh");
				String diachi=rs.getString("diachi");
				double tien=rs.getDouble("tienluong");
				int sdt=rs.getInt("sdt");
				NhanVien nv = new NhanVien(manv,tenkh,maca,tennv,cmnd,tuoi,ngaysinh,gioitinh,diachi,tien,sdt);
				dsNV.add(nv);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		
	}
		return dsNV;
	}
	public ArrayList<NhanVien> getTimSDT(int sdt){
		ArrayList<NhanVien> dsNV = new ArrayList<NhanVien>();
		Statement st = null;
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
			st= con.createStatement();
			ResultSet rsTimKiem= st.executeQuery("select * from nhanvien where sdt= '" + sdt +"' ");
			while(rsTimKiem.next()) {
				int manv=rsTimKiem.getInt("manv");
				String tenkh= rsTimKiem.getString("tentk");
				int maca=rsTimKiem.getInt("maca");
				String tennv=rsTimKiem.getString("tennv");
				int cmnd=rsTimKiem.getInt("cmnd");
				int tuoi=rsTimKiem.getInt("tuoi");
				Date ngaysinh=rsTimKiem.getDate("ngaysinh");
				boolean gioitinh= rsTimKiem.getBoolean("gioitinh");
				String diachi=rsTimKiem.getString("diachi");
				double tien=rsTimKiem.getDouble("tienluong");
				int SDT=rsTimKiem.getInt("sdt");
				NhanVien nv = new NhanVien(manv,tenkh,maca,tennv,cmnd,tuoi,ngaysinh,gioitinh,diachi,tien,SDT);
				dsNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dsNV;
	}
	public boolean create(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "insert into nhanvien values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int n = 0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, nv.getTentk());
			stmt.setInt(2, nv.getMaca());
			stmt.setString(3, nv.getTennv());
			stmt.setInt(4, nv.getCmnd());
			stmt.setInt(5, nv.getTuoi());
			stmt.setDate(6, nv.getNgaysinh());
			stmt.setBoolean(7, nv.isGioitinh());
			stmt.setString(8, nv.getDiachi());
			stmt.setDouble(9, nv.getTienluong());
			stmt.setInt(10, nv.getSdt());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	public boolean delete(int manv){
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "DELETE FROM nhanvien WHERE manv = '" + manv + "'";
		int n = 0;
		try {
			stmt = con.prepareStatement(sql);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return n > 0;
	}

	public boolean update(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update nhanvien set tentk = ?,maca = ?,tennv= ?,cmnd= ?,tuoi=? ,ngaysinh=? ,gioitinh=? ,diachi=? ,tienluong=? ,sdt=? WHERE manv = ?");
			stmt.setString(1, nv.getTentk());
			stmt.setInt(2, nv.getMaca());
			stmt.setString(3, nv.getTennv());
			stmt.setInt(4, nv.getCmnd());
			stmt.setInt(5, nv.getTuoi());
			stmt.setDate(6, nv.getNgaysinh());
			stmt.setBoolean(7, nv.isGioitinh());
			stmt.setString(8, nv.getDiachi());
			stmt.setDouble(9, nv.getTienluong());
			stmt.setInt(10, nv.getSdt());
			stmt.setInt(11, nv.getManv());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	
	  
	

}
