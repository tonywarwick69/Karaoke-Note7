package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.TaiKhoan;

public class TaiKhoan_DAO {
	public ArrayList<TaiKhoan> getTatCaTK(){
		ArrayList<TaiKhoan> dsTK=new ArrayList<TaiKhoan>();
		try {
			ConnectDB.getInstance();
			Connection con= ConnectDB.getConnection();
			String sql="Select * from taikhoan";
			Statement statement= con.createStatement();
			ResultSet rs= statement.executeQuery(sql);
			while(rs.next()) {
				String tentk= rs.getString("tentk");
				String matkhau=rs.getString("matkhau");
				boolean chucvu = rs.getBoolean("vaitro");
				TaiKhoan tk=new TaiKhoan(tentk, matkhau,chucvu);
				dsTK.add(tk);
			} 
			}catch (SQLException e) {
				e.printStackTrace();
			
		}
		return dsTK;
	}
	public boolean create(TaiKhoan tk) {
		ConnectDB.getInstance();
		Connection con= ConnectDB.getConnection();
		PreparedStatement stmt=null;
		String sql= "insert into taikhoan values(?, ?,?)";
		int n=0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tk.getTenTK());
			stmt.setString(2, tk.getMatkhau());
			stmt.setBoolean(3, tk.isVaitro());
			n= stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return n>0;
	}
	public ArrayList<TaiKhoan> getTimVaitro(String tentk){
		ArrayList<TaiKhoan> dstk = new ArrayList<TaiKhoan>();
		Statement st = null;
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
			st= con.createStatement();
			ResultSet rsTimKiem= st.executeQuery("select * from taikhoan where tentk= '" + tentk +"' ");
			while(rsTimKiem.next()) {
				
				String taikhoan =rsTimKiem.getString("tentk");
				String matkhau =rsTimKiem.getString("matkhau");
				boolean vaitro = rsTimKiem.getBoolean("vaitro");
		
				TaiKhoan tk = new TaiKhoan(taikhoan,matkhau,vaitro);
				dstk.add(tk);
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
		return dstk;
	}

	
	

}
