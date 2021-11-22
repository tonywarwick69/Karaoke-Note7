package entity;

import java.sql.Date;

public class HoaDon {
	private int mahd;
	/**
	 * @param maphong
	 * @param manv
	 * @param ngaylaphd
	 * @param thanhtien
	 */
	public HoaDon(int maphong, int manv, Date ngaylaphd, double thanhtien) {
		super();
		this.maphong = maphong;
		this.manv = manv;
		this.ngaylaphd = ngaylaphd;
		this.thanhtien = thanhtien;
	}
	@Override
	public String toString() {
		return "HoaDon [mahd=" + mahd + ", maphong=" + maphong + ", manv=" + manv + ", tennv=" + tennv + ", ngaylaphd="
				+ ngaylaphd + ", thanhtien=" + thanhtien + "]";
	}
	/**
	 * 
	 */
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param maphong
	 * @param manv
	 * @param tennv
	 * @param ngaylaphd
	 * @param thanhtien
	 */
	public HoaDon(int maphong, int manv, String tennv, Date ngaylaphd, double thanhtien) {
		super();
		this.maphong = maphong;
		this.manv = manv;
		this.tennv = tennv;
		this.ngaylaphd = ngaylaphd;
		this.thanhtien = thanhtien;
	}
	/**
	 * @param mahd
	 * @param maphong
	 * @param manv
	 * @param tennv
	 * @param ngaylaphd
	 * @param thanhtien
	 */
	public HoaDon(int mahd, int maphong, int manv, String tennv, Date ngaylaphd, double thanhtien) {
		super();
		this.mahd = mahd;
		this.maphong = maphong;
		this.manv = manv;
		this.tennv = tennv;
		this.ngaylaphd = ngaylaphd;
		this.thanhtien = thanhtien;
	}
	private int maphong;
	private int manv;
	private String tennv;
	private Date ngaylaphd;
	private double thanhtien;
	/**
	 * @return the mahd
	 */
	public int getMahd() {
		return mahd;
	}
	/**
	 * @param mahd the mahd to set
	 */
	public void setMahd(int mahd) {
		this.mahd = mahd;
	}
	/**
	 * @return the maphong
	 */
	public int getMaphong() {
		return maphong;
	}
	/**
	 * @param maphong the maphong to set
	 */
	public void setMaphong(int maphong) {
		this.maphong = maphong;
	}
	/**
	 * @return the manv
	 */
	public int getManv() {
		return manv;
	}
	/**
	 * @param manv the manv to set
	 */
	public void setManv(int manv) {
		this.manv = manv;
	}
	/**
	 * @return the tennv
	 */
	public String getTennv() {
		return tennv;
	}
	/**
	 * @param tennv the tennv to set
	 */
	public void setTennv(String tennv) {
		this.tennv = tennv;
	}
	/**
	 * @return the ngaylaphd
	 */
	public Date getNgaylaphd() {
		return ngaylaphd;
	}
	/**
	 * @param ngaylaphd the ngaylaphd to set
	 */
	public void setNgaylaphd(Date ngaylaphd) {
		this.ngaylaphd = ngaylaphd;
	}
	/**
	 * @return the thanhtien
	 */
	public double getThanhtien() {
		return thanhtien;
	}
	/**
	 * @param thanhtien the thanhtien to set
	 */
	public void setThanhtien(double thanhtien) {
		this.thanhtien = thanhtien;
	}
	
	
	
}
