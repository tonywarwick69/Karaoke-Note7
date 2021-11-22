package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.DatPhong;
import entity.Hang;
import entity.Phong;

public class GoiDichVu_DAO {
	public ArrayList<Hang> getTatcaDV(){
		ArrayList<Hang> dsHang = new ArrayList<Hang>();		
		Connection con;
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery("select mahang, tenhang, dongia, soluong from hang");
			while(rs.next()) {
				int mahang = rs.getInt("mahang");
				String tenhang = rs.getString("tenhang");
				Double dongia = rs.getDouble("dongia");
				int soluong = rs.getInt("soluong");
				
				Hang h = new Hang(mahang, tenhang,dongia,soluong);
				dsHang.add(h);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsHang;
	}
	//tinhtrang=0=false -> "Chưa thanh toán" tinhtrang=1=true -> "Đã thanh toán"
	public  ArrayList<Hang> getTatCaHoaDonDV(){
		ArrayList<Hang> dsHDDV= new ArrayList<Hang>();
		Hang h = new Hang();
		   try {
	        	ConnectDB.getInstance();
				Connection con = ConnectDB.getConnection();
				String sql = "Select mahddv, tenhang, dongia, soluong from hoadon_dichvu where tinhtrang=0 ";
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				while(rs.next()) {
					int mahddv = rs.getInt("mahddv");
					String tenhang = rs.getString("tenhang");
					double dongia =rs.getDouble("dongia");
					int soluong= rs.getInt("soluong");
					double thanhtien = dongia*soluong;
					
					Hang hoadonDV = new Hang(mahddv,tenhang,dongia,soluong,thanhtien);
					dsHDDV.add(hoadonDV);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return dsHDDV;
		
	}
	//tinhtrang=0=false -> "Chưa thanh toán" tinhtrang=1=true -> "Đã thanh toán"
		public  ArrayList<Hang> getTatCaMaHoaDonDV(){
			ArrayList<Hang> dsHDDV= new ArrayList<Hang>();
			Hang h = new Hang();
			   try {
		        	ConnectDB.getInstance();
					Connection con = ConnectDB.getConnection();
					String sql = "Select  mahddv, tenhang, dongia, soluong from hoadon_dichvu  ";
					Statement statement = con.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					while(rs.next()) {
						int mahddv = rs.getInt("mahddv");
						String tenhang = rs.getString("tenhang");
						double dongia =rs.getDouble("dongia");
						int soluong= rs.getInt("soluong");
						double thanhtien = dongia*soluong;
						Hang hoadonDV = new Hang(mahddv,tenhang,dongia,soluong,thanhtien);
						dsHDDV.add(hoadonDV);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return dsHDDV;
			
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
				Double dongia = rsTimKiem.getDouble("dongia");
				int soluong = rsTimKiem.getInt("soluong");
				Hang h = new Hang(mahang, tenhang, dongia,  soluong);
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
	public boolean createHoadonDV(Hang t) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "insert into hoadon_dichvu values(?, ?, ?, ?, ?,?)";
		int n = 0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, t.getMahddv());
			stmt.setInt(2, t.getMahang());
			stmt.setString(3, t.getTenhang());
			stmt.setDouble(4, t.getDongia());
			stmt.setInt(5, t.getSoluong());
			stmt.setBoolean(6, false);
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
	//update tinhtrang khi thanh toán hóa đơn thành công
	public boolean updateHoaDonDV(Hang t) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update hoadon_dichvu set  tinhtrang = ? WHERE mahddv = ?");
			stmt.setBoolean(1, t.isTinhtrang());
			stmt.setInt(2, t.getMahddv());
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
	public String TongTienDV(int ma) {
		String tongtien="";
		double congtien =0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql ="SELECT * FROM hoadon_dichvu WHERE mahddv = '" + ma + "'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			 while(rs.next()) {
				 congtien+=(Integer.parseInt(rs.getString("soluong"))*Double.parseDouble(rs.getString("dongia")));
			 }
			 tongtien=String.valueOf(congtien);
			 return tongtien;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public String TongTienTatCaDV() {
		String tongtien="";
		double congtien =0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			
			String sql ="SELECT * FROM hoadon_dichvu";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			 while(rs.next()) {
				 congtien+=(Integer.parseInt(rs.getString("soluong"))*Double.parseDouble(rs.getString("dongia")));
			 }
			 tongtien=String.valueOf(congtien);
			 return tongtien;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean deleteHoadonDV(int ma){
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		String sql = "DELETE FROM hoadon_dichvu WHERE mahddv = '" + ma + "'";
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
	public ArrayList<Hang> getTimMaHoaDon(int ma){
		ArrayList<Hang> dsHDDV = new ArrayList<Hang>();
		Statement st = null;
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=karaokeNote7", "sa", "sapassword");
			st= con.createStatement();
			ResultSet rsTimKiem= st.executeQuery("select * from hoadon_dichvu where mahddv= '" + ma +"' ");
			while(rsTimKiem.next()) {
				int mahddv = rsTimKiem.getInt("mahddv");
				String tenhang = rsTimKiem.getString("tenhang");
				double dongia =rsTimKiem.getDouble("dongia");
				int soluong= rsTimKiem.getInt("soluong");
				double thanhtien = dongia*soluong;
				boolean tinhtrang=false;
				
				Hang hoadonDV = new Hang(mahddv,tenhang,dongia,soluong,thanhtien,tinhtrang);
				dsHDDV.add(hoadonDV);
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
		return dsHDDV;
	}
}
