package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import connectDB.ConnectDB;
import entity.CaTruc;
import entity.Hang;
import entity.Phong;


public class CaTruc_DAO {
	public ArrayList<CaTruc> getDSCa(){
		ArrayList<CaTruc> dsca= new ArrayList<CaTruc>();
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery("select * from catruc");
			while(rs.next()) {
				int ma=rs.getInt("maca");
				String loai= rs.getString("loaica");
				Time thoigianlam= rs.getTime("thoigianlam");
				CaTruc ca = new CaTruc(ma,loai,thoigianlam);
				dsca.add(ca);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
	}
		return dsca;
		
	}
	public boolean create(CaTruc ca) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "insert into catruc values( ?, ?)";
		int n = 0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ca.getLoaica());
			stmt.setTime(2, ca.getThoigianlam());
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
		String sql = "DELETE FROM CaTruc WHERE maca = '" + ma + "'";
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

	public boolean update(CaTruc ca) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update catruc set  loaica = ?, thoigianlam = ? WHERE maca = ?");
			stmt.setString(1, ca.getLoaica());
			stmt.setTime(2, ca.getThoigianlam());
			stmt.setInt(3, ca.getMaca());
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
