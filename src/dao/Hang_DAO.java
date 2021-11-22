package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Hang;

public class Hang_DAO {
	public ArrayList<Hang> getHangs(){
		ArrayList<Hang> dsHang = new ArrayList<Hang>();		
		Connection con;
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery("select * from hang");
			while(rs.next()) {
				int mahang = rs.getInt("mahang");
				String tenhang = rs.getString("tenhang");
				String loaihang = rs.getString("loaihang");
				Double dongia = rs.getDouble("dongia");
				int soluong = rs.getInt("soluong");
				String nhasanxuat = rs.getString("nhasanxuat");
				Hang h = new Hang(mahang, tenhang, loaihang, dongia, nhasanxuat, soluong);
				dsHang.add(h);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsHang;
	}
	
	public ArrayList<Hang> getTimKiemTheoTen(String ten){
		ArrayList<Hang> dsHang = new ArrayList<Hang>();
        Statement st = null;
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
            st= con.createStatement();
            ResultSet rsTimKiem= st.executeQuery("select * from hang where tenhang= '" + ten +"' ");
			while(rsTimKiem.next()) {
				int mahang = rsTimKiem.getInt("mahang");
				String tenhang = rsTimKiem.getString("tenhang");
				String loaihang = rsTimKiem.getString("loaihang");
				Double dongia = rsTimKiem.getDouble("dongia");
				String nhasanxuat = rsTimKiem.getString("nhasanxuat");
				int soluong = rsTimKiem.getInt("soluong");
				Hang h = new Hang(mahang, tenhang, loaihang, dongia, nhasanxuat, soluong);
				dsHang.add(h);
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
		return dsHang;
	}
	
	public boolean create(Hang t) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "insert into hang values(?, ?, ?, ?, ?)";
		int n = 0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, t.getTenhang());
			stmt.setString(2, t.getLoaihang());
			stmt.setDouble(3, t.getDongia());
			stmt.setInt(4, t.getSoluong());
			stmt.setString(5, t.getNhasanxuat());
			
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
		String sql = "DELETE FROM hang WHERE mahang = '" + ma + "'";
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

	public boolean update(Hang t) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update hang set  tenhang = ?, loaihang = ?, dongia = ?, nhasanxuat = ?, soluong = ? WHERE mahang = ?");
			stmt.setString(1, t.getTenhang());
			stmt.setString(2, t.getLoaihang());
			stmt.setDouble(3, t.getDongia());
			stmt.setString(4, t.getNhasanxuat());
			stmt.setInt(5, t.getSoluong());
			stmt.setInt(6, t.getMahang());
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
	public boolean updateThanhtoan(Hang t) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update hang set  soluong = ? WHERE mahang = ?");
			stmt.setInt(1, t.getSoluong());
			stmt.setInt(2, t.getMahang());
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
