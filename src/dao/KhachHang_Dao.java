package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.KhachHang;

import java.time.LocalDate;
import connectDB.ConnectDB;
public class KhachHang_Dao {
	public ArrayList<KhachHang> getTatCaKhachHang(){
		ArrayList<KhachHang> dsKH= new ArrayList<KhachHang>();
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery("select * from khachhang");
			while(rs.next()) {
				int ma=rs.getInt("makh");
				String tenkh= rs.getString("tenkh");
				int cmnd=rs.getInt("cmnd");			
				int tuoi=rs.getInt("tuoi");
				boolean gioitinh= rs.getBoolean("gioitinh");
				Date ngaysinh=rs.getDate("ngaysinh");
				int sdt=rs.getInt("sdt");
				KhachHang kh = new KhachHang(ma,tenkh,cmnd,tuoi,gioitinh,ngaysinh,sdt);
				dsKH.add(kh);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		
	}
		return dsKH;
	}
	public ArrayList<KhachHang> getTimSDT(int sdt){
		ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
		Statement st = null;
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
			st= con.createStatement();
			ResultSet rsTimKiem= st.executeQuery("select * from khachhang where sdt= '" + sdt +"' ");
			while(rsTimKiem.next()) {
				int makh = rsTimKiem.getInt("makh");
				String tenkh =rsTimKiem.getString("tenkh");
				int cmnd = rsTimKiem.getInt("cmnd");
				int tuoi=rsTimKiem.getInt("tuoi");
				boolean gioitinh = rsTimKiem.getBoolean("gioitinh");
				Date ngaysinh=rsTimKiem.getDate("ngaysinh");
				int SDT=rsTimKiem.getInt("sdt");
				KhachHang kh = new KhachHang(makh,tenkh,cmnd,tuoi,gioitinh,ngaysinh,SDT);
				dsKH.add(kh);
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
		return dsKH;
	}
	public boolean create(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "insert into khachhang values(?, ?, ?, ?, ?,?)";
		int n = 0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, kh.getTenkh());
			stmt.setInt(2, kh.getCmnd());
			stmt.setInt(3, kh.getTuoi());
			stmt.setBoolean(4, kh.isGioitinh());
			stmt.setDate(5, kh.getNgaysinh());
			stmt.setInt(6, kh.getSdt());
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
	public boolean delete(int ma){
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "DELETE FROM khachhang WHERE makh = '" + ma + "'";
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

	public boolean update(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update khachhang set tenkh = ?,cmnd = ?,tuoi= ?,gioitinh= ?,ngaysinh=? ,sdt=? WHERE makh = ?");
			stmt.setString(1, kh.getTenkh());	
			stmt.setInt(2, kh.getCmnd());	
			stmt.setInt(3, kh.getTuoi());
			stmt.setBoolean(4, kh.isGioitinh());
			stmt.setDate(5, kh.getNgaysinh());
			stmt.setInt(6, kh.getSdt());
			stmt.setInt(7, kh.getMakh());
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
