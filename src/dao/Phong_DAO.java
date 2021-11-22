package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Phong;

public class Phong_DAO {
	public ArrayList<Phong> getTatCaPhong(){
		ArrayList<Phong> dsP= new ArrayList<Phong>();
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery("select * from phong");
			while(rs.next()) {
				int ma=rs.getInt("maphong");
				String loai= rs.getString("loaiphong");
				double giathue =rs.getDouble("giathuephong");
				boolean tinhtrang= rs.getBoolean("tinhtrang");
				Phong p = new Phong(ma,loai,giathue,tinhtrang);
				dsP.add(p);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		
	}
		return dsP;
	}
	public ArrayList<Phong> getTimKiemTheoMa(int ma){
		ArrayList<Phong> dsP = new ArrayList<Phong>();
		Statement st = null;
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
			st= con.createStatement();
			ResultSet rsTimKiem= st.executeQuery("select * from phong where maphong= '" + ma +"' ");
			while(rsTimKiem.next()) {
				int maphong = rsTimKiem.getInt("maphong");
				String loaiphong = rsTimKiem.getString("loaiphong");
				double giathuephong = rsTimKiem.getDouble("giathuephong");
				boolean tinhtrang = rsTimKiem.getBoolean("tinhtrang");
				Phong p = new Phong(maphong, loaiphong, giathuephong, tinhtrang);
				dsP.add(p);
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
		return dsP;
	}
	public boolean create(Phong p) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "insert into phong values( ?, ?, ?)";
		int n = 0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, p.getLoaiphong());
			stmt.setDouble(2, p.getGiathuephong());
			stmt.setBoolean(3, p.isTinhtrang());
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
		String sql = "DELETE FROM phong WHERE maphong = '" + ma + "'";
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

	public boolean update(Phong p) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update phong set loaiphong = ?, giathuephong = ?, tinhtrang = ? WHERE maphong = ?");
			stmt.setString(1, p.getLoaiphong());
			stmt.setDouble(2, p.getGiathuephong());
			stmt.setBoolean(3, p.isTinhtrang());
			stmt.setInt(4, p.getMaphong());
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
	public boolean updateThanhToan(Phong p) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update phong set tinhtrang = ? WHERE maphong = ?");
			stmt.setBoolean(1, p.isTinhtrang());
			stmt.setInt(2, p.getMaphong());
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
	public int getMaPhongSQL(){
		ArrayList<Phong> ds = new ArrayList<Phong>();
		
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select maphong from phong";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int ma = rs.getInt("maphong");
				return ma;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	  
	

}
