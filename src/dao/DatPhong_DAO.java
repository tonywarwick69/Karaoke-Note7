package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.DatPhong;
import entity.KhachHang;
import entity.Phong;

public class DatPhong_DAO {
	//tinhtrang=0=false -> "Phòng trống" tinhtrang=1=true -> "Phòng đang sử dụng"
	public ArrayList<DatPhong> getTatCaDatPhong(){
		ArrayList<DatPhong> dsdatphong = new ArrayList<DatPhong>();	
        try {
        	ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select p.maphong, kh.makh,p.loaiphong,p.giathuephong,dp.tinhtrang,thoigiandatphong,ngaydatphong from phong p  join datphong dp on p.maphong = dp.maphong join khachhang kh on dp.makh = kh.makh where dp.tinhtrang = 1";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int maphong = rs.getInt("maphong");
				int makh= rs.getInt("makh");
				String loaiphong = rs.getString("loaiphong");
				double giathuephong =rs.getDouble("giathuephong");
				boolean tinhtrang= rs.getBoolean("tinhtrang");
				Time thoigiandatphong =rs.getTime("thoigiandatphong");
				Date ngaydatphong = rs.getDate("ngaydatphong");
				
				DatPhong dp = new DatPhong(maphong,makh,loaiphong,giathuephong,tinhtrang,thoigiandatphong,ngaydatphong);
				dsdatphong.add(dp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsdatphong;
	}
	public boolean create(DatPhong dp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "insert into datphong values( ?, ?, ?, ?, ?, ?, ?)";
		int n = 0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, dp.getMaphong());
			stmt.setInt(2, dp.getMakh());
			stmt.setString(3, dp.getLoaiphong());
			stmt.setDouble(4, dp.getGiathuephong());
			stmt.setBoolean(5, dp.isTinhtrang());
			stmt.setTime(6, dp.getThoigiandatphong());
			stmt.setDate(7, dp.getNgaydatphong());
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
		String sql = "DELETE FROM datphong WHERE maphong = '" + ma + "'";
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
	public boolean update(DatPhong dp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update datphong set maphong= ?, makh = ?, loaiphong = ?,giathuephong = ?,tinhtrang= ?,thoigiandatphong= ?,ngaydatphong=? WHERE maphong = ?");			
			stmt.setInt(1, dp.getMaphong());	
			stmt.setInt(2, dp.getMakh());
			stmt.setString(3, dp.getLoaiphong());	
			stmt.setDouble(4, dp.getGiathuephong());
			stmt.setBoolean(5, dp.isTinhtrang());
			stmt.setTime(6, dp.getThoigiandatphong());
			stmt.setDate(7, dp.getNgaydatphong());
			stmt.setInt(8, dp.getMaphong());
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
	public boolean updateThanhToan(DatPhong dp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update datphong set tinhtrang= ? WHERE maphong = ?");			
			stmt.setBoolean(1, dp.isTinhtrang());
			stmt.setInt(2, dp.getMaphong());
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
	public ArrayList<DatPhong> getTimKiemTheoMa(int ma){
		ArrayList<DatPhong> dsdatphong = new ArrayList<DatPhong>();
		Statement st = null;
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
			st= con.createStatement();
			ResultSet rs= st.executeQuery("select * from datphong where maphong= '" + ma +"' ");
			while(rs.next()) {
				int maphong = rs.getInt("maphong");
				int makh= rs.getInt("makh");
				String loaiphong = rs.getString("loaiphong");
				double giathuephong =rs.getDouble("giathuephong");
				boolean tinhtrang= rs.getBoolean("tinhtrang");
				Time thoigiandatphong =rs.getTime("thoigiandatphong");
				Date ngaydatphong = rs.getDate("ngaydatphong");
				DatPhong dp = new DatPhong(maphong,makh,loaiphong,giathuephong,tinhtrang,thoigiandatphong,ngaydatphong);
				dsdatphong.add(dp);
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
		return dsdatphong;
	}
	/*
	public boolean create(DatPhong dp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "insert into datphong values( ?, ?, ?, ?, ?, ?, ?)";
		int n = 0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, dp.getMaphong());
			stmt.setInt(2, dp.getMakh());
			stmt.setString(3, dp.getLoaiphong());
			stmt.setDouble(4, dp.getGiathuephong());
			stmt.setBoolean(5, dp.isTinhtrang());
			stmt.setTime(6, dp.getThoigiandatphong());
			stmt.setDate(7, dp.getNgaydatphong());
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
	*/
	public String demGio(String gioden,String giodi)
	{
		

		Time d1 = null;
		Time d2 = null;

		try {
	

		long diff = d2.getTime() - d1.getTime();
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return diffHours+"";

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"lỗi đếm giờ: "+e.toString());
		return null;
		}
	}
	
	

}
