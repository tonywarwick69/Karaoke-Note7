package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;

import java.sql.Date;

import entity.Hang;
import entity.HoaDon;
public class HoaDon_DAO {
	public ArrayList<HoaDon> getAllHoaDon(){
		ArrayList<HoaDon> dshd = new ArrayList<HoaDon>();
		Connection con;
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery("select mahd,maphong,hd.manv,nv.tennv,ngaylaphd,thanhtien \r\n"
            		+ "from hoadon hd join nhanvien nv on hd.manv=nv.manv ");
			while(rs.next()) {
				int mahd = rs.getInt("mahd");
				int maphong = rs.getInt("maphong");
				int manv = rs.getInt("manv");
				String tennv = rs.getString("tennv");
				Date ngaylaphd = rs.getDate("ngaylaphd");
				Double thanhtien = rs.getDouble("thanhtien");
				HoaDon hd = new HoaDon(mahd, maphong, manv,tennv, ngaylaphd, thanhtien);
				dshd.add(hd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dshd;
	}
	public boolean create(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "insert into hoadon values( ?, ?, ?, ?)";
		int n = 0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, hd.getMaphong());
			stmt.setInt(2, hd.getManv());
			stmt.setDate(3, hd.getNgaylaphd());
			stmt.setDouble(4, hd.getThanhtien());
		
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
		String sql = "DELETE FROM hoadon WHERE mahd = '" + ma + "'";
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

	public boolean update(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update hoadon set  maphong = ?, manv = ?, ngaylaphd = ?, thanhtien = ? WHERE mahd = ?");
			stmt.setInt(1, hd.getMaphong());
			stmt.setInt(2, hd.getManv());
			stmt.setDate(3, hd.getNgaylaphd());
			stmt.setDouble(4, hd.getThanhtien());
			stmt.setInt(5, hd.getMahd());
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
	/*select mahd,maphong,hd.manv,nv.tennv,ngaylaphd,thanhtien \r\n"
            		+ "from hoadon hd join nhanvien nv on hd.manv=nv.manv*/
	//where mahd= '" + ma +"' "
	public ArrayList<HoaDon> getTimKiemTheoMa(int ma){
		ArrayList<HoaDon> dshd = new ArrayList<HoaDon>();
        Statement st = null;
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
            st= con.createStatement();
            ResultSet rs= st.executeQuery("select mahd,maphong,hd.manv,nv.tennv,ngaylaphd,thanhtien from hoadon hd join nhanvien nv on hd.manv=nv.manv where mahd= '" + ma +"' ");
			while(rs.next()) {
				int mahd = rs.getInt("mahd");
				int maphong = rs.getInt("maphong");
				int manv = rs.getInt("manv");
				String tennv = rs.getString("tennv");
				Date ngaylaphd = rs.getDate("ngaylaphd");
				Double thanhtien = rs.getDouble("thanhtien");
				HoaDon hd = new HoaDon(mahd, maphong, manv,tennv, ngaylaphd, thanhtien);
				dshd.add(hd);
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
		return dshd;
	}

}
