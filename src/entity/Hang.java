package entity;

public class Hang {
	private int mahang;
	private String tenhang;
	private String loaihang;
	private double dongia;
	private int soluong;
	private String nhasanxuat;
	private double thanhtien;
	private boolean tinhtrang;
	
	/**
	 * @return the tinhtrang
	 */
	public boolean isTinhtrang() {
		return tinhtrang;
	}
	/**
	 * @param tinhtrang the tinhtrang to set
	 */
	public void setTinhtrang(boolean tinhtrang) {
		this.tinhtrang = tinhtrang;
	}
	/**
	 * @return the thanhtien
	 */
	public double getThanhtien() {
		return thanhtien ;
	}
	/**
	 * @param thanhtien the thanhtien to set
	 */
	public void setThanhtien(double thanhtien) {
		this.thanhtien = dongia*soluong;
	}
	/**
	 * @return the mahang
	 */
	public int getMahang() {
		return mahang;
	}
	/**
	 * @param mahang the mahang to set
	 */
	public void setMahang(int mahang) {
		this.mahang = mahang;
	}
	/**
	 * @return the tenhang
	 */
	public String getTenhang() {
		return tenhang;
	}
	/**
	 * @param tenhang the tenhang to set
	 */
	public void setTenhang(String tenhang) {
		this.tenhang = tenhang;
	}
	/**
	 * @return the loaihang
	 */
	public String getLoaihang() {
		return loaihang;
	}
	/**
	 * @param loaihang the loaihang to set
	 */
	public void setLoaihang(String loaihang) {
		this.loaihang = loaihang;
	}
	/**
	 * @return the dongia
	 */
	public double getDongia() {
		return dongia;
	}
	/**
	 * @param dongia the dongia to set
	 */
	public void setDongia(double dongia) {
		this.dongia = dongia;
	}
	/**
	 * @return the nhasanxuat
	 */
	public String getNhasanxuat() {
		return nhasanxuat;
	}
	/**
	 * @param nhasanxuat the nhasanxuat to set
	 */
	public void setNhasanxuat(String nhasanxuat) {
		this.nhasanxuat = nhasanxuat;
	}
	/**
	 * @return the soluong
	 */
	public int getSoluong() {
		return soluong;
	}
	/**
	 * @param soluong the soluong to set
	 */
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	private int mahddv;
	/**
	 * @param tenhang
	 * @param dongia
	 * @param soluong
	 * @param thanhtien
	 */
	public Hang(int mahddv,String tenhang, double dongia, int soluong, double thanhtien,boolean tinhtrang) {
		super();
		this.mahddv=mahddv;
		this.tenhang = tenhang;
		this.dongia = dongia;
		this.soluong = soluong;
		this.thanhtien = thanhtien;
		this.tinhtrang=tinhtrang;
	}
	/**
	 * @param mahang
	 * @param tenhang
	 * @param dongia
	 * @param soluong
	 * @param mahddv
	 */
	public Hang( int mahddv,int mahang, String tenhang, double dongia, int soluong,double thanhtien) {
		super();
		this.mahang = mahang;
		this.tenhang = tenhang;
		this.dongia = dongia;
		this.soluong = soluong;
		this.mahddv = mahddv;
		this.thanhtien=thanhtien;
	
	}
	/**
	 * @return the mahddv
	 */
	public int getMahddv() {
		return mahddv;
	}
	/**
	 * @param mahddv the mahddv to set
	 */
	public void setMahddv(int mahddv) {
		this.mahddv = mahddv;
	}
	@Override
	public String toString() {
		return "Hang [mahang=" + mahang + ", tenhang=" + tenhang + ", loaihang=" + loaihang + ", dongia=" + dongia
				+ ", nhasanxuat=" + nhasanxuat + ", soluong=" + soluong + "]";
	}
	public Hang(int mahang, String tenhang, String loaihang, double dongia, String nhasanxuat, int soluong) {
		super();
		this.mahang = mahang;
		this.tenhang = tenhang;
		this.loaihang = loaihang;
		this.dongia = dongia;
		this.nhasanxuat = nhasanxuat;
		this.soluong = soluong;
	}
	public Hang() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Hang( String tenhang, String loaihang, double dongia, int soluong, String nhasanxuat) {
		super();
		
		this.tenhang = tenhang;
		this.loaihang = loaihang;
		this.dongia = dongia;
		this.soluong = soluong;
		this.nhasanxuat = nhasanxuat;
	}
	public Hang(int mahang, String tenhang, double dongia, int soluong) {
		super();
		this.mahang = mahang;
		this.tenhang = tenhang;
		this.dongia = dongia;
		this.soluong = soluong;
	}
	public Hang( String tenhang, int soluong,double thanhtien) {
		super();
		this.tenhang = tenhang;
		this.soluong = soluong;
		this.thanhtien=thanhtien;
	}
	public Hang(int mahddv,String tenhang, double dongia, int soluong, double thanhtien) {
		super();
		this.mahddv=mahddv;
		this.tenhang = tenhang;
		this.dongia = dongia;
		this.soluong = soluong;
		this.thanhtien = thanhtien;
		
	}
}